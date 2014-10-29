package com.bzh.gt.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称 ： GraduationThesis-Role
 * 类描述 ： 岗位
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月11日 下午8:13:49
 */

public class Role implements Serializable {

    private static final long serialVersionUID = -2689630632601947127L;

    // ==============普通字段=========
    private Long id;

    /**
     * 字段: name 岗位名称
     */
    private String name;

    /**
     * 字段: description 岗位说明
     */
    private String description;

    // ===============关联关系========
    /**
     * 岗位与用户的多对多关系
     *
     * 字段: uesrs 岗位拥有的用户
     */
    private Set<User> users = new HashSet<User>();

    /**
     * 岗位与部门的多对一关系
     *
     * 字段: department 岗位所属部门
     */
    private Department department;
    /**
     * 岗位与权限的多对多关系
     *
     * 字段: privileges 岗位所拥有权限
     */
    private Set<Privilege> privileges;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // ================方法区========
    @Override
    public String toString() {
        return "主键：" + id + "，岗位名：" + name + "，描述：" + description + "，下属用户：" + "，所属部门：" + description + "，所有权限：" + privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (description != null ? !description.equals(role.description) : role.description != null) return false;
        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}