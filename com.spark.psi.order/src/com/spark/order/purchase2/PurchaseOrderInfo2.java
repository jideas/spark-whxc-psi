package com.spark.order.purchase2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderFather;

public class PurchaseOrderInfo2 extends OrderFather{
	private GUID consigneeId;//	�ջ���Guid	GUID
	private String consignee;//	�ջ���	V(10)
	private String consigneeTel;//	�ջ��˵绰	V(20)
	private String consigneePhone;//	�ջ����ֻ�	nvarchar	30
	private String address;//	�ջ���ַ	V(200)
	private double disAmount;//	�����ۿ�	N
	private GUID relevaId;//	�ֿ�/�ͻ�GUID
	private String relevaName;//	�ֿ�/�ͻ�����
	//----------ֱ��----------
	private GUID saleId; // ���۶���GUID��ֱ��ʱ����
	public GUID getConsigneeId() {
		return consigneeId;
	}
	public void setConsigneeId(GUID consigneeId) {
		this.consigneeId = consigneeId;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsigneeTel() {
		return consigneeTel;
	}
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(double disAmount) {
		this.disAmount = disAmount;
	}
	public GUID getRelevaId() {
		return relevaId;
	}
	public void setRelevaId(GUID relevaId) {
		this.relevaId = relevaId;
	}
	public String getRelevaName() {
		return relevaName;
	}
	public void setRelevaName(String relevaName) {
		this.relevaName = relevaName;
	}
	public GUID getSaleId() {
		return saleId;
	}
	public void setSaleId(GUID saleId) {
		this.saleId = saleId;
	}
}
