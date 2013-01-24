package com.spark.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ʵ��ȽϹ���
 * 
 * @author zhangyu
 * 
 */
public class EntityCompareUtil {

	/**
	 * �Ƚ����������Ƿ����
	 * 
	 * @param src
	 *            Դ����
	 * @param tar
	 *            Ŀ�����
	 * @return
	 */
	public static boolean compare(Object src, Object tar) {
		return compare(src, tar, null);
	}

	/**
	 * �Ƚ����������Ƿ����<br>
	 * ���ݹ�һ��Ƚ�
	 * 
	 * @param src
	 *            Դ����
	 * @param tar
	 *            Ŀ�����
	 * @param ignoreFilds
	 *            ���Եĳ�Ա����
	 * @return
	 */
	public static boolean compare(Object src, Object tar, String[] ignoreFilds) {
		return compare(src, tar, ignoreFilds, false);
	}

	/**
	 * �Ƚ����������Ƿ����<br>
	 * ���ݹ�һ��Ƚ�
	 * 
	 * @param src
	 *            Դ����
	 * @param tar
	 *            Ŀ�����
	 * @param ignoreFilds
	 *            ��Ա����
	 * @param need
	 *            ����/��Ҫ Ĭ��false
	 * 
	 * @return
	 */
	public static boolean compare(Object src, Object tar, String[] filds,
			boolean need) {
		if (src == null || tar == null) {
			return src==tar; 
//			throw new IllegalArgumentException("��������Ϊ�� ");
		} else if (src.getClass() != tar.getClass()) {
			throw new IllegalArgumentException("�������Ͳ�ͬ");
		}

		if (isBaseObject(src)) {
			return src.equals(tar);
		} else if (src instanceof Object[]) {
			return campareArray((Object[]) src, (Object[]) tar);
		} else {
			return campareObject(src, tar, filds, need);
		}
	}

	/**
	 * �Ƿ�Java��������
	 * 
	 * @param obj
	 * @return
	 */
	private static boolean isBaseObject(Object obj) {
		boolean flag = false;
		if (obj instanceof Integer) {
			flag = true;
		} else if (obj instanceof Float) {
			flag = true;
		} else if (obj instanceof Double) {
			flag = true;
		} else if (obj instanceof Long) {
			flag = true;
		} else if (obj instanceof String) {
			flag = true;
		} else if (obj instanceof Short) {
			flag = true;
		} else if (obj instanceof Byte) {
			flag = true;
		} else if (obj instanceof Character) {
			flag = true;
		}
		return flag;
	}

	/**
	 * �������Ƿ����ָ��ֵ
	 * 
	 * @param array
	 * @param str
	 * @return
	 */
	private static boolean contains(String[] array, String str) {
		for (int j = 0; j < array.length; j++) {
			if (str.equals(array[j])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �Ƚ�����
	 * 
	 * @param srcArr
	 * @param tarArr
	 * @return
	 */
	private static boolean campareArray(Object[] srcArr, Object[] tarArr) {
		if (srcArr.length != tarArr.length) {
			return false;
		} else {
			boolean flag = true;
			for (int i = 0; i < srcArr.length && flag; i++) {
				Object srcObj = srcArr[i];
				Object tarObj = tarArr[i];
				flag &= compare(srcObj, tarObj);
			}
			return flag;
		}

	}

	/**
	 * �Ƚ϶���
	 * 
	 * @param src
	 * @param tar
	 * @param ignoreFilds
	 * @return
	 */
	private static boolean campareObject(Object src, Object tar,
			String[] ignoreFilds, boolean need) {
		Field[] fields = getClassFields(src.getClass());
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (ignoreFilds != null) {
				String fieldName = field.getName();
				if (need) {
					if (!contains(ignoreFilds, fieldName)) {
						continue;
					}
				} else {
					if (contains(ignoreFilds, fieldName)) {
						continue;
					}
				}
			}
			field.setAccessible(true);
			try {
				Object srcValue = field.get(src);
				Object tarValue = field.get(tar);
				if (srcValue == null) {
					if (tarValue != null && !tarValue.equals("")) {
						return false;
					}
				} else {
					if (!internalCompare(srcValue, tarValue)) {
						return false;
					}
				}
			} catch (IllegalArgumentException e) {
				return false;
			} catch (IllegalAccessException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * �ڲ��Ƚϣ����Ƚϳ������ͣ������ٵݹ�Ƚϣ��Է�ֹѭ��������ɵ���ѭ��
	 * 
	 * @param src
	 * @param tar
	 * @return
	 */
	private static boolean internalCompare(Object src, Object tar) {
		if (isBaseObject(src)) {
			return src.equals(tar);
		} else if (src instanceof Object[]) {
			return campareArray((Object[]) src, (Object[]) tar);
		} else {
			return src.equals(tar);
		}
	}

	/**
	 * �ݹ�ȡ�õ�ǰ�༰�������г�Ա
	 * 
	 * @param clazz
	 * @return
	 */
	private static Field[] getClassFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Class<?> superClazz = clazz;
		while (superClazz != Object.class) {
			Field[] fields = superClazz.getDeclaredFields();
			for (Field field : fields) {
				list.add(field);
			}
			superClazz = superClazz.getSuperclass();
		}

		return list.toArray(new Field[list.size()]);
	}

	/**
	 * ���Դ���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TestEntity src = new TestEntityExt();
		src.setA(10);
		TestEntity tar = new TestEntityExt();
		tar.setA(15);
		// , new String[] { "a" }
		System.out.println(EntityCompareUtil.compare(src, tar,
				new String[] { "a" }));
	}

	private static class TestEntityExt extends TestEntity {
		private int e;

		@SuppressWarnings("unused")
		public void setE(int e) {
			this.e = e;
		}

		@SuppressWarnings("unused")
		public int getE() {
			return e;
		}

	}

	/**
	 * ������
	 * 
	 * @author zhangyu
	 * 
	 */
	private static class TestEntity {
		private int a;
		private String b;
		private float c;
		private double d;

		@SuppressWarnings("unused")
		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		@SuppressWarnings("unused")
		public String getB() {
			return b;
		}

		@SuppressWarnings("unused")
		public void setB(String b) {
			this.b = b;
		}

		@SuppressWarnings("unused")
		public float getC() {
			return c;
		}

		@SuppressWarnings("unused")
		public void setC(float c) {
			this.c = c;
		}

		@SuppressWarnings("unused")
		public double getD() {
			return d;
		}

		@SuppressWarnings("unused")
		public void setD(double d) {
			this.d = d;
		}
	}
}
