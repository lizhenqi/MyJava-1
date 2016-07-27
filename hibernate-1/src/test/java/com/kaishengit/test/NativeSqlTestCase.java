package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;


/**
 * Created by Administrator on 2016/7/26.
 */
public class NativeSqlTestCase {


//    用原生sql查询(查询全部)
    @Test
    public void testFindAll(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        String sql="select * from t_user";
        SQLQuery sqlQuery=session.createSQLQuery(sql);

        List<Object[]> userList=sqlQuery.list();//注意：List里面是Object数组

        for(Object[] objects:userList){
            System.out.println(objects[0]+"->"+objects[1]+"->"+objects[2]);
        }
        transaction.commit();
    }

    //    用原生sql查询(按ID查询)
    @Test
    public void testFindById(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        String sql="select * from t_user where id=:id";//也可以用“?”
        SQLQuery sqlQuery=session.createSQLQuery(sql);

        sqlQuery.setParameter("id",7);
        Object[] objects= (Object[]) sqlQuery.uniqueResult();//返回值为对象数组

        System.out.println(objects[0]+"->"+objects[1]+"->"+objects[2]);
        transaction.commit();
    }

    //    用原生sql查询(按ID查询转成对象输出)
    @Test
    public void testFindByIdToUser(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        String sql="select * from t_user where id=?";//也可以用“?”
        SQLQuery sqlQuery=session.createSQLQuery(sql).addEntity(User.class);//返回值为User

        sqlQuery.setParameter(0,7);//误报，不影响。
        User user= (User) sqlQuery.uniqueResult();

        System.out.println(user);
        transaction.commit();
    }


}
