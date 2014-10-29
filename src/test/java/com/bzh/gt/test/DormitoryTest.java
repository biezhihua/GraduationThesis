package com.bzh.gt.test;

import com.bzh.gt.service.DormitoryService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/8/20.
 */
public class DormitoryTest extends SpringUtils {

    @Test
    public void testMap() {
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        System.out.println(session.createQuery("FROM Dormitory d WHERE d.apartment.name like '10%' AND d.name = 325").uniqueResult());
    }


}
