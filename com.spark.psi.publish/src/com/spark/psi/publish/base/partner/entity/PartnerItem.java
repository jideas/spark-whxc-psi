package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * ������飨�ͻ���Ӧ�̣�ά���б�
 * </p>
 *  
 *  
 * @version 2012-3-2
 */
public interface  PartnerItem {

	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();
	
	public String getPartnerNo();
	/**
	 * �ͻ�ȫ��
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * �ͻ����
	 * 
	 * @return String
	 */
	public String getShortName();

	/**
	 * �����ܽ��
	 * 
	 * @return Double
	 */
	public Double getTradeTotalAmount();

	/**
	 * �����ܴ���
	 * 
	 * @return int
	 */
	public int getTradeTotalCount();

	/**
	 * ���һ�ν�������
	 * 
	 * @return long
	 */
	public long getRecentTradeDate();

	/**
	 * Ӧ��Ӧ�����
	 * 
	 * @return Double
	 */
	public Double getBalanceAmount();

	/**
	 * ��ϵ��id
	 * 
	 * @return GUID
	 */
	public GUID getContactId();

	/**
	 * ��ϵ������
	 * 
	 * @return String
	 */
	public String getContactName();
	
	/**
	 * ��ϵ���ֻ�
	 * 
	 * @return String
	 */
	public String getContactMobileNo();
	
	/**
	 * ��ϵ�˹̶��绰
	 * 
	 * @return String
	 */
	public String getContactLandLineNumber();
	
	/**
	 * ��ϵ������
	 * 
	 * @return String
	 */
	public String getContactEmail();

	/**
	 * �Ƿ��Ѿ�����
	 * 
	 * @return boolean
	 */
	public boolean isUsed();
	

}
