package com.bzh.gt.service;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.User;

import java.util.List;


/**    
 * 项目名称	：	GraduationThesis-UserService  
 * 类描述		： 	用户业务层
 * 创建人		：	别志华
 * 创建时间	：	2014年7月22日 下午1:23:25         
 * version    	：	
 */
 
public interface UserService extends DaoSupport<User> {

	/**
	 * 概要:根据登录名、密码查找用户	
	 * 返回类型: 没找到为null
	 */
	User findByLoginNameAndPassword(String loginName, String password);

    /**
     * 获取普通用户集合
     * @return
     */
    List<User> getCustomers();
}
