package com.spark.oms.publish;

/**
 * �������ֲ������տ�˿��Ʊ���ۿ�
 */
public enum PayAction {
	Receipt("01","�տ�"),
	Refund("02","����"),
	DrawBill("04","��Ʊ"),
	Income("03","�ۿ�");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
