package com.bzh.gt.test;

import com.bzh.gt.bean.Clasz;
import com.bzh.gt.bean.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

/**
 * Created by Administrator on 2014/9/29.
 */
public class ClaszTest extends SpringUtils {

    @Test
    public void ClaszToStudent() {
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session =   sessionFactory.openSession();



    }
}
