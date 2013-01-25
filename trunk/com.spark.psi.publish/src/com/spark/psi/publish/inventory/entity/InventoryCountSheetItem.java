package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;

/**
 * ����̵㵥��Ŀ<br>
 * ��ѯ������ListEntity<InventoryCountSheetItem> + GetInventoryCountSheetListKey
 */
public interface InventoryCountSheetItem {

	/**
	 * ��ȡ����ID
	 * 
	 * @return
	 */
	public GUID getSheetId();

	/**
	 * ��ȡ���ݺ�
	 * 
	 * @return
	 */
	public String getSheetNumber();

	/**
	 * ��ȡ�̵㵥״̬
	 * 
	 * @return
	 */
	public InventoryCountStatus getSheetstatus();

	/**
	 * ��ȡ��ʼ����
	 * 
	 * @return
	 */
	public long getStartDate();

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public long getEndDate();

	/**
	 * ��ȡ�ֿ�ID
	 * 
	 * @return
	 */
	public GUID getStoreId();

	/**
	 * ��ȡ�ֿ�����
	 * 
	 * @return
	 */
	public String getStoreName();

	/**
	 * ��ȡ��ӯ����
	 * 
	 * @return
	 */
	public int getCountProfit();

	/**
	 * ��ȡ�̿�����
	 * 
	 * @return
	 */
	public int getCountLoss();

	/**
	 * ����
	 */
	public InventoryCountType getType();
}
