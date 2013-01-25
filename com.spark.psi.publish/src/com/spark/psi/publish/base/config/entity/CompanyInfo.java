package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

/**
 * 
 * <p>企业基础信息</p>
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
	 * 企业全称
	 * 
	 * @return String
	 */
	public String getCompanyName();
	/**
	 * 企业简称
	 * 
	 * @return String
	 */
	public String getCompanyShortName();

	/**
	 * 系统名称
	 * 
	 * @return String
	 */
	public String getSystemName();

	/**
	 * 省
	 * 
	 * @return String
	 */
	public EnumEntity getProvince();

	/**
	 * 市
	 * 
	 * @return String
	 */
	public EnumEntity getCity();

	/**
	 * 县
	 * 
	 * @return String
	 */
	public EnumEntity getDistrict();

	/**
	 * 详细地址
	 * 
	 * @return String
	 */
	public String getAddress();

	/**
	 * 邮编
	 * 
	 * @return String
	 */
	public String getPostcode();

	/**
	 * 电话
	 * 
	 * @return String
	 */
	public String getLandLineNumber();

	/**
	 * 传真
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
