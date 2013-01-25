package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ���̨����<br>
 * ��ѯ������ListEntry<InventoryBookItem>+GetInventoryBookKey
 */
public interface InventoryBookItem {

	/**
	 * ��ȡ��Ʒ��ĿId
	 */
	public GUID getGoodsItemId();

	/**
	 * ��ȡ��Ʒ��Ŀ����
	 */
	public String getGoodsItemCode();

	/**
	 * ��ȡ��Ʒ��Ŀ����
	 */
	public String getGoodsItemName();

	/**
	 * ��ȡ��Ʒ��Ŀ����
	 */
	public String getGoodsItemProperties();

	/**
	 * ��ȡ��Ʒ��Ŀ��λ
	 */
	public String getGoodsItemUnit();

	/**
	 * ��ȡ�ڳ�����
	 */
	public double getBeginningCount();

	/**
	 * ��ȡ�ڳ����
	 */
	public double getBeginningAmount();

	/**
	 * ��ȡ�������
	 */
	public double getCheckedInCount();

	/**
	 * ��ȡ�����
	 */
	public double getCheckedInAmount();

	/**
	 * ��ȡ��������
	 */
	public double getCheckedOutCount();

	/**
	 * ��ȡ������
	 */
	public double getCheckedOutAmount();

	/**
	 * ��ȡ��ĩ����
	 */
	public double getEndingCount();

	/**
	 * ��ȡ��ĩ���
	 */
	public double getEndingAmount();

}
