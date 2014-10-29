package com.bzh.gt.service;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.Clasz;

import java.util.List;

/**
 * Created by biezhihua on 14-9-21.
 */
public interface ClaszService extends DaoSupport<Clasz> {
    /**
     * 获取年级信息
     * @return
     */
    List<String> getGradeInfo();

    /**
     * 根据年级，获取年级下班级的信息
     * @param grade
     * @return
     */
    List<Clasz> getGradeInClaszInfo(String grade);

    List<Clasz> getAllOrderByName();

    List<Clasz> getAllByIsReservoir();

    Clasz getByName(String claszName);
}
