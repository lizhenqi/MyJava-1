package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/27.
 */

//student和teacher多对多关系

@Entity  //标明为pojo类
@Table(name = "t_student") //标明对应数据库的表（误报错）
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增
    private Integer id;
    private String stuname;

    @ManyToMany(mappedBy = "studentSet") //本次选择的是student放弃关系维护,student在对方那里叫studentSet

//student放弃了关系维护，所以下面的关联表就不用写了。teacher维护teacher写就行了
//    @JoinTable(name = "t_student_teacher",
//            joinColumns = @JoinColumn(name = "stuid"),
//            inverseJoinColumns = @JoinColumn(name = "teaid"))
//    //关联表，自己在关联表的外键，对方在关联表的外键（当注解里面只有一个值时候{}可以省略）



    private Set<Teacher> teacherSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }
}
