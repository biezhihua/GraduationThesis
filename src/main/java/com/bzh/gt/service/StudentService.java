package com.bzh.gt.service;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.Apartment;
import com.bzh.gt.bean.Clasz;
import com.bzh.gt.bean.Student;

import java.util.List;

public interface StudentService extends DaoSupport<Student> {

    /**
     * 根据班级ID获取班级中的学生
     *
     * @param id
     * @return
     */
    List getByClaszId(Long id);

    /**
     * 根据班级ID和学生姓名查找学生
     *
     * @param id
     * @param studentName
     * @return
     */
    Student getByClassAndName(Long id, String studentName);
}
