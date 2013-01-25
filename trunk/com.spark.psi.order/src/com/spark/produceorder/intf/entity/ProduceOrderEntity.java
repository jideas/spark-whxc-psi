package com.spark.produceorder.intf.entity;

import com.jiuqi.dna.core.type.GUID;

public class ProduceOrderEntity {

	private GUID id;//	行标识
	private String billsNo;//	订单编号
	private long planDate;//	计划完成日期
	private double goodsCount;//	商品数量
	private String remark;//	备注
	private String creator;//	制单人
	private long createDate;//	制单日期
	private long finishDate;//	完成日期
	private String status;//	状态
	private String rejectReason;
	private String approvePerson;//	审核人	
	private long approveDate;//	审核时间

	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public long getPlanDate() {
		return planDate;
	}
	public void setPlanDate(long planDate) {
		this.planDate = planDate;
	}
	public double getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(double goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public long getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(long finishDate) {
		this.finishDate = finishDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprovePerson() {
		return approvePerson;
	}
	public void setApprovePerson(String approvePerson) {
		this.approvePerson = approvePerson;
	}
	public long getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(long approveDate) {
		this.approveDate = approveDate;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
}
