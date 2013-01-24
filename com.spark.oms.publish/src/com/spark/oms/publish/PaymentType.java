package com.spark.oms.publish;
/**
 * ֧�����ͣ��ֽ�ת�ˣ�֧Ʊ
 */
public enum PaymentType {
	Cash("01","�ֽ�"),
	Check("02","֧Ʊ"),
	FromStore("04","�˻��ۿ�"),
	Transfer("03","ת��");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
