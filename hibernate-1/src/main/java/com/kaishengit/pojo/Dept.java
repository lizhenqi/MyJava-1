package com.kaishengit.pojo;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/26.
 */

//Dept和Employee一对多关系
@Entity  //标明为pojo类
@Table(name = "t_dept")  //标明对应数据库的表（误报错）
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键策略自动增长
    private Integer id;
    private String deptname;

    @OneToMany(mappedBy = "dept")//（最佳实践）让一的一端放弃关系维护，dept指的是自己在对方那边的名字
    @Cascade(CascadeType.DELETE) //级联删除
    private Set<Employee> employeeSet;//一的这边存多的那边的集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptname='" + deptname + '\'' +
                ", employeeSet=" + employeeSet +
                '}';
    }
}
