package com.spark.psi.publish.base.partner.entity;

/**
 * �ͻ�ά��ʵ��
 * 
 * @author zhoulijun
 * 
 */
public interface CustomerInfo extends PartnerInfo {
	/**
	 * �۸����
	 */
	public String getPricePolicy();

	public String getCustomerType();

	public String getTaxNumber();
}
