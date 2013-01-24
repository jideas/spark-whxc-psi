package com.spark.psi.account.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.account.entity.ReceiptItem;

/**
 * �տ��¼�б���<br>
 * ��ѯ������ReceiptListEntity+GetReceiptLogListKey
 * 
 */
public class ReceiptItemImpl implements ReceiptItem{

	private GUID id;//	��ʶ
	private String receiptsNo;//	���
	private String partnerName;//	�տ����
	private GUID partnerId;//	�տ����
	private DealingsWay receiptMode;//	�տʽ
	private String reason;//	�տ�ԭ��
	private long receiptDate;//	�տ�����
	private ReceiptStatus status;//	״̬
	private double amount;//	�ܽ��
	private double receiptedAmount;//	���ս��
	private String remark;//	��ע
	private GUID creatorId;//	������
	private String creator;//	����������
	private long createDate;//	��������
	private ReceiptType receiptType;//	�տ�����
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
	

}
