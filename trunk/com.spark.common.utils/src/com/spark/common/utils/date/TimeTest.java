package com.spark.common.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.spark.common.utils.date.DateUtil;

public class TimeTest{
	//用来全局控制 上一周，本周，下一周的周数变化    
	private int weeks = 0;

	@SuppressWarnings("deprecation")
	public static void main(String[] a){
		TimeTest tt = new TimeTest();
		System.out.println("下个月的开始日期:"
		        + new Date(tt.getNextMonthBegin()).toLocaleString());
		System.out.println("下个季的开始日期:"
		        + new Date(tt.getNextSeasonBegin()).toLocaleString());
		System.out.println("下年的开始日期:"
		        + new Date(tt.getNextYearBegin()).toLocaleString());
		System.out.println("上个月开始日期:"
		        + new Date(tt.getPreviousMonthFirst()).toLocaleString());
		System.out.println("当前月开始时间:"
		        + new Date(tt.getFirstDayOfMonth()).toLocaleString());
		System.out.println("上周开始日期:"
		        + new Date(tt.getPreviousWeekday()).toLocaleString());
		System.out.println("本周开始日期:"
		        + new Date(tt.getMondayOFWeek()).toLocaleString());
		System.out.println("获得本年第一天的日期 :"
		        + new Date(tt.getCurrentYearFirst()).toLocaleString());
		System.out.println("去年第一天日期:"
		        + new Date(tt.getPreviousYearFirst()).toLocaleString());
		System.out.println("前年第一天日期:"
		        + new Date(tt.getPreviousPreviousYearFirst()).toLocaleString());
		System.out.println("本季开始日期:"
		        + new Date(tt.getThisSeasonTime()).toLocaleString());

	}

	/**
	 * 下个月的开始日期
	 * @return Long
	 */
	public Long getNextMonthBegin(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		long date = DateUtil.truncDay(c.getTime().getTime());
		return date;
	}

	/**
	 * 下个季的开始日期
	 * @return Long
	 */
	public Long getNextSeasonBegin(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式       
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		int month = new Date().getMonth() + 1;
		int array[][] = { {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {1, 2, 3}};
		int season = 1;
		if(month >= 1 && month <= 3){
			season = 1;
		}
		if(month >= 4 && month <= 6){
			season = 2;
		}
		if(month >= 7 && month <= 9){
			season = 3;
		}
		if(month >= 10 && month <= 12){
			season = 4;
			years_value++;
		}
		int start_month = array[season - 1][0];
		int start_days = 1;
		String seasonDate = years_value + "-" + start_month + "-" + start_days;
		return DateUtil.trunc(DateUtil.convertStringToDate(seasonDate),
		        Calendar.DATE);
	}

	/**
	 * 下年的开始日期
	 * @return Long
	 */
	public Long getNextYearBegin(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.YEAR, 1);
		long date = DateUtil.truncDay(c.getTime().getTime());
		return date;
	}

