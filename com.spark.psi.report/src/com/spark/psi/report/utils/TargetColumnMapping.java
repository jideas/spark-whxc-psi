/**
 * 
 */
package com.spark.psi.report.utils;

import java.util.HashMap;
import java.util.Map;

import com.spark.psi.report.constant.TargetEnum.InventoryBookEnum;

/**
 *
 */
public class TargetColumnMapping {
	/**
	 * 库存流水
	 */
	public static Map<InventoryBookEnum, String> createInventoryBookColumnMap() {
		Map<InventoryBookEnum, String> tmap = new HashMap<InventoryBookEnum, String>();
		// 商品条目编号
		tmap.put(InventoryBookEnum.GoodsItemNumber, "goodsNo");
		// 商品条目名称
		tmap.put(InventoryBookEnum.GoodsItemName, "goodsName");
		// 商品条目属性
		tmap.put(InventoryBookEnum.GoodsProperties, "goodsAttr");
		// 商品条目单位
		tmap.put(InventoryBookEnum.GoodsUnit, "goodsUnit");
		// 期初库存数量
		tmap.put(InventoryBookEnum.InitialInventoryCount, "min(beginStoreCount)");
		// 期初库存金额
		tmap.put(InventoryBookEnum.InitialInventoryAmount, "min(beginStoreMoney)");
		// 入库数量
		tmap.put(InventoryBookEnum.CheckInCount, "sum(instoCount)");
		// 入库金额
		tmap.put(InventoryBookEnum.CheckInAmount, "sum(instoAmount)");
		// 出库数量
		tmap.put(InventoryBookEnum.CheckOutCount, "sum(outstoCount)");
		// 出库金额
		tmap.put(InventoryBookEnum.CheckOutAmount, "sum(outstoAmount)");
		// 期末库存数量
		tmap.put(InventoryBookEnum.TerminalInventoryCount, "max(endStoreCount)");
		// 期末库存金额
		tmap.put(InventoryBookEnum.TerminalInventoryAmount, "max(endStoreMoney)");
		return tmap;
	}

	 

}
