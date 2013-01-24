package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.account.entity.BalanceInfoItem;

/**
 * ������ϸ��Ŀ ��ѯ������<br>
 * (1)��ѯָ���ͻ����߹�Ӧ�̵�������ϸ�б�ListEntry<DealingsItem>+GetDealingsListKey<br>
 */
public class BalanceInfoItemImpl implements BalanceInfoItem{
	private GUID id;//	��ʶ
	private GUID partnerId;//	�ͻ���Ӧ��GUID
	private GUID billsId;//	����GUID
	private String billsNo;//	���ݱ��
	private DealingsType billsType;//	��������
	private DealingsWay receiptType;//	�տʽ
	private double planAmount;//	Ӧ��/Ӧ�����
	private double realAmount;//	ʵ��/ʵ�����
	private double balance;//	Ӧ��/Ӧ�����
	private long createDate;//	����
	private String remark;//	��ע
	private GUID accountBillsId;//	���񵥾�GUID
	private String accountBillsNo;//	���񵥾ݱ��
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
