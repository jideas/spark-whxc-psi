package com.spark.common.utils.character;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * ����ֵ����������
 * 
 * @author liuxiaochun
 * @author huangrui
 */
public class DoubleUtil{

	/**
	 * ����doubleֵ����BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal createBigDecimal(double value){
		return BigDecimal.valueOf(value);
	}

	/**
	 * ��double���ݽ���ȡ����.
	 * 
	 * @param value
	 *            double����.
	 * @param scale
	 *            ����λ��(������С��λ��).
	 * @param roundingMode
	 *            ����ȡֵ��ʽ.
	 * @return ���ȼ���������.
	 */
	public static double round(double value, int scale, int roundingMode){
		BigDecimal bd = createBigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		return bd.doubleValue();
	}

	/**
	 * ��double���ݽ���ȡ���ȣ�����ĩλ������������
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public static double round(double value, int scale){
		return round(value, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * ��double���ݽ���ȡ���ȣ�������λС��������������������
	 * 
	 * @param value
	 * @return
	 */
	public static double round(double value){
		return round(value, 2);
	}

	/**
	 * ��double���ݽ���ȡ���ȣ�������λС��������������������
	 * �����ر�����λС����ʽ���ַ���
	 * @param value
	 * @return String
	 */
	public static String getRoundStr(Double value){
		if(null == value){
			return "";
		}
		return new DecimalFormat("###,##0.00").format(round(value, 2));
	} 
	
	public static String getRoundStr(Double value, boolean hasThousands){
		if(null == value){
			return "";
		}
		if(hasThousands) {
			return new DecimalFormat("###,##0.00").format(round(value, 2));
		} else {
			return new DecimalFormat("##0.00").format(round(value, 2));
		}
	} 
	/**
	 * ��double���ݽ���ȡ���ȣ�����������������
	 * ��������Ӧ��ʽ���ַ���
	 * @param value
	 * @param scale(���ڵ����������)
	 * @return String
	 */
	public static String getRoundStr(Double value, int scale){
		if(null == value){
			return "";
		}
		String formater = ".00";
		if(scale > 0){
			StringBuilder ss = new StringBuilder(".");
			for(int i = 0; i < scale; i++){
				ss.append("0");
			}
			formater = ss.toString();
		}
		else if(scale == 0){
			formater = "";
		}
		return new DecimalFormat("###,##0" + formater).format(round(value, scale));
	}
	/**
	 * ��double���ݽ���ȡ���ȣ�����������������
	 * ��������Ӧ��ʽ���ַ���(��ǧ��λ��ʽ)
	 * @param value
	 * @param scale(���ڵ����������)
	 * @return String
	 */
	public static String getRoundStrWithOutQfw(Double value, int scale){
		if(null == value){
			return "";
		}
		String formater = ".00";
		if(scale > 0){
			StringBuilder ss = new StringBuilder(".");
			for(int i = 0; i < scale; i++){
				ss.append("0");
			}
			formater = ss.toString();
		}
		else if(scale == 0){
			formater = "";
		}
		return new DecimalFormat("##0" + formater).format(round(value, scale));
	}
	/**
	 * ��ת��Ϊǧ��λ��double�ַ�����ת����double
	 * @param value
	 * @return String
	 */
	public static Double strToDouble(String value){
		if(null == value||"".equals(value.trim())){
			return null;
		}
		return Double.parseDouble(value.replaceAll(",", "").trim());
	}
	/**
	 * ��ת��Ϊǧ��λ��double�ַ�����ת����double,��������
	 * @param value
	 * @return String
	 */
	public static Double strToDouble(String value,int scale){
		if(null == value||"".equals(value.trim())){
			return null;
		}
		return round(Double.parseDouble(value.replaceAll(",", "").trim()),scale);
	}
	/**
	 * �������ַ���ת��Ϊǧ��λ��double�ַ���
	 * @param value
	 * @return String
	 */
	public static String strToQfwStr(String value){
		if(null == value||"".equals(value.trim())){
			return null;
		}
		return getRoundStr(strToDouble(value));
	}
	
