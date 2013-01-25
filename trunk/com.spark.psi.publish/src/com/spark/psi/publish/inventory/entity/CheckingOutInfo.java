package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckedOutItem;

/**
 * ������������<br>
 * ��ѯ������ͨ�����ⵥID��ѯCheckingOutInfo����
 * ok
 */
public interface CheckingOutInfo {

	/**
	 * ��ȡ���ⵥid
	 */
	public GUID getSheetId();

	/**
	 * ��ȡ��������
	 */
	public long getCreateDate();

	/**
	 * ��ȡ�ƻ�����ʱ��
	 */
	public long getPlanCheckoutDate();

	/**
	 * ��ȡ�ϴγ���ʱ��
	 */
	public long getLastCheckoutDate();

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
	 * ��ȡ���ⵥ״̬
	 */
	public CheckingOutStatus getStatus();

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
	 * ��ȡ��ֹԭ����ֹ״̬��
	 */
	public String getStopReason();
	
	public boolean isStoped();

	/**
	 * ��ȡ������Ʒ�б����۳�����߲ɹ��˻����⣩
	 */
	public CheckingGoodsItem[] getCheckingGoodsItems();

	/**
	 * ��ȡ������Ʒ�б�������Ʒ���⣩
	 */
	public CheckKitItem[] getCheckKitItems();

	/**
	 * ��ȡȷ�ϴ����¼�б�
	 */
	public CheckedOutItem[] getCheckedOutItems();

	/**
	 * ȷ�ϳ����¼
	 */
}