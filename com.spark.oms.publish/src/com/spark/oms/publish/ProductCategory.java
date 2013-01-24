package com.spark.oms.publish;

import com.spark.uac.publish.ProductSerialsEnum;

/**
 * 产品类别：计量产品，租赁产品
 * @author mengyongfeng
 *
 */
public enum ProductCategory {
	Lease("LS","租赁产品"),
	Piece("PS","计量产品");
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;
	
	private ProductCategory(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public static ProductCategory getProductCategory(String code){
		if(Lease.code.equals(code)){
			return Lease;
		}else if(Piece.code.equals(code)){
			return Piece;
		}else{
			return null;
		}
	}
	
	public static ProductCategory getProductCategory(ProductSerialsEnum productSeries){
		String code = productSeries.getCode().substring(0,2);
		if(Lease.code.equals(code)){
			return Lease;
		}else if(Piece.code.equals(code)){
			return Piece;
		}else{
			return null;
		}
	}
	
}
