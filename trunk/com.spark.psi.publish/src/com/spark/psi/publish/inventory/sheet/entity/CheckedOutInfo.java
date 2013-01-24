package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public interface CheckedOutInfo {

	/**
	 * ��ȡ���ⵥid
	 */
	public GUID getSheetId();
	public String getSheetNo(); 

	/**
	 * ��ȡ����ʱ��
	 */
	public long getCheckoutDate(); 

	/**
	 * ��ȡ��ص��ݱ��
	 */
	public String getRelaBillsNo();

	/**
	 * ��ȡ����ֿ�id
	 */
	public GUID getStoreId();

	/**
	 * ��ȡ����ֿ�����
	 */
	public String getStoreName(); 
	/**
	 * ��ȡ��������
	 */
	public CheckingOutType getType();

	/**
	 * ��ȡ�ͻ����߹�Ӧ��ID
	 */
	public GUID getPartnerId();

	/**
	 * ��ȡ�ͻ����߹�Ӧ������
	 */
	public String getPartnerName();

	/**
	 * ��ȡ��ע��Ϣ
	 */
	public String getRemark();
	
	/**
	 * ��Ʒ��Դ
	 */
	public String getGoodsFrom();
	
	/**
	 * ��Ʒ��;
	 */
	public String getGoodsUse(); 

	/**
	 * ��ȡ������Ʒ�б����۳�����߲ɹ��˻����⣩
	 */
	public CheckedGoodsItem[] getCheckedGoodsItems();

	/**
	 * ��ȡ������Ʒ�б�������Ʒ���⣩
	 */
	public CheckKitItem[] getCheckKitItems();

	/**
	 * ��ȡȷ�ϴ����¼�б�
	 */
	public CheckedOutItem[] getCheckedOutItems(); 
}
