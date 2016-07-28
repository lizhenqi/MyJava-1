package com.kaishengit.dao;

import com.kaishengit.pojo.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Named
public class BookDao {

    @Inject
    private SessionFactory sessionFactory; //会自动注入配置文件里面的sessionFactory


    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
//增、修改
    public void save(Book book){
        getSession().saveOrUpdate(book);
    }
//按对象删除
    public void delte(Book book){
        getSession().delete(book);
    }
//    按id查
    public Book findById(Integer id){
        return (Book) getSession().get(Book.class,id);
    }

//    按id删除
    public void deleteById(Integer id){
        getSession().delete(findById(id));
    }

    public List<Book> findAll(){
        Criteria criteria=getSession().createCriteria(Book.class);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }


}
