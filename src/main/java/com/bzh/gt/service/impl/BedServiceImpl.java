package com.bzh.gt.service.impl;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Bed;
import com.bzh.gt.service.BedService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by biezhihua on 14-9-13.
 */

@Service
@Transactional
public class BedServiceImpl extends DaoSupportImpl<Bed> implements BedService {
    @Override
    public List<Bed> getByDormitory(Long id) {
        return getSession().createQuery("FROM Bed b WHERE b.dormitory.id=" + id).list();
    }

    @Override
    public Bed getbyStudentId(Long id) {
        return (Bed) getSession().createQuery("FROM Bed b WHERE b.student.id=" + id).uniqueResult();
    }

    @Override
    public Bed getByApartmentAndDormitoryAndBed(String apartmentName, String dormitoryName, String bedNo) {

        return (Bed) getSession().createQuery("FROM Bed b WHERE b.dormitory.apartment.name='" + apartmentName + "' AND b.dormitory.name=" + dormitoryName + " AND b.bedNO=" + bedNo).uniqueResult();
    }

    @Override
    public List<Bed> getByApartmentAndDormitory(Long apartmentId, Long dormitoryId) {
        return getSession().createQuery("FROM Bed b WHERE b.dormitory.apartment.id=" + apartmentId + " AND b.dormitory.id=" + dormitoryId + " ORDER BY b.bedNO ASC").list();
    }
}
