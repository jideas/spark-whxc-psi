package com.spark.b2c.publish.JointVenture.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * 新增/编辑结算
 * 
 */
public class JointSettlementTask extends Task<JointSettlementTask.Method> {

	public enum Method {
		Create, Update;
	}

	private GUID id;
	private String supplierName;
	private String namePY;
	private String shortName;
	private String supplierNo;
	private GUID supplierId;
	private long beginDate;
	private long endDate;
	private double salesAmount;
	private double percentageAmount;
	private double adjustAmount;
	private double amount;
	private Item[] items;
	private String recordIds;
	private String remark;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getNamePY() {
		return namePY;
	}

	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public GUID getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(GUID supplierId) {
		this.supplierId = supplierId;
	}

	public long getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public double getPercentageAmount() {
		return percentageAmount;
	}

	public void setPercentageAmount(double percentageAmount) {
		this.percentageAmount = percentageAmount;
	}

	public double getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(double adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	/**
	 * 
	 * @param recordIds
	 *            交易记录ID串，分隔符：逗号 <br>
	 *            StringBuffer recordIds = new StringBuffer();<br>
	 *            for(int i=0;i<task.getRecordIds().length;i++)<br>
	 *            {<br>
	 *            if(i!=task.getRecordIds().length-1)<br>
	 *            {<br>
	 *            recordIds.append(task.getRecordIds()[i].toString()).append(",")
	 *            ;<br>
	 *            }<br>
	 *            else<br>
	 *            {<br>
	 *            recordIds.append(task.getRecordIds()[i].toString());<br>
	 *            }<br>
	 *            }<br>
	 *            task.setRecordIds(recordIds.toString());
	 */
	public void setRecordIds(String recordIds) {
		this.recordIds = recordIds;
	}

	public String getRecordIds() {
		return recordIds;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public class Item {
		private GUID goodsId;
		private String goodsCode;
		private String goodsNo;
		private String goodsSpec;
		private String goodsUnit;
		private String goodsName;
		private double goodsPrice;
		private double count;
		private double amount;
		private double percentage;
		private double percentageAmount;

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

		public String getGoodsSpec() {
			return goodsSpec;
		}

		public void setGoodsSpec(String goodsSpec) {
			this.goodsSpec = goodsSpec;
		}

		public String getGoodsUnit() {
			return goodsUnit;
		}

		public void setGoodsUnit(String goodsUnit) {
			this.goodsUnit = goodsUnit;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public double getGoodsPrice() {
			return goodsPrice;
		}

		public void setGoodsPrice(double goodsPrice) {
			this.goodsPrice = goodsPrice;
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

		public double getPercentage() {
			return percentage;
		}

		public void setPercentage(double percentage) {
			this.percentage = percentage;
		}

		public double getPercentageAmount() {
			return percentageAmount;
		}

		public void setPercentageAmount(double percentageAmount) {
			this.percentageAmount = percentageAmount;
		}

	}

}
