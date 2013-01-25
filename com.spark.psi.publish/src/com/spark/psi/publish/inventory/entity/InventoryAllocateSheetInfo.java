package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryAllocateStatus;

/**
 * ��������������Ŀ<br>
 * ��ѯ���������ݵ�����ID��ѯInventoryAllocateSheetInfo����
 */
public interface InventoryAllocateSheetInfo {

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
	 * ��������
	 * 
	 * @return
	 */
	public long getApproveDate();

	/**
	 * ������
	 * 
	 * @return
	 */
	public String getApprovePerson();
	/**
	 * ������ID
	 * 
	 * @return
	 */
	public GUID getApprovePersonId();

	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	public String getAllocateInName();

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return
	 */
	public long getAllocateInDate();
	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	public String getAllocateOutName();

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
	 * ��ȡ����ԭ��
	 * 
	 * @return
	 */
	public String getCause();
	/**
	 * ��ȡ�˻�ԭ��
	 */
	public String getDanyCause();

	/**
	 * ��ȡ ������Ʒ��Ŀ�б�
	 * 
	 * @return
	 */
	public AllocateGoodsItem[] getItems();

	/**
	 * ������Ʒ��Ŀ��Ϣ
	 */
	public static interface AllocateGoodsItem {

		public GUID getId();
		
		/**
		 * ��ȡ��Ʒ��ĿID
		 * 
		 * @return
		 */
		public GUID getGoodsItemId();

		/**
		 * ��ȡ��Ʒ��Ŀ����
		 * 
		 * @return
		 */
		public String getGoodsItemCode();
		
		public String getStockNumber();

		/**
		 * ��ȡ��Ʒ��Ŀ����
		 * 
		 * @return
		 */
		public String getGoodsItemName();

		/**
		 * ��ȡ��Ʒ��Ŀ����
		 * 
		 * @return
		 */
		public String getGoodsItemProperties();

		/**
		 * ��ȡ��Ʒ��Ŀ��λ
		 * 
		 * @return
		 */
		public String getGoodsItemUnit();

		/**
		 * ��ȡ��Ʒ����С��λ
		 * 
		 * @return
		 */
		public int getScale();

		/**
		 * ��ȡ���ÿ��
		 * 
		 * @return
		 */
		public double getAvailableCount();

		/**
		 * ��ȡ��������
		 * 
		 * @return
		 */
		public double getAllocateCount();

	}

}
