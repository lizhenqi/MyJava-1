package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/7/26.
 */
@Entity  //标明为pojo类
@Table(name = "t_employee") //对应数据库的表
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键策略自动增长
    private Integer id;
    private String empname;

    @ManyToOne  //(fetch = FetchType.LAZY) 提前加载，不让它懒加载
    @JoinColumn(name = "deptid")  //指定外键

    private Dept dept;//多的这边存一的那边的对象

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
}
