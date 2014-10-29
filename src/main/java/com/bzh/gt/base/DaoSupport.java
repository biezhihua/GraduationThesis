package com.bzh.gt.base;

import java.util.List;

import com.bzh.gt.utils.QueryHelper;
import com.bzh.gt.vo.PageBean;

/**
 * 项目名称 ： GraduationThesis-DaoSupport
 * 类描述 ： 提供Dao接口
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月12日 下午4:04:33
 * version ：
 */

public interface DaoSupport<T> {

    /**
     * 概要: 将对象保存成实体
     * 参数: @param 对象
     */
    void save(T entity);

    void saveOrUpdate(T entity);

    /**
     * 概要: 根据主键ID删除一个实体
     * 参数: @param 对象
     */
    void delete(Long id);

    /**
     * 概要: 将对象更新到实体中
     * 参数: @param 被更新对象
     * 返回类型:
     */
    void update(T entity);

    /**
     * 概要: 根据主键ID查找一个实体
     * 参数: @param 主键ID
     * 返回类型: 实体
     */
    T getById(Long id);

    T loadById(Long id);

    /**
     * 概要: 根据主键ID数组查找一个实体数组
     * 参数: @param 主键ID数组
     * 返回类型: 实体数组
     */
    List<T> getByIds(Long[] ids);

    /**
     * 概要: 获取所有实体
     * 返回类型: 实体集合
     */
    List<T> getAll();

    /**
     * 概要:	查询带条件的分页方法
     * 参数:	currentPage 当前页	pageSize 记录条数	queryHelper 拼接SQL查询语句
     * 返回类型:
     */
    PageBean getPageBean(int currentPage, int pageSize, QueryHelper queryHelper);
}
