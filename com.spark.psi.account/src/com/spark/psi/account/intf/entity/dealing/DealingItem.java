package com.spark.psi.account.intf.entity.dealing;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 往来明细实体
 * </p>
 * 
 */
public class DealingItem {

	public DealingItem(String billsType) {
		this.billsType = billsType;
	}
	
	public DealingItem()
	{
		
	}

	private GUID id;// 标识
	private GUID partnerId;// 客户供应商GUID
	private GUID billsId;// 单据GUID
	private String billsNo;// 单据编号
	private String billsType;// 单据类型
	private String receiptType;// 收款方式
	private double planAmount;// 应收/应付金额
	private double realAmount;// 实收/实付金额
	private double balance;// 应收/应付余额
	private long createDate;// 日期
	private String remark;// 备注
	private GUID accountBillsId;// 财务单据GUID
	private String accountBillsNo;// 财务单据编号
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

	public String getBillsType() {
		return billsType;
	}

	public void setBillsType(String billsType) {
		this.billsType = billsType;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
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

}
