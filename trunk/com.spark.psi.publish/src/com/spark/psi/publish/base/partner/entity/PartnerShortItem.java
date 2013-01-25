package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

/**
 * 
 * <p>
 * 合作伙伴（客户供应商）维护列表
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
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
	 * 全称
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * 简称
	 * 
	 * @return String
	 */
	public String getShortName();

	/**
	 * 传真号码
	 * 
	 * @return String
	 */
	public String getFax();
	
	/**
	 * 获得信用额度
	 * 
	 * @return double
	 */
	public double getCreditAmount();

	
	/**
	 * 省
	 * 
	 * @return EnumEntity
	 */
	public EnumEntity getProvince();
	
	/**
	 * 市
	 * 
	 * @return EnumEntity
	 */
	public EnumEntity getCity();

	/**
	 * 县
	 * 
	 * @return EnumEntity
	 */
	public EnumEntity getTown();	
	
	/**
	 * 详细地址
	 * 
	 * @return String
	 */
	public String getAddress();

}
