package com.spark.psi.inventory.intf.publish.entity;

import com.spark.psi.publish.inventory.entity.KitInventoryItem;

/**
 * 仓库其他物品条目<br>
 * 查询方法：使用仓库ID查询KitInventoryItem列表
 */
public class KitInventoryItemImpl implements KitInventoryItem {

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
	 * 初始数量
	 */
	private double count;

	/**
	 * @return the kitName
	 */
	public String getKitName() {
		return kitName;
	}

	/**
	 * @param kitName
	 *            the kitName to set
	 */
	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	/**
	 * @return the kitDescription
	 */
	public String getKitDescription() {
		return kitDescription;
	}

	/**
	 * @param kitDescription
	 *            the kitDescription to set
	 */
	public void setKitDescription(String kitDescription) {
		this.kitDescription = kitDescription;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the count
	 */
	public double getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}

}
