package com.spark.oms.publish;
/**
 * 产品状态：在售，停售
 */
public enum ProductStatus {
	onSale("01","在售"),
	unSale("02","停售");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
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
