package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.account.entity.BalanceInfoItem;

/**
 * 往来明细条目 查询方法：<br>
 * (1)查询指定客户或者供应商的往来明细列表：ListEntry<DealingsItem>+GetDealingsListKey<br>
 */
public class BalanceInfoItemImpl implements BalanceInfoItem{
	private GUID id;//	标识
	private GUID partnerId;//	客户供应商GUID
	private GUID billsId;//	单据GUID
	private String billsNo;//	单据编号
	private DealingsType billsType;//	单据类型
	private DealingsWay receiptType;//	收款方式
	private double planAmount;//	应收/应付金额
	private double realAmount;//	实收/实付金额
	private double balance;//	应收/应付余额
	private long createDate;//	日期
	private String remark;//	备注
	private GUID accountBillsId;//	财务单据GUID
	private String accountBillsNo;//	财务单据编号
	private boolean showLink;

	public GUID getAccountBillsId() {
		return accountBillsId;
	}
	public void setAccountBillsId(GUID accountBillsId) {
		this.accountBillsId = accountBillsId;
	}
	public String getAccountBillsNo() {
		return accountBillsNo;
	}
	public void setAccountBillsNo(String accountBillsNo) {
		this.accountBillsNo = accountBillsNo;
	}
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
	public GUID getBillsId() {
		return billsId;
	}
	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
	}
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public DealingsType getBillsType() {
		return billsType;
	}
	public void setBillsType(DealingsType billsType) {
		this.billsType = billsType;
	}
	public DealingsWay getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(DealingsWay receiptType) {
		this.receiptType = receiptType;
	}
	public double getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(double planAmount) {
		this.planAmount = planAmount;
	}
	public double getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setShowLink(boolean showLink) {
		this.showLink = showLink;
	}
	public boolean isShowLink() {
		return showLink;
	}
	
}
