package com.bzh.gt.bean;


import java.io.Serializable;

/**
 * version ：
 * 项目名称 ： GraduationThesis-Student
 * 类描述 ： 学生信息
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月25日 下午8:32:29
 */

public class Student  implements Serializable {

    private Long id;
    /**
     * 字段:学生姓名
     */
    private String name;

    /**
     * 字段:性别
     */
    private String sex;

    /**
     * 字段: 学号
     */
    private String sno;

    /**
     * 字段: 手机号
     */
    private String phoneNumber;

    public Student() {
    }


    // =========关联关系==========
    /**
     * 字段:学生所属班级，学生与班级的多对一关系
     */
    private Clasz clasz;

    /**
     * 字段:学生所属床铺，学生与床铺的一对一关系
     */
    private Bed bed;

    /**
     * 寝室长与寝室的一对一关系
     */
    private Dormitory dormitory;

    // -========方法区=======
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(student.phoneNumber) : student.phoneNumber != null) return false;
        if (sex != null ? !sex.equals(student.sex) : student.sex != null) return false;
        if (sno != null ? !sno.equals(student.sno) : student.sno != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (sno != null ? sno.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
