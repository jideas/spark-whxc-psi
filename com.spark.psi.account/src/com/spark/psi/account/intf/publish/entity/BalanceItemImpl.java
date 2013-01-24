package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.account.entity.BalanceItem;

/**
 * 客户或者供应商的应收或者应付信息<BR>
 * 查询方法：<br>
 * (1)查询所有客户的BalanceItem列表：ListEntry<BalanceItem>+GetCustomerBalanceListKey<br>
 * (2)查询所有供应商的BalanceItem列表：ListEntry<BalanceItem>+GetSupplierBalanceListKey<br>
 */
public class BalanceItemImpl implements BalanceItem{

	private GUID id;//	标识
	private GUID partnerId;//	
	private String partnerName;//	客户供应商全称
	private String namePY;//	名称拼音
	private String partnerShortName;//	客户供应商简称
	private String type;//	类型（客户/供应商）
	private double amount;//	应收/应付
	private double initAmount;//	初始化应收/应付
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getPartnerShortName() {
		return partnerShortName;
	}
	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getInitAmount() {
		return initAmount;
	}
	public void setInitAmount(double initAmount) {
		this.initAmount = initAmount;
	}
	
	
}
