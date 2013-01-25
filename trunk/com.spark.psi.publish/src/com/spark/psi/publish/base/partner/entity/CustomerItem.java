package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 客户维护列表
 * </p>
 * 客户维护主列表查询所有客户  ListEntity<CustomerItem>+GetCustomerListKey
 * 销售订单选择客户界面查询可用客户列表  ListEntity<CustomerItem>+GetCustomerListKey
 * 
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-2
 */
public interface CustomerItem extends PartnerItem {

	/**
	 * 信用额度
	 */
	public Double getCreditAmount();

	/**
	 * 账期
	 * 
	 * @return int
	 */
	public int getAccountPeriod();

	/**
	 * 是否已过账期
	 * 
	 * @return boolean
	 */
	public boolean isCreditExpired();
	
	/**
	 * 是否临近账期
	 * 
	 * @return boolean
	 */
	public boolean isCreditTowards();
	
	/**
	 * 预警天数
	 * 
	 * @return int
	 */
	public long getRemindDay();

	/**
	 * 业务负责人id
	 * 
	 * @return GUID
	 */
	public GUID getSalesmanId();

	/**
	 * 业务负责人姓名
	 * 
	 * @return String
	 */
	public String getSalesmanName();
	
	/**
	 * 获得城市
	 * 
	 * @return String
	 */
	public String getCity();
	
	/**
	 * 获得省市
	 * 
	 * @return String
	 */
	public String getProvince();
	
	/**
	 * 详细地址
	 * 
	 * @return String
	 */
	public String getAddress();
	

}
