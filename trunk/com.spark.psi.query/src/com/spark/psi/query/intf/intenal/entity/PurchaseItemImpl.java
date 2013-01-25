package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.internal.service.DataConverterUtil;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.query.intf.publish.entity.PurchaseItem;

/**
 * 采购订单查询实体<BR>
 * 查询方法：<BR>
 * PurchaseListEntity+GetPurchaseListKey;
 *
 */
public class PurchaseItemImpl implements PurchaseItem {

	private String supplierNo;
	private String supplierName;
	private String shortName;
	private GUID supplierId;
	private String billsNo;
	private GUID billsId;
	private GUID goodsId;//	商品Guid
	private String goodsCode;//	商品编码
	private String goodsName;//	商品名称
	private String unit;//	单位
	private double price;//	单价
	private double count;//	数量
	private double amount;//	金额
	private double checkedinCount;
	private double checkedinAmount;
	private double checkinCount;
	private double checkinAmount;
	private long createDate;
	private long deliveryDate;
	private OrderStatus status;
	private double standardPrice;
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public GUID getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public GUID getBillsId() {
		return billsId;
	}
	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCheckedinCount() {
		return checkedinCount;
	}
	public void setCheckedinCount(double checkedinCount) {
		this.checkedinCount = checkedinCount;
	}
	public double getCheckedinAmount() {
		return checkedinAmount;
	}
	public void setCheckedinAmount(double checkedinAmount) {
		this.checkedinAmount = checkedinAmount;
	}
	public double getCheckinCount() {
		return checkinCount;
	}
	public void setCheckinCount(double checkinCount) {
		this.checkinCount = checkinCount;
	}
	public double getCheckinAmount() {
		return checkinAmount;
	}
	public void setCheckinAmount(double checkinAmount) {
		this.checkinAmount = checkinAmount;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public long getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(String statusStr) {
		this.status = DataConverterUtil.getOrderStatus(BillsEnum.PURCHASE, TypeEnum.getType(TypeEnum.PLAIN.getKey()), statusStr);
		
	}
	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}
	public double getStandardPrice() {
		return standardPrice;
	}
	
	

}
