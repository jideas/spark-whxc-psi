package com.spark.psi.order.browser.util;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SEditTable;

/**
 * <p>采购工具实例化工厂</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-10
 */
public final class OrderUtilFactory {
	/**
	 * 采购商品分组（生成采购订单用）
	 * @return IPurchaseGoodsGroup
	 */
	public static IPurchaseGoodsGroup getPurchaseGoodsGroup(Context context, SEditTable table){
		return new PurchaseGoodsGroupImpl(context, table);
	}
	
	/**
	 * 所有列排序
	 *  void
	 */
	public static void addAllColumnSort(STableColumn[] columns){
		for(int i = 0; i<columns.length && null != columns[i]; i++){
			columns[i].setSortable(true);
		}
	}
	
	/**
	 * 设置自动样式，Booolean数组，第一标识自动填充宽度，第二表示排序
	 * @param columns
	 * @param booleans void
	 */
	public static void addAllColumnParam(STableColumn[] columns, Boolean...booleans){
		if(null == booleans || 0 == booleans.length){
			return;
		}
		for(int i = 0; i<columns.length && null != columns[i]; i++){
			for(int j = 0; j<booleans.length; j++){
				switch (j) {
				case 0:
					columns[i].setGrab(booleans[j]);
					break;
				case 1:
					columns[i].setSortable(booleans[j]);
					break;

				default:
					break;
				}
			}
		}
	}
}
