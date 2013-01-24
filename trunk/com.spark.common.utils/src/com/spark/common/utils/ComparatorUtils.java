package com.spark.common.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.PinyinHelper;

/**
 * ���򹤾���
 * 
 */
public class ComparatorUtils {

	private ComparatorUtils() {
	}

	/**
	 * �б���������
	 * @param <T>
	 * @param list     		 ��Ҫ������б�,list��������Jdk���������ͣ����list����ŵ����Զ�������������������
	 */
	public static <T> void sort(List<T> list) {
		sort(list, false);
	}
	
	/**
	 * �б�����
	 * @param <T>
	 * @param list     		 ��Ҫ������б�,list��������Jdk���������ͣ����list����ŵ����Զ�������������������
	 * @param howCompare			 ����ʽ��falseΪ����trueΪ����
	 */
	public static <T> void sort(List<T> list,boolean howCompare) {
		sort(list, null , howCompare, false);
	}
	
	/**
	 * �б���������
	 * @param <T>
	 * @param list     		 ��Ҫ������б�
	 * @param sortProperty	 ������������ƣ����Ҫ����Ĳ����Զ����������null
	 */
	public static <T> void sort(List<T> list,String sortProperty) {
		sort(list, sortProperty , false, false);
	}
	
	/**
	 * �б�����	
	 * @param <T>
	 * @param list     		 ��Ҫ������б�
	 * @param sortProperty	 ������������ƣ����Ҫ����Ĳ����Զ����������null
	 * @param howCompare	 ����ʽ��falseΪ����trueΪ����
	 */
	public static <T> void sort(List<T> list,String sortProperty,boolean howCompare) {
		sort(list, sortProperty, howCompare,false);
	}
	
	/**
	 * �б�����	
	 * @param <T>
	 * @param list     		 ��Ҫ������б�
	 * @param sortProperty	 ������������ƣ����Ҫ����Ĳ����Զ����������null
	 * @param howCompare	 ����ʽ��falseΪ����trueΪ����
	 * @param comparaByPY	 �Ƿ��պ��ֵ�ƴ������
	 */
	public static <T> void sort(List<T> list,String sortProperty,boolean howCompare,boolean comparaByPY) {
		Collections.sort(list, getComparator(sortProperty, howCompare,comparaByPY));
	}
	
	/**
	 * ͨ�����򷽷�(�������)
	 * 
	 * @param name
	 *            ������������ƣ����Ҫ����Ĳ����Զ����������null
	 * @param howCompare
	 *            ����ʽ��falseΪ����trueΪ����Ĭ��Ϊ���򣬴���nullΪ����
	 * @param comparaByPY �Ƿ��պ��ֵ�ƴ������
	 * @return
	 */
	public static Comparator<Object> getComparator(final String name,
			final Boolean howCompare, final boolean comparaByPY) {
		return new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				int result = 0;
				Object value1 = null;
				Object value2 = null;
				if (name != null && name.length() > 0) {
					String letter = name.substring(0, 1).toUpperCase();
					String methodName = "get".concat(letter).concat(name.substring(1));
					value1 = getMethodValue(o1, methodName);
					value2 = getMethodValue(o2, methodName);
				} else {
					value1 = o1;
					value2 = o2;
				}
				if (value1 == null || value2 == null) {
					return result;
				}
				if (value1 instanceof String && value2 instanceof String) {
					String str1 = (String) value1;
					String str2 = (String) value2;
					if(comparaByPY) {
						result = compareString(str1, str2);
					} else {
						result = str1.compareTo(str2);
					}
				} else if (value1 instanceof Integer
						&& value2 instanceof Integer) {
					Integer int1 = (Integer) value1;
					Integer int2 = (Integer) value2;
					if (int1.intValue() > int2.intValue()) {
						result = 1;
					} else if (int1.intValue() < int2.intValue()) {
						result = -1;
					}
				} else if (value1 instanceof Long && value2 instanceof Long) {
					Long long1 = (Long) value1;
					Long long2 = (Long) value2;
					if (long1.longValue() > long2.longValue()) {
						result = 1;
					} else if (long1.longValue() < long2.longValue()) {
						result = -1;
					}
				} else if (value1 instanceof Float && value2 instanceof Float) {
					Float float1 = (Float) value1;
					Float float2 = (Float) value2;
					if (float1.floatValue() > float2.floatValue()) {
						result = 1;
					} else if (float1.floatValue() < float2.floatValue()) {
						result = -1;
					}
				} else if (value1 instanceof Double && value2 instanceof Double) {
					Double double1 = (Double) value1;
					Double double2 = (Double) value2;
					if (double1.doubleValue() > double2.doubleValue()) {
						result = 1;
					} else if (double1.doubleValue() < double2.doubleValue()) {
						result = -1;
					}
				} else if (value1 instanceof Date && value2 instanceof Date) {
					Date date1 = (Date) value1;
					Date date2 = (Date) value2;
					result = date1.compareTo(date2);
				} else if (value1 instanceof BigDecimal
						&& value2 instanceof BigDecimal) {
					BigDecimal big1 = (BigDecimal) value1;
					BigDecimal big2 = (BigDecimal) value2;
					result = big1.compareTo(big2);
				} else {
					 result = value1.toString().compareTo(value2.toString());
				 }

				if (howCompare != null && howCompare) {
					result = -result;
				}
				return result;
			}
		};
	}

	/**
	 * ���ֵ�����
	 * @param s1
	 * @param s2
	 * @return
	 */
	private static int compareString(String s1, String s2) {
		if(CheckIsNull.isEmpty(s1) || CheckIsNull.isEmpty(s2)) {
			return 0;
		}
		//�õ�ƴ������ĸ
		String s1py = PinyinHelper.getLetter(s1, true);
		String s2py = PinyinHelper.getLetter(s2, true);
		char c1 = s1py.charAt(0);
		char c2 = s2py.charAt(0);
		if (c1 > c2)
			return 1;
		else if (c1 < c2)
			return -1;
		else
			return 0;
	}
	
	private static Object getMethodValue(Object o, String methodName) {
		Object object = null;
		if (o == null) {
			return object;
		}
		try {
			Method method = o.getClass().getDeclaredMethod(methodName);
			object = method.invoke(o);
		} catch (Exception e) {
			System.out.println("�÷���������:" + methodName);
		}
		return object;
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("����");
		list.add("����");
		list.add("�Ϻ�");
		list.add("����");
		list.add("����");
		list.add("�人");
		list.add("���");
		list.add("����");
		
		sort(list,false);
		
		for (String string : list) {
			System.out.println(string);
		}
	}
}
