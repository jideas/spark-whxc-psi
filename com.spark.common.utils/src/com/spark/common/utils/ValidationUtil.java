package com.spark.common.utils;

/**
 * 
 * <p>通用基础验证类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-16
 */
public class ValidationUtil{
	
	/**
	 * 
	 * <p>日期验证格式</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author Zhoulj
	 * @version 2011-5-16
	 */
	public enum DateFormat{
		/**
		 * 标准日期格式 yyyy-MM-dd
		 */
		DEFAULT(new Checker(){

			public boolean validation(Object obj){	         
	            return true;
            }
			
		}),
		/**
		 * 月份格式 yyyyMM
		 */
		YYYYMM(new Checker(){

			public boolean validation(Object obj){
	            // TODO Auto-generated method stub
	            return true;
            }
			
		});
		
		protected interface Checker{
			public boolean validation(Object obj);
		}
		
		private Checker checker;
		DateFormat(Checker checker){
			this.checker = checker;
		}
		
		protected boolean check(Object obj){
			return checker.validation(obj);
		}
	}
	
	/**
	 * 验证是否为空
	 * obj == null return true;
	 * obj.length()==0 return true;
	 * @param obj 要验证的对象
 	 * @return boolean
	 */
	public static boolean isEmpty(Object obj){
		if(null == obj){
			return true;
		}
		if(obj instanceof String){
			if(obj.toString().trim().length()==0)
				return true;
		}
		return false;
	}
	
	/**
	 * 验证是否数字
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isNumber(Object obj){
		return true;
	}
	
	/**
	 * 验证是否日期格式
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isDate(Object obj){
		return DateFormat.DEFAULT.check(obj);
	}
	
	/**
	 * 验证输入对象是否是指定格式的日期字符串
	 * 
	 * @param obj
	 * @param format 日期格式枚举值
	 * @return boolean
	 */
	public static boolean isDateForDateForamt(Object obj,DateFormat format){
	    return format.check(obj);
    }
	
	/**
	 * 验证输入对象是否是中华人民共和国身份证格式
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isChineID(Object obj){
		return true;
	}
	
	/**
	 * 验证输入对象是否是电话号码
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isPhone(Object obj){
		return true;
	}
	
	/**
	 * 验证输入对象是否是手机号码
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isMoblie(Object obj){
		return true;
	}
	
	/**
	 * 验证输入内容是否是正确的密码格式
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isPassword(Object obj){
		return true;
	}
	
	/**
	 * 验证输入内容是否是正确的邮箱地址格式
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isEmail(Object obj){
		return true;
	}
	
	/**
	 * 验证输入的内容是否是正确的网址格式
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isURL(Object obj){
		return true;
	}
	
	/**
	 * 返回验证最大输入长度的正则表达式
	 * 
	 * @param length 需要匹配的长度
	 * @return String
	 */
	public static String getLengthMaxReg(int length){
		return new StringBuilder("^.{0,").append(length).append("}$").toString();
	}
}
