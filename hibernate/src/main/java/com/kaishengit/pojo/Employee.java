package com.kaishengit.pojo;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Employee {
    private Integer id;
    private String empname;
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
