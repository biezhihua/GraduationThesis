package com.bzh.gt.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Role;
import com.bzh.gt.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements
		RoleService {

}
