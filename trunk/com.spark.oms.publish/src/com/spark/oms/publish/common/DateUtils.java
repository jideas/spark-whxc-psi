package com.spark.oms.publish.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.spark.oms.publish.OrderServiceFeeStatus;
import com.spark.oms.publish.ProductCycle;

public class DateUtils {
	
		public static final int BeforeChargeFeeDay = 7;
		public static final int LossedTenantsRefundDays = 90;
		
		/**
		 * 计算产品费用状态
		 * 
		 * 如果此订单：
		 *     未缴款，订单的费用状态到了正常，则此订单为无效订单
		 *     已缴款，订单的费用状态到了完成，则此订单服务成为历史订单
		 */
		public static int getOrderServiceDefaultFeestatus(String orderTypeCode,long billingBegin){
			int status = OrderServiceFeeStatus.Free.getCode();
			long now = getCurrentTime();
			if(com.spark.oms.publish.OrderType.NewOrder.getCode().equals(orderTypeCode)){
				if(before(now, billingBegin)){
					//计价开始日期>当前日期
					if(before(now, getOffsetDate(billingBegin, -BeforeChargeFeeDay))){
						//免费
						status = OrderServiceFeeStatus.Free.getCode();
					}else{
						//临近续费
						status = OrderServiceFeeStatus.Pro.getCode();
					}
				}
			}else{
				status = OrderServiceFeeStatus.Wait.getCode();
			}
			return status;
		}
		public static Date getBeforeHalfYearTime(){
			Date now = new Date();
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, -6);
			gc.set(GregorianCalendar.DAY_OF_MONTH,1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			return gc.getTime();
		}
		
		public static Date getBefore3MonthTime(){
			Date now = new Date();
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, -3);
			gc.set(GregorianCalendar.DAY_OF_MONTH,1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			return gc.getTime();
		}
		/**
		 * 获取订单服务结束时间
		 * startTime： 新建订单的创建时间 或 被续单的结束时间
		 */
		public static long getServiceEndDate(long startTime,String chargeType){
			long ret = startTime;
			if(ProductCycle.Month.getCode().equals(chargeType)){
				ret = getNextMonthLastDayTime(startTime);
			}else if(ProductCycle.Quarter.getCode().equals(chargeType)){
				ret = getNextQuarterLastDayTime(startTime);
			}else if(ProductCycle.HalfYear.getCode().equals(chargeType)){
				ret = getNextHalfYearLastDayTime(startTime);
			}else if(ProductCycle.Year.getCode().equals(chargeType)){
				ret = getNextYearLastDayTime(startTime);
			}
			return ret;
		}
		
		public static int getMonthNum(long startTime,long endTime){
			int num = 0;
			Calendar gc = new GregorianCalendar();
			gc.setTimeInMillis(startTime);
			int syear = gc.get(Calendar.YEAR);
			int smonth = gc.get(Calendar.MONTH);;
			
			gc = new GregorianCalendar();
			gc.setTimeInMillis(endTime);
			
			int eyear = gc.get(Calendar.YEAR);
			int emonth = gc.get(Calendar.MONTH);
			
			num = eyear*12+emonth-(syear*12+smonth);
			return num;
		}
		
		/**
		 * 获取当前日期
		 */
		public static Date getCurrentDate(){
			 return new Date();
		}
		/**
		 * 获取当前日期
		 */
		public static long getCurrentTime(){
			 return (new Date()).getTime();
		}
		/**
		 * 获取当前日期 如:20120329
		 */
		public static String getCurrentDateStr(){
			String datestr = null;
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
			try{
				datestr =  simpleDateFormat.format(new Date()).toString();
			}catch (Exception e) {
				return null;
			}
			return datestr;
		}
		
		/**
		 * 获取当前时间  如:2012-03-29 hh:mm:ss
		 */
		public static String getCurrentTimeStr(){
			String datestr = null;
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
			try{
				datestr =  simpleDateFormat.format(new Date()).toString();
			}catch (Exception e) {
				return null;
			}
			return datestr;
		}
		public static String getCurrentShortDate(){
			String datestr = null;
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			try{
				datestr =  simpleDateFormat.format(new Date()).toString();
			}catch (Exception e) {
				return null;
			}
			return datestr;
		}
		
		/**
		 * 获取指定日期的前几天/后几天
		 */
		public static long getOffsetDate(long time,int offsetDay){
			
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.DAY_OF_YEAR, offsetDay);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		/**
		 * 获取当前日期的前几天/后几天
		 */
		public static long getOffsetDate(int offsetDay){
			return getOffsetDate((new Date()).getTime(),offsetDay);
		}
		
