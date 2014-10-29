package com.bzh.gt.test;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;

import com.bzh.gt.bean.Role;
import com.bzh.gt.bean.User;
import com.bzh.gt.service.UserService;

public class MakeTableTest extends SpringUtils{

	@Test
	public void makeTable() {
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
}
