package com.kaishengit.test;

import com.kaishengit.pojo.Dept;
import com.kaishengit.pojo.Employee;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/26.
 */
public class OneToManyTestCase {

    /*
        最佳实践：
        1：保存的时候先存一再存多。
        2：要让一的一端放弃关系维护。


     */
    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Dept dept = new Dept();
        dept.setDeptname("C#开发部");

        Employee employee = new Employee();
        employee.setEmpname("李振起");
        employee.setDept(dept);

        Employee employee1 = new Employee();
        employee1.setEmpname("张三");
        employee1.setDept(dept);

/*加上这些就会双向关联，也会update共五条sql，没用应该去掉
在Dept.hbm.xml配置 inverse="true"表示当前对象放弃关系维护，即使写也只有三条sql。
（2：要让一的一端放弃关系维护。）
        Set<Employee> employees=new HashSet<>();
        employees.add(employee);
        employees.add(employee1);

        dept.setEmployeeSet(employees);

*/


//这样写的话也会插入，但是会有五条sql，性能底
//        session.save(employee);
//        session.save(employee1);
//        session.save(dept);

//        应该这样只要三条sql，先插外键的。这样提交的时候不用update了（1：保存的时候先存一再存多。）
        session.save(dept);
        session.save(employee);
        session.save(employee1);

        transaction.commit();


    }

    @Test
    public void testFind() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Dept dept = (Dept) session.get(Dept.class, 15);

//        System.out.println(dept.getDeptname());

        //查询本部门的员工（懒加载）
        Set<Employee> employeeSet=dept.getEmployeeSet();
        for(Employee employee:employeeSet){
            System.out.println(employee.getEmpname());
        }



        transaction.commit();
    }

    @Test
    public void testFindEmployee(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Employee employee= (Employee) session.get(Employee.class,29);

        Dept dept=employee.getDept();
        System.out.println(dept.getDeptname());
        System.out.println(employee.getEmpname());

        transaction.commit();
    }

    @Test
    public void testFindEmployeeAll(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria=session.createCriteria(Employee.class);


        //这样就会产生N+1问题（查询‘多’再去查‘一’）
        //最佳解决办法：把当前对象的配置文件里加：fetch=“join”
        List<Employee> employeeList=criteria.list();
        for(Employee employee:employeeList){
            System.out.println("id="+employee.getId()+
                    ":empname="+employee.getEmpname()+
                    ":deptname"+employee.getDept().getDeptname());
        }


        transaction.commit();
    }

    @Test
    public void testDelete(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Dept dept= (Dept) session.get(Dept.class,15);
        session.delete(dept);
        //直接删除不行，因为它是employee的外键有级联。
        // 故在本对象配置文件中配置：cascade="delete"，删除自己之前会先去删除级联文件

        transaction.commit();
    }
}
