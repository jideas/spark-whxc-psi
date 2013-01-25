package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.PartnerStatus;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * 客户维护实体
 * 
 */
public interface PartnerInfo {
	/**
	 * 客户id
	 * 
	 * @return GUID
	 */
	public GUID getId();

	public String getNumber();
	/**
	 * 客户全称
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * 客户简称
	 * 
	 * @return String
	 */
	public String getShortName();
	
	public String getWorkTel();
	
	public String getPostcode();

	/**
	 * 传真号码
	 * 
	 * @return String
	 */
	public String getFax();

	/**
	 * 联系人列表
	 * 
	 */
	public String getLinkmanName();

	public String getLinkmanSuffix();

	public String getLinkmanTel();

	public String getLinkmanMobile();

	public String getLinkmanEmail();

	/**
	 * 获得信用额度
	 * 
	 * @return double
	 */
	public double getCreditAmount();

	/**
	 * 获得客户供应商类型（客户还是供应商）
	 * 
	 * @return PartnerType
	 */
	public PartnerType getPartnerType();

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
	 * 业务负责人
	 * 
	 * @return GUID
	 */
	public EmployeeInfo getBusinessPerson();

	/**
	 * 状态
	 * 
	 * @return PartnerStatus
	 */
	public PartnerStatus getStatus();

	/**
	 * 已经使用过
	 * 
	 * @return boolean
	 */
	public boolean isUsed();

	/**
	 * 地址
	 * 
	 * @return String
	 */
	public String getAddress();

	/**
	 * 账期天数
	 * 
	 * @return int
	 */
	public int getAccountPeriod(); 

	/**
	 * 预警天数
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
