package com.spark.common.utils.character;

public class StringHelper {

	static final String Null_Str = "";

	/**
	 * 
	 */
	public static String toTrimStr(Object obj) {
		return toStr(obj).trim();
	}

	public static String toStr(Object obj) {
		if (obj == null)
			return Null_Str;
		if (obj instanceof Integer)
			return "" + obj;
		return obj.toString();
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String) {
			return com.jiuqi.util.StringHelper.isNull(String.valueOf(obj));
		}
		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return isEmpty(obj) ? false : true;
	}

	/**
	 * 对数字字符串进行加一处理
	 */
	public static String addOneInt(String str, int digits) {
		if (digits <= 0) {
			return null;
		}
		if (CheckIsNull.isEmpty(str)) {
			return ZEROSTR.substring(0, digits - 1) + "1";
		}
		Long value = 0l;
		try {
			value = Long.parseLong(str);
		} catch (NumberFormatException e) {
			System.err.println("要增1的字符串格式错误");
			return null;
		}
		value++;
		String s = "" + value;
		if (s.length() < digits) {
			return ZEROSTR.substring(0, digits - s.length()) + s;
		} else {
			return s;
		}
	}

	/**
	 * 对数字字符串进行加一处理
	 */
	public static String addOneInt(String str) {
		if (CheckIsNull.isEmpty(str)) {
			return null;
		}
		return addOneInt(str, str.length());
	}

	private static final String ZEROSTR = "00000000000000000000";
}
