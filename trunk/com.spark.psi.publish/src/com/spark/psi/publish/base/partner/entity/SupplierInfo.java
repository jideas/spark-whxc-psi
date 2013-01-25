/**
 * 
 */
package com.spark.psi.publish.base.partner.entity;

import java.util.List;

/**
 * @author 客户供应商
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
