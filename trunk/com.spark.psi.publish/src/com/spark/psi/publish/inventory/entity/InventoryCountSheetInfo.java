package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;

/**
 * �̵㵥��ϸ��Ϣ<br>
 * ��ѯ�����������̵㵥ID��ѯInventoryCountSheetInfo����
 */
public interface InventoryCountSheetInfo {

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
	 * ��ȡ�̵㵥����
	 * 
	 * @return
	 */
	public InventoryCountType getSheetType();

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
	 * ��ȡ�̵�������
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * ��ȡ��ע��Ϣ
	 * 
	 * @return
	 */
	public String getRemark();

	/**
	 * ��ȡ��Ʒ�̵���Ŀ�б�
	 * 
	 * @return
	 */
	public GoodsCountItem[] getGoodsCountItems();

	/**
	 * ��ȡ������Ʒ�̵���Ŀ�б�
	 * 
	 * @return
	 */
	public KitCountItem[] getKitCountItems();

	/**
	 * ��Ʒ�̵���Ŀ
	 */
	public static interface GoodsCountItem {

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
		public String getGoodsCode();
		/**
		 * ��ȡ��Ʒ��Ŀ����
		 * 
		 * @return
		 */
		public String getGoodsNo();

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
		 * ��ȡ��Ʒ��������
		 * 
		 * @return
		 */
		public double getCount();

		/**
		 * ��ȡ��Ʒʵ������
		 * 
		 * @return
		 */
		public double getActualCount();

		/**
		 * ��ȡ˵��
		 * 
		 * @return
		 */
		public String getRemark();
		
		/**
		 * �Ƿ���ԭ�ֿ��д��ڣ��ж���������Ʒ������ԭʼ��Ʒ
		 * @return
		 */
		public boolean isExistInventory();

	}

	/**
	 * ������Ʒ�̵���Ŀ
	 */
	public static interface KitCountItem {
		/**
		 * ��ȡ��Ʒ����
		 * 
		 * @return
		 */
		public String getKitName();

		/**
		 * ��ȡ��Ʒ����
		 * 
		 * @return
		 */
		public String getKitDesc();

		/**
		 * ��ȡ��Ʒ��λ
		 * 
		 * @return
		 */
		public String getKitUnit();

		/**
		 * ��ȡ��Ʒ��������
		 * 
		 * @return
		 */
		public double getCount();

		/**
		 * ��ȡ��Ʒʵ������
		 * 
		 * @return
		 */
		public double getActualCount();

		/**
		 * ��ȡ˵��
		 * 
		 * @return
		 */
		public String getRemark();
		
		/**
		 * �Ƿ���ԭ�ֿ��д��ڣ��ж���������Ʒ������ԭʼ��Ʒ
		 * @return
		 */
		public boolean isExistInventory();

	}

}
