package com.spark.order.intf.entity;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>订单明细抽象类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-26
 */
public abstract class OrderItemFather extends EntityFather{
	@StructField
	protected GUID orderId;//	销售订单GUID	guid	
	@StructField
	protected GUID goodsItemId;//	商品规格GUID	guid	
	@StructField
	protected String goodsCode;//	商品条码	nvarchar	20
	@StructField
	protected String goodsName;//	商品名称	nvarchar	50
	@StructField
	protected String goodsProperties;//	商品属性	nvarchar	1000
	@StructField
	protected String goodsUnit;//	单位	nvarchar	10
	@StructField
	protected int countDecimal;//	商品小数位数	int	
	@StructField
	protected double price;//	单价	numeric	17
	@StructField
	protected double num	;//数量	numeric	17
	@StructField
	protected double amount;//	金额	numeric	17
	public GUID getOrderId() {
		return orderId;
	}
	public void setOrderId(GUID orderId) {
		this.orderId = orderId;
	}
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
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
	public String getGoodsProperties() {
		return goodsProperties;
	}
	public void setGoodsProperties(String goodsProperties) {
		this.goodsProperties = goodsProperties;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public int getScale() {
		return countDecimal;
	}
	public void setCountDecimal(int countDecimal) {
		this.countDecimal = countDecimal;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
