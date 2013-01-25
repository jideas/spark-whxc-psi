package com.spark.produceorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;

/**
 * 生产订单列表<BR>
 * 查询方法：<BR>
 * GetProduceOrderListKey+ProduceOrderListEntity
 * 
 */
public class ProduceOrderItemImpl implements ProduceOrderItem {

	private GUID id;//	行标识
	private String billsNo;//	订单编号
	private long planDate;//	计划完成日期
	private double goodsCount;//	商品数量
	private String remark;//	备注
	private String creator;//	制单人
	private long createDate;//	制单日期
	private long finishDate;//	完成日期
	private ProduceOrderStatus status;//	状态
	
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
	public ProduceOrderStatus getStatus() {
		return status;
	}
	public void setStatus(ProduceOrderStatus status) {
		this.status = status;
	}

}
