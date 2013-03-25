package com.spark.common.components.controls.text;

public class TextRegexp {

	/**
	 * ��༸λС����������ʽ
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
	 * ȫ�־�ֻ̬��������λС����������������<br>
	 * 
	 */
	public static final String POSITIVE_DOUBLE = "^\\d*(\\.)?(\\d)?(\\d)?$";

	public static final String POSITIVE_DOUBLE_FUSHU_LIMIT = "^([+-]?\\d{0,15}(\\.\\d{0,2})?)$";

	/**
	 * ȫ�־�̬�̻��ʹ�����֤ ���ܸ�ʽ XXXX-XXXX-XXX ����XXXX�������֣������޳���
	 */
	public static final String PHONE = "(^\\d*)([+-]?)(\\d*)([+-]?)(\\d*$)";

	/**
	 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ8λ��С�������Ϊ2λ<br>
	 */
	public static final String NUMBER_TEN_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * �ۿ۶��5��2��0%-100%
	 */
	public static final String NUMBER_FIVE_TWO = "(^(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?$)|(^[1][0][0]$)";

	/**
	 * ȫ�־�̬�ʱ�<br>
	 * 
	 */
	public static final String POSTCODE = "^[0-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

	/**
	 * ȫ�־�̬�ֻ�<br>
	 * 
	 */
	public static final String MOBILE = "^[1-9](\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$";

	/**
	 * ȫ�־�ֻ̬��������λ������λС����������������<br>
	 * 
	 */
	public static final String POSITIVE_FIVE_DOUBLE = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	public static final String NUMBER_ONE_FOUR = "(^[0]?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ8λ��С�������Ϊ2λ<br>
	 */
	public static final String NUMBER_EIGHT_TWO = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * ȫ�־�ֻ̬����������(������)
	 */
	public static final String NUMBER = "^[0-9]+$";
	/**
	 * ȫ�־�ֻ̬����������(����)
	 */
	public static final String ALLNUMBER = "^-?\\d+$";

	/**
	 * ȫ�־�ֻ̬���������ֺ���ĸ
	 */
	public static final String ENGLISH_AND_NUMBER = "^[A-Za-z0-9]+$";
	/**
	 * ȫ�־�ֻ̬������ʱ���ʽ
	 */
	public static final String TIEM_FORAMT = "^([0-9]?[0-9]?(\\:)?[0-5]?[0-9]$)?";
	/**
	 * ȫ�־�̬����У��
	 */
	// public static final String Mail = "";
	// public static final String Mail="^[A-Za-z0-9_]@[A-Za-z].[A-Za-z]+$";

	/**
	 * ȫ�־�ֻ̬������һλС����������������,���������Ϊ15λ��û��С������<br>
	 */
	public static final String POSITIVE_ZERO_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * ȫ�־�ֻ̬������һλС����������������,���������Ϊ15λ��С�������Ϊ1λ<br>
	 */
	public static final String POSITIVE_ONE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ2λ<br>
	 */
	public static final String POSITIVE_DOUBLE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ3λ<br>
	 */
	public static final String POSITIVE_THREE_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";
	/**
	 * ȫ�־�ֻ̬��������λС����������������,���������Ϊ15λ��С�������Ϊ4λ<br>
	 */
	public static final String POSITIVE_FOUR_LIMIT = "(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)|(^(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?(\\d)?$)";

	/**
	 * Сʱ������ʽ
	 */
	public static final String HOUR = "(^[0-9]$)|(^[0-1](\\d)?$)|(^[2][0-3]?$)";
	/**
	 * ��������ʽ
	 */
	public static final String MINUTE = "(^[0-9]$)|(^[0-5](\\d)?$)";

}
