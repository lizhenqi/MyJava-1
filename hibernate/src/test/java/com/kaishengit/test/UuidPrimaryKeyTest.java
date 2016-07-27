package com.kaishengit.test;

import com.kaishengit.pojo.Task;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.UUID;

/**
 * Created by Administrator on 2016/7/27.
 */
public class UuidPrimaryKeyTest {

//利用UUID生成id（String类型）
    @Test
    public void testSave(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Task task=new Task();
        task.setTitle("主题");
        task.setId(UUID.randomUUID().toString());

        session.save(task);

        transaction.commit();
    }

    @Test
    public void testFindById(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

//        这样的（查询同一个对象两次以上）话会触发‘一级缓存’（同一个session中多次查询同一个对象），当session关闭时候就失效了
        Task task= (Task) session.get(Task.class,"40288195562ad4fc01562ad4ff000000");
        System.out.println(session.contains(task));//session.contains(user) 用于判断对象是否存在于一级缓存中

//        session.clear();//用于清除一级缓存（还有一个作用是把持久态变为游离态）（这样的话就会查询两次）
//        session.evict(task);//将指定对象从一级缓存中清除。

        Task task2= (Task) session.get(Task.class,"40288195562ad4fc01562ad4ff000000");
        System.out.println(task2.getTitle());//上面已经查询过了有缓存了，现在只会有一句sql查询 。


        transaction.commit();
    }


    @Test
    public void testEhcache(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Task task= (Task) session.get(Task.class,"40288195562ad4fc01562ad4ff000000");

        System.out.println(task.getTitle());
        transaction.commit();

        //用于清除二级缓存对象
//        Cache cache=session.getSessionFactory().getCache();
//        cache.evictEntityRegion(Task.class);

        Session session1= HibernateUtil.getSession();
        Transaction transaction1=session1.beginTransaction();

        Task task2= (Task) session1.get(Task.class,"40288195562ad4fc01562ad4ff000000");
        System.out.println(task2.getTitle());

        transaction1.commit();


//        这样的话上面总共会有一句查询sql（不加二级缓存会有两句sql）。因为在task配置的<cache usage="read-write"/>加入了二级缓存
    }

}
