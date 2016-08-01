package com.kaishengit.action;

import com.kaishengit.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/1.
 */
public class HomeAction {


//    不管是获取从JSP传来的值，还是向JSP传值，都得声明属性和生成对应的get 和set
//接收值
//    (声明属性)
//    private String username;
//    private String address;
    private User user;//属性多可以封装成对象

//    向JSP传值
    private List<String> names;


    public String toSave() {
        System.out.println("toSave");
        return "success";
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

