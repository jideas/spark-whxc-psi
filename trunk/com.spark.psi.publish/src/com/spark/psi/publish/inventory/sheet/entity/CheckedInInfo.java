package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;

public interface CheckedInInfo {

	/**
	 * ��ȡ��ⵥid
	 */
	public GUID getSheetId(); 
	public String getSheetNo(); 

	/**
	 * ��ȡ���ʱ��
	 */
	public long getCheckinDate(); 

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
	 * ��ȡ�����Ʒ�б��ɹ������������˻���⣩
	 */
	public CheckingGoodsItem[] getCheckingGoodsItems();

	/**
	 * ��ȡ�����Ʒ�б�������Ʒ����⣩
	 */
	public CheckKitItem[] getCheckKitItems();  

	/**
	 * ��ȡ������ʱ�����ʽ
	 */
	public EnumEntity getDealingsWay();
	
	/**
	 * ��Ʒ��Դ
	 */
	public String getGoodsFrom();
 
}
