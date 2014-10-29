package com.bzh.gt.service;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.bean.Apartment;
import com.bzh.gt.bean.Clasz;

import java.util.List;

/**
 * 项目名称 ： GraduationThesis-Apartment
 * 类描述 ： 公寓业务
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月26日 下午2:07:28
 * version ：
 */

public interface ApartmentService extends DaoSupport<Apartment> {

    List<Object[]> getPartFieldInfo(String... fileds);

    // 获取公寓的最高层
    Integer getTopLevel();

    /**
     * 根据班级名称获取班级实体，这里采用的是模糊匹配，
     * @param className
     * @return
     */
    Clasz getByName(String className);
}
