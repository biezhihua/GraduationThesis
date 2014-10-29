package com.bzh.gt.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 项目名称 ： GraduationThesis-GenericsUtils
 * 类描述 ： 获取泛型的真实类型
 * 创建人 ： 别志华
 * 创建时间 ： 2014年7月12日 下午4:25:20
 */

public class GenericsUtils {

	/**
	 * 概要: 通过反射，获得定义clazz是声明父类的类型化参数的类型
	 * 参数: clazz 类
	 * 返回类型:返回类型化参数的真实类型
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 概要: 通过反射，获得定义clazz是声明父类的类型化参数的类型
	 * 参数: clazz 类
	 * 参数: index 参数下标
	 * 返回类型:返回类型化参数的真实类型
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
		// Type是什么？：Type 是 Java 编程语言中所有类型的公共高级接口。
		// 它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。

		// 返回表示此clazz所表示的实体（可以是类、接口、基本类型或void）的直接超类的Type
		Type genericType = clazz.getGenericSuperclass();

		// ParameterizedType是什么？：ParameterizedType 表示参数化类型，
		// 如 Collection<String>。参数化类型在反射方法首次需要时创建（在此包中指定）。

		// 如果不是参数化类型，则返回上帝类
		if (!(genericType instanceof ParameterizedType)) {
			return Object.class;
		}

		// 将参数类型强转成参数化类型
		ParameterizedType genParType = (ParameterizedType) genericType;

		// 返回表示此类型实际类型参数的 Type 对象的数组
		Type[] params = genParType.getActualTypeArguments();

		// 获取的参数下标是否超出边界
		if (index >= params.length || index < 0) {
			// 返回上帝类
			return Object.class;
		}

		// 获取的参数是否是一个类
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		// 返回类型化参数的真实类型
		return (Class<?>) params[index];
	}
}
