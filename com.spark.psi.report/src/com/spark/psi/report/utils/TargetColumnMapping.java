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
	 * �����ˮ
	 */
	public static Map<InventoryBookEnum, String> createInventoryBookColumnMap() {
		Map<InventoryBookEnum, String> tmap = new HashMap<InventoryBookEnum, String>();
		// ��Ʒ��Ŀ���
		tmap.put(InventoryBookEnum.GoodsItemNumber, "goodsNo");
		// ��Ʒ��Ŀ����
		tmap.put(InventoryBookEnum.GoodsItemName, "goodsName");
		// ��Ʒ��Ŀ����
		tmap.put(InventoryBookEnum.GoodsProperties, "goodsAttr");
		// ��Ʒ��Ŀ��λ
		tmap.put(InventoryBookEnum.GoodsUnit, "goodsUnit");
		// �ڳ��������
		tmap.put(InventoryBookEnum.InitialInventoryCount, "min(beginStoreCount)");
		// �ڳ������
		tmap.put(InventoryBookEnum.InitialInventoryAmount, "min(beginStoreMoney)");
		// �������
		tmap.put(InventoryBookEnum.CheckInCount, "sum(instoCount)");
		// �����
		tmap.put(InventoryBookEnum.CheckInAmount, "sum(instoAmount)");
		// ��������
		tmap.put(InventoryBookEnum.CheckOutCount, "sum(outstoCount)");
		// ������
		tmap.put(InventoryBookEnum.CheckOutAmount, "sum(outstoAmount)");
		// ��ĩ�������
		tmap.put(InventoryBookEnum.TerminalInventoryCount, "max(endStoreCount)");
		// ��ĩ�����
		tmap.put(InventoryBookEnum.TerminalInventoryAmount, "max(endStoreMoney)");
		return tmap;
	}

	 

}
