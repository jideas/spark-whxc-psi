package com.spark.psi.publish.inventory.sheet.entity;

/**
 * 物品出入库明细<br>
 * 不提供单独查询
 * ok
 */
public interface CheckKitItem {

	/**
	 * 物品名称
	 */
	public String getKitName();

	/**
	 * 物品描述
	 */
	public String getKitDescription();

	/**
	 * 单位
	 */
	public String getUnit();

	/**
	 * 数量
	 */
	public int getCount();
}