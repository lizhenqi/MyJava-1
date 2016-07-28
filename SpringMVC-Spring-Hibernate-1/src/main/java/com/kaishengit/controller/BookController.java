package com.kaishengit.controller;

import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Page;
import com.kaishengit.util.SearchParam;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Repeat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */

@Controller
public class BookController {

    @Inject
    private BookService bookService;


    @RequestMapping(value = "/book",method = RequestMethod.GET)
    public String home(@RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                       Model model,
                       HttpServletRequest request){
//        List<Book> bookList=bookService.findAll();

        List<SearchParam> searchParamList=SearchParam.buildSearchParam(request);

        Page<Book> pageList=bookService.findByPage(pageNo,searchParamList);
        model.addAttribute("pageList",pageList);

        return "book/list";
    }

//    新增
    @RequestMapping(value = "/book/new",method = RequestMethod.GET)
   public String newBook(Model model){
        List<BookType> bookTypeList=bookService.findAllBookType();
        List<Publisher> publisherList=bookService.findAllPublisher();

        model.addAttribute("bookTypeList",bookTypeList);
        model.addAttribute("publisherList",publisherList);

        return "book/new";
   }
    @RequestMapping(value = "/book/new",method = RequestMethod.POST)
   public String newBook(Book book,RedirectAttributes redirectAttributes){
        bookService.saveBook(book);

        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/book";
   }

//    删除
    @RequestMapping(value = "/book/del/{id:\\d+}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @RequestMapping(value = "/book/update/{id:\\d+}",method = RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model){
        Book book=bookService.findById(id);
        model.addAttribute("book",book);

        List<BookType> bookTypeList=bookService.findAllBookType();
        List<Publisher> publisherList=bookService.findAllPublisher();

        model.addAttribute("bookTypeList",bookTypeList);
        model.addAttribute("publisherList",publisherList);

        return "book/edit";
    }
    @RequestMapping(value = "/book/update/{id:\\d+}",method = RequestMethod.POST)
    public String update(Book book){
       bookService.saveBook(book);

        return "redirect:/book";
    }






}
