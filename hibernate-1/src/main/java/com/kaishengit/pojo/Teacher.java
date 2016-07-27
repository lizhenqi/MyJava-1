package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/27.
 */
@Entity //标明为pojo类
@Table(name = "t_teacher")  //标明对应数据库的表（误报错）
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增

    private Integer id;
    private String teaname;

    @ManyToMany
    @JoinTable(name = "t_student_teacher",
                joinColumns = @JoinColumn(name = "teaid"),
                inverseJoinColumns = @JoinColumn(name = "stuid"))
    //关联表，自己在关联表的外键，对方在关联表的外键（当注解里面只有一个值时候{}可以省略）

    @OrderBy("id desc ") //查询排序
    private Set<Student> studentSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname;
    }
}