		/**
		 * 判断日期大小
		 * time before anotherTime ?true:false
		 */
		public static boolean before(long time,long anotherTime){
			GregorianCalendar gc = new GregorianCalendar();
			GregorianCalendar ogc = new GregorianCalendar();
			
			gc.setTimeInMillis(time);
			ogc.setTimeInMillis(anotherTime);
			return gc.before(ogc);
		}
		
		/**
		 * 获取本月15日
		 */
		public static long getCurrentMonth15Time(){
			Date now = new Date(new Date().getTime());
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.set(GregorianCalendar.DAY_OF_MONTH,15);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE,59);
			gc.set(GregorianCalendar.SECOND, 59);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		/**
		 * 获取本月1日
		 */
		public static long getCurrentMonthFirstDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.set(GregorianCalendar.DAY_OF_MONTH,1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		public static long getCurrentMonthFirstDayTime(){
			Date now = new Date();
			return getCurrentMonthFirstDayTime(now.getTime());
		}
		
		/**
		 * 获取本月最后一天
		 */
		public static long getCurrentMonthEndDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE,59);
			gc.set(GregorianCalendar.SECOND, 59);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		public static long getCurrentMonthEndDayTime(){
			return getCurrentMonthEndDayTime(new Date().getTime());
		}
		
		/**
		 * 获取当前日期的下个月的1日
		 */
		public static long getNextMonthFirstDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, 1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		public static long getNextDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.DAY_OF_YEAR, 1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		/**
		 * 获取上个月的最后一天
		 */
		public static long getLastMonthLastDayTime(long time){
				Date now = new Date(time);
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(now);
				gc.add(GregorianCalendar.MONTH, -1);
				gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
				gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
				gc.set(GregorianCalendar.MINUTE,59);
				gc.set(GregorianCalendar.SECOND, 59);
				gc.set(GregorianCalendar.MILLISECOND, 0);
				long ret = gc.getTime().getTime();
				gc.setTime(new Date());
				return ret;
		}
		
		public static long getLastMonthLastDayFirstTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, -1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		public static long getLastMonthLastDayFirstTime(){
				Date now = new Date();
				return getLastMonthLastDayFirstTime(now.getTime());
		}
		
		/**
		 *待实现
		 * @param time
		 * @return
		 */
		public static long getLastQuartFirstDayFirstTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			int month = gc.get(GregorianCalendar.MONTH);
			gc.set(GregorianCalendar.MONTH, getQuarterInMonth(month,true));
			gc.set(GregorianCalendar.DAY_OF_MONTH,1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			//区别本月本季
			gc.set(GregorianCalendar.MILLISECOND, 1);
			long ret = gc.getTime().getTime();
			System.out.println("====================="+formatDateToShortStr(ret));
			gc.setTime(new Date());
			return ret;
		}
		
		public static long getLastQuartFirstDayFirstTime(){
			Date now = new Date();
			return getLastQuartFirstDayFirstTime(now.getTime());
		}
		
		public static long getLastMonthFirstDayFirstTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, -1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		public static long getLastMonthFirstDayFirstTime(){
				Date now = new Date();
				return getLastMonthFirstDayFirstTime(now.getTime());
		}
		
		public static long getYearFirstDayFirstTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.set(GregorianCalendar.MONTH, 0);
			gc.set(GregorianCalendar.DAY_OF_MONTH,1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			gc.set(GregorianCalendar.MILLISECOND, 2);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		public static long getYearFirstDayFirstTime(){
				Date now = new Date();
				return getYearFirstDayFirstTime(now.getTime());
		}
		
		
		public static boolean isLastDay(){
			Date now = new Date();
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			int currentDay = gc.get(GregorianCalendar.DAY_OF_MONTH);
			return currentDay==gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			
		}
		
		/**
		 * 获取上个月的最后第二天
		 */
		public static long getLastMonthLastSecondDayTime(long time){
				Date now = new Date(time);
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(now);
				gc.add(GregorianCalendar.MONTH, -1);
				gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
				gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
				gc.set(GregorianCalendar.MINUTE,59);
				gc.set(GregorianCalendar.SECOND, 59);
				gc.set(GregorianCalendar.MILLISECOND, 0);
				gc.add(GregorianCalendar.DAY_OF_MONTH, -1);
				long ret = gc.getTime().getTime();
				gc.setTime(new Date());
				return ret;
		}
		
		/**
		 * 获得当前日期下个月的最后一天
		 */
		public static long getNextMonthLastDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, 1-1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE,59);
			gc.set(GregorianCalendar.SECOND, 59);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		/**
		 * 获得当前日期的23:59:59
		 */
		public static long getDayOfLastMintue(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE,59);
			gc.set(GregorianCalendar.SECOND, 59);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		public static long getDayOfLastMintue(){
			return getDayOfLastMintue(new Date().getTime());
		}
		
