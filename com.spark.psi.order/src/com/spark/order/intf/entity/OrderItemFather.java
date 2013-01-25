package com.spark.order.intf.entity;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>������ϸ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-26
 */
public abstract class OrderItemFather extends EntityFather{
	@StructField
	protected GUID orderId;//	���۶���GUID	guid	
	@StructField
	protected GUID goodsItemId;//	��Ʒ���GUID	guid	
	@StructField
	protected String goodsCode;//	��Ʒ����	nvarchar	20
	@StructField
	protected String goodsName;//	��Ʒ����	nvarchar	50
	@StructField
	protected String goodsProperties;//	��Ʒ����	nvarchar	1000
	@StructField
	protected String goodsUnit;//	��λ	nvarchar	10
	@StructField
	protected int countDecimal;//	��ƷС��λ��	int	
	@StructField
	protected double price;//	����	numeric	17
	@StructField
	protected double num	;//����	numeric	17
	@StructField
	protected double amount;//	���	numeric	17
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
