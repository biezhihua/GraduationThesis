package com.bzh.gt.bean;

import java.io.Serializable;

/**
 * Created by biezhihua on 14-9-21.
 */
public class Teacher implements  Serializable {

    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String phoneNumber;


    // ===========关联关系==========
    /**
     * 负责的班级
     */
    private Clasz clasz;


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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Clasz getClasz() {
        return clasz;
    }

    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;

        Teacher teacher = (Teacher) o;

        if (id != null ? !id.equals(teacher.id) : teacher.id != null) return false;
        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(teacher.phoneNumber) : teacher.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
