package com.spark.psi.publish.base.store.entity;

/**
 * �ֿ��ʼ��������Ʒ��Ŀ<br>
 * ��ѯ������ʹ�òֿ�ID��ѯStoreInitKitItem�б�
 */
public final class StoreInitKitItem {

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
	 * ��ʼ����
	 */
	private double count;

	/**
	 * @return the kitName
	 */
	public String getKitName() {
		return kitName;
	}

	/**
	 * @param kitName
	 *            the kitName to set
	 */
	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	/**
	 * @return the kitDescription
	 */
	public String getKitDescription() {
		return kitDescription;
	}

	/**
	 * @param kitDescription
	 *            the kitDescription to set
	 */
	public void setKitDescription(String kitDescription) {
		this.kitDescription = kitDescription;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the count
	 */
	public double getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}

}
