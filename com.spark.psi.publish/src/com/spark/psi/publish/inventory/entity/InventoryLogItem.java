package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryLogType;

/**
 * �����ˮ��<br>
 * ��ѯ������ListEntry<InventoryLogItem>+GetInventoryLogKey
 */
public interface InventoryLogItem {

	/**
	 * ��ȡ��ˮID
	 */
	public GUID getId();

	/**
	 * ��ȡ��������
	 */
	public long getOccorDate();

	/**
	 * ��ȡ��ص��ݺ�
	 */
	public String getRelatedNumber();

	/**
	 * ��ȡ��Ʒ��ĿId
	 */
	public GUID getGoodsItemId();

	/**
	 * ��ȡ��Ʒ��Ŀ����
	 */
	public String getGoodsItemCode();
	
	public String getGoodsItemNumber();

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
	 * ��ȡ��ˮ����
	 */
	public InventoryLogType getLogType();

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
	 * ����С��λ
	 */
	public int getScale();

}
