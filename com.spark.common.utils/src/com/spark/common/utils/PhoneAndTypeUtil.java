package com.spark.common.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 电话号码及其类型处理工具
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author 莫迪
 * @version 2011-11-17
 */
public final class PhoneAndTypeUtil {
	public static String separator = "＠";
	private final static Map<String, PhoneType> phoneMap = new HashMap<String, PhoneType>();
	private PhoneAndTypeUtil() {

	}
	public enum PhoneType
	{
		/**
		 * 固话
		 */
		TELEPHONE("1", "固话"),
		/**
		 * 手机
		 */
		MOBILEPHONE("2", "手机"),
		/**
		 * 传真
		 */
		FAX("3", "传真");
		private String key;
		private String name;
		private PhoneType(String str, String name){
			this.key = str;
			this.name = name;
			phoneMap.put(str, this);
		}
		/**
		 * 获得类型标识
		 * @return String
		 */
		public String getKey() {
			return key;
		}
		
		/**
		 * 获得类型名称
		 * @return String
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * 根据标识获取固话类型
		 * @param key
		 * @return PhoneType
		 */
		public static PhoneType getPhoneType(String key){
			return phoneMap.get(key);
		}
	}

	private PhoneType type;// 电话号码类型
	private String tel;// 电话号码

	/**
	 * 获得拼接字符串
	 * @return String
	 */
	public String spell(){
		return spell(tel, type);
	}
	
	/**
	 * 电话+类型拼接
	 * 
	 * @param tel
	 * @param type
	 * @return String
	 */
	public static String spell(String tel, PhoneType type) {
		return type.getKey() + separator + tel;
	}

	/**
	 * 电话+类型拆分
	 * 
	 * @param telStr
	 * @return PhoneAndTypeUtil
	 */
	public static PhoneAndTypeUtil split(String telStr) {
		PhoneAndTypeUtil util = new PhoneAndTypeUtil();
		if(null == telStr){
			return util;
		}
		String[] strs = telStr.split(separator);
		if (strs.length > 1) {
			util.setTel(strs[1]);
			util.setType(PhoneType.getPhoneType(strs[0]));
		}
		return util;
	}

	/**
	 * 按出入first优先级自动筛选号码
	 * 
	 * @param mobile
	 *            手机
	 * @param tel
	 *            固话
	 * @param first
	 *            优先类型
	 * @return String
	 */
	public static PhoneAndTypeUtil selectTel(String mobile, String tel,
			PhoneType first) {
		PhoneAndTypeUtil util = new PhoneAndTypeUtil();
		switch (first) {
		case MOBILEPHONE:
			if(null == mobile){
				util.setType(PhoneType.TELEPHONE);
				util.setTel(tel == null?"":tel);
			}
			else{
				util.setType(PhoneType.MOBILEPHONE);
				util.setTel(mobile == null?"":mobile);
			}
			break;
		case TELEPHONE:
			if(null == tel){
				util.setType(PhoneType.MOBILEPHONE);
				util.setTel(mobile == null?"":mobile);
			}
			else{
				util.setType(PhoneType.TELEPHONE);
				util.setTel(tel == null?"":tel);
			}
			break;

		default:
			break;
		}
		return util;
	}

	/**
	 * @return the type
	 */
	public PhoneType getType() {
		return type;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(PhoneType type) {
		this.type = type;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
}
