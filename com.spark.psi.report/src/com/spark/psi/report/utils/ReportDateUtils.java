/**
 * 
 */
package com.spark.psi.report.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public abstract class ReportDateUtils{

	/**
	 * �������
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearNo(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String s = sdf.format(date);
		return Integer.valueOf(s);
	}

	/**
	 * ����±��
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthNo(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String s = sdf.format(date);
		return Integer.valueOf(s);
	}

	/**
	 * ����ձ��
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateNo(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String s = sdf.format(date);
		return Integer.valueOf(s);
	}

	/**
	 * ��ü����
	 * 
	 * @param date
	 * @return
	 */
	public static int getQuarter(Date date){
		int l = 0;
		String s = "";
		if(null != date){
			String da = new SimpleDateFormat("MM").format(date);
			String yy = new SimpleDateFormat("yyyy").format(date);
			int m = Integer.valueOf(da);
			int cs = (m - 1) / 3 + 1;
			if(String.valueOf(cs).length() < 2){
				s = yy + "0" + String.valueOf(cs);
			}
			else{
				s = yy + String.valueOf(cs);
			}
			l = Integer.valueOf(s);
		}
		return l;
	}

	/**
	 * ������ת��Ϊ�����ֵ��ַ���,�������
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 *             String
	 */
	public static String quaterToStrYear(String quater){
		String s = "";
		s = quater.substring(0, 4) + "���" + upperNum(quater.substring(5)) + "����";
		return s;
	}

	/**
	* ���·�ת��Ϊ�����ֵ��ַ���,�������
	* 
	* @param date
	* @return
	* @throws ParseException
	*             String
	*/
	public static String monthToStrYear(String month){
		String s = "";
		s = month.substring(0, 4) + "��" + Integer.parseInt(month.substring(4)) + "��";
		return s;
	}

	/**
	 * ��������ת����
	 * @param num
	 * @return
	 */
	private static String upperNum(String num){
		if("1".equals(num)){
			return "һ";
		}
		else if("2".equals(num)){
			return "��";
		}
		else if("3".equals(num)){
			return "��";
		}
		else if("4".equals(num)){
			return "��";
		}
		return "";
	}

	/**
	 * ��long��ʱ������ת����String����
	 * 
	 * @param date
	 * @return
	 * @throws ParseException String
	 */
	public static String longToString(long time){
		String s = "";
		if(time > 0){
			Date date = new Date(time);
			String fmt = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			s = sdf.format(date);
		}
		return s;
	}

	/**
	 * ��long��ʱ������ת����String����
	 * 
	 * @param date
	 * @return
	 * @throws ParseException String
	 */
	public static String longToString(long time, String fmt){
		String s = "";
		if(time > 0){
			Date date = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			s = sdf.format(date);
		}
		return s;
	}

	/**
	 * �ϸ��±��
	 * @param monthNo
	 * @return
	 */
	public static int getLastMonthNo(int monthNo){
		int m = Integer.parseInt(("" + monthNo).substring(4));
		if(m % 12 != 1){
			return monthNo - 1;
		}
		else{
			return monthNo - 89;
		}
	}

	/**
	 * ȥ��ı��
	 * @param yearNo
	 * @return
	 */
	public static int getLastYearNo(int yearNo){
		return yearNo - 1;
	} 

	/**
	 * �ϸ����ȱ��
	 * @param quarter
	 * @return
	 */
	public static int getLastQuarter(int quarter){
		if(quarter % 4 != 1){
			return quarter - 1;
		}
		else{
			return quarter - 97;
		}
	}

	public static String getDateStr(int dateNo){
		String value = dateNo+"";
		StringBuilder ss = new StringBuilder();
		ss.append(value.substring(0,4));
		ss.append("-");
		ss.append(value.substring(4,6));
		ss.append("-");
		ss.append(value.substring(6));
		return ss.toString();
	}
}
