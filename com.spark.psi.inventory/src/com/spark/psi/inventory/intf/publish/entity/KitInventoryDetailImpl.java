package com.spark.psi.inventory.intf.publish.entity;

import com.spark.psi.publish.inventory.entity.KitInventoryDetail;

/**
 * ��Ʒ�����ϸ<br>
 * ��ѯ����������GetKitInventoryDetailListKey��ѯKitInventoryDetail�б�
 * 
 */
public class KitInventoryDetailImpl implements KitInventoryDetail{

	/**
	 * ��Ʒ����
	 */
	private String kitName;

	/**
	 * ��Ʒ����
	 */
	private String kitDesc;

	/**
	 * ��λ
	 */
	private String unit;

	/**
	 * ����
	 */
	private double count;

	public String getKitName() {
		return kitName;
	}

	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	public String getKitDesc() {
		return kitDesc;
	}

	public void setKitDesc(String kitDesc) {
		this.kitDesc = kitDesc;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

}
