package com.spark.oms.publish;

import com.spark.uac.publish.ProductSerialsEnum;

/**
 * ��Ʒ��𣺼�����Ʒ�����޲�Ʒ
 * @author mengyongfeng
 *
 */
public enum ProductCategory {
	Lease("LS","���޲�Ʒ"),
	Piece("PS","������Ʒ");
	/**
	 * ����
	 */
	private String code;

	/**
	 * ����
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
