package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * �ͻ�ά��ʵ��
 * 
 */
public interface PartnerInfo {
	/**
	 * �ͻ�id
	 * 
	 * @return GUID
	 */
	public GUID getId();

	public String getNumber();
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
	
	public String getWorkTel();
	
	public String getPostcode();

	/**
	 * �������
	 * 
	 * @return String
	 */
	public String getFax();

	/**
	 * ��ϵ���б�
	 * 
	 */
	public String getLinkmanName();

	public String getLinkmanSuffix();

	public String getLinkmanTel();

	public String getLinkmanMobile();

	public String getLinkmanEmail();

	/**
	 * ������ö��
	 * 
	 * @return double
	 */
	public double getCreditAmount();

	/**
	 * ��ÿͻ���Ӧ�����ͣ��ͻ����ǹ�Ӧ�̣�
	 * 
	 * @return PartnerType
	 */
	public PartnerType getPartnerType();

	/**
	 * ʡ
	 * 
	 * @return EnumEntity
	 */
	public EnumEntity getProvince();

	/**
	 * ��
	 * 
	 * @return EnumEntity
	 */
	public EnumEntity getCity();

	/**
	 * ��
	 * 
	 * @return EnumEntity
	 */
	public EnumEntity getTown();

	/**
	 * ҵ������
	 * 
	 * @return GUID
	 */
	public EmployeeInfo getBusinessPerson();

	/**
	 * ״̬
	 * 
	 * @return PartnerStatus
	 */
	public PartnerStatus getStatus();

	/**
	 * �Ѿ�ʹ�ù�
	 * 
	 * @return boolean
	 */
	public boolean isUsed();

	/**
	 * ��ַ
	 * 
	 * @return String
	 */
	public String getAddress();

	/**
	 * ��������
	 * 
	 * @return int
	 */
	public int getAccountPeriod(); 

	/**
	 * Ԥ������
	 * 
	 * @return int
	 */
	public int getAccountRemind(); 
	
	/**
	 * 
	 * @return
	 */
	public String getRemark();
	
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
	
	 
}
