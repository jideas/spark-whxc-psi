package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * �ͻ�ά���б�
 * </p>
 * �ͻ�ά�����б��ѯ���пͻ�  ListEntity<CustomerItem>+GetCustomerListKey
 * ���۶���ѡ��ͻ������ѯ���ÿͻ��б�  ListEntity<CustomerItem>+GetCustomerListKey
 * 
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-2
 */
public interface CustomerItem extends PartnerItem {

	/**
	 * ���ö��
	 */
	public Double getCreditAmount();

	/**
	 * ����
	 * 
	 * @return int
	 */
	public int getAccountPeriod();

	/**
	 * �Ƿ��ѹ�����
	 * 
	 * @return boolean
	 */
	public boolean isCreditExpired();
	
	/**
	 * �Ƿ��ٽ�����
	 * 
	 * @return boolean
	 */
	public boolean isCreditTowards();
	
	/**
	 * Ԥ������
	 * 
	 * @return int
	 */
	public long getRemindDay();

	/**
	 * ҵ������id
	 * 
	 * @return GUID
	 */
	public GUID getSalesmanId();

	/**
	 * ҵ����������
	 * 
	 * @return String
	 */
	public String getSalesmanName();
	
	/**
	 * ��ó���
	 * 
	 * @return String
	 */
	public String getCity();
	
	/**
	 * ���ʡ��
	 * 
	 * @return String
	 */
	public String getProvince();
	
	/**
	 * ��ϸ��ַ
	 * 
	 * @return String
	 */
	public String getAddress();
	

}
