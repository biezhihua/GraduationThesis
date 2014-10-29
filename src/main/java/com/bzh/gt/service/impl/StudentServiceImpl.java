package com.bzh.gt.service.impl;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Apartment;
import com.bzh.gt.bean.Clasz;
import com.bzh.gt.bean.Student;
import com.bzh.gt.service.ApartmentService;
import com.bzh.gt.service.StudentService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * version ：
 * 项目名称 ： GraduationThesis-ApartmentServiceImpl
 * 类描述 ： 公寓业务的具体实现
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月26日 下午2:11:11
 */

@Service
@Transactional
public class StudentServiceImpl extends DaoSupportImpl<Student> implements
        StudentService {

    @Override
    public List getByClaszId(Long id) {
        if (id == null) {
            return null;
        }
        return getSession().createQuery("FROM Student s WHERE s.clasz.id=" + id).list();
    }

    @Override
    public Student getByClassAndName(Long id, String studentName) {
        if (id == null || studentName == null || "".equals(studentName)) {
            return null;
        }
        return (Student) getSession().createQuery("FROM Student s WHERE s.clasz.id=" + id + " AND s.name='" + studentName+"'").uniqueResult();
    }
}