	// 上月第一天    
	public long getPreviousMonthFirst(){
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);//设为当前月的1号    
		lastDate.add(Calendar.MONTH, -1);//减一个月，变为下月的1号    
		return DateUtil.trunc(lastDate.getTime().getTime(), Calendar.DATE);
	}

	//获取当月第一天    
	public long getFirstDayOfMonth(){
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);//设为当前月的1号    
		return DateUtil.trunc(lastDate.getTime().getTime(), Calendar.DATE);
	}

	// 获得当前日期与本周日相差的天数    
	private int getMondayPlus(){
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......    
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; //因为按中国礼拜一作为第一天所以这里减1    
		if(dayOfWeek == 1){
			return 0;
		}
		else{
			return 1 - dayOfWeek;
		}
	}

	//获得本周一的日期    
	public long getMondayOFWeek(){
		weeks = 0;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		return DateUtil.trunc(monday.getTime(), Calendar.DATE);
	}

	// 获得上周星期一的日期    
	public long getPreviousWeekday(){
		weeks--;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		return DateUtil.trunc(monday.getTime(), Calendar.DATE);
	}

	private int getYearPlus(){
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);//获得当天是一年中的第几天    
		cd.set(Calendar.DAY_OF_YEAR, 1);//把日期设为当年第一天    
		cd.roll(Calendar.DAY_OF_YEAR, -1);//把日期回滚一天。    
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if(yearOfNumber == 1){
			return -MaxYear;
		}
		else{
			return 1 - yearOfNumber;
		}
	}

	//获得本年第一天的日期    
	public long getCurrentYearFirst(){
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		return DateUtil.trunc(yearDay.getTime(), Calendar.DATE);
	}

	//获得上年第一天的日期 *    
	public long getPreviousYearFirst(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式       
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		String str = years_value + "-1-1";
		return DateUtil.trunc(DateUtil.convertStringToDate(str), Calendar.DATE);
	}

	//获得前年第一天的日期 *    
	public long getPreviousPreviousYearFirst(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式       
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		years_value--;
		String str = years_value + "-1-1";
		return DateUtil.convertStringToDate(str);
	}

	//获得本季度    
	@SuppressWarnings("deprecation")
	public long getThisSeasonTime(){
		int month = new Date().getMonth();
		int array[][] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
		int season = 1;
		if(month >= 1 && month <= 3){
			season = 1;
		}
		if(month >= 4 && month <= 6){
			season = 2;
		}
		if(month >= 7 && month <= 9){
			season = 3;
		}
		if(month >= 10 && month <= 12){
			season = 4;
		}
		int start_month = array[season - 1][0];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式       
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int start_days = 1;//years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);    
		String seasonDate = years_value + "-" + start_month + "-" + start_days;
		return DateUtil.trunc(DateUtil.convertStringToDate(seasonDate),
		        Calendar.DATE);
	}

	/**   
	* 得到二个日期间的间隔天数   
	*/
	public static long getTwoDay(long date1, long date2){
		date1 = DateUtil.truncDay(date1);
		date2 = DateUtil.truncDay(date2);
		return (date1 - date2) / (24 * 60 * 60 * 1000);

	}

	/**
	 * 
	 * @param date 当前季度
	 * @return 得到当前月日期所属季度
	 */
	public static int getCurrQuarter(Date date){
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
	 * 得到一年的开始时间
	 * 
	 * @return long
	 */
	public static long fromYear(){
		Calendar nowDate = Calendar.getInstance();
		nowDate.set(Calendar.HOUR_OF_DAY, 0);
		nowDate.set(Calendar.MINUTE, 0);
		nowDate.set(Calendar.SECOND, 0);
		nowDate.set(Calendar.MILLISECOND, 0);
		nowDate.set(Calendar.DAY_OF_MONTH, 1);
		nowDate.set(Calendar.MONTH, 0);
		return nowDate.getTimeInMillis();
	}

	/**
	 * 得到一年的结束时间
	 * 
	 * @return long
	 */
	public static long toYear(){
		Calendar nowDate = Calendar.getInstance();
		nowDate.set(Calendar.HOUR_OF_DAY, 0);
		nowDate.set(Calendar.MINUTE, 0);
		nowDate.set(Calendar.SECOND, -1);
		nowDate.set(Calendar.MILLISECOND, 0);
		nowDate.set(Calendar.DAY_OF_MONTH, 1);
		nowDate.set(Calendar.MONTH, 12);
		return nowDate.getTimeInMillis();
	}

	/**
	 * 得到一月的开始时间
	 * 
	 * @return long
	 */
	public static long fromMonth(){
		Calendar nowDate = Calendar.getInstance();
		nowDate.set(Calendar.HOUR_OF_DAY, 0);
		nowDate.set(Calendar.MINUTE, 0);
		nowDate.set(Calendar.SECOND, 0);
		nowDate.set(Calendar.MILLISECOND, 0);
		nowDate.set(Calendar.DAY_OF_MONTH, 1);
		return nowDate.getTimeInMillis();
	}

	/**
	 * 得到一月的结束时间
	 * 
	 * @return long
	 */
	public static long toMonth(){
		Calendar nowDate = Calendar.getInstance();
		nowDate.set(Calendar.HOUR_OF_DAY, 0);
		nowDate.set(Calendar.MINUTE, 0);
		nowDate.set(Calendar.SECOND, -1);
		nowDate.set(Calendar.MILLISECOND, 0);
		nowDate.set(Calendar.DAY_OF_MONTH, Calendar.getInstance()
		        .getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
		return nowDate.getTimeInMillis();
	}

	/**
	 * 
	 * 
	 * @param date2011-01-01
	 * @return int如:201101
	 */
	public static int dateStrToInt(Date date){
		int l = 0;
		if(date != null){
			String da = new SimpleDateFormat("MM").format(date);
			String yy = new SimpleDateFormat("yyyy").format(date);
			l = Integer.valueOf(yy + da);
		}
		return l;
	}

}