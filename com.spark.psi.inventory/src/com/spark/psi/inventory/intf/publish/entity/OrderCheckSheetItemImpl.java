package com.spark.psi.inventory.intf.publish.entity;

import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;

/**
 * 订单（销售/销售退货/采购/采购退货）对应的出入库单数据<br>
 * 查询方法：getList(OrderCheckSheetItem.class,GetOrderCheckSheetItemKey);
 */
public class OrderCheckSheetItemImpl implements OrderCheckSheetItem{

	/**
	 * 获取出入库仓库名称
	 * 
	 * @return
	 */
	public String storeName;

	/**
	 * 获取出入库单号
	 * 
	 * @return
	 */
	public String checkSerialNumber;

	public String getCheckedSerialNos() {
		return checkSerialNumber;
	}

	public void setCheckSerialNumber(String checkSerialNumber) {
		this.checkSerialNumber = checkSerialNumber;
	}

	/**
	 * 获取预计出库日期
	 * 
	 * @return
	 */
	public long playCheckDate;

	/**
	 * 得到明细商品条目信息
	 * 
	 * @return
	 */
	public OrderCheckSheetItem.Item[] items;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public long getPlayCheckDate() {
		return playCheckDate;
	}

	public void setPlayCheckDate(long playCheckDate) {
		this.playCheckDate = playCheckDate;
	}

	public OrderCheckSheetItem.Item[] getItems() {
		return items;
	}

	public void setItems(OrderCheckSheetItem.Item[] items) {
		this.items = items;
	}
}
