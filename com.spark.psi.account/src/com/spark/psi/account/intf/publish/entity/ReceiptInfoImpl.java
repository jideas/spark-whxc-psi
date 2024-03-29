package com.spark.psi.account.intf.publish.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.account.entity.ReceiptInfo;
import com.spark.psi.publish.account.entity.ReceiptInfoItem;
import com.spark.psi.publish.account.entity.ReceiptLog;

public class ReceiptInfoImpl implements ReceiptInfo {
	
	private GUID id;//	标识
	private String receiptsNo;//	编号
	private String partnerName;//	收款对象
	private GUID partnerId;//	收款对象
	private DealingsWay receiptMode;//	收款方式
	private long receiptDate;//	收款日期
	private ReceiptStatus status;//	状态
	private double amount;//	总金额
	private double receiptedAmount;//	已收金额
	private String remark;//	备注
	private GUID creatorId;//	创建人
	private String creator;//	创建人姓名
	private long createDate;//	创建日期
	private ReceiptType receiptType;//	收款类型

	private ReceiptInfoItemImpl items[];
	private ReceiptLogImpl[] logs;
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
	public DealingsWay getReceiptMode() {
		return receiptMode;
	}
	public void setReceiptMode(DealingsWay receiptMode) {
		this.receiptMode = receiptMode;
	}
	public long getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(long receiptDate) {
		this.receiptDate = receiptDate;
	}
	public ReceiptStatus getStatus() {
		return status;
	}
	public void setStatus(ReceiptStatus status) {
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
	public ReceiptType getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(ReceiptType receiptType) {
		this.receiptType = receiptType;
	}
	public ReceiptInfoItemImpl[] getItems() {
		return items;
	}
	public void setItems(ReceiptInfoItemImpl[] items) {
		this.items = items;
	}
	public ReceiptLogImpl[] getLogs() {
		return logs;
	}
	public void setLogs(ReceiptLogImpl[] logs) {
		this.logs = logs;
	}
	
	
}
