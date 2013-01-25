package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryLogType;

/**
 * 库存流水项<br>
 * 查询方法：ListEntry<InventoryLogItem>+GetInventoryLogKey
 */
public interface InventoryLogItem {

	/**
	 * 获取流水ID
	 */
	public GUID getId();

	/**
	 * 获取发生日期
	 */
	public long getOccorDate();

	/**
	 * 获取相关单据号
	 */
	public String getRelatedNumber();

	/**
	 * 获取商品条目Id
	 */
	public GUID getGoodsItemId();

	/**
	 * 获取商品条目代码
	 */
	public String getGoodsItemCode();
	
	public String getGoodsItemNumber();

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
	 * 获取流水类型
	 */
	public InventoryLogType getLogType();

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
	 * 数量小数位
	 */
	public int getScale();

}
