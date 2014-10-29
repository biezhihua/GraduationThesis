package com.bzh.gt.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称 ： GraduationThesis-Privilege
 * 类描述 ： 权限
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月11日 下午8:24:12
 * version ：
 */

public class Privilege implements Serializable {

	private static final long serialVersionUID = -7916756844462205568L;

	// ============普通字段==============
	private Long id;

	/**
	 * 
	 * 字段: name 权限名称
	 */
	private String name;

	/**
	 * 
	 * 字段: url 权限URL地址
	 */
	private String url;

	// =============关联关系==============
	/**
	 * 本权限与父权限的多对一关系
	 * 
	 * 字段: parent 父权限
	 */
	private Privilege parent;

	/**
	 * 本权限与子权限的一对多关系
	 * 
	 * 字段: children's 子权限
	 */
	private Set<Privilege> childrens = new HashSet<Privilege>();

	/**
	 * 本Url权限与岗位的多对多关系
	 * 
	 * 字段: roles 所属的岗位
	 */
	private Set<Role> roles;

	public Privilege(String name, String url, Privilege parent) {
		this.name = name;
		this.url = url;
		this.parent = parent;
	}

	public Privilege() {
	}

	// ==============方法区===============
	@Override
	public String toString() {
		return "name:"+name+",url:"+url;
	}
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Privilege> childrens) {
		this.childrens = childrens;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Privilege)) return false;

        Privilege privilege = (Privilege) o;

        if (id != null ? !id.equals(privilege.id) : privilege.id != null) return false;
        if (name != null ? !name.equals(privilege.name) : privilege.name != null) return false;
        if (url != null ? !url.equals(privilege.url) : privilege.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
