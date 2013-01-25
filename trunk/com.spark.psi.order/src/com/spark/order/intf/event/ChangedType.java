package com.spark.order.intf.event;



/**
 * <p>ִ�ж���ö�٣������¼���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-15
 */
public enum ChangedType {
	/**
	 * ����
	 */
	SAVE("save"),
	/**
	 * �ύ
	 */
	Submit("submit"),
	/**
	 * ����
	 */
	Approval("approval"),
	/**
	 * ��Ч��
	 */
	Effectual("effectual"),
	/**
	 * ��ֹ
	 */
	Stop("stop"),
	/**
	 * ����ִ��
	 */
	Execut("execut"),
	/**
	 * ȷ�Ϸ���
	 */
	consignment("consignment"),
	/**
	 * ����
	 */
	Deny("deny"),
	/**
	 * �������
	 */
	StoreFinish("storeFinish"),
	/**
	 * ���
	 */
	Finish("finish"),
	/**
	 * ������
	 */
	Distribute("distribute"),
	/**
	 * ɾ��
	 */
	Delete("delete");
	private String code;
	
	private ChangedType(String code){
		this.code = code;
		OrderChangedEvent.changedTypeMap.put(code.toUpperCase(), this);
	}
	/**
	 * ����ַ�����־
	 * @return String
	 */
	public String toString() {
		return code;
	}
	/**
	 * ͨ���ַ�����־���ö��
	 * @param str
	 * @return ChangedType
	 */
	public static ChangedType getChangedType(String str){
		if(null==str){
			return null;
		}
		return OrderChangedEvent.changedTypeMap.get(str.toUpperCase());
	}
}
