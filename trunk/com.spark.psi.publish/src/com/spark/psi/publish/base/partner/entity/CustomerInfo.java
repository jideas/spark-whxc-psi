package com.spark.psi.publish.base.partner.entity;

/**
 * 客户维护实体
 * 
 * @author zhoulijun
 * 
 */
public interface CustomerInfo extends PartnerInfo {
	/**
	 * 价格策略
	 */
	public String getPricePolicy();

	public String getCustomerType();

	public String getTaxNumber();
}
