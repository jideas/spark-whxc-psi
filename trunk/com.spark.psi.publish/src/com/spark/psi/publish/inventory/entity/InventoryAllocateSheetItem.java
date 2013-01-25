package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryAllocateStatus;

/**
 * ���������б���Ŀ<br>
 * ��ѯ������ListEntity<InventoryAllocateSheetItem>+GetInventoryAllocateSheetListKey
 */
public interface InventoryAllocateSheetItem {

	/**
	 * ��ȡ������ID
	 * 
	 * @return
	 */
	public GUID getSheetId();

	/**
	 * ��ȡ����������
	 * 
	 * @return
	 */
	public String getSheetNumber();
	
	/**
	 * ������
	 * 
	 * @return
	 */
	public String getApprovePerson();

	/**
	 * ��ȡ������״̬
	 * 
	 * @return
	 */
	public InventoryAllocateStatus getStatus();

	/**
	 * ��ȡԴ�ֿ�ID
	 * 
	 * @return
	 */
	public GUID getSourceStoreId();

	/**
	 * ��ȡԴ�ֿ�����
	 * 
	 * @return
	 */
	public String getSourceStoreName();

	/**
	 * ��ȡĿ�Ĳֿ�ID
	 * 
	 * @return
	 */
	public GUID getDestinationStoreId();

	/**
	 * ��ȡĿ�Ĳֿ�����
	 * 
	 * @return
	 */
	public String getDestinationStoreName();

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return
	 */
	public long getAllocateInDate();

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return
	 */
	public long getAllocateOutDate();

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return
	 */
	public long getCreateDate();

	/**
	 * ��ȡ�Ƶ���ID
	 * 
	 * @return
	 */
	public GUID getCreatorId();

	/**
	 * ��ȡ�Ƶ�������
	 * 
	 * @return
	 */
	public String getCreatorName();
	/**
	 * ��ȡ��������������
	 * 
	 * @return
	 */
	public String getInExamName();
	/**
	 * ��ȡ��������������
	 * 
	 * @return
	 */
	public String getOutExamName();

}
