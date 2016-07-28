package com.kaishengit.service;

import com.kaishengit.dao.BookDao;
import com.kaishengit.dao.BookTypeDao;
import com.kaishengit.dao.PublisherDao;
import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Named
@Transactional   //service里面的所有都要在事务中进行
public class BookService {

    @Inject
    private BookDao bookDao;
    @Inject
    private BookTypeDao bookTypeDao;
    @Inject
    private PublisherDao publisherDao;



//    增、改
    public void saveBook(Book book){
        bookDao.save(book);
    }
//    删除
    public void delete(Book book){
        bookDao.delte(book);
    }
//    按id删除
    public void deleteById(Integer id){
        bookDao.deleteById(id);
    }
//    按id查询
    public Book findById(Integer id){
        return bookDao.findById(id);
    }
//    查询所有
    public List<Book> findAll(){
        return bookDao.findAll();
    }
//    查询所有出版社
    public List<Publisher> findAllPublisher(){
        return publisherDao.findAllPublisher();
    }
//    查询所有书类型
    public List<BookType> findAllBookType(){
        return bookTypeDao.findAllBookType();
    }


}
