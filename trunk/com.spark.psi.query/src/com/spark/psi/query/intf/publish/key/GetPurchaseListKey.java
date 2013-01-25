package com.spark.psi.query.intf.publish.key;

import com.spark.psi.publish.LimitKey;

public class GetPurchaseListKey extends LimitKey {

	public GetPurchaseListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private String supplierNo;
	private String supplierName;
	private String billsNo;
	private String goodsCode;//	��Ʒ����
	private String goodsName;//	��Ʒ����
	private long createDateBegin; //�µ�����
	private long deliveryDateEnd;//��������
	private long createDateEnd; //�µ�����
	private long deliveryDateBegin;//��������
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public long getCreateDateBegin() {
		return createDateBegin;
	}
	public void setCreateDateBegin(long createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	public long getDeliveryDateEnd() {
		return deliveryDateEnd;
	}
	public void setDeliveryDateEnd(long deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}
	public long getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(long createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public long getDeliveryDateBegin() {
		return deliveryDateBegin;
	}
	public void setDeliveryDateBegin(long deliveryDateBegin) {
		this.deliveryDateBegin = deliveryDateBegin;
	} 
}
