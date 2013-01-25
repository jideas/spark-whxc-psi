package com.spark.psi.query.intf.intenal.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.query.intf.publish.entity.ProduceItem;

/**
 * 生产订单查询实体<BR>
 * 查询方法：<BR>
 * ProduceListEntity+GetProduceListKey;
 *
 */
public class ProduceItemImpl implements ProduceItem {

	private GUID billsId;//	订单GUID
	private String billsNo;//	订单编号
	private GUID goodsId;//	商品Guid
	private String goodsCode;//	商品编号
	private String goodsName;//	商品名称
	private String unit;//	单位
	private double count;//	数量
	private double finishedCount;//	已完成数量
	private long createDate;//制单日期
	private long planDate;//预计完成日期
	
	private LogImpl[] logs;
	private ItemImpl[] items;

	public GUID getBillsId() {
		return billsId;
	}
	public void setBillsId(GUID billsId) {
		this.billsId = billsId;
	}
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
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
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getFinishedCount() {
		return finishedCount;
	}
	public void setFinishedCount(double finishedCount) {
		this.finishedCount = finishedCount;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public long getPlanDate() {
		return planDate;
	}
	public void setPlanDate(long planDate) {
		this.planDate = planDate;
	}
	public LogImpl[] getLogs() {
		return logs;
	}
	public void setLogs(LogImpl[] logs) {
		this.logs = logs;
	}
	public ItemImpl[] getItems() {
		return items;
	}
	public void setItems(ItemImpl[] items) {
		this.items = items;
	}
	public class LogImpl implements Log
	{
		private GUID billsId;//	订单GUID
		private GUID goodsId;//	商品Guid
		private String goodsCode;//	商品编号
		private String goodsName;//	商品名称
		private String unit;//	单位
		private double count;//	数量
		private GUID creatorId;//	经手人
		private String creator;//	经手人姓名
		private long createDate;//	日期
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
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
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
	public class ItemImpl implements Item
	{
		private GUID billsId;//	订单GUID
		private GUID materialId;//	商品Guid
		private String materialCode;//	商品编号
		private String materialName;//	商品名称
		private String unit;//	单位
		private double count;//	数量
		private double cost;//单位成本
		private double totalCost;//子件成本
		private String materialNo;
		private String spec;
		public String getMaterialNo() {
			return materialNo;
		}
		public void setMaterialNo(String materialNo) {
			this.materialNo = materialNo;
		}
		public String getSpec() {
			return spec;
		}
		public void setSpec(String spec) {
			this.spec = spec;
		}
		public GUID getBillsId() {
			return billsId;
		}
		public void setBillsId(GUID billsId) {
			this.billsId = billsId;
		}
		public GUID getMaterialId() {
			return materialId;
		}
		public void setMaterialId(GUID materialId) {
			this.materialId = materialId;
		}
		public String getMaterialCode() {
			return materialCode;
		}
		public void setMaterialCode(String materialCode) {
			this.materialCode = materialCode;
		}
		public String getMaterialName() {
			return materialName;
		}
		public void setMaterialName(String materialName) {
			this.materialName = materialName;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
		public double getTotalCost() {
			return totalCost;
		}
		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}

		
	}
}
