package com.kaishengit.action;

import com.kaishengit.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/1.
 */
public class HomeAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware,ServletContextAware {
//推荐HomeAction 继承 ActionSupport


//    不管是获取从JSP传来的值，还是向JSP传值，都得声明属性和生成对应的get 和set
//接收值
//    (声明属性)
//    private String username;
//    private String address;
    private User user;//属性多可以封装成对象

//    向JSP传值
    private List<String> names;


//    每次都创建新对象，故strtus2是线程安全的
//    public HomeAction(){
//        System.out.println("创建对象");
//    }

    //    设置属性（获取Session的第二种方式）
    private Map<String,Object> session;
//    -----------------------------------------
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;




    public String toSave() {

        /**
         * 1. 获取Session的第一种方式
                ActionContext actionContext=ActionContext.getContext();
                Map<String,Object> session=actionContext.getSession();
                session.put("message","Session测试");
                在jsp直接：<h1>${sessionScope.message}</h1>就获取了
         */

        /**1. 获取Session的第二种方式
         *  1.1 类要implements SessionAware（还要实现方法里面的map就是session）
         *  1.2 设置属性Map
         *  1.3 对1.1添加this.session=session;
         *   1.4 直接 session.put("message","测试Session");
         *
         */

        session.put("message","测试Session");



/**
 * 获取HttpServletRequest和HttpServletResponse
    HttpServletRequest request= ServletActionContext.getRequest();
    HttpServletResponse response=ServletActionContext.getResponse();

    HttpSession session=request.getSession();
    ServletContext servletContext=session.getServletContext();

 */

/*
    Map<String,Object> application = ActionContext.getContext().getApplication();
    application.put("hello","world");
*/



        System.out.println("toSave");
        return SUCCESS;
//        也可以直接使用ActionSupport的父接口Action里面的常量(五个)，
    }

    public String save() {
        System.out.println("username：" + user.getUsername() + ";" + "address：" + user.getAddress());
        return "success";
    }

    public String list() {
        names=new ArrayList<>();
        names.add("向JSP传值测试");
        names.add("向JSP传值测试");
        names.add("向JSP传值测试");

        System.out.println("list");
        return "success";
    }


//    (声明上面属性的)get set（当传进来对应的值时候会自动set get赋值）

//names的get 和set
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }



    //    User的get 和set
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    实现SessionAware的实现方法（获取Session的第二种方式）
    @Override
    public void setSession(Map<String, Object> session) {
        this.session=session;//相当于自动注入

    }



//    实现ServletRequestAware,ServletResponseAware,ServletContextAware的方法
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }


//    属性少直接写（多则要像上面进行封装）
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
}

