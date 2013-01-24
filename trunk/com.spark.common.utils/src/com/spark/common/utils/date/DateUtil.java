package com.spark.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.spark.common.utils.character.CheckIsNull;

/**
 * 
 * <p>
 * ���ڹ�����
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author yl
 * @version 2011-6-29
 */
public class DateUtil{

	private final static String EMPTY = "-";
	private final static String DEFAULT_PATTERN = "yyyy-MM-dd";
	
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
	
//	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
	/**
	 * һ��ĺ�����
	 */
	public final static long ONE_DATE_TIME = 24 * 60 * 60 * 1000l;

	/**
	 * ��long���͵�����ת��ָ����ʽpattern���ַ�������
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String dateFromat(long time, String pattern){
		if(time > 0){
			Date thisDate = new Date(time);
			String p = DEFAULT_PATTERN;
			if(CheckIsNull.isNotEmpty(pattern)){
				p = pattern;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(p);
			return sdf.format(thisDate);
		}
		return EMPTY;
	}

	/**
	 * ��long���͵�����ת��"yyyy-MM-dd"���ַ�������
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String dateFromat(long time){
		if(time > 0){
			Date thisDate = new Date(time);
			String p = DEFAULT_PATTERN;
			SimpleDateFormat sdf = new SimpleDateFormat(p);
			return sdf.format(thisDate);
		}
		return EMPTY;
	}

	/**
	 * ����ת��ũ������
	 * 
	 * @return
	 */
	public static long new2OldDate(long newDate){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(newDate);
		Date time =
		        ChineseCalendar.sCalendarSolarToLundar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal
		                .get(Calendar.DAY_OF_MONTH));
		return time.getTime();
	}

	/**
	 * ũ��ת����
	 * 
	 * @param oldDate
	 * @return
	 */
	public static long old2NewDate(long oldDate){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(oldDate);
		Date time =
		        ChineseCalendar.sCalendarLundarToSolar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal
		                .get(Calendar.DAY_OF_MONTH));
		return time.getTime();
	}

	/**
	 * ������2003-2-2��ʱ���ַ���ת��Ϊ Date��������ַ�����ʽ����ȷ�����ص�ǰʱ�����
	 * 
	 * @param dateStr
	 * @return long
	 */
	public static long convertStringToDate(String dateStr){
		return convertStringToDate2(dateStr).getTime();
	}

	/**
	 * ������2003-2-2��ʱ���ַ���ת��Ϊ Date��������ַ�����ʽ����ȷ�����ص�ǰʱ�����
	 * 
	 * @param dateStr
	 * @return long
	 */
	public static Date convertStringToDate2(String dateStr){
		return com.jiuqi.util.DateUtil.convertStringToDate1(dateStr);
	}

	/**
	 * ��long���͵�����Ϊ Date����
	 * 
	 * @param date
	 */
	public static Date convertLongToDate(Long date){
		return convertStringToDate2(dateFromat(date));
	}

	/**
	 * ����ȡ������<Br>
	 * ��ָ�����ڴ�ָ����ʱ�䵥λ����ʼȡ�����罫2011-11-1 15:30 Ĩȥʱ�� ��� 2011-11-1 00:00
	 * 
	 * 
	 * @param time
	 * @param field
	 *            ����ֵΪjava.util.Calendar�ĳ��� Calendar.DATE ����������ʼȡ��
	 *            Calendar.MONTH ���·ݴ���ʼȡ�� 2011-5-3 ���� 2011-5-1 Calendar.YEAR
	 *            ����ݴ���ʼȡ�� Calendar.HOUR ��Сʱȡ��
	 * @return long
	 */
	public static long trunc(long time, int field){
		String format = "yyyyMMdd 00:00";
		switch(field){
			case Calendar.DATE:
				break;
			case Calendar.MONTH:
				format = "yyyyMM";
				break;
			case Calendar.YEAR:
				format = "yyyy";
				break;
			case Calendar.HOUR:
				format = "yyyyMMdd hh:00";
				break;
			default:
				throw new IllegalArgumentException("����ȡ��ʱ��������û��" + field + "�������͡�����ֵ��ο�java.util.Calendar");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			return sdf.parse(sdf.format(time)).getTime();
		}
		catch(ParseException e){
			e.printStackTrace();
			return time;
		}
	}

	/**
	 * ��ָ��ʱ������Сʱ������Ϣ����
	 * 
	 * @param time
	 * @return long
	 */
	public static long truncDay(long time){
		return DateUtil.trunc(time, Calendar.DATE);
	}

	/**
	 * �õ�ָ���յ���ʼʱ�䣨0ʱ0��0�룩
	 * 
	 * @param date
	 * @return
	 */
	public static long getDayStartTime(long date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTimeInMillis();
	}

	/**
	 * �õ�ָ���յĽ���ʱ�䣨23ʱ59��59��...��
	 * 
	 * @param date
	 * @return
	 */
	public static long getDayEndTime(long date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTimeInMillis() + 24 * 60 * 60 * 1000 - 1;
	}

	/**
	 * ��ȡ���µ�һ������
	 * 
	 * @return
	 */
	public static Date getThisMonth(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * ��ȡ���쿪ʼ��ʱ��
	 * 
	 * @return
	 */
	public static long getToday(){
		return truncDay(System.currentTimeMillis());
	}

	/**
	 * �ж����������Ƿ����
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkIsNewYear(long beginDate, long endDate){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(beginDate);
		int beginYear = cal.get(Calendar.YEAR);
		cal.setTimeInMillis(endDate);
		int endYear = cal.get(Calendar.YEAR);
		return beginYear != endYear;
	}

	/**
	 * ���뵱ǰ���ڣ���ʽ��yyyy-mm-dd
	 * 
	 * @param date
	 * @return ���ش��������ǵ�ǰ�Ľ���ĳ���µ�ĳ������ ��ʽΪ:yyyymmww
	 */
	public static String getDayofWeekofMonth(String time){
		// String time = "2011-12-02";
		try{
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.DAY_OF_WEEK) == 1){
				/*
				 * System.out.println("������ĩʱ�䣺 <=" + new
				 * SimpleDateFormat("yyyyMMdd").format(calendar .getTime()));
				 */
				/*
				 * calendar.set(Calendar.DAY_OF_YEAR, calendar
				 * .get(Calendar.DAY_OF_YEAR) - 7);
				 * System.out.println("�����ڳ�ʱ�䣺<=" + new
				 * SimpleDateFormat("yyyyMMdd").format(calendar .getTime()));
				 */
				return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
			}
			else{
				while(calendar.get(Calendar.DAY_OF_WEEK) < 7){
					calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) + 1);
				}
				calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
				/*
				 * System.out.println("������ĩʱ�䣺 <=" + new
				 * SimpleDateFormat("yyyyMMdd").format(calendar .getTime()));
				 * calendar.set(Calendar.DAY_OF_YEAR, calendar
				 * .get(Calendar.DAY_OF_YEAR) - 7);
				 * System.out.println("�����ڳ�ʱ�䣺<=" + new
				 * SimpleDateFormat("yyyyMMdd").format(calendar .getTime()));
				 */
				return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
			}
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		return time;

		/*
		 * Calendar c_now = new GregorianCalendar(); Calendar c_begin = new
		 * GregorianCalendar(); Calendar c_end = new GregorianCalendar();
		 * DateFormatSymbols dfs = new DateFormatSymbols(); SimpleDateFormat df
		 * = new SimpleDateFormat("yyyy-M-dd"); java.util.Date cDate = null; try
		 * { cDate = df.parse(date); } catch (ParseException e) {
		 * e.printStackTrace(); } c_now.setTime(cDate); int year =
		 * c_now.get(Calendar.YEAR); int month = c_now.get(Calendar.MONTH) + 1;
		 * int today = c_now.get(Calendar.DAY_OF_MONTH);
		 * System.out.println(today); int[] days = { 0, 31, 28, 31, 30, 31, 30,
		 * 31, 31, 30, 31, 30, 31 }; if (year % 4 == 0) { days[2] = 29;// ���� }
		 * c_begin.set(2010, month - 1, 1); // �� 0-11 �� 0- c_end.set(2010, month
		 * - 1, days[month]);
		 * 
		 * int count = 1; c_end.add(Calendar.DAY_OF_YEAR, 1); //
		 * ���������¹�һ����Ϊ�˰������һ�� int day = c_now.get(Calendar.DAY_OF_WEEK) - 1;
		 * while (c_begin.before(c_end)) { if (day == 8) { count++; day = 1; }
		 * 
		 * if (today == c_begin.get(Calendar.DAY_OF_MONTH)) {
		 * //System.out.println(df.format(c_begin.getTime()) + "��" + month //+
		 * "�µ�" + count + "�ܵĵ�" + day + "��"); String returnmonth =
		 * (c_begin.get(Calendar.YEAR)+1) + "" +
		 * (c_begin.get(Calendar.MONTH)+1); Date date2; String returnvalue = "";
		 * try { date2 = new SimpleDateFormat("yyyyMM").parse(returnmonth);
		 * returnvalue = new SimpleDateFormat("yyyyMM").format(date2)+ "0" +
		 * count; } catch (ParseException e) { e.printStackTrace(); } return
		 * returnvalue ;
		 * 
		 * } day++;
		 * 
		 * // System.out.println("��"+count+"��  ���ڣ�"+new //
		 * java.sql.Date(c_begin.
		 * getTime().getTime())+", "+weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
		 * 
		 * c_begin.add(Calendar.DAY_OF_YEAR, 1); }
		 * //System.out.println("���ƣ���Խ����" + (count) + "��"); return null;
		 */
	}

	/**
	 * 
	 * @param date
	 *            ��ǰ����
	 * @return �õ���ǰ��������������
	 */
	public static int getCurrQuarter(String str){
		String s = "";
		if(null != str){
			String da = str.substring(4, 6);
			String yy = str.substring(0, 4);
			int m = Integer.valueOf(da);
			int cs = (m - 1) / 3 + 1;
			s = yy + String.valueOf(cs);

		}
		return Integer.parseInt(s);
	}

	/**
	 * �õ���ǰ�·�
	 * 
	 * @param str
	 * @return
	 */
	public static int getMonth(String str){
		int reValue = 0;
		if(null != str){
			String s = str.substring(0, 6);
			reValue = Integer.parseInt(s);
		}
		return reValue;
	}

	/**
	 * �õ���һ�����ڵ�����
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Calendar getBeforeWeekDay(String dateStr){
		Date date = null;
		try{
			date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
		}
		catch(ParseException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
		return calendar;
	}

	/**
	 * �õ��������ڵļ������
	 */
	public static long getDaysBetween(Date startDate, Date endDate){
		long days = 0;
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);
		days = ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
		return days;
	}

	/**
	 * �ж�ָ�������Ƿ�Ϊ����
	 */
	public static boolean isCurrentDay(long date){
		return getDayStartTime(new Date().getTime()) <= date
		        && getDayEndTime(new Date().getTime()) >= date;
	}

}
