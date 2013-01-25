package com.spark.psi.publish;
/**
 * 付款状态：未付款，已付款
 */
public enum ReceiptStatus {
	Submitting("01","待提交"),
	Receipting("02","进行中"),
	Receipted("03","已完成");
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
		if(Submitting.code.equals(code)){
			return Submitting;
		}else if(Receipting.code.equals(code)){
			return Receipting;
		}
		else if(Receipted.code.equals(code)){
			return Receipted;
		}else{
			return null;
		}
	}
}
