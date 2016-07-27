package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.SortComparator;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class HQLtTestCase {

    @Test
    public void testFindAll(){

//        HQL 全部是java中的对象跟数据库无关
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from User";
        Query query=session.createQuery(hql);
        List<User> userList=query.list();
        for(User user:userList){
            System.out.println(user);
        }
        transaction.commit();
    }


//    按条件查询
    @Test
    public void testFindWhere(){
//        HQL 全部是java中的对象跟数据库无关

        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

//        String hql="from User where password=:msg";//也可以不用？，给它起个名字(引用占位符)

        String hql="from User where password=? and username=?";
        Query query=session.createQuery(hql);

//        query.setParameter("msg","密码测试");//起名字的获取方式

        query.setParameter(0,"密码测试");//JDBC从‘1’开始，heibernate从‘0’开始！
        query.setParameter(1,"成龙");

        List<User> userList=query.list();
        for(User user:userList){
            System.out.println(user);
        }
        transaction.commit();
    }


    //唯一结果的查询
    @Test
    public void testFindUnique(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        String hql="from User where password=:pwd";
        Query query=session.createQuery(hql);

        query.setParameter("pwd","测试2");//要确保结果就一条，否则直接报错
        User user= (User) query.uniqueResult();

        System.out.println(user);

        transaction.commit();
    }


//    查询某一列
    @Test
    public void testFindByCloumn(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="select username from User";
        Query query=session.createQuery(hql);
        List<String> stringList=query.list();
        //那一列是什么类型，list里面就是什么类型
        for(String username :stringList){
            System.out.println(username);
        }
        transaction.commit();
    }

    //    查询多列
    @Test
    public void testFindByCloumns(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        String hql="select id,username from User";
        Query query=session.createQuery(hql);

        List<Object[]> result=query.list();
        //多列的里面返回的是数组

        for(Object[] objects :result){
            System.out.println("id="+objects[0]+"->"+"username="+objects[1]);
        }
        transaction.commit();
    }


//    统计数量（一列）
    @Test
    public void testCount(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="select count(*) from User";
        Query query=session.createQuery(hql);

        Long count= (Long) query.uniqueResult();//数量肯定是唯一的

        System.out.println(count);
        transaction.commit();
    }

    //    统计数量（多列）
    @Test
    public void testCounts(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="select count(*),max(id) from User";
        Query query=session.createQuery(hql);

        Object[] count= (Object[]) query.uniqueResult();//数量肯定是唯一的

        System.out.println("count="+count[0]+"->"+"max[id]="+count[1]);
        transaction.commit();
    }


    //   分页
    @Test
    public void testPage(){

        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        String hql="from User order by id desc ";
        Query query=session.createQuery(hql);

        query.setFirstResult(3);//从第三个开始取（不包括第三个）
        query.setMaxResults(3);//取三个
        List<User> userList=query.list();
        for(User user:userList){
            System.out.println(user);
        }
        transaction.commit();
    }


    //   利用Criteria的查询
    @Test
    public void testFindAllByCriteria(){

        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Criteria criteria=session.createCriteria(User.class);//不用hql了

        List<User> userList=criteria.list();
        for(User user:userList){
            System.out.println(user);
        }
        transaction.commit();
    }



    //   利用Criteria的查询bywhere
    @Test
    public void testFindBywhereByCriteria(){

        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Criteria criteria=session.createCriteria(User.class);//不用hql了

//      查询password为密码测试and username为成龙的
//      criteria.add(Restrictions.eq("password","密码测试"));
//      criteria.add(Restrictions.eq("username","成龙"));


//        上面的也可以如下写
//        Disjunction disjunction=Restrictions.disjunction();
//        disjunction.add(Restrictions.eq("password","密码测试"));
//        disjunction.add(Restrictions.eq("username","成龙"));
//        criteria.add(disjunction);



//      查询username为李振起 or username为成龙的
//        criteria.add(Restrictions.in("username",new Object[]{"成龙","李振起"}));//也可如下写
//        criteria.add(Restrictions.or(Restrictions.eq("username","李振起"),Restrictions.eq("username","成龙")));

//        模糊查询（like要选带匹配模型的那个否则就相当于eq了）
        criteria.add(Restrictions.like("username","起",MatchMode.ANYWHERE));//模糊查询MatchMode里面有以参数开始、结束等等

        List<User> userList=criteria.list();
        for(User user:userList){
            System.out.println(user);
        }
        transaction.commit();
    }

    //   利用Criteria的查询（唯一查询）
    @Test
    public void testFindUniqueByCriteria(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Criteria criteria=session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username","李振起"));

        User user= (User) criteria.uniqueResult();
        System.out.println(user);

        transaction.commit();
    }

    //   利用Criteria进行分页（唯一查询）
    @Test
    public void testPageCriteria(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Criteria criteria=session.createCriteria(User.class);

        criteria.addOrder(Order.desc("id"));//排序

        criteria.setFirstResult(0);
        criteria.setMaxResults(4);

        List<User> userList=criteria.list();
        for(User user:userList){
            System.out.println(user);
        }
        transaction.commit();
    }


    //   利用Criteria进行count(单列和多列)

    @Test
    public void testCountByCriteria(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Criteria criteria=session.createCriteria(User.class);

//        criteria.setProjection(Projections.rowCount());//rowCount()这个相当于count(*)
//        criteria.setProjection(Projections.count("password"));//这个就是按列名统计
//        criteria.setProjection(Projections.count("id"));//这个就是按列名统计

//        Long count= (Long) criteria.uniqueResult();

//        统计多列不能直接set，set了，应该如下
        ProjectionList projectionList=Projections.projectionList();
        projectionList.add(Projections.count("id"));
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);

        Object[] count= (Object[]) criteria.uniqueResult();

        System.out.println(count[0]+"--"+count[1]);

        transaction.commit();
    }


}
