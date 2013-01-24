package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;

/**
 * 库存盘点单条目<br>
 * 查询方法：ListEntity<InventoryCountSheetItem> + GetInventoryCountSheetListKey
 */
public interface InventoryCountSheetItem {

	/**
	 * 获取单据ID
	 * 
	 * @return
	 */
	public GUID getSheetId();

	/**
	 * 获取单据号
	 * 
	 * @return
	 */
	public String getSheetNumber();

	/**
	 * 获取盘点单状态
	 * 
	 * @return
	 */
	public InventoryCountStatus getSheetstatus();

	/**
	 * 获取开始日期
	 * 
	 * @return
	 */
	public long getStartDate();

	/**
	 * 获取结束日期
	 * 
	 * @return
	 */
	public long getEndDate();

	/**
	 * 获取仓库ID
	 * 
	 * @return
	 */
	public GUID getStoreId();

	/**
	 * 获取仓库名称
	 * 
	 * @return
	 */
	public String getStoreName();

	/**
	 * 获取盘盈数量
	 * 
	 * @return
	 */
	public int getCountProfit();

	/**
	 * 获取盘亏数量
	 * 
	 * @return
	 */
	public int getCountLoss();

	/**
	 * 类型
	 */
	public InventoryCountType getType();
}
