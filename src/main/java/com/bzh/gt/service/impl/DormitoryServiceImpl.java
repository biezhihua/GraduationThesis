package com.bzh.gt.service.impl;

import com.bzh.gt.bean.Bed;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Dormitory;
import com.bzh.gt.service.DormitoryService;

import java.util.List;

/**
 * 项目名称 ： GraduationThesis-DormitoryServiceImpl
 * 类描述 ： 宿舍的业务逻辑实现层
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月26日 下午2:50:08
 */

@Service
@Transactional
public class DormitoryServiceImpl extends DaoSupportImpl<Dormitory> implements
        DormitoryService {

    @Override
    public Bed getBedByBedNo(Long id, int i) {

        return (Bed) getSession().createQuery("FROM Bed b WHERE b.dormitory.id=" + id + " and b.bedNO=" + i).uniqueResult();
    }

    @Override
    public Dormitory getByDorNameAndByApartmentName(String apartmentName, String dormitoryName) {
        if (apartmentName == null || dormitoryName == null || "".equals(apartmentName) || "".equals(dormitoryName)) {
            return null;
        }
        return (Dormitory) getSession().createQuery("FROM Dormitory d WHERE d.apartment.name like '" + apartmentName + "%' AND d.name=" + dormitoryName).uniqueResult();
    }

    @Override
    public List<Dormitory> getByApartmentId(Long apartmentId) {
        return getSession().createQuery("FROM Dormitory d WHERE d.apartment.id=" + apartmentId).list();
    }

    @Override
    public List<Dormitory> getByApartmentIdAndFloor(Long id, int level) {
        return getSession().createQuery("FROM Dormitory d WHERE d.apartment.id=" + id + "AND d.level=" + level + " ORDER BY d.name ASC").list();
    }
}
