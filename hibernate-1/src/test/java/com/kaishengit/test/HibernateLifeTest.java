package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.usertype.UserType;
import org.junit.Test;
import org.omg.CORBA.Object;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by Administrator on 2016/7/26.
 */
public class HibernateLifeTest {

    @Test
    public void testOne(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        User user=new User();
        user.setPassword("xx");
        user.setUsername("ss");
//        这属于自由态（并没有与数据库相关联），可根据是否有id判别
        transaction.commit();
    }

    @Test
    public void testTwo(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        User user=new User();
        user.setPassword("测试1");
        user.setUsername("测试2");
//        这属于持久态（变化会被同步到数据库的）
        transaction.commit();
//        提交之后user就属于游离态
    }



//    get 和 load 区别

    @Test
    public void testGet(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        User user= (User) session.get(User.class,10);
//get 即使不用也会直接加载
        //        查询时候若对象不存在，get抛出null；
        transaction.commit();
//        注意：不管什么只要提交了，就不能再操作了因为没有session了，除非再次获得session
    }
    @Test
    public void testLoad(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        User user= (User) session.get(User.class,10);
//load是懒加载。只有调用时候才加载。例如在下面打印一下就会加载。
//        查询时候若对象不存在，load抛出ObjectNotFoundException
        transaction.commit();
    }



//    save 和 persist
    @Test
    public void testSave(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        User user=new User();
        user.setPassword("河南");
        user.setUsername("焦作");
        Integer id= (Integer) session.save(user);
//save 会返回保存对象的id
        System.out.println(id);
        transaction.commit();
    }
    @Test
    public void testPersist(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        User user=new User();
        user.setPassword("焦作");
        user.setUsername("腾云");
        session.persist(user);
//persist 则不会返回保存对象的id
        System.out.println();
        transaction.commit();
    }


//    save 和 update
    @Test
    public void testUpdate(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        User user=new User();
        user.setUsername("李四");
        user.setPassword("密码");

        session.save(user);//save将自由态的对象保存

        transaction.commit();

        user.setPassword("密码测试");//写在这里和下面无所谓

        Session session1=HibernateUtil.getSession();
        Transaction transaction1=session1.beginTransaction();

        session1.update(user);//update将游离态的对象保存

        transaction1.commit();
    }



    //saveOrUpdate会自动根据传入的状态选择save还是update
    @Test
    public void testSaveOrUpdate(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        User user=new User();
        user.setUsername("王五");
        user.setPassword("测试");

        session.saveOrUpdate(user);//saveOrUpdate会自动根据传入的状态选择save还是update
        System.out.println(user.getId());

        transaction.commit();

        user.setPassword("密码测试");//写在这里和下面无所谓

        Session session1=HibernateUtil.getSession();
        Transaction transaction1=session1.beginTransaction();

        session1.saveOrUpdate(user);//saveOrUpdate会自动根据传入的状态选择save还是update

        transaction1.commit();
    }


    //merge（不改变对象的状态 ）
    @Test
    public void testMerge(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        User user=new User();
        user.setUsername("成龙");
        user.setPassword("洪金宝");

        session.merge(user);//给merge什么态，它操作完就还是什么态。（现在还是自由态）
        System.out.println(user.getId());

        transaction.commit();

        user.setPassword("密码测试");//写在这里和下面无所谓

        Session session1=HibernateUtil.getSession();
        Transaction transaction1=session1.beginTransaction();

        session1.merge(user);//给merge什么态，它操作完就还是什么态。（现在还是自由态）

        transaction1.commit();
    }

    @Test
    public void testDelete(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        User user= (User) session.get(User.class,18);

        session.delete(user);

        transaction.commit();
    }




}
