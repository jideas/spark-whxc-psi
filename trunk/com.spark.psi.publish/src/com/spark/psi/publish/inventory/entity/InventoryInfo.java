package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 商品库存详细数据<br>
 * 查询方法：<br>
 * (1)根据GetGoodsInventoryDetailListKey查询GoodsInventoryDetail列表<br>
 * (2)根据GetWarningGoodsItemListKey查询GoodsInventoryDetail列表<br>
 */
public interface InventoryInfo {

	/**
	 * 获取商品条目id
	 */
	public GUID getGoodsItemId();

	/**
	 * 获取仓库ID
	 */
	public GUID getStoreId();

	/**
	 * 获取库存数量
	 */
	public double getCount();

	/**
	 * 获取库存金额
	 */
	public double getAmount();

	/**
	 * 获取采购中数量
	 */
	public double getPurchasingCount();

	/**
	 * 获取采购中金额
	 */
	public double getPurchasingAmount();

	/**
	 * 获取交货需求数量
	 */
	public double getDeliveryingCount();

	/**
	 * 获取交货需求金额
	 */
	public double getDeliveryingAmount();

	/**
	 * 获取库存数量上限
	 */
	public double getUpperLimitCount();

	/**
	 * 获取库存数量下限
	 */
	public double getLowerLimitCount();

	/**
	 * 获取库存金额上限
	 */
	public double getUpperLimitAmount();

	/**
	 * 获取库存金额下限
	 */
	public double getLowerLimitAmount();

	/**
	 * 获取采购数量建议
	 */
	public double getAdviseCount();
	
	/**
	 * 商品条目名称
	 * @return String
	 */
	public String getGoodsName();
	/**
	 * 商品属性
	 * @return String
	 */
	public String getGoodsProperties();
	/**
	 * 单位
	 * @return String
	 */
	public String getGoodsUnit();
	
	/**
	 * 商品数量小数位数
	 * 
	 * @return int
	 */
	public int getScale();
	/**
	 * 仓库名称
	 * @return String
	 */
	public String getStoreName();
}
