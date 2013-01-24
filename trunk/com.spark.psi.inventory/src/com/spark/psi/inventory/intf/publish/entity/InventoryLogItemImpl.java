package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.inventory.entity.InventoryLogItem;

/**
 * �����ˮ��<br>
 * ��ѯ������ListEntry<InventoryLogItem>+GetInventoryLogKey
 */
public class InventoryLogItemImpl implements InventoryLogItem{

	/**
	 * ��ˮID
	 */
	private GUID Id;

	/**
	 * ��������
	 */
	private long occorDate;

	/**
	 * ��ص��ݺ�
	 */
	private String relatedNumber;

	/**
	 * ��Ʒ��ĿId
	 */
	private GUID goodsItemId;

	/**
	 * ��Ʒ��Ŀ����
	 */
	private String goodsItemCode;

	/**
	 * ��Ʒ��Ŀ����
	 */
	private String goodsItemName;

	/**
	 * ��Ʒ��Ŀ����
	 */
	private String goodsItemProperties;

	/**
	 * ��Ʒ��Ŀ��λ
	 */
	private String goodsItemUnit;

	/**
	 * ��ˮ����
	 */
	private InventoryLogType logType;

	/**
	 * �������
	 */
	private double checkedInCount;

	/**
	 * �����
	 */
	private double checkedInAmount;

	/**
	 * ��������
	 */
	private double checkedOutCount;

	/**
	 * ������
	 */
	private double checkedOutAmount;

	/**
	 * ����С��λ
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
