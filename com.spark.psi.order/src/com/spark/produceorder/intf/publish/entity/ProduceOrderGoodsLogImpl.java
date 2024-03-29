package com.spark.produceorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.produceorder.entity.ProduceOrderGoodsLog;

/**
 * 生产订单完成商品记录<BR>
 * 查询方法：<BR>
 * GetProduceOrderGoodsLogListKey+ProduceOrderGoodsLogListEntity;
 */
public class ProduceOrderGoodsLogImpl implements ProduceOrderGoodsLog {

	private GUID id;//	行标识
	private GUID billsId;//	订单GUID
	private GUID goodsId;//	商品Guid
	private String goodsCode;//	商品编号
	private String goodsNo;//	商品条码
	private String goodsName;//	商品名称
	private String goodsSpec;//	商品规格
	private String unit;//	单位
	private int goodsScale;//	商品小数位数
	private double count;//	数量
	private GUID bomId;//	bom表Id
	private double thistimeCount;//	本次完成数量
	private GUID creatorId;//	经手人
	private String creator;//	经手人姓名
	private long createDate;//	日期
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
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
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getGoodsScale() {
		return goodsScale;
	}
	public void setGoodsScale(int goodsScale) {
		this.goodsScale = goodsScale;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public GUID getBomId() {
		return bomId;
	}
	public void setBomId(GUID bomId) {
		this.bomId = bomId;
	}
	public double getThistimeCount() {
		return thistimeCount;
	}
	public void setThistimeCount(double thistimeCount) {
		this.thistimeCount = thistimeCount;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
