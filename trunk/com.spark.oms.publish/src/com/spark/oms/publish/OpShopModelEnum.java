package com.spark.oms.publish;

public enum OpShopModelEnum {
	Store("01","ʵ���"),
	NetShop("02","����"),
	All("03","ʵ�������");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
	 */
	private String name;
	
	private OpShopModelEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static OpShopModelEnum getOpShopModel(String code){
		if(Store.code.equals(code)){
			return Store;
		}else if(NetShop.code.equals(code)){
			return NetShop;
		}else if(All.code.equals(code)){
			return All;
		}else{
			return null;
		}
	}
}
