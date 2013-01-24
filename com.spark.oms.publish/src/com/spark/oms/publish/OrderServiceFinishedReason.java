package com.spark.oms.publish;

/**
 * �������ԭ����������������ڣ���ֹ��δ����
 * @author mengyongfeng
 *
 */
public enum OrderServiceFinishedReason {
	/**
	 * ʹ��
	 */
	UnFinish("00","δ����"),
	/**
	 * ʹ��
	 */
	Expired("01","����"),
	Changed("02","���"),
	Extend("03","���"),
	Restore("06","�ָ�"),
	/**
	 * ʹ��
	 */
	Suspend("04","��ֹ"),
	/**
	 * �ӷ�����ֹ
	 */
	SuspendChidren("07",""),
	PreChangeOrder("08","���"),
	NonPayment("05","");
//	NonPayment("05","δ����");
	
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private OrderServiceFinishedReason(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OrderServiceFinishedReason getOrderServiceFinishedReason(String code){
		if(Expired.code.equals(code)){
			return Expired;
		}else if(Changed.code.equals(code)){
			return Changed;
		}else if(Suspend.code.equals(code)){
			return Suspend;
		}else if(Extend.code.equals(code)){
			return Extend;
		}else if(Restore.code.equals(code)){
			return Restore;
		}else if(NonPayment.code.equals(code)){
			return NonPayment;
		}else if(UnFinish.code.equals(code)){
			return UnFinish;
		}else if(PreChangeOrder.code.equals(code)){
			return PreChangeOrder;
		}else{
			return null;
		}
	}
}
