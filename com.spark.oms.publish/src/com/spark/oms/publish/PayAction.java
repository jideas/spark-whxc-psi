package com.spark.oms.publish;

/**
 * 定义四种操作：收款，退款，开票，扣款
 */
public enum PayAction {
	Receipt("01","收款"),
	Refund("02","付款"),
	DrawBill("04","开票"),
	Income("03","扣款");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private PayAction(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static PayAction getPayAction(String code){
		if(Receipt.code.equals(code)){
			return Receipt;
		}else if(Refund.code.equals(code)){
			return Refund;
		}else if(DrawBill.code.equals(code)){
			return DrawBill;
		}else if(Income.code.equals(code)){
			return Income;
		}{
			return null;
		}
	}
}
