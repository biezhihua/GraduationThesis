package com.bzh.gt.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bzh.gt.bean.Department;

/**
 * 项目名称 ： GraduationThesis-DepartmentUtil
 * 类描述 ： 部门工具类
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月16日 下午4:29:49
 */

public class DepartmentUtil {

	/**    
	 * 字段:通过jQuery插件
	 */    
	public static final boolean AUTO = true;
	
	/**    
	 * 字段:手动创建树状部门
	 */    
	public static final boolean MANUAL = false;
	/**
	 * 概要:根据传递进来的顶级部门列表，进行递归遍历将其“制作”成树状列表
	 * 参数:顶级部门列表
	 * 返回类型:树状列表
	 */
	public static List<Department> getListTree(List<Department> topList,
			boolean auto) {
		// 1，创建空集合，为了存放部门列表的副本，如果直接该种topList可能会在后期操作中
		// 无意修改掉数据。
		List<Department> listTree = new ArrayList<Department>();
		// 2，递归遍历
		if (auto) {
			walkTreeList(topList, "", listTree, auto);
		} else {
			walkTreeList(topList, "┣", listTree, auto);
		}

		return listTree;
	}

	/**
	 * param auto
	 * 概要: 递归遍历顶级部门列表
	 * 参数: topList:顶级部门列表，prefix:树状前缀，listTree:copy列表
	 * 返回类型:
	 */
	public static void walkTreeList(Collection<Department> topList,
			String prefix, List<Department> listTree, boolean auto) {
		if (auto) {
			for (Department top : topList) {
                top.setName(prefix+top.getName());
				listTree.add(top);
				walkTreeList(top.getChildrens(), "　　" + prefix, listTree, auto);
			}
		} else {
			// 1，循环顶级部门列表
			for (Department top : topList) { // 如果topList集合为空会直接跳过
				// 1.1 拷贝副本，不直接修改部门信息
				// Department copy = top; 错误的形式，这样只是把top的引用交给了copy，还是同一个对象
				Department copy = new Department();
				// 1.2 设置树状列表的必要信息(ID,名称)
				copy.setId(top.getId());
				copy.setName(prefix + top.getName());
				// 1.3 添加到拷贝集合
				listTree.add(copy);

				// 2，递归遍历子部门列列表
				walkTreeList(top.getChildrens(), "　" + prefix, listTree, auto);
			}
		}
	}
}
