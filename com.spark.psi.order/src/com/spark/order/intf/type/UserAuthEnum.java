package com.spark.order.intf.type;

/**
 * <p>用户职责</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */
public enum UserAuthEnum {
	/**
	 * 总经理 
	 */
	BOSS,
	/**
	 * 经理
	 */
	MANGER,
	/**
	 * 员工
	 */
	EMPLOYEE;
	public boolean isIn(UserAuthEnum...enums){
		for(UserAuthEnum auth : enums){
			if(this == auth){
				return true;
			}
		}
		return false;
	}
}
