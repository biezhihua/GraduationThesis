package com.bzh.gt.service;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.Bed;

import java.util.List;

/**
 * Created by biezhihua on 14-9-13.
 */
public interface BedService extends DaoSupport<Bed> {

    /**
     * 根据宿舍ID获取床铺
     * @param id
     * @return
     */
    List<Bed> getByDormitory(Long id);

    Bed getbyStudentId(Long id);

    Bed getByApartmentAndDormitoryAndBed(String apartmentName, String dormitoryName, String bedNo);

    List<Bed> getByApartmentAndDormitory(Long apartmentId, Long dormitoryId);
}
