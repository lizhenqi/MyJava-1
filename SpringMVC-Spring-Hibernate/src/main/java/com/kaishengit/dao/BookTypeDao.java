package com.kaishengit.dao;

import com.kaishengit.pojo.BookType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Named
public class BookTypeDao {
    @Inject
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<BookType> findAllBookType(){
        Criteria criteria=getSession().createCriteria(BookType.class);

        return  criteria.list();
    }

}
