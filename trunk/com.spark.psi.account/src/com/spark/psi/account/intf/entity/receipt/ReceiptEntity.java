package com.spark.psi.account.intf.entity.receipt;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * 收款纪录类
 * </p>
 * 
 */

public class ReceiptEntity {
	private GUID id;// 标识
	private String receiptsNo;// 编号
	private String partnerName;// 收款对象
	private GUID partnerId;// 收款对象
	private String receiptMode;// 收款方式
	private String reason;// 收款原因
	private long receiptDate;// 收款日期
	private String status;// 状态
	private double amount;// 总金额
	private double receiptedAmount;// 已收金额
	private String remark;// 备注
	private GUID creatorId;// 创建人
	private String creator;// 创建人姓名
	private long createDate;// 创建日期
	private String receiptType;// 收款类型
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getReceiptsNo() {
		return receiptsNo;
	}
	public void setReceiptsNo(String receiptsNo) {
		this.receiptsNo = receiptsNo;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public String getReceiptMode() {
		return receiptMode;
	}
	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(long receiptDate) {
		this.receiptDate = receiptDate;
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
	public double getReceiptedAmount() {
		return receiptedAmount;
	}
	public void setReceiptedAmount(double receiptedAmount) {
		this.receiptedAmount = receiptedAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	
}
