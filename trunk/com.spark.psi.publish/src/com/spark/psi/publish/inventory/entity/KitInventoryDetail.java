package com.spark.psi.publish.inventory.entity;

/**
 * ��Ʒ�����ϸ<br>
 * ��ѯ����������GetKitInventoryDetailListKey��ѯKitInventoryDetail�б�
 * ok
 */
public interface KitInventoryDetail {

	/**
	 * ��ȡ��Ʒ����
	 */
	public String getKitName();

	/**
	 * ��ȡ��Ʒ����
	 */
	public String getKitDesc();

	/**
	 * ��ȡ��λ
	 */
	public String getUnit();

	/**
	 * ��ȡ����
	 */
	public double getCount();

}
