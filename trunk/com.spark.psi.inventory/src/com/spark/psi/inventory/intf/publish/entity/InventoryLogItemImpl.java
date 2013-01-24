package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.inventory.entity.InventoryLogItem;

/**
 * 库存流水项<br>
 * 查询方法：ListEntry<InventoryLogItem>+GetInventoryLogKey
 */
public class InventoryLogItemImpl implements InventoryLogItem{

	/**
	 * 流水ID
	 */
	private GUID Id;

	/**
	 * 发生日期
	 */
	private long occorDate;

	/**
	 * 相关单据号
	 */
	private String relatedNumber;

	/**
	 * 商品条目Id
	 */
	private GUID goodsItemId;

	/**
	 * 商品条目代码
	 */
	private String goodsItemCode;

	/**
	 * 商品条目名称
	 */
	private String goodsItemName;

	/**
	 * 商品条目属性
	 */
	private String goodsItemProperties;

	/**
	 * 商品条目单位
	 */
	private String goodsItemUnit;

	/**
	 * 流水类型
	 */
	private InventoryLogType logType;

	/**
	 * 入库数量
	 */
	private double checkedInCount;

	/**
	 * 入库金额
	 */
	private double checkedInAmount;

	/**
	 * 出库数量
	 */
	private double checkedOutCount;

	/**
	 * 出库金额
	 */
	private double checkedOutAmount;

	/**
	 * 数量小数位
	 */
	private int countDecimal;
	
	private String goodsItemNumber;

	public GUID getId(){
		return Id;
	}

	public void setId(GUID id){
		Id = id;
	}

	public long getOccorDate(){
		return occorDate;
	}

	public void setOccorDate(long occorDate){
		this.occorDate = occorDate;
	}

	public String getRelatedNumber(){
		return relatedNumber;
	}

	public void setRelatedNumber(String relatedNumber){
		this.relatedNumber = relatedNumber;
	}

	public GUID getGoodsItemId(){
		return goodsItemId;
	}

	public void setGoodsItemId(GUID goodsItemId){
		this.goodsItemId = goodsItemId;
	}

	public String getGoodsItemCode(){
		return goodsItemCode;
	}

	public void setGoodsItemCode(String goodsItemCode){
		this.goodsItemCode = goodsItemCode;
	}

	public String getGoodsItemName(){
		return goodsItemName;
	}

	public void setGoodsItemName(String goodsItemName){
		this.goodsItemName = goodsItemName;
	}

	public String getGoodsItemProperties(){
		return goodsItemProperties;
	}

	public void setGoodsItemProperties(String goodsItemProperties){
		this.goodsItemProperties = goodsItemProperties;
	}

	public String getGoodsItemUnit(){
		return goodsItemUnit;
	}

	public void setGoodsItemUnit(String goodsItemUnit){
		this.goodsItemUnit = goodsItemUnit;
	}

	public InventoryLogType getLogType(){
		return logType;
	}

	public void setLogType(InventoryLogType logType){
		this.logType = logType;
	}

	public double getCheckedInCount(){
		return checkedInCount;
	}

	public void setCheckedInCount(double checkedInCount){
		this.checkedInCount = checkedInCount;
	}

	public double getCheckedInAmount(){
		return checkedInAmount;
	}

	public void setCheckedInAmount(double checkedInAmount){
		this.checkedInAmount = checkedInAmount;
	}

	public double getCheckedOutCount(){
		return checkedOutCount;
	}

	public void setCheckedOutCount(double checkedOutCount){
		this.checkedOutCount = checkedOutCount;
	}

	public double getCheckedOutAmount(){
		return checkedOutAmount;
	}

	public void setCheckedOutAmount(double checkedOutAmount){
		this.checkedOutAmount = checkedOutAmount;
	}

	public int getScale(){
		return countDecimal;
	}

	public void setCountDecimal(int countDecimal){
		this.countDecimal = countDecimal;
	}

	public void setGoodsItemNumber(String goodsItemNumber) {
		this.goodsItemNumber = goodsItemNumber;
	}

	public String getGoodsItemNumber() {
		return goodsItemNumber;
	}

}
