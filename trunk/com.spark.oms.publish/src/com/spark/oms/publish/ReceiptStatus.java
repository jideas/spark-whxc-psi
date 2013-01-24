package com.spark.oms.publish;
/**
 * 付款状态：未付款，已付款
 */
public enum ReceiptStatus {
	Receipt("1","已付款"),
	UnReceipt("0","未付款");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
