package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ�����ϸ����<br>
 * ��ѯ������<br>
 * (1)����GetGoodsInventoryDetailListKey��ѯGoodsInventoryDetail�б�<br>
 * (2)����GetWarningGoodsItemListKey��ѯGoodsInventoryDetail�б�<br>
 */
public interface InventoryInfo {

	/**
	 * ��ȡ��Ʒ��Ŀid
	 */
	public GUID getGoodsItemId();

	/**
	 * ��ȡ�ֿ�ID
	 */
	public GUID getStoreId();

	/**
	 * ��ȡ�������
	 */
	public double getCount();

	/**
	 * ��ȡ�����
	 */
	public double getAmount();

	/**
	 * ��ȡ�ɹ�������
	 */
	public double getPurchasingCount();

	/**
	 * ��ȡ�ɹ��н��
	 */
	public double getPurchasingAmount();

	/**
	 * ��ȡ������������
	 */
	public double getDeliveryingCount();

	/**
	 * ��ȡ����������
	 */
	public double getDeliveryingAmount();

	/**
	 * ��ȡ�����������
	 */
	public double getUpperLimitCount();

	/**
	 * ��ȡ�����������
	 */
	public double getLowerLimitCount();

	/**
	 * ��ȡ���������
	 */
	public double getUpperLimitAmount();

	/**
	 * ��ȡ���������
	 */
	public double getLowerLimitAmount();

	/**
	 * ��ȡ�ɹ���������
	 */
	public double getAdviseCount();
	
	/**
	 * ��Ʒ��Ŀ����
	 * @return String
	 */
	public String getGoodsName();
	/**
	 * ��Ʒ����
	 * @return String
	 */
	public String getGoodsProperties();
	/**
	 * ��λ
	 * @return String
	 */
	public String getGoodsUnit();
	
	/**
	 * ��Ʒ����С��λ��
	 * 
	 * @return int
	 */
	public int getScale();
	/**
	 * �ֿ�����
	 * @return String
	 */
	public String getStoreName();
}
