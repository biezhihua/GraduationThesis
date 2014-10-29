package com.bzh.gt.base.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.bzh.gt.base.DaoSupport;
import com.bzh.gt.utils.GenericsUtils;
import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;

/**
 * 项目名称 ： GraduationThesis-DaoSupportImpl
 * 类描述 ： 一般DAO操作实现类
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月12日 下午4:11:38
 */
@SuppressWarnings("unchecked")
// 对DAO操作进行事务管理，具有继承性
@Transactional
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

    static Logger logger = Logger.getLogger(DaoSupportImpl.class.getName());

    /**
     * 字段:使用注解对SessionFactory进行注入
     */
    @Resource
    private SessionFactory sessionFactory;

    /**
     * 字段:泛型T的真实类型
     */
    private Class<T> clazz;

    /**
     * 构造函数: 通过反射技术得到T的真实类型
     */
    public DaoSupportImpl() {

        // 默认获取第一个参数化类型的真实类型
        this.clazz = (Class<T>) GenericsUtils//
                .getSuperClassGenricType(this.getClass());
        logger.info(clazz.getName() + "类已获取参数化类型的真是类型:" + this.clazz);
    }

    /**
     * 概要:帮助继承类方便的获取Session
     * 返回类型: Session
     */
    protected Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
        logger.info(clazz.getName() + "类已保存实体:" + entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) {
        Object obj = getById(id);
        if (obj != null) {
            getSession().delete(obj);
            logger.info(clazz.getName() + "类已删除实体:" + obj);
        }
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
        logger.info(clazz.getName() + "类已更新实体:" + entity);
    }

    @Override
    public T getById(Long id) {
        if (id == null) {
            return null;
        }
        logger.info(clazz.getName() + "类已根据:" + id + "获取实体");
        return (T) getSession().get(clazz, id);
    }

    @Override
    public T loadById(Long id) {
        if (id == null) {
            return null;
        }
        logger.info(clazz.getName() + "类已根据:" + id + "获取实体");
        return (T) getSession().load(clazz, id);
    }

    @Override
    public List<T> getByIds(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }
        logger.info(clazz.getName() + "类已根据:" + ids + "获取实体");
        return getSession().createQuery(//
                "FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
                .setParameterList("ids", ids)//
                .list();
    }

    @Override
    public List<T> getAll() {
        logger.info("已获取" + clazz.getName() + "类的所有实体");
        return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public PageBean getPageBean(int currentPage, int pageSize,
                                QueryHelper queryHelper) {
        // 根据查询语句获取Query对象
        Query listQuery = getSession().createQuery(
                queryHelper.getListQueryHQL());
        // 获取查询语句对应的参数列表
        List<Object> parameters = queryHelper.getParameters();
        // 设置参数
        if (parameters != null) {
            int count = parameters.size();
            for (int i = 0; i < count; i++) {
                listQuery.setParameter(i, parameters.get(i));
            }
        }
        // 设置分页数据
        listQuery.setFirstResult((currentPage - 1) * pageSize); // 起点
        listQuery.setMaxResults(pageSize); // 每页记录数
        // 查询分页后的结果数据
        List recordList = listQuery.list();

        // 查询记录总量
        Query countQuery = getSession().createQuery(
                queryHelper.getCountQueryHQL());
        // 设置参数
        if (parameters != null) {
            int count = parameters.size();
            for (int i = 0; i < count; i++) {
                countQuery.setParameter(i, parameters.get(i));
            }
        }
        // 执行查询
        Long recordCount = (Long) countQuery.uniqueResult();

        return new PageBean(currentPage, pageSize, recordCount.intValue(),
                recordList);
    }

    // ========Getter Setter 区=========================
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

}
