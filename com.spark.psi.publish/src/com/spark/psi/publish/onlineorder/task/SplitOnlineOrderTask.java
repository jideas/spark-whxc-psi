package com.spark.psi.publish.onlineorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * 网上订单拆分
 *
 */
public class SplitOnlineOrderTask extends SimpleTask {

	private GUID id;
	private double totalAmount;//	总金额
	private double splitingAmount;
	private Item[] items;
	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getSplitingAmount() {
		return splitingAmount;
	}

	public void setSplitingAmount(double splitingAmount) {
		this.splitingAmount = splitingAmount;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public Item[] getItems() {
		return items;
	}

	public class Item
	{
		private GUID id;
		private double count;
		private double splitingCount;
		private double amount;
		private double splitingAmount;
		public GUID getId() {
			return id;
		}
		public void setId(GUID id) {
			this.id = id;
		}
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
		}
		public double getSplitingCount() {
			return splitingCount;
		}
		public void setSplitingCount(double splitingCount) {
			this.splitingCount = splitingCount;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public double getSplitingAmount() {
			return splitingAmount;
		}
		public void setSplitingAmount(double splitingAmount) {
			this.splitingAmount = splitingAmount;
		}
	}
}
