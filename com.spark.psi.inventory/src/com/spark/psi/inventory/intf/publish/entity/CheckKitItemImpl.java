package com.spark.psi.inventory.intf.publish.entity;

import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;

/**
 * ��Ʒ�������ϸ<br>
 * ���ṩ������ѯ
 */
public class CheckKitItemImpl implements CheckKitItem{

	/**
	 * ��Ʒ����
	 */
	private String kitName;

	/**
	 * ��Ʒ����
	 */
	private String kitDescription;

	/**
	 * ��λ
	 */
	private String unit;

	/**
	 * ����
	 */
	private int count;

	public String getKitName() {
		return kitName;
	}

	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	public String getKitDescription() {
		return kitDescription;
	}

	public void setKitDescription(String kitDescription) {
		this.kitDescription = kitDescription;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}