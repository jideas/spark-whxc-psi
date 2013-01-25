package com.spark.psi.publish.inventory.entity;

/**
 * 物品库存详细<br>
 * 查询方法：根据GetKitInventoryDetailListKey查询KitInventoryDetail列表
 * ok
 */
public interface KitInventoryDetail {

	/**
	 * 获取物品名称
	 */
	public String getKitName();

	/**
	 * 获取物品描述
	 */
	public String getKitDesc();

	/**
	 * 获取单位
	 */
	public String getUnit();

	/**
	 * 获取数量
	 */
	public double getCount();

}
