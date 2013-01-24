package com.spark.oms.publish.product.key;
/**
 * 获取产品列表
 * GetProductItemKey & ProductListIntf
 */
public class GetProductItemKey {
	/**
	 * 产品类别
	 */
	private String category;
		
	/**
	 * 产品名称:编码,系列
	 */
	private String key;
	
	/**
	 * 产品状态
	 */
	private String status;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
