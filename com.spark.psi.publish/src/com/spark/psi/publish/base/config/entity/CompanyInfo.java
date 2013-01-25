package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

/**
 * 
 * <p>��ҵ������Ϣ</p>
 *


 *
 
 * @version 2012-4-9
 */
public interface CompanyInfo{
	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();
	/**
	 * ��ҵȫ��
	 * 
	 * @return String
	 */
	public String getCompanyName();
	/**
	 * ��ҵ���
	 * 
	 * @return String
	 */
	public String getCompanyShortName();

	/**
	 * ϵͳ����
	 * 
	 * @return String
	 */
	public String getSystemName();

	/**
	 * ʡ
	 * 
	 * @return String
	 */
	public EnumEntity getProvince();

	/**
	 * ��
	 * 
	 * @return String
	 */
	public EnumEntity getCity();

	/**
	 * ��
	 * 
	 * @return String
	 */
	public EnumEntity getDistrict();

	/**
	 * ��ϸ��ַ
	 * 
	 * @return String
	 */
	public String getAddress();

	/**
	 * �ʱ�
	 * 
	 * @return String
	 */
	public String getPostcode();

	/**
	 * �绰
	 * 
	 * @return String
	 */
	public String getLandLineNumber();

	/**
	 * ����
	 * 
	 * @return String
	 */
	public String getFaxNumber();
	
	/**
	 * logo
	 * 
	 * @return byte[]
	 */
	public byte[] getLogo();
}
