package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

/**
 * 
 * <p>
 * ������飨�ͻ���Ӧ�̣�ά���б�
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-2
 */
public interface  PartnerShortItem {

	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();

	/**
	 * ȫ��
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * ���
	 * 
	 * @return String
	 */
	public String getShortName();

	/**
	 * �������
	 * 
	 * @return String
	 */
	public String getFax();
	
	/**
	 * ������ö��
	 * 
	 * @return double
	 */
	public double getCreditAmount();

	
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
	 * ��ϸ��ַ
	 * 
	 * @return String
	 */
	public String getAddress();

}
