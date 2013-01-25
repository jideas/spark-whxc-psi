package com.spark.order.intf.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;

/**
 * µ•æ›∏∏¿‡
 */
@StructClass
public class OrderInfo {

	private GUID RECID;
	private String billsNo;
	private String billType;
	private GUID partnerId;
	private String fax;
	private String partnerShortName;
	private String partnerName;
	private String partnerNamePY;
	private String address;
	private String rejectReason;
	private String stopReason;
	private String remark;
	private double totalAmount;
	private GUID creatorId;
	private String creator;
	private long createDate;
	private GUID deptId;
	private String status;
	private boolean isStoped;
	private String approveStr;
	private long finishedDate;
	private String linkman;
	private long deliveryDate;

	private GUID storeId;
	private String storeName;
	private String oldStatus,newStatus;

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public long getDeliveryDate() {
		return deliveryDate;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public GUID getRECID() {
		return RECID;
	}

	public void setRECID(GUID rECID) {
		RECID = rECID;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerShortName() {
		return partnerShortName;
	}

	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerNamePY() {
		return partnerNamePY;
	}

	public void setPartnerNamePY(String partnerNamePY) {
		this.partnerNamePY = partnerNamePY;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
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

	public GUID getDeptId() {
		return deptId;
	}

	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isStoped() {
		return isStoped;
	}

	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}

	public String getApproveStr() {
		return approveStr;
	}

	public void setApproveStr(String approveStr) {
		this.approveStr = approveStr;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(long finishedDate) {
		this.finishedDate = finishedDate;
	}

}
