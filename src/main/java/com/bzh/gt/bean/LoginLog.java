package com.bzh.gt.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称 ： GraduationThesis-LoginLog
 * 类描述 ： 登录退出日志
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月11日 下午8:24:30
 * version ：
 */

public class LoginLog implements Serializable {

    private static final long serialVersionUID = -961717127032534457L;

    // =============普通字段============
    private Long id;

    /**
     * 字段: ip 登录IP
     */
    private String ip;

    /**
     * 字段: logindate 登录时间
     */
    private Date loginDate;

    /**
     * 字段: logoutDate 退出时间
     */
    private Date logoutDate;

    // ================关联关系==============

    /**
     * 登录退出日志与用户的多对一关系
     * <p/>
     * 字段: user 所属用户
     */
    private User user;

    // ================方法区=================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginLog)) return false;

        LoginLog loginLog = (LoginLog) o;

        if (id != null ? !id.equals(loginLog.id) : loginLog.id != null) return false;
        if (ip != null ? !ip.equals(loginLog.ip) : loginLog.ip != null) return false;
        if (loginDate != null ? !loginDate.equals(loginLog.loginDate) : loginLog.loginDate != null) return false;
        if (logoutDate != null ? !logoutDate.equals(loginLog.logoutDate) : loginLog.logoutDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (loginDate != null ? loginDate.hashCode() : 0);
        result = 31 * result + (logoutDate != null ? logoutDate.hashCode() : 0);
        return result;
    }
}
