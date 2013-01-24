package com.spark.psi.publish;

public enum PaymentStatus {


	Submitting("01","���ύ"),
	Submitted("02","�����"),
	Paying("03","������"),
	Paid("04","�����"),
	Deny("05","���˻�"),;
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
