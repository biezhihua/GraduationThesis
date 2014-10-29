package com.bzh.gt.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.User;
import com.bzh.gt.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements
        UserService {

    @Override
    public User findByLoginNameAndPassword(String loginName, String password) {
        return (User) getSession()//
                .createQuery("FROM User u WHERE u.loginName=? AND u.password=?")//
                .setParameter(0, loginName)//
                .setParameter(1, password)//
                .uniqueResult();
    }

    @Override
    public List<User> getCustomers() {
        return getSession()//
                .createQuery("FROM User u WHERE u.loginName!='administrator'")//
                .list();
    }
}
