package com.spark.psi.inventory.intf.publish.entity;

import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;

/**
 * ����������/�����˻�/�ɹ�/�ɹ��˻�����Ӧ�ĳ���ⵥ����<br>
 * ��ѯ������getList(OrderCheckSheetItem.class,GetOrderCheckSheetItemKey);
 */
public class OrderCheckSheetItemImpl implements OrderCheckSheetItem{

	/**
	 * ��ȡ�����ֿ�����
	 * 
	 * @return
	 */
	public String storeName;

	/**
	 * ��ȡ����ⵥ��
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
	 * ��ȡԤ�Ƴ�������
	 * 
	 * @return
	 */
	public long playCheckDate;

	/**
	 * �õ���ϸ��Ʒ��Ŀ��Ϣ
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
