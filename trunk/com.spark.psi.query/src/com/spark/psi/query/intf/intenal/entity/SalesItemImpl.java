package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.internal.service.DataConverterUtil;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.query.intf.publish.entity.SalesItem;

/**
 * 销售订单查询实体<BR>
 * 查询方法：<BR>
 * SalesListEntity+GetSalesListKey;
 *
 */
public class SalesItemImpl implements SalesItem {

	private GUID billsId;//	销售订单GUID
	private GUID goodsId;//	商品ID
	private String goodsCode;//	商品编码
	private String goodsNo;//	商品条码
	private String goodsName;//	商品名称
	private String unit;//	单位
	private double price;//	单价
	private double count;//	数量
	private double amount;//	金额
	private double checkedoutCount;// 交货数量
	private double checkedoutAmount;//交货金额
	private double checkoutCount;// 交货数量
	private double checkoutAmount;//交货金额 
	private String billsNo;
	private long createDate;
	private long deliveryDate;
	private String customerShortName;
	private String customerName;
	private String sheetNo;
	private GUID customerId;
	private String customerNo;
	private OrderStatus status;
	
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
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
	public double getCheckedoutCount() {
		return checkedoutCount;
	}
	public void setCheckedoutCount(double checkedoutCount) {
		this.checkedoutCount = checkedoutCount;
	}
	public double getCheckedoutAmount() {
		return checkedoutAmount;
	}
	public void setCheckedoutAmount(double checkedoutAmount) {
		this.checkedoutAmount = checkedoutAmount;
	}
	public double getCheckoutCount() {
		return checkoutCount;
	}
	public void setCheckoutCount(double checkoutCount) {
		this.checkoutCount = checkoutCount;
	}
	public double getCheckoutAmount() {
		return checkoutAmount;
	}
	public void setCheckoutAmount(double checkoutAmount) {
		this.checkoutAmount = checkoutAmount;
	}
	public String getCustomerShortName() {
		return customerShortName;
	}
	public void setCustomerShortName(String customerShortName) {
		this.customerShortName = customerShortName;
	}
	public GUID getCustomerId() {
		return customerId;
	}
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(String statusStr) {
		this.status = DataConverterUtil.getOrderStatus(BillsEnum.PURCHASE, TypeEnum.getType(TypeEnum.PLAIN.getKey()), statusStr);
	}
	

	
}
