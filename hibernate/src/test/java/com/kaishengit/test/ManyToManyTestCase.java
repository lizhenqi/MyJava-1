package com.kaishengit.test;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Teacher;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ManyToManyTestCase {

    /*
        最佳实践：让其中一方放弃维护关系

    */

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student();
        student.setStuname("S1");

        Student student1 = new Student();
        student1.setStuname("S2");

        Teacher teacher=new Teacher();
        teacher.setTeaname("T1");

        Teacher teacher1=new Teacher();
        teacher1.setTeaname("T2");

/*
        Set<Teacher> teacherSet=new HashSet<>();
        teacherSet.add(teacher);
        teacherSet.add(teacher1);//把老师都放在集合中

        student.setTeacherSet(teacherSet);//让每个学生都有两个老师
        student1.setTeacherSet(teacherSet);
        上面写的和下面一样（注意：这个和下面不能同时存在（报错），
        即不能同时维护这种关系，要有一方放弃（需要在（这里让老师维护关系所以需要学生放弃）student的配置文件里面：inverse="true"）
        这样的话这段代码写不写都没有影响了）。

        或者这段不写，也不用inverse="true"了
*/

        Set<Student> studentSet=new HashSet<>();
        studentSet.add(student);
        studentSet.add(student1);

        teacher.setStudentSet(studentSet);
        teacher1.setStudentSet(studentSet);



        session.save(student);
        session.save(student1);
        session.save(teacher);
        session.save(teacher1);

        transaction.commit();
    }

    @Test
    public void testFind(){
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Teacher teacher= (Teacher) session.get(Teacher.class,27);
//        System.out.println(teacher.getTeaname());

        Set<Student> list=teacher.getStudentSet();//集合

        for(Student student:list){
            System.out.println(student.getId()+":"+student.getStuname());
        }
        transaction.commit();
    }
}
