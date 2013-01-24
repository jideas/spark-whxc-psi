package com.spark.psi.account.intf.entity.jointSettlement;

import com.jiuqi.dna.core.type.GUID;

public class JointSettlementEntity {

	private GUID id;
	private String sheetNo;
	private String supplierName;
	private String namePY;
	private String shortName;
	private String supplierNo;
	private GUID supplierId;
	private long beginDate;
	private long endDate;
	private double salesAmount;
	private double percentageAmount;
	private double adjustAmount;
	private double molingAmount;
	private double amount;
	private double paidAmount;
	private GUID creatorId;
	private String creator;
	private long createDate;
	private String status;
	private String remark;
	private String recordIds;
	private String denyReason;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRecordIds() {
		return recordIds;
	}
	public void setRecordIds(String recordIds) {
		this.recordIds = recordIds;
	}
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getNamePY() {
		return namePY;
	}
	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public GUID getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}
	public long getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public double getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}
	public double getPercentageAmount() {
		return percentageAmount;
	}
	public void setPercentageAmount(double percentageAmount) {
		this.percentageAmount = percentageAmount;
	}
	public double getAdjustAmount() {
		return adjustAmount;
	}
	public void setAdjustAmount(double adjustAmount) {
		this.adjustAmount = adjustAmount;
	}
	public double getMolingAmount() {
		return molingAmount;
	}
	public void setMolingAmount(double molingAmount) {
		this.molingAmount = molingAmount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}
	public String getDenyReason() {
		return denyReason;
	}

	
}
