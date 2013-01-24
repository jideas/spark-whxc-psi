package com.spark.psi.inventory.intf.publish.entity;

import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;

/**
 * 物品出入库明细<br>
 * 不提供单独查询
 */
public class CheckKitItemImpl implements CheckKitItem{

	/**
	 * 物品名称
	 */
	private String kitName;

	/**
	 * 物品描述
	 */
	private String kitDescription;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 数量
	 */
	private int count;

	public String getKitName() {
		return kitName;
	}

	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	public String getKitDescription() {
		return kitDescription;
	}

	public void setKitDescription(String kitDescription) {
		this.kitDescription = kitDescription;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}