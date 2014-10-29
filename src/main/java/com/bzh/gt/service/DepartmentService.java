package com.bzh.gt.service;

import java.util.List;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.Department;

/**
 * 项目名称 ： GraduationThesis-DepartmentService
 * 类描述 ：部门的业务逻辑层，实现主要的业务逻辑计算
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月15日 下午7:02:49
 * version ：
 */

public interface DepartmentService extends DaoSupport<Department> {

	/**
	 * 概要: 获取顶级部门列表，parent为NULL
	 */
	List<Department> getTopList();

}
