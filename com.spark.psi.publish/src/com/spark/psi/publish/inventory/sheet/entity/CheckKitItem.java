package com.spark.psi.publish.inventory.sheet.entity;

/**
 * ��Ʒ�������ϸ<br>
 * ���ṩ������ѯ
 * ok
 */
public interface CheckKitItem {

	/**
	 * ��Ʒ����
	 */
	public String getKitName();

	/**
	 * ��Ʒ����
	 */
	public String getKitDescription();

	/**
	 * ��λ
	 */
	public String getUnit();

	/**
	 * ����
	 */
	public int getCount();
}