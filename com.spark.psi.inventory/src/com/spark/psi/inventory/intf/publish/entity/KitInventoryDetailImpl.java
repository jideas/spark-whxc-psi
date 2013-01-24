package com.spark.psi.inventory.intf.publish.entity;

import com.spark.psi.publish.inventory.entity.KitInventoryDetail;

/**
 * 物品库存详细<br>
 * 查询方法：根据GetKitInventoryDetailListKey查询KitInventoryDetail列表
 * 
 */
public class KitInventoryDetailImpl implements KitInventoryDetail{

	/**
	 * 物品名称
	 */
	private String kitName;

	/**
	 * 物品描述
	 */
	private String kitDesc;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 数量
	 */
	private double count;

	public String getKitName() {
		return kitName;
	}

	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	public String getKitDesc() {
		return kitDesc;
	}

	public void setKitDesc(String kitDesc) {
		this.kitDesc = kitDesc;
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

}
