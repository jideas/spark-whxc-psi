/**
 * 
 */
package com.spark.psi.inventory.intf.entity.inventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * �����ˮ
 *
 */
public class InventoryLog {
	private GUID RECID;//Ψһ��ʶ
	private GUID storeGuid;//�ֿ�guid
	private GUID goodsGuid;//��ƷGUID
	private String createPerson;//������
	private long createdDate;//��������
	private String goodsName;//��Ʒ����
	private String goodsAttr;//��Ʒ����
	private String goodsUnit;//��Ʒ��λ
	private GUID goodsTypeGuid;//��Ʒ����GUID
	private String goodsNo;//��Ʒ���
	private int goodsScale;//��Ʒ����
	private String streamType;//��ˮ����,��StoreStreamTypeEnum�������ȥ����Ӧ��ö��
	private long currDate;//����ʱ��
	private double instoCount;//�������
	private double instoAmount;//�����
	private double outstoCount;//��������
	private double outstoAmount;//��������
	private GUID orderGuid;//����仯�ĵ��ݵ�GUID
	private String orderNo;//����仯�ĵ��ݵı��
	private InventoryType inventoryType;
	/*----------------��������������ʱ���ô�------------------------*/
	private int monthNo;//��,����
	private int dayNo;//��,����
	private int quarter;//����,����
	private int yearNo;//��,����
	
	
	public int getMonthNo() {
		return monthNo;
	}
	public void setMonthNo(int monthNo) {
		this.monthNo = monthNo;
	}
	public int getDayNo() {
		return dayNo;
	}
	public void setDayNo(int dayNo) {
		this.dayNo = dayNo;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public int getYearNo() {
		return yearNo;
	}
	public void setYearNo(int yearNo) {
		this.yearNo = yearNo;
	}
	public GUID getRECID() {
		return RECID;
	}
	
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public long getCreatedDate() {
		return createdDate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public GUID getGoodsTypeGuid() {
		return goodsTypeGuid;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public int getGoodsScale() {
		return goodsScale;
	}
	public String getStreamType() {
		return streamType;
	}
	public long getCurrDate() {
		return currDate;
	}
	public GUID getOrderGuid() {
		return orderGuid;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public double getInstoCount() {
		return instoCount;
	}
	public double getInstoAmount() {
		return instoAmount;
	}
	public double getOutstoCount() {
		return outstoCount;
	}
	public double getOutstoAmount() {
		return outstoAmount;
	}
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	
	public void setStoreGuid(GUID storeGuid) {
		this.storeGuid = storeGuid;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public void setGoodsTypeGuid(GUID goodsTypeGuid) {
		this.goodsTypeGuid = goodsTypeGuid;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public void setCurrDate(long currDate) {
		this.currDate = currDate;
	}
	public void setOrderGuid(GUID orderGuid) {
		this.orderGuid = orderGuid;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setInstoCount(double instoCount) {
		this.instoCount = instoCount;
	}
	public void setInstoAmount(double instoAmount) {
		this.instoAmount = instoAmount;
	}
	public void setOutstoCount(double outstoCount) {
		this.outstoCount = outstoCount;
	}
	public void setOutstoAmount(double outstoAmount) {
		this.outstoAmount = outstoAmount;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}
	public InventoryType getInventoryType() {
		return inventoryType;
	}
}
