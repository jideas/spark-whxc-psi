package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckedInItem;

/**
 * ��ⵥ����<br>
 * ok
 */
public interface CheckingInInfo {

	/**
	 * ��ȡ��ⵥid
	 */
	public GUID getSheetId(); 

	/**
	 * ��ȡ��������
	 */
	public long getCreateDate();

	/**
	 * ��ȡ�ƻ����ʱ��
	 */
	public long getPlanCheckinDate();

	/**
	 * ��ȡ�ϴ����ʱ��
	 */
	public long getLastCheckinDate();

	/**
	 * ��ȡ��ص��ݱ��
	 */
	public String getRelaBillsNo();

	/**
	 * ��ȡ���ֿ�id
	 */
	public GUID getStoreId();

	/**
	 * ��ȡ���ֿ�����
	 */
	public String getStoreName();

	/**
	 * ��ȡ��ⵥ״̬
	 */
	public CheckingInStatus getStatus();

	/**
	 * ��ȡ�������
	 */
	public CheckingInType getType();

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
	 * ��ȡ��ֹԭ����ֹ״̬��
	 */
	public String getStopReason();
	public boolean isStoped();
	/**
	 * ��ȡ�����Ʒ�б��ɹ������������˻���⣩
	 */
	public CheckingGoodsItem[] getCheckingGoodsItems();

	/**
	 * ��ȡ�����Ʒ�б�������Ʒ����⣩
	 */
	public CheckKitItem[] getCheckKitItems();

	/**
	 * ��ȡȷ����ⵥ�б�
	 */
	public CheckedInItem[] getCheckedInItems();

	/**
	 * ��ȡ������ʱ�����ʽ
	 */
	public EnumEntity getDealingsWay();
	
	/**
	 * ��Ʒ��Դ
	 */
	public String getGoodsFrom();

	 
}