		/**
		 * 获取给定日期的，距离为一个季度的截至日期
		 */
		public static long getNextQuarterLastDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, 3-1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE,59);
			gc.set(GregorianCalendar.SECOND, 59);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		/**
		 * 获取指定日期的 周期为半年的那个月的最后一天的日期
		 */
		public static long getNextHalfYearLastDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, 6-1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE,59);
			gc.set(GregorianCalendar.SECOND, 59);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		/**
		 * 获取指定日期的 周期为年的那个月的最后一天的日期
		 */
		public static long getNextYearLastDayTime(long time){
			Date now = new Date(time);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(now);
			gc.add(GregorianCalendar.MONTH, 12-1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE,59);
			gc.set(GregorianCalendar.SECOND, 59);
			gc.set(GregorianCalendar.MILLISECOND, 0);
			long ret = gc.getTime().getTime();
			gc.setTime(new Date());
			return ret;
		}
		
		/**
		 * 格式化字符串
		 * @param args
		 */
		public static String formatDateToTimeStr(long time){
			String datestr = null;
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
				datestr =  simpleDateFormat.format(new Date(time)).toString();
			}catch (Exception e) {
				return null;
			}
			return datestr;
		}
		
		/**
		 * 格式化字符串
		 * @param args
		 */
		public static String formatDateToShortStr(long time){
			String datestr = null;
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			try{
				datestr =  simpleDateFormat.format(new Date(time)).toString();
			}catch (Exception e) {
				return null;
			}
			return datestr;
		}
		
		/**
		 * 获取指定年月日的日期 
		 * @param args
		 */
		public static long getDate(int year,int month,int day){
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(GregorianCalendar.YEAR, year);
			gc.set(GregorianCalendar.MONTH, month-1);
			gc.set(GregorianCalendar.DAY_OF_MONTH,day);
			
			/**
			 * hour不同
			 */
			gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gc.set(GregorianCalendar.MINUTE,0);
			gc.set(GregorianCalendar.SECOND, 0);
			long ret = gc.getTime().getTime();
			
	//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  00:00:00");
	//		String datestr = simpleDateFormat.format(ret);
	//		try{
	//			ret = simpleDateFormat.parse(datestr).getTime();
	//		}catch (Exception e) {
	//		}
			gc.setTime(new Date());
			return ret;
		}
		
		/**
		 * 得到两个日期的间隔天数
		 */
		public static long getDaysBetween(long startDate, long endDate){
			long days = 0;
			Calendar fromCalendar = Calendar.getInstance();
			fromCalendar.setTime(new Date(startDate));
			fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
			fromCalendar.set(Calendar.MINUTE, 0);
			fromCalendar.set(Calendar.SECOND, 0);
			fromCalendar.set(Calendar.MILLISECOND, 0);

			Calendar toCalendar = Calendar.getInstance();
			toCalendar.setTime(new Date(endDate));
			toCalendar.set(Calendar.HOUR_OF_DAY, 0);
			toCalendar.set(Calendar.MINUTE, 0);
			toCalendar.set(Calendar.SECOND, 0);
			toCalendar.set(Calendar.MILLISECOND, 0);
			days = ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
			return days;
		}
		 private static int getQuarterInMonth(int month, boolean isQuarterStart) {  
	       int months[] = { 0, 3, 6, 9 };  
	       if (month >= 0 && month <= 2)  
	           return months[0];  
	       else if (month >= 3 && month <= 5)  
	           return months[1];  
	       else if (month >= 6 && month <= 8)  
	           return months[2];  
	        else  
	            return months[3];  
	    } 
		 
		public static void main(String args[]){
			long  time = getNextMonthFirstDayTime(getCurrentTime());
			System.out.println(formatDateToTimeStr(time));
			System.out.println(formatDateToTimeStr(getOffsetDate(time, 2)));
			long end = getServiceEndDate(time,ProductCycle.Month.getCode());
			System.out.println(formatDateToTimeStr(getServiceEndDate(time,ProductCycle.Month.getCode())));
			System.out.println(formatDateToTimeStr(getOffsetDate(end, 2)));
			long en = Long.valueOf("1375459200000");
			System.out.println(formatDateToTimeStr(en));
		}

		
}

