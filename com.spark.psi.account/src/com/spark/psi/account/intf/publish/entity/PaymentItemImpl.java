package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.entity.PaymentItem;

/**
 * 付款记录列表项<br>
 * 查询方法：ListEntry<PaymentItem>+GetPaymentListKey
 * 
 */
public class PaymentItemImpl implements PaymentItem {

	private GUID id;// 标识
	private String paymentNo;// 编号
	private GUID partnerId;// 付款对象
    private String partnerName;
	private PaymentType paymentType;// 付款类型
	private String denyReason;// 退回原因
	private long payDate;// 付款日期
	private String status;// 状态
	private double amount;// 总金额
	private double paidAmount;// 已付金额
	private String remark;// 备注
	private GUID approvePerson;// 审核人
	private String approvePersonName;// 审核人姓名
	private long approveDate;// 审核日期
	private GUID creatorId;// 创建人
	private String creator;// 创建人姓名
	private long createDate;// 创建日期
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
	public long getPayDate() {
		return payDate;
	}
	public void setPayDate(long payDate) {
		this.payDate = payDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public GUID getApprovePerson() {
		return approvePerson;
	}
	public void setApprovePerson(GUID approvePerson) {
		this.approvePerson = approvePerson;
	}
	public String getApprovePersonName() {
		return approvePersonName;
	}
	public void setApprovePersonName(String approvePersonName) {
		this.approvePersonName = approvePersonName;
	}
	public long getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(long approveDate) {
		this.approveDate = approveDate;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}
	public String getDenyReason() {
		return denyReason;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerName() {
		return partnerName;
	}	

}
