package com.bzh.gt.test;

import com.bzh.gt.bean.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

/**
 * Created by Administrator on 2014/10/11.
 */
public class StudentTest  extends SpringUtils {

    @Test
    public void studentInfo() {
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session =   sessionFactory.openSession();

        Student student = new Student();
        student.setName("别志华");
        session.save(student);
        System.out.println(student);
        Student student1 = (Student) session.get(Student.class, student.getId());
        System.out.println(student1);



    }
}
