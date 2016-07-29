package com.kaishengit.dao;

import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.util.Page;
import com.kaishengit.util.SearchParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
//PK extends Serializable表示泛型PK是可序列号的
public class BaseDao<T,PK extends Serializable> {

    @Inject
    private SessionFactory sessionFactory;
    private Class<?> entityClass;

//获取fanxing.class
    public BaseDao(){
        ParameterizedType parameterizedType= (ParameterizedType) this.getClass().getGenericSuperclass();//获取泛型的数组
        entityClass= (Class<?>) parameterizedType.getActualTypeArguments()[0];

    }


    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //增、修改
    public void save(T entity){
        getSession().saveOrUpdate(entity);
    }

    //按对象删除
    public void delete(T entity){
        getSession().delete(entity);
    }

//查询全部
    public List<T> findAll(){
        Criteria criteria=getSession().createCriteria(entityClass);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

//按id查（有的可能为uuid故这里整泛型）
    public T findById(PK id){
        return (T) getSession().get(entityClass,id);//id要求是可序列化的（故要把PK继承Serializable）
    }

    //    按id删除
    public void deleteById(PK id){
        getSession().delete(findById(id));
    }



//获取所有书籍类型
    public List<T> findAllBookType(){
        Criteria criteria=getSession().createCriteria(entityClass);
        return  criteria.list();
    }
//获取所有出版社
    public List<T> findAllPublisher(){
        Criteria criteria=getSession().createCriteria(entityClass);

        return  criteria.list();
    }

//----------------------关于分页的-----------------------------

//获取总数
    public Long count(){
        Criteria criteria=getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();
    }
    public Page<T> findByPageNo(Integer pageNo,Integer pageSize){
        Integer totalSize=count().intValue();
        Page<T> page=new Page<>(pageSize,totalSize,pageNo);

        Criteria criteria=getSession().createCriteria(entityClass);

        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getSize());

        List<T> result=criteria.list();

        page.setItems(result);
        return page;
    }


    public Long count(Criteria criteria) {
        ResultTransformer resultTransformer = criteria.ROOT_ENTITY;

        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();

        criteria.setProjection(null);
        criteria.setResultTransformer(resultTransformer);

        return count;
    }



    public Page<T> findByPageNo(Integer pageNo, Integer pageSize, List<SearchParam> searchParamList) {
        Criteria criteria = buildCriteriaBySearchParam(searchParamList);

        Integer totalSize = count(criteria).intValue();
        Page<T> page = new Page<>(pageSize,totalSize,pageNo);

        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getSize());


        List<T> result = criteria.list();
        page.setItems(result);


        return page;
    }


    private Criteria buildCriteriaBySearchParam(List<SearchParam> searchParamList) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.addOrder(Order.desc("id"));

        for(SearchParam searchParam : searchParamList) {
            String propertyName = searchParam.getPropertyName();
            Object value = searchParam.getValue();
            String type = searchParam.getType();

            if("eq".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.eq(propertyName,value));
            } else if("like".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.like(propertyName,value.toString(), MatchMode.ANYWHERE));
            } else if("ge".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.ge(propertyName,value));
            } else if("gt".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.gt(propertyName,value));
            } else if("le".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.le(propertyName,value));
            } else if("lt".equalsIgnoreCase(type)) {
                criteria.add(Restrictions.lt(propertyName,value));
            }
        }

        return criteria;

    }




}
