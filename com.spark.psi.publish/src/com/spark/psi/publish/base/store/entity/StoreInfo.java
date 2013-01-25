package com.spark.psi.publish.base.store.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * �ֿ���ϸ��Ϣ<br>
 * ��ѯ������ͨ���ֿ�IDֱ�Ӳ�ѯStoreInfo����
 */
public interface StoreInfo  {

	/**
	 * �汾��
	 * 
	 * @return int
	 */
	public int getRecver();
	
	/**
	 * id
	 * @return the id
	 */
	public GUID getId();

	/**
	 * �ֿ�����
	 * @return the name
	 */
	public String getName();
	
	/**
	 * ��òֿ���
	 * @return
	 */
	public String getStoreNo();

	/**
	 * ״̬
	 * @return the status
	 */
	public StoreStatus getStatus();

	/**
	 * ʡ
	 * @return the province
	 */
	public EnumEntity getProvince();

	/**
	 * ��
	 * @return the city
	 */
	public EnumEntity getCity();

	/**
	 * ��
	 * @return the district
	 */
	public EnumEntity getTown();

	/**
	 * ��ϸ��ַ
	 * @return the address
	 */
	public String getAddress();
	/**
	 * �ʱ�
	 * @return the postCode
	 */
	public String getPostcode();

	/**
	 * �ջ���
	 * @return the consignee
	 */
	public String getConsignee();

	/**
	 * �ջ����ֻ�
	 * @return the mobileNumber
	 */
	public String getMobileNo();

	/**
	 * ��Ӧ�Ĳֿ⸺����
	 * @return the keepers
	 */
	public EmployeeInfo[] getKeepers();
	
	/**
	 * ��øòֿ�Ļ�λ
	 * @return
	 */
	public ShelfItem[] getShelfItems();
	
	/**
	 * ��øòֿ�Ļ�λ����
	 * @return
	 */
	public int getShelfCount();
	
	/**
	 * ��û�λ��Ĭ�ϲ���
	 * @return
	 */
	public int getDefaultTiersCount();

}
