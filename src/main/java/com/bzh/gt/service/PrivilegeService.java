package com.bzh.gt.service;

import java.util.Collection;
import java.util.List;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.Privilege;

/**
 * 项目名称 ： GraduationThesis-DepartmentService
 * 类描述 ：部门的业务逻辑层，实现主要的业务逻辑计算
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月15日 下午7:02:49
 * version ：
 */

public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * 概要: 获取顶级权限列表，parent为NULL
	 */
	List<Privilege> getTopList();

    Collection<String> getAllPrivilegeUrls();
}
