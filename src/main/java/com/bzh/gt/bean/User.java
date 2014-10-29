package com.bzh.gt.bean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.config.entities.ActionConfig;

import javax.swing.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称 ： GraduationThesis-User
 * 类描述 ： 用户实体
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月11日 下午8:02:53
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1813533329977164299L;

    private static final String ADMIN = "administrator";

    // ============普通字段================
    private Long id;

    /**
     * 字段: loginName 登录名
     */
    private String loginName;

    /**
     * 字段: password 密码
     */
    private String password;

    /**
     * 字段: name 姓名
     */
    private String name;

    /**
     * 字段: gender 性别
     */
    private String gender;

    /**
     * 字段: phoneNumber 手机号码
     */
    private String phoneNumber;

    /**
     * 字段: qq QQ
     */
    private String qq;

    /**
     * 字段: description 个人描述
     */
    private String description;

    // ===============关联关系====================

    /**
     * 用户与班级的一对一双向关联，班长所负责的班级
     */
    private Clasz clasz;

    /**
     * 用户与岗位的多对多关系
     * <p/>
     * 字段: roles 用户所属岗位
     */
    private Set<Role> roles = new HashSet<Role>();

    /**
     * 用户与登录退出日志的一对多关系
     * <p/>
     * 字段: loginLogs 登录退出日志集合
     */
    private Set<LoginLog> loginLogs = new HashSet<LoginLog>();

    // =============================================

    /**
     * 概要: 判断是否有指定名称的权限
     */
    public boolean hasPrivilegeByName(String name) {
        // 超级管理员
        if (isAdministrator()) {
            return true;
        }

        // 针对普通用户，判断是否含有这个权限
        for (Role role : roles) {
            for (Privilege privilege : role.getPrivileges()) {
                // 比较内容
                if (privilege.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean hasPrivilegeByUrl(String url) {
        // 超级管理员
        if (isAdministrator()) {
            return true;
        }

        int pos = url.indexOf("?");
        if (pos > -1) {
            url = url.substring(0, pos);
        }

        if (url.endsWith("UI")) {
            url = url.substring(0, url.length() - 2);
        }

        // 如果本URL不需要控制
        Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
        if (!containsEndsWith(allPrivilegeUrls, url)) {
            return true;
        } else {
            // 针对普通用户，判断是否含有这个权限
            for (Role role : roles) {
                for (Privilege privilege : role.getPrivileges()) {
                    // 比较内容
                    if (privilege.getUrl().endsWith(url)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsEndsWith(Collection<String> allPrivilegeUrls, String privUrl) {
        for (String url: allPrivilegeUrls) {
            if (url.endsWith(privUrl)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdministrator() {
        return ADMIN.equals(loginName);
    }

    // ===============方法区=======================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<LoginLog> getLoginLogs() {
        return loginLogs;
    }

    public void setLoginLogs(Set<LoginLog> loginLogs) {
        this.loginLogs = loginLogs;
    }

    public Clasz getClasz() {
        return clasz;
    }

    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
    }

    @Override
    public String toString() {
        return "登录名：" + loginName + "，姓名：" + name + "，性别：" + gender + "，手机号：" + phoneNumber + "，QQ：" + qq + "，描述：" + description + "，所属岗位：";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (description != null ? !description.equals(user.description) : user.description != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (loginName != null ? !loginName.equals(user.loginName) : user.loginName != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (qq != null ? !qq.equals(user.qq) : user.qq != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
