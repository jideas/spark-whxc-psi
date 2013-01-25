package com.spark.order.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.StatusEnum;
import com.spark.psi.publish.inventory.entity.AllreadyAmountAndCount;
import com.spark.psi.publish.inventory.key.GetBillsAllreadyAmountAndCountKey;

/**
 * <p>
 * 库存相关处理
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-3-20
 */
class InventoryDataUtil {
	/**
	 * 根据入库单信息获取单据状态
	 * 
	 * @param in
	 * @return StatusEnum
	 */
	public static StatusEnum getOrderStatusByIn(Context context, GUID relaOrderId) {
		// GetRelationCheckInSheetKey key = new GetRelationCheckInSheetKey();
		// RelationCheckInSheet rc = context.find(RelationCheckInSheet.class,
		// key);
		return getOrderStatus(context.find(AllreadyAmountAndCount.class, GetBillsAllreadyAmountAndCountKey.getCheckInKey(relaOrderId)));
	}

	/**
	 * 根据出库单信息获取单据状态
	 * 
	 * @param in
	 * @return StatusEnum
	 */
	public static StatusEnum getOrderStatusByOut(Context context, GUID relaOrderId) {
		// GetRelationCheckOutSheetKey key = new GetRelationCheckOutSheetKey();
		// RelationCheckOutSheet rc = context.find(RelationCheckOutSheet.class,
		// key);
		return getOrderStatus(context.find(AllreadyAmountAndCount.class, GetBillsAllreadyAmountAndCountKey.getCheckOutKey(relaOrderId)));
	}

	// /**
	// * 根据入库单信息获取单据状态
	// *
	// * @param in
	// * @return StatusEnum
	// */
	// public static StatusEnum getOrderStatus(AllreadyAmountAndCount data) {
	// if (data.get == data.getCheckedInAmount() &&
	// data.getOrderGoodsTotalCount() == data.getCheckedInCount()) {
	// return StatusEnum.Finished;
	// } else if (data.getOrderGoodsTotalCount() == data.getCheckedInCount()) {
	// return StatusEnum.Store_All;
	// } else if (data.getCheckedInCount() > 0) {
	// return StatusEnum.Store_Part;
	// } else {
	// return StatusEnum.Store_N0;
	// }
	// }

	/**
	 * 根据出库单信息获取单据状态
	 * 
	 * @param in
	 * @return StatusEnum
	 */
	public static StatusEnum getOrderStatus(AllreadyAmountAndCount obj) {
		if (obj.getBillAmount() == obj.getPaidOrReceiptedAmount() && obj.getBillCount() == obj.getAllreadyCount()) {
			return StatusEnum.Finished;
		} else if (obj.getBillCount() == obj.getAllreadyCount()) {
			return StatusEnum.Store_All;
		} else if (obj.getAllreadyCount() > 0) {
			return StatusEnum.Store_Part;
		} else {
			return StatusEnum.Store_N0;
		}
	}
}
