package com.bzh.gt.service.impl;

import com.bzh.gt.bean.Clasz;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Apartment;
import com.bzh.gt.service.ApartmentService;

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
public class ApartmentServiceImpl extends DaoSupportImpl<Apartment> implements
        ApartmentService {

    /**
     * 根据传递进来的字段名，获取数据列
     */
    @Override
    public List<Object[]> getPartFieldInfo(String... fileds) {
        StringBuffer sb = new StringBuffer("SELECT ");
        for (String filed : fileds) {
            sb.append(filed + ",");
        }
        sb.append("FROM Apartment");
        sb.setCharAt(sb.lastIndexOf(","), ' ');
        Query query = getSession().createQuery(sb.toString());

        List<Object[]> partInfo = query.list();
        return partInfo;
    }

    /**
     * 获取公寓的最高层
     * <p/>
     * return
     */
    @Override
    public Integer getTopLevel() {
        Object o = getSession().createSQLQuery("SELECT MAX(LEVEL) FROM Dormitory").uniqueResult();
        return o == null ? null : Integer.valueOf(String.valueOf(o));
    }

    @Override
    public Clasz getByName(String className) {
        return (Clasz) getSession().createQuery("FROM Clasz c WHERE c.name like '" + className + "%'").uniqueResult();
    }
}
