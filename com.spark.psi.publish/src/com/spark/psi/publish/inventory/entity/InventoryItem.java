package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 商品库存列表数据<br>
 * 查询方法：<br>
 * 根据GetInventoryItemKey查询InventoryItem列表<br>
 */

public interface InventoryItem {

	/**
	 * 商品条目ID
	 * 
	 * @return GUID
	 */
	public GUID getStockId();
	/**
	 * 商品条目编号
	 * 
	 * @return String
	 */
	public String getCode();
	
	/**
	 * 条码
	 * @return
	 */
	public String getNumber();
	
	/**
	 * 商品条目名称
	 * 
	 * @return String
	 */
	public String getName();
	/**
	 * 商品属性
	 * 
	 * @return String
	 */
	public String getProperties();
	/**
	 * 单位
	 * 
	 * @return String
	 */
	public String getUnit();
	
	/**
	 * 规格
	 * @return
	 */
	public String getSpec();
	
	/**
	 * 保质期
	 * @return
	 */
	public int getShelfLife();
	/**
	 * 库存数量
	 * 
	 * @return double
	 */
	public double getCount();
	
	/**
	 * 数量小数位
	 * 
	 * @return
	 */
	public int getScale();
	
}
