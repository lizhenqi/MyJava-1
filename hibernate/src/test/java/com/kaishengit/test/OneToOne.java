package com.kaishengit.test;

import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Person;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by Administrator on 2016/7/26.
 */
public class OneToOne {

    //一对一表
    /*
    *               最佳实践
    *               1：先存主，再存从
    *
    *
    *
    */


    @Test
    public void testSave(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Person person=new Person();
        person.setName("习近平");

        Card card=new Card();
        card.setCardname("黑卡-1");
        card.setPerson(person);

        session.save(person);
        session.save(card);


        transaction.commit();
    }


    @Test
    public void testFindPerson(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Person person= (Person) session.get(Person.class,12);

        System.out.println(person.getName());
        System.out.println(person.getCard().getCardname());

        transaction.commit();
    }


    @Test
    public void testFindCard(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Card card= (Card) session.get(Card.class,10);

        System.out.println(card.getCardname());
        System.out.println(card.getPerson().getName());

        transaction.commit();
    }

    @Test
    public void testDeleteCard(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Card card= (Card) session.get(Card.class,12);
        session.delete(card);
//这个删除卡不能删除人（级联也不行，应该在person里面配置级联删除）
        transaction.commit();
    }

    @Test
    public void testDeletePerson(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Person person= (Person) session.get(Person.class,12);
        session.delete(person);
//        在本对象的配置文件中配置级联删除
        transaction.commit();
    }

}
