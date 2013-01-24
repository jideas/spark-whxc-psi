/**
 * 
 */
package com.spark.psi.publish.base.partner.entity;

import java.util.List;

/**
 * @author �ͻ���Ӧ��
 * 
 */
public interface SupplierInfo extends PartnerInfo {

	public String getSupplierType();

	public String getVatNo();

	public double getTaxRate();

	public String getNoticeAddress();

	public String getNoticePostcode();

	public List<BankAccountItem> getBanks();

	public String getSupplierCooperation();
}
