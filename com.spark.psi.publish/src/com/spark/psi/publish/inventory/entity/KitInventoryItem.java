package com.spark.psi.publish.inventory.entity;

/**
 * 仓库其他物品条目<br>
 * 查询方法：使用仓库ID查询KitInventoryItem列表
 * ok
 */
public interface KitInventoryItem {

	/**
	 * 物品名称
	 */
	public String getKitName();

	

	/**
	 * 描述
	 */
	public String getKitDescription();

	
	/**
	 * 单位
	 */
	public String getUnit();

	
	/**
	 * 数量
	 */
	public double getCount();

}
