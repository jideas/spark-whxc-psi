package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 库存台账项<br>
 * 查询方法：ListEntry<InventoryBookItem>+GetInventoryBookKey
 */
public interface InventoryBookItem {

	/**
	 * 获取商品条目Id
	 */
	public GUID getGoodsItemId();

	/**
	 * 获取商品条目代码
	 */
	public String getGoodsItemCode();

	/**
	 * 获取商品条目名称
	 */
	public String getGoodsItemName();

	/**
	 * 获取商品条目属性
	 */
	public String getGoodsItemProperties();

	/**
	 * 获取商品条目单位
	 */
	public String getGoodsItemUnit();

	/**
	 * 获取期初数量
	 */
	public double getBeginningCount();

	/**
	 * 获取期初金额
	 */
	public double getBeginningAmount();

	/**
	 * 获取入库数量
	 */
	public double getCheckedInCount();

	/**
	 * 获取入库金额
	 */
	public double getCheckedInAmount();

	/**
	 * 获取出库数量
	 */
	public double getCheckedOutCount();

	/**
	 * 获取出库金额
	 */
	public double getCheckedOutAmount();

	/**
	 * 获取期末数量
	 */
	public double getEndingCount();

	/**
	 * 获取期末金额
	 */
	public double getEndingAmount();

}
