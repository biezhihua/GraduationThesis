package com.bzh.gt.service;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.Bed;
import com.bzh.gt.bean.Dormitory;

import java.util.List;

/**
 * @version ：
 * @项目名称 ： GraduationThesis-DormitoryService
 * @类描述 ： 宿舍的业务层接口
 * @创建人 ： 别志华
 * @创建时间 ： 2014年7月26日 下午2:49:06
 */

public interface DormitoryService extends DaoSupport<Dormitory> {

    /**
     * 查找宿舍下指定的床铺
     *
     * @param id
     * @param i
     * @return
     */
    Bed getBedByBedNo(Long id, int i);

    /**
     * 根据公寓名称和寝室名称查找寝室
     *
     * @param apartmentName
     * @param dormitoryName
     * @return
     */
    Dormitory getByDorNameAndByApartmentName(String apartmentName, String dormitoryName);

    List<Dormitory> getByApartmentId(Long apartmentId);

    /**
     * 根据公寓ID和层数拿到寝室集合
     * @return
     * @param id
     * @param level
     */
    List<Dormitory> getByApartmentIdAndFloor(Long id, int level);
}
