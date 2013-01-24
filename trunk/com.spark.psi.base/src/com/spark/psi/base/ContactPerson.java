package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;

/**
 * 
 * <p>联系人</p>
 *


 *
 
 * @version 2012-3-9
 */
public interface ContactPerson{


	/**
	 * 联系人名称
	 */
	public String getName();


	/**
	 * 所属客户或者供应商的ID
	 */
	public GUID getPartnerId();

	/**
	 * 部门
	 */
	public String getDepartment();


	/**
	 * 固定电话
	 */
	public String getLandLineNumber();

	/**
	 * 手机
	 */
	public String getMobileNo();
	
	/**
	 * 电子邮件
	 */
	public String getEmail();

	
	/**
	 * 地址所在省份
	 */
	public EnumEntity getProvince();


	/**
	 * 地址所在城市
	 */
	public EnumEntity getCity();


	/**
	 * 地址所在区县
	 */
	public EnumEntity getDistrict();

	/**
	 * 地址
	 */
	public String getAddress();

	/**
	 * 邮政编码
	 */
	public String getPostCode();
	
	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();

	/**
	 * 最后使用日期
	 * 
	 * @return long
	 */
	public long getLastDate();
	
}
