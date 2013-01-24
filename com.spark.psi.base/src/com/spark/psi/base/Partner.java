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
 * 客户供应商
 * </p>
 * 
 */
public interface Partner {

	/**
	 * 零售客户ID
	 */
	static final GUID RetailCustomerId = RetailPartner.Customer.getId();
	static final String RetailCustomerName = RetailPartner.Customer.getName();
	/**
	 * 零星采购供应商ID
	 */
	static final GUID RetailPurchaseSupplierId = RetailPartner.Supplier.getId();
	static final String RetailPurchaseSupplierName = RetailPartner.Supplier
			.getName();

	/**
	 * 客户id
	 * 
	 * @return GUID
	 */
	public GUID getId();
	public String getCode();

	/**
	 * 客户全称
	 * 
	 * @return String
	 */
	public String getName();
	public String getSupplierType();
	public String getSupplierCooperation();
	
	/**
	 * 客户简称
	 * 
	 * @return String
	 */
	public String getShortName();
	public String getWorkTel() ;
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
	 * @return Employee
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

}
