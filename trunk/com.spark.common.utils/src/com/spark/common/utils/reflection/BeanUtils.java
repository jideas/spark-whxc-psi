package com.spark.common.utils.reflection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * ����һ�����������ֵ������һ������ֻҪ�����������������һ���������㸴������
 * 
 * 
 */
public class BeanUtils {

	/**
	 * ʹ�÷�����Object o = BeanUtils.copyProperties(object,Object.class);
	 * @param dest  Դ����
	 * @param orig	Ŀ���������
	 * @return
	 * @throws Exception
	 */
	public static <T> T copyProperties(Object dest, Class<T> orig) throws Exception {
		T o = orig.newInstance();
		copyProperties(dest, o);
		return o;
	}

	/**
	 * ʹ�÷�����BeanUtils.copyProperties(object,new Object());
	 * @param dest  Դ����
	 * @param orig	Ŀ�����
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
	 * ʹ�÷�����BeanUtils.copyProperties(object,new Object());
	 * @param dest  Դ����
	 * @param orig	Ŀ�����
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