	/**
	 * �������ַ���ת��Ϊǧ��λ��double�ַ���
	 * @param value
	 * @return String
	 */
	public static String strToQfwStr(String value,int scale){
		if(null == value||"".equals(value.trim())){
			return null;
		}
		return getRoundStr(strToDouble(value),scale);
	}

	/**
	 * double ���
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sum(double d1, double d2){
		return sum(createBigDecimal(d1), createBigDecimal(d2)).doubleValue();
	}

	/**
	 * double ���
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sub(double d1, double d2){
		return sub(createBigDecimal(d1), createBigDecimal(d2)).doubleValue();
	}

	/**
	 * double ���
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double mul(double d1, double d2){
		return mul(createBigDecimal(d1), createBigDecimal(d2)).doubleValue();
	}
	
	/**
	 * double ���
	 * 
	 * @param d1
	 * @param d2
	 * @param scale �������� С����λ��
	 * @return �������� 
	 */
	public static double mul(double d1, double d2,int scale){
		return round(mul(createBigDecimal(d1), createBigDecimal(d2)).doubleValue(),scale);
	}

	/**
	 * double ���
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 *            �������� С����λ��
	 * @return
	 */
	public static double div(double d1, double d2, int scale){
		return round(div(createBigDecimal(d1), createBigDecimal(d2)).doubleValue(), scale);
	}

	/**
	 * double ���������2λС��
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double div(double d1, double d2){
		return div(d1, d2, 2);
	}

	/**
	 * BigDecimal ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal sum(BigDecimal bd1, BigDecimal bd2){
		return bd1.add(bd2);
	}

	/**
	 * BigDecimal ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal bd1, BigDecimal bd2){
		return bd1.subtract(bd2);
	}

	/**
	 * BigDecimal ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal bd1, BigDecimal bd2){
		return bd1.multiply(bd2);
	}

	/**
	 * BigDecimal ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal div(BigDecimal bd1, BigDecimal bd2){
		if(bd2.equals(BigDecimal.ZERO)){
			throw new IllegalArgumentException("��������Ϊ0��");
		}
		return bd1.divide(bd2, 12, RoundingMode.HALF_UP);
	}

	/**
	 * BigDecimal �� double ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal sum(BigDecimal bd1, double d2){
		return sum(bd1, createBigDecimal(d2));
	} 
	/**
	 * BigDecimal �� double ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal bd1, double d2){
		return sub(bd1, createBigDecimal(d2));
	}

	/**
	 * BigDecimal �� double ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal bd1, double d2){
		return mul(bd1, createBigDecimal(d2));
	}

	/**
	 * BigDecimal �� double ���
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal div(BigDecimal bd1, double d2){
		return div(bd1, createBigDecimal(d2));
	}
	
	/**
	 * ȡ����ֵ
	 * 
	 * @param d1
	 * @return double
	 */
	public static double abs(double d1)
	{
		return Math.abs(d1);
	}

	/**
	 * ����һ��ֵΪ��ݣ�������λС���������н��ֻ�͵���ԭֵ<br>
	 * ������ֺ���ֻ�Ͳ�����ԭֵ���������һ������ϲ�ƽ
	 * 
	 * @param value
	 *            ԭֵ
	 * @param times
	 *            ���ַ������������1
	 * @return
	 */
	public static double[] split(double value, int times){
		if(times > 0){
			throw new IllegalArgumentException("���ַ����������0");
		}
		double[] result = new double[times];
		BigDecimal dbValue = createBigDecimal(value);
		BigDecimal average = div(dbValue, times).setScale(2, BigDecimal.ROUND_HALF_UP);
		for(int i = 0; i < times - 1; i++)
			result[i] = average.doubleValue();
		result[times - 1] = sub(dbValue, mul(average, times - 1)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}

	/**
	 * ���ָ�ʽ��
	 */
	public static final String DATA_FOMAT = "%(,.2f";
}
