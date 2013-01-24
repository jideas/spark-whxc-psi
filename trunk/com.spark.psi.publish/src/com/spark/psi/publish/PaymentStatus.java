package com.spark.psi.publish;

public enum PaymentStatus {


	Submitting("01","待提交"),
	Submitted("02","待审核"),
	Paying("03","进行中"),
	Paid("04","已完成"),
	Deny("05","已退回"),;
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private PaymentStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static PaymentStatus getPaymentStatus(String code){
		if(Submitting.code.equals(code)){
			return Submitting;
		}else if(Submitted.code.equals(code)){
			return Submitted;
		}
		else if(Paying.code.equals(code)){
			return Paying;
		}
		else if(Paid.code.equals(code)){
			return Paid;
		}
		else if(Deny.code.equals(code)){
			return Deny;
		}else{
			return null;
		}
	}

}
