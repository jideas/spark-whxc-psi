package com.spark.common.utils.reflection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 复制一个对象的属性值到另外一个对象，只要两个对象的属性名称一样，就满足复制条件
 * 
 * 
 */
public class BeanUtils {

	/**
	 * 使用方法：Object o = BeanUtils.copyProperties(object,Object.class);
	 * @param dest  源对象
	 * @param orig	目标对象类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T copyProperties(Object dest, Class<T> orig) throws Exception {
		T o = orig.newInstance();
		copyProperties(dest, o);
		return o;
	}

	/**
	 * 使用方法：BeanUtils.copyProperties(object,new Object());
	 * @param dest  源对象
	 * @param orig	目标对象
	 * @return
	 * @throws Exception
	 */
	public static <T> void copyProperties(Object dest, T orig) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(dest.getClass());
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		Method get = null;
		Method set = null;
		for (int i = 0; i < properties.length; i++) {
			try {
				get = properties[i].getReadMethod();
				set = orig.getClass().getMethod(
						properties[i].getWriteMethod().getName(),
						new Class[] { properties[i].getPropertyType() });
			} catch (Throwable a) {
				continue;
			}
			if (set != null && get != null) {
				set.invoke(orig, new Object[] { get.invoke(dest,
						new Object[] {}) });
				set = null;
				get = null;
			}
		}
	}
	
	/**
	 * 使用方法：BeanUtils.copyProperties(object,new Object());
	 * @param dest  源对象
	 * @param orig	目标对象
	 * @return
	 * @throws Exception
	 */
	public static <T> void copyObject(T dest, T orig) throws Exception {
		Method get = null;
		Method set = null;
		Class<?> c = dest.getClass();
		while (Object.class != c) {
			BeanInfo beanInfo = Introspector.getBeanInfo(c);
			PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < properties.length; i++) {
				try {
					get = properties[i].getReadMethod();
					set = orig.getClass().getMethod(
							properties[i].getWriteMethod().getName(),
							new Class[] { properties[i].getPropertyType() });
				} catch (Throwable a) {
					continue;
				}
				if (set != null && get != null) {
					set.invoke(orig, new Object[] { get.invoke(dest,
							new Object[] {}) });
					set = null;
					get = null;
				}
			}
			c = c.getSuperclass();
		}
	}
}