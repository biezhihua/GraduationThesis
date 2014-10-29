package com.bzh.gt.listener;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bzh.gt.bean.Privilege;
import com.bzh.gt.bean.User;
import com.bzh.gt.service.PrivilegeService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 项目名称 ： GraduationThesis-InitListener
 * 类描述 ： 监听器，监听最大作用域的，也就是WEB应用程序，需要在Web.xml配置
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月23日 下午5:21:18
 */

public class  InitListener implements ServletContextListener {

	static Logger logger = Logger.getLogger(InitListener.class.getClass());
	
	// @Resource 无法注入，因为监听器是servlet使用反射自己生成的
	//private PrivilegeService privilegeService;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	// 最大作用域创建的时候 
	// 因为是一个监听器，不会经过过滤器，当我们获取到topPRivileges数据时，
	// 出去这个方法，session就会被关闭，所以在前台页面会出现no session异常。
	// 这是由于不是同一个请求造成了，openfilterview没有起作用
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 1，获取容器与相关的对象
		// 使用spring的工具类，获取放在application的spring容器
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		// 2，准备数据
		List<Privilege> topPrivileges = privilegeService.getTopList();
		ServletContext application = sce.getServletContext(); // 代表着web应用程序
		application.setAttribute("topPrivileges", topPrivileges);
		logger.info("== 已经在最大作用域中存入左侧菜单列表 ==");


        // 准备数据 allPrivilegeUrls
        Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
        application.setAttribute("allPrivilegeUrls",allPrivilegeUrls);
        logger.info("== 已经在最大作用域中存入所有权限URL列表 ==");
	}

}
