package com.spark.oms.publish;
/**
 * ����״̬��δ����Ѹ���
 */
public enum ReceiptStatus {
	Receipt("1","�Ѹ���"),
	UnReceipt("0","δ����");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private ReceiptStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static ReceiptStatus getReceiptStatus(String code){
		if(Receipt.code.equals(code)){
			return Receipt;
		}else if(UnReceipt.code.equals(code)){
			return UnReceipt;
		}else{
			return null;
		}
	}
}
