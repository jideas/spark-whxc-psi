package com.spark.oms.publish;
/**
 * ��Ʒ״̬�����ۣ�ͣ��
 */
public enum ProductStatus {
	onSale("01","����"),
	unSale("02","ͣ��");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private ProductStatus(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static ProductStatus getProductStatus(String code){
		if(onSale.code.equals(code)){
			return onSale;
		}else if(unSale.code.equals(code)){
			return unSale;
		}else{
			return null;
		}
	}
	
	
}
