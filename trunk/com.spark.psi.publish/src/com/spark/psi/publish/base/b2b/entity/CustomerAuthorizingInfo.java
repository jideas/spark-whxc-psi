package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

/**
 * �Կͻ�����Ȩ��Ϣ <br>
 * ��ѯ���������ݿͻ�ID��ѯAuthorizingCustomerInfo����
 */
public interface CustomerAuthorizingInfo {

	/**
	 * ��ȡ�ͻ�ID
	 * 
	 * @return
	 */
	public GUID getCustomerId();

	/**
	 * ��ȡ�ͻ�����
	 * 
	 * @return
	 */
	public String getCustomerName();

	/**
	 * ��ȡ��ϵ������
	 * 
	 * @return
	 */
	public ContactBookInfo getContactInfo();

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public String getSalesmanName();

	/**
	 * ��ȡ��Ȩ��Ʒ�б�
	 * 
	 * @return
	 */
	public Item[] getItems();

	/**
	 * ��Ȩ��Ʒ��Ŀ
	 */
	public static interface Item {

		/**
		 * ��ȡ��Ʒ��ĿID
		 */
		public GUID getGoodsItemId();

		/**
		 * ��ȡ��Ʒ��ĿCode
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
		 * ��ȡ��Ȩ�۸�
		 * 
		 * @return
		 */
		public double getAuthorizingPrice();

		/**
		 * ��ȡ˵��
		 */
		public String getRemark();

		/**
		 * ��ȡ״̬
		 * 
		 * @return
		 */
		public boolean isEnabled();

	}

}
