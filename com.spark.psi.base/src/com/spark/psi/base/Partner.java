package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.RetailPartner;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * 
 * <p>
 * �ͻ���Ӧ��
 * </p>
 * 
 */
public interface Partner {

	/**
	 * ���ۿͻ�ID
	 */
	static final GUID RetailCustomerId = RetailPartner.Customer.getId();
	static final String RetailCustomerName = RetailPartner.Customer.getName();
	/**
	 * ���ǲɹ���Ӧ��ID
	 */
	static final GUID RetailPurchaseSupplierId = RetailPartner.Supplier.getId();
	static final String RetailPurchaseSupplierName = RetailPartner.Supplier
			.getName();

	/**
	 * �ͻ�id
	 * 
	 * @return GUID
	 */
	public GUID getId();
	public String getCode();

	/**
	 * �ͻ�ȫ��
	 * 
	 * @return String
	 */
	public String getName();
	public String getSupplierType();
	public String getSupplierCooperation();
	
	/**
	 * �ͻ����
	 * 
	 * @return String
	 */
	public String getShortName();
	public String getWorkTel() ;
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
	 * @return Employee
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

}
