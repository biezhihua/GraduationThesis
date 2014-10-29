package com.bzh.gt.service.impl;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Department;
import com.bzh.gt.service.DepartmentService;

// 业务逻辑层

/**
 * 项目名称 ： GraduationThesis-DepartmentServiceImpl
 * 类描述 ： 部门的业务逻辑层具体实现类
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月15日 下午7:05:21
 */

@Service
// 事务控制
@Transactional
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements
		DepartmentService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getTopList() {

		return getSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

}
