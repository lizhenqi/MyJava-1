package com.kaishengit.test;

import com.kaishengit.pojo.Task;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.persistence.Table;
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
        Task task= (Task) session.get(Task.class,"5a4479cc562cd6e301562cd6e5bf0000");
        System.out.println(session.contains(task));//session.contains(user) 用于判断对象是否存在于一级缓存中

//        session.clear();//用于清除一级缓存（还有一个作用是把持久态变为游离态）（这样的话就会查询两次）
//        session.evict(task);//将指定对象从一级缓存中清除。

        Task task2= (Task) session.get(Task.class,"5a4479cc562cd6e301562cd6e5bf0000");
        System.out.println(task2.getTitle());//上面已经查询过了有缓存了，现在只会有一句sql查询 。

        transaction.commit();
    }


    //乐观锁
    @Test
    public void testUpdate() throws InterruptedException {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Task task= (Task) session.get(Task.class,"5a4479cc562ce17501562ce177db0000");

        task.setTitle("乐观锁5");

        Thread.sleep(10000);//等它查询出来迅速取修改version，等10秒过后commit的时候，
        // 它会再去查询version和之前是否一样。不一样则不提交。这就是乐观锁

        transaction.commit();
    }

    //悲观锁（测试这个之前把version属性注释掉：带version就是乐观锁）
    @Test
    public void testUpdate2() throws InterruptedException {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Task task= (Task) session.get(Task.class,"5a4479cc562ce17501562ce177db0000", LockOptions.UPGRADE);

        task.setTitle("悲观锁测试");
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Session session=HibernateUtil.getSession();
                Transaction transaction=session.beginTransaction();
                Task task1= (Task) session.get(Task.class,"5a4479cc562ce17501562ce177db0000");
                task1.setTitle("悲观锁测试2");//这个方法的运算结果为：悲观锁测试2
//因为主线程是悲观锁，不管sleep多久，都得等它结束子线程才能运行。
                transaction.commit();
            }
        });
        thread.start();

        Thread.sleep(10000);


        transaction.commit();
    }




    @Test
    public void testEhcache(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Task task= (Task) session.get(Task.class,"5a4479cc562cd6e301562cd6e5bf0000");

        System.out.println(task.getTitle());
        transaction.commit();

        //用于清除二级缓存对象
//        Cache cache=session.getSessionFactory().getCache();
//        cache.evictEntityRegion(Task.class);

        Session session1= HibernateUtil.getSession();
        Transaction transaction1=session1.beginTransaction();

        Task task2= (Task) session1.get(Task.class,"5a4479cc562cd6e301562cd6e5bf0000");
        System.out.println(task2.getTitle());

        transaction1.commit();


//        这样的话上面总共会有一句查询sql（不加二级缓存会有两句sql）。因为在task配置的<cache usage="read-write"/>加入了二级缓存
    }

}
