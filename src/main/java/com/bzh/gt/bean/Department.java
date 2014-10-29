package com.bzh.gt.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称 ： GraduationThesis-Department
 * 类描述 ： 部门
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月11日 下午8:07:22
 */

public class Department implements Serializable {

    private static final long serialVersionUID = -136154876402747571L;
    // ===============普通字段===========
    private Long id;
    /**
     * 字段: name 部门名称
     */
    private String name;
    /**
     * 字段: description 部门描述
     */
    private String description;


    // ===============关联关系============
    /**
     * 本部门与上级部门的多对一关系
     *
     * 字段: parent 父部门
     */
    private Department parent;

    /**
     * 本部门与下级部门的一对多关系
     *
     * 字段: childrens 子部门
     */
    private Set<Department> childrens = new HashSet<Department>();

    /**
     * 部门与岗位的一对多关系
     *
     * 字段: roles 部门所拥有的岗位
     */
    private Set<Role> roles = new HashSet<Role>();

    // ===============方法区==============
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

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public Set<Department> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<Department> childrens) {
        this.childrens = childrens;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

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
