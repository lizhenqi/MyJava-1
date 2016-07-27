package com.kaishengit.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Administrator on 2016/7/25.
 */
public class HibernateUtil {


    private  static SessionFactory sessionFactory=buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        Configuration configuration=new Configuration().configure();
        ServiceRegistry registry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory=configuration.buildSessionFactory(registry);
        return sessionFactory;
    }
    //上面能确保只有一份（第一次使用的时候才会被调用）


//    获取SessionFactory
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    //    获取Session
    public static Session getSession(){
        return getSessionFactory().getCurrentSession();
    }






}
