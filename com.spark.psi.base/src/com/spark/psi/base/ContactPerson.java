package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

/**
 * 
 * <p>��ϵ��</p>
 *


 *
 
 * @version 2012-3-9
 */
public interface ContactPerson{


	/**
	 * ��ϵ������
	 */
	public String getName();


	/**
	 * �����ͻ����߹�Ӧ�̵�ID
	 */
	public GUID getPartnerId();

	/**
	 * ����
	 */
	public String getDepartment();


	/**
	 * �̶��绰
	 */
	public String getLandLineNumber();

	/**
	 * �ֻ�
	 */
	public String getMobileNo();
	
	/**
	 * �����ʼ�
	 */
	public String getEmail();

	
	/**
	 * ��ַ����ʡ��
	 */
	public EnumEntity getProvince();


	/**
	 * ��ַ���ڳ���
	 */
	public EnumEntity getCity();


	/**
	 * ��ַ��������
	 */
	public EnumEntity getDistrict();

	/**
	 * ��ַ
	 */
	public String getAddress();

	/**
	 * ��������
	 */
	public String getPostCode();
	
	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();

	/**
	 * ���ʹ������
	 * 
	 * @return long
	 */
	public long getLastDate();
	
}
