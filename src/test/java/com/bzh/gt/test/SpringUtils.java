package com.bzh.gt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

	public static ApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext("beans.xml");
	}
}
