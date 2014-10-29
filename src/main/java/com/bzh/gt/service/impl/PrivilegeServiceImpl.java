package com.bzh.gt.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Privilege;
import com.bzh.gt.service.PrivilegeService;

// 业务逻辑层

/**
 * 项目名称 ： GraduationThesis-DepartmentServiceImpl
 * 类描述 ： 权限的业务逻辑层具体实现类
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月15日 下午7:05:21
 */

@Service
// 事务控制
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements
		PrivilegeService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> getTopList() {
		return getSession().createQuery(//
				"FROM Privilege d WHERE d.parent IS NULL ORDER BY d.id ASC")//
				.list();
	}

    @Override
    public Collection<String> getAllPrivilegeUrls() {
        return getSession().createQuery("SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL").list();
    }

}
