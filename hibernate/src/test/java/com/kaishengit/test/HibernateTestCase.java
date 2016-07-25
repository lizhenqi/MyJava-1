package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class HibernateTestCase {

    @Test
    public void testSave() {
        Configuration configuration = new Configuration().configure();

//        hibernate 3.x用，现在已经不推荐用了
//        SessionFactory sessionFactory=configuration.buildSessionFactory();


// hiberrnate 4.3以后才这样用
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(registry);


//    session
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();//session要求所有操作都要在事务中进行

        User user = new User();
        user.setUsername("李振起");
        user.setPassword("密码");
        session.save(user);


//      session.getTransaction().commit();//事务的结束要么回滚，要么commit这两种都行。
//      session.getTransaction().rollback();如果不想获取事务，可以在开始事务时候返回一个对象，直接回滚或是commit。
        transaction.commit();//结束事务（会自动关闭连接池）

//        session.close();//因为事务结束之后就会自动关闭这里就不要再次关闭了，否则会报错！

    }

    @Test
    public void testFindById(){
//        步骤
        Session session=HibernateUtil.getSession();//1：取Session
        Transaction transaction=session.beginTransaction();//2：开始事务

        //3:操作
        User user= (User) session.get(User.class,1);

        System.out.println(user);

        //4:提交或回滚（包括关闭session）
        transaction.commit();
    }
    @Test
    public void testUpdate(){
//        步骤
        Session session=HibernateUtil.getSession();//1：取Session
        Transaction transaction=session.beginTransaction();//2：开始事务

        //3:操作
        User user= (User) session.get(User.class,1);
        user.setUsername("Hibernate修改测试");//先出来再改

        //4:提交或回滚（包括关闭session）
        transaction.commit();
    }

    @Test
    public void testDel(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        User user= (User) session.get(User.class,9);
        session.delete(user);//根据对象删除，不是id（先查出来再删除）

        transaction.commit();
    }

    @Test
    public void testFindAll(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        String hql="from User";//是pojo中的对象，不是数据库的表。
        Query query=session.createQuery(hql);
        List<User> userList=query.list();
        for(User user:userList){
            System.out.println(user);
        }

        transaction.commit();
    }



}

