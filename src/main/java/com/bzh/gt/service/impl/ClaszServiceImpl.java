package com.bzh.gt.service.impl;

import com.bzh.gt.base.impl.DaoSupportImpl;
import com.bzh.gt.bean.Clasz;
import com.bzh.gt.service.ClaszService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by biezhihua on 14-9-21.
 */
@Service
@Transactional
public class ClaszServiceImpl extends DaoSupportImpl<Clasz> implements ClaszService {
    @Override
    public List<String> getGradeInfo() {
        return getSession().createQuery("SELECT DISTINCT(c.grade) FROM Clasz c").list();
    }

    @Override
    public List<Clasz> getGradeInClaszInfo(String grade) {
        return getSession().createQuery("FROM Clasz c where c.grade='" + grade + "' ORDER BY c.name ASC").list();
    }

    @Override
    public List<Clasz> getAllOrderByName() {
        return getSession().createQuery("FROM Clasz c ORDER BY c.name ASC").list();
    }

    @Override
    public List<Clasz> getAllByIsReservoir() {
        return  getSession().createQuery("FROM Clasz c WHERE c.isReservoir=true ORDER BY c.name ASC").list();
    }

    @Override
    public Clasz getByName(String claszName) {
        return (Clasz) getSession().createQuery("FROM Clasz c WHERE c.name="+claszName).uniqueResult();
    }


}
