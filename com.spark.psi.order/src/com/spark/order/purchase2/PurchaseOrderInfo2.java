package com.spark.order.purchase2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderFather;

public class PurchaseOrderInfo2 extends OrderFather{
	private GUID consigneeId;//	收货人Guid	GUID
	private String consignee;//	收货人	V(10)
	private String consigneeTel;//	收货人电话	V(20)
	private String consigneePhone;//	收货人手机	nvarchar	30
	private String address;//	收货地址	V(200)
	private double disAmount;//	整单折扣	N
	private GUID relevaId;//	仓库/客户GUID
	private String relevaName;//	仓库/客户名称
	//----------直供----------
	private GUID saleId; // 销售订单GUID，直供时候用
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
