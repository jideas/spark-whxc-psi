package com.spark.psi.publish;
/**
 * ����״̬��δ����Ѹ���
 */
public enum ReceiptStatus {
	Submitting("01","���ύ"),
	Receipting("02","������"),
	Receipted("03","�����");
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
