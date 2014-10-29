package com.bzh.gt.test;

import org.hibernate.SessionFactory;

/**
 * Created by Administrator on 2014/10/24.
 */
public class DormitoryAllocation extends SpringUtils {

    private SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");


}
