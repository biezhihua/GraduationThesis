package com.bzh.gt.bean;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称 ： GraduationThesis-Clasz
 * 类描述 ： 班级
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月25日 下午8:31:16
 */

public class Clasz implements Serializable {

    private Long id;

    /**
     * 班级名称 例如:A1121
     */
    private String name;

    /**
     * 年级: 2011级
     */
    private String grade;

    /**
     * 班级人数
     */
    private Integer number;

    private Boolean isReservoir; // 是否待分配

    private Integer manReservoir; // 待分配的男生人数

    private Integer womanReservoir; // 待分配的女生人数


    public Clasz() {
    }

    public Clasz(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    // ==========关联关系=========
    /**
     * 班级所有的学生，班级与学生的一对多关系
     */
    private Set<Student> students = new HashSet<Student>();

    /**
     * 班级所有的宿舍，班级与宿舍的一对多关系
     */
    private Set<Dormitory> dormitories = new HashSet<Dormitory>();

    /**
     * 班主任
     */
    private Teacher teacher;

    /**
     * 班长
     *
     * @return
     */
    private Student monitor;

    private User user;
    // ==========方法区===========
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Dormitory> getDormitories() {
        return dormitories;
    }

    public void setDormitories(Set<Dormitory> dormitories) {
        this.dormitories = dormitories;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getNumber() {
        number = students.size();
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getMonitor() {
        return monitor;
    }

    public void setMonitor(Student monitor) {
        this.monitor = monitor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getManReservoir() {
        return manReservoir;
    }

    public void setManReservoir(Integer manReservoir) {
        this.manReservoir = manReservoir;
    }

    public Integer getWomanReservoir() {
        return womanReservoir;
    }

    public void setWomanReservoir(Integer womanReservoir) {
        this.womanReservoir = womanReservoir;
    }

    public Boolean getIsReservoir() {
        return isReservoir;
    }

    public void setIsReservoir(Boolean isReservoir) {
        this.isReservoir = isReservoir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clasz)) return false;

        Clasz clasz = (Clasz) o;

        if (grade != null ? !grade.equals(clasz.grade) : clasz.grade != null) return false;
        if (id != null ? !id.equals(clasz.id) : clasz.id != null) return false;
        if (name != null ? !name.equals(clasz.name) : clasz.name != null) return false;
        if (number != null ? !number.equals(clasz.number) : clasz.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
