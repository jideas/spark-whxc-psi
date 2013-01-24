package com.spark.common.utils;

/**
 * 
 * <p>ͨ�û�����֤��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-16
 */
public class ValidationUtil{
	
	/**
	 * 
	 * <p>������֤��ʽ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author Zhoulj
	 * @version 2011-5-16
	 */
	public enum DateFormat{
		/**
		 * ��׼���ڸ�ʽ yyyy-MM-dd
		 */
		DEFAULT(new Checker(){

			public boolean validation(Object obj){	         
	            return true;
            }
			
		}),
		/**
		 * �·ݸ�ʽ yyyyMM
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
	 * ��֤�Ƿ�Ϊ��
	 * obj == null return true;
	 * obj.length()==0 return true;
	 * @param obj Ҫ��֤�Ķ���
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
	 * ��֤�Ƿ�����
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isNumber(Object obj){
		return true;
	}
	
	/**
	 * ��֤�Ƿ����ڸ�ʽ
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isDate(Object obj){
		return DateFormat.DEFAULT.check(obj);
	}
	
	/**
	 * ��֤��������Ƿ���ָ����ʽ�������ַ���
	 * 
	 * @param obj
	 * @param format ���ڸ�ʽö��ֵ
	 * @return boolean
	 */
	public static boolean isDateForDateForamt(Object obj,DateFormat format){
	    return format.check(obj);
    }
	
	/**
	 * ��֤��������Ƿ����л����񹲺͹����֤��ʽ
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isChineID(Object obj){
		return true;
	}
	
	/**
	 * ��֤��������Ƿ��ǵ绰����
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isPhone(Object obj){
		return true;
	}
	
	/**
	 * ��֤��������Ƿ����ֻ�����
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isMoblie(Object obj){
		return true;
	}
	
	/**
	 * ��֤���������Ƿ�����ȷ�������ʽ
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isPassword(Object obj){
		return true;
	}
	
	/**
	 * ��֤���������Ƿ�����ȷ�������ַ��ʽ
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isEmail(Object obj){
		return true;
	}
	
	/**
	 * ��֤����������Ƿ�����ȷ����ַ��ʽ
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isURL(Object obj){
		return true;
	}
	
	/**
	 * ������֤������볤�ȵ�������ʽ
	 * 
	 * @param length ��Ҫƥ��ĳ���
	 * @return String
	 */
	public static String getLengthMaxReg(int length){
		return new StringBuilder("^.{0,").append(length).append("}$").toString();
	}
}
