package com.spark.psi.query.intf.publish.key;

import com.spark.psi.publish.LimitKey;

public class GetSalesListKey extends LimitKey {

	public GetSalesListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	private String goodsCode;// ��Ʒ����
	private String goodsNo;// ��Ʒ����
	private String goodsName;// ��Ʒ����
	private String billsNo;
	private long createDateBegin; // �µ�����
	private long deliveryDateEnd;// ��������
	private long createDateEnd; // �µ�����
	private long deliveryDateBegin;// ��������
	private String customerName;

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
