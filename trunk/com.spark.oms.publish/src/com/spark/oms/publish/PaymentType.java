package com.spark.oms.publish;
/**
 * 支付类型：现金，转账，支票
 */
public enum PaymentType {
	Cash("01","现金"),
	Check("02","支票"),
	FromStore("04","账户扣款"),
	Transfer("03","转账");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private PaymentType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static PaymentType getPaymentType(String code){
		if(Cash.code.equals(code)){
			return Cash;
		}else if(Check.code.equals(code)){
			return Check;
		}else if(FromStore.code.equals(code)){
			return FromStore;
		}else if(Transfer.code.equals(code)){
			return Transfer;
		}else{
			return null;
		}
	}
}
