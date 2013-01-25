package com.spark.order.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import com.spark.common.utils.reflection.BeanUtils;

/**
 * <p>
 * ���ൽ֮������ת��
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-11-24
 */
public final class ReflectionUtil {
	private ReflectionUtil(){
	}

	/**
	 * ���������е�����COPY�������С� �ඨ����childһ��Ҫextends father��
	 * ����child��fatherһ��Ϊ�ϸ�javabeanд��������ΪdeleteDate������ΪgetDeleteDate
	 * 
	 * @throws Exception
	 *             public void fatherToChild (F father,C child)throws Exception{
	 *             if(!(child.getClass().getSuperclass()==father.getClass())){
	 *             throw new ClassCastException("child����father������"); } Class<?>
	 *             fatherClass= father.getClass(); Field ff[]=
	 *             fatherClass.getDeclaredFields(); for(int
	 *             i=0;i<ff.length;i++){ Field f=ff[i];//ȡ��ÿһ�����ԣ���deleteDate //
	 *             Class<?> type=f.getType(); Method
	 *             m=fatherClass.getMethod("get"
	 *             +upperHeadChar(f.getName()));//����getDeleteDate Object
	 *             obj=m.invoke(father);//ȡ������ֵ f.set(child,obj); } }
	 */

	/**
	 * ���������е�����COPY�������С� �ඨ����childһ��Ҫextends father��
	 * ����child��fatherһ��Ϊ�ϸ�javabeanд��������ΪdeleteDate������ΪgetDeleteDate
	 * 
	 * @param father
	 * @param child
	 *            void
	 */
	public static void fatherToChild(Object father, Object child) throws ClassCastException {
		if (!(child.getClass().getSuperclass() == father.getClass())) {
			throw new ClassCastException("child����father������");
		}
		try {
			BeanUtils.copyProperties(father, child);
		} catch (Exception e) {
			throw new ClassCastException();
		}
//		Field[] fields = father.getClass().getDeclaredFields();
//		for(Field f : fields){
//			String fieldName = f.getName();
//			invokeSet(child, fieldName, invokeGet(father, fieldName), f.getType());
//		}
	}
	
	/**
	 * �� ����1�е�����ֵ���������2��ͬ����ͬ���͵�������
	 * @param father
	 * @param child
	 *            void
	 */
	public static void Obj1ToObj2(Object obj1, Object obj2) throws ClassCastException {
		try {
			BeanUtils.copyProperties(obj1, obj2);
		} catch (Exception e) {
			throw new ClassCastException();
		}
//		Field[] fields = obj1.getClass().getDeclaredFields();
//		for(Field f : fields){
//			String fieldName = f.getName();
//			invokeSet(obj2, fieldName, invokeGet(obj1, fieldName), f.getType());
//		}
	}
	
	/**
	 * �� ����1�е�����ֵ���������2�С���Obj������ͬ����ͬ���͵�������
	 * @param father
	 * @param child
	 *            void
	 */
	public static void Obj1ToObj2_Obj(Object obj1, Object obj2, Class<?> obj){
//		BeanInfo beanInfo = Introspector.getBeanInfo(obj);
//		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
//		Method get = null;
//		Method set = null;
//		for (int i = 0; i < properties.length; i++) {
//			try {
//				get = obj1.getClass().getMethod(properties[i].getReadMethod().getName(), 
//						new Class[]{properties[i].getPropertyType()});
//				set = obj2.getClass().getMethod(properties[i].getWriteMethod().getName(),
//						new Class[] { properties[i].getPropertyType() });
//			} catch (Throwable a) {
//				continue;
//			}
//			if (set != null && get != null) {
//				set.invoke(obj2, new Object[] { get.invoke(obj1,
//						new Object[] {}) });
//				set = null;
//				get = null;
//			}
//		}
		Field[] fields = obj.getDeclaredFields();
		for(Field f : fields){
			String fieldName = f.getName();
			try {
				invokeSet(obj2, fieldName, invokeGet(obj1, fieldName), f.getType());
			} catch (Exception e) {
				continue;
				//e.printStackTrace();
			} 
		}
	}
	
	/**
	 * ����ֶ�����
	 * @param o
	 * @param fieldName
	 * @return Class<?>
	 */
	public static Class<?> getFieldType(Object o, String fieldName){
		try {
			Field field = o.getClass().getDeclaredField(fieldName);
			return field.getType();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * ִ��set����,�Զ���ȡ����
	 * 
	 * @param o
	 *            ִ�ж���
	 * @param fieldName
	 *            ����
	 * @param value
	 *            ֵ
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void invokeSet(Object o, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = getSetMethod(o.getClass(), fieldName, getFieldType(o, fieldName));
//		try {
		method.invoke(o, value);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * ִ��set����
	 * 
	 * @param o
	 *            ִ�ж���
	 * @param fieldName
	 *            ����
	 * @param value
	 *            ֵ
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void invokeSet(Object o, String fieldName, Object value, Class<?>...parameterTypes ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = getSetMethod(o.getClass(), fieldName, parameterTypes);
//		try {
		method.invoke(o, value);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * ִ��get����
	 * 
	 * @param o
	 *            ִ�ж���
	 * @param fieldName
	 *            ����
	 */
	public static Object invokeGet(Object o, String fieldName) {
		Method method = getGetMethod(o.getClass(), fieldName);
		try {
			return method.invoke(o, new Object[0]);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * java����bean��get����
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	public static Method getGetMethod(Class<?> objectClass, String fieldName) {
		// StringBuffer sb = new StringBuffer();
		// sb.append("get");
		// sb.append(fieldName.substring(0, 1).toUpperCase());
		// sb.append(fieldName.substring(1));
		try {
			return objectClass.getMethod("get" + upperHeadChar(fieldName));
		} catch (Exception e) {
			return null;
//			e.printStackTrace();
		}
	}

	/**
	 * java����bean��set����
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	public static Method getSetMethod(Class<?> objectClass, String fieldName, Class<?>... parameterTypes ) {
		try {
//			Class<?>[] parameterTypes = new Class[1];
//			Field field = objectClass.getDeclaredField(fieldName);
//			parameterTypes[0] = field.getType();
			// StringBuffer sb = new StringBuffer();
			// sb.append("set");
			// sb.append(fieldName.substring(0, 1).toUpperCase());
			// sb.append(fieldName.substring(1));
			Method method = objectClass.getMethod("set"
					+ upperHeadChar(fieldName), parameterTypes);
			return method;
		} catch (Exception e) {
			return null;
//			e.printStackTrace();
		}
	}

	/**
	 * ����ĸ��д��in:deleteDate��out:DeleteDate
	 */
	private static String upperHeadChar(String in) {
		String head = in.substring(0, 1);
		return head.toUpperCase(Locale.CHINESE) + in.substring(1, in.length());
	}
}
