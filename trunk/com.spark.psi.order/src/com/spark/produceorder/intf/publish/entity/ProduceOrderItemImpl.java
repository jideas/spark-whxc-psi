package com.spark.produceorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;

/**
 * ���������б�<BR>
 * ��ѯ������<BR>
 * GetProduceOrderListKey+ProduceOrderListEntity
 * 
 */
public class ProduceOrderItemImpl implements ProduceOrderItem {

	private GUID id;//	�б�ʶ
	private String billsNo;//	�������
	private long planDate;//	�ƻ��������
	private double goodsCount;//	��Ʒ����
	private String remark;//	��ע
	private String creator;//	�Ƶ���
	private long createDate;//	�Ƶ�����
	private long finishDate;//	�������
	private ProduceOrderStatus status;//	״̬
	
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
