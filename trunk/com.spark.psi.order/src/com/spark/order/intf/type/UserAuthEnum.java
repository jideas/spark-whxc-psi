package com.spark.order.intf.type;

/**
 * <p>�û�ְ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-9
 */
public enum UserAuthEnum {
	/**
	 * �ܾ��� 
	 */
	BOSS,
	/**
	 * ����
	 */
	MANGER,
	/**
	 * Ա��
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
