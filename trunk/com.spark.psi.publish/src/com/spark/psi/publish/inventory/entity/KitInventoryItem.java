package com.spark.psi.publish.inventory.entity;

/**
 * �ֿ�������Ʒ��Ŀ<br>
 * ��ѯ������ʹ�òֿ�ID��ѯKitInventoryItem�б�
 * ok
 */
public interface KitInventoryItem {

	/**
	 * ��Ʒ����
	 */
	public String getKitName();

	

	/**
	 * ����
	 */
	public String getKitDescription();

	
	/**
	 * ��λ
	 */
	public String getUnit();

	
	/**
	 * ����
	 */
	public double getCount();

}
