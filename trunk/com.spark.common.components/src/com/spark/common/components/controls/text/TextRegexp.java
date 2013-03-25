package com.spark.common.components.controls.text;

public class TextRegexp {

	/**
	 * 最多几位小数的正则表达式
	 * 
	 * @param scale
	 * @return String
	 */
	public static String getReg(final int scale) {
		if (5 < scale) {
			return null;
		} else if (0 == scale) {
			return "^\\d{1,15}$";
		}
		int length = 16 - scale;
		return "^\\d{1," + length + "}(\\.\\d{0," + scale + "})?$";
	}

	/**
	 * 全局静态只能输入两位小数的正浮点数对象<br>
	 * 
	 */
	public static final String POSITIVE_DOUBLE = "^\\d*(\\.)?(\\d)?(\\d)?$";

	public static final String POSITIVE_DOUBLE_FUSHU_LIMIT = "^([+-]?\\d{0,15}(\\.\\d{0,2})?)$";

	/**
	 * 全局静态固话和传真验证 接受格式 XXXX-XXXX-XXX 其中XXXX代表数字，但不限长度
	 */
	public static final String PHONE = "(^\\d*)([+-]?)(\\d*)([+-]?)(\\d*$)";

	/**
	 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为8位，小数部分最长为2位<br>
	 */
	public static final String NUMBER_TEN_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * 折扣额等5（2）0%-100%
	 */
	public static final String NUMBER_FIVE_TWO = "(^(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?$)|(^[1][0][0]$)";

	/**
	 * 全局静态邮编<br>
	 * 
	 */
	public static final String POSTCODE = "^[0-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

	/**
	 * 全局静态手机<br>
	 * 
	 */
	public static final String MOBILE = "^[1-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

	/**
	 * 全局静态只能输入五位整数两位小数的正浮点数对象<br>
	 * 
	 */
	public static final String POSITIVE_FIVE_DOUBLE = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	public static final String NUMBER_ONE_FOUR = "(^[0]?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为8位，小数部分最长为2位<br>
	 */
	public static final String NUMBER_EIGHT_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * 全局静态只能输入数字(正整数)
	 */
	public static final String NUMBER = "^[0-9]+$";
	/**
	 * 全局静态只能输入数字(整数)
	 */
	public static final String ALLNUMBER = "^-?\\d+$";

	/**
	 * 全局静态只能输入数字和字母
	 */
	public static final String ENGLISH_AND_NUMBER = "^[A-Za-z0-9]+$";
	/**
	 * 全局静态只能输入时间格式
	 */
	public static final String TIEM_FORAMT = "^([0-9]?[0-9]?(\\:)?[0-5]?[0-9]$)?";
	/**
	 * 全局静态邮箱校验
	 */
	// public static final String Mail = "";
	// public static final String Mail="^[A-Za-z0-9_]@[A-Za-z].[A-Za-z]+$";

	/**
	 * 全局静态只能输入一位小数的正浮点数对象,整数部分最长为15位，没有小数部分<br>
	 */
	public static final String POSITIVE_ZERO_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * 全局静态只能输入一位小数的正浮点数对象,整数部分最长为15位，小数部分最长为1位<br>
	 */
	public static final String POSITIVE_ONE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * 全局静态只能输入两位小数的正浮点数对象,整数部分最长为15位，小数部分最长为2位<br>
	 */
	public static final String POSITIVE_DOUBLE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * 全局静态只能输入三位小数的正浮点数对象,整数部分最长为15位，小数部分最长为3位<br>
	 */
	public static final String POSITIVE_THREE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * 全局静态只能输入四位小数的正浮点数对象,整数部分最长为15位，小数部分最长为4位<br>
	 */
	public static final String POSITIVE_FOUR_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * 小时正则表达式
	 */
	public static final String HOUR = "(^[0-9]$)|(^[0-1](\\d)?$)|(^[2][0-3]?$)";
	/**
	 * 分正则表达式
	 */
	public static final String MINUTE = "(^[0-9]$)|(^[0-5](\\d)?$)";

}
