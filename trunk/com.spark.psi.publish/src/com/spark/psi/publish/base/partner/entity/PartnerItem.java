package com.spark.psi.publish.base.partner.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 合作伙伴（客户供应商）维护列表
 * </p>
 *  
 *  
 * @version 2012-3-2
 */
public interface  PartnerItem {

	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();
	
	public String getPartnerNo();
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

	/**
	 * 交易总金额
	 * 
	 * @return Double
	 */
	public Double getTradeTotalAmount();

	/**
	 * 交易总次数
	 * 
	 * @return int
	 */
	public int getTradeTotalCount();

	/**
	 * 最近一次交易日期
	 * 
	 * @return long
	 */
	public long getRecentTradeDate();

	/**
	 * 应收应付余额
	 * 
	 * @return Double
	 */
	public Double getBalanceAmount();

	/**
	 * 联系人id
	 * 
	 * @return GUID
	 */
	public GUID getContactId();

	/**
	 * 联系人姓名
	 * 
	 * @return String
	 */
	public String getContactName();
	
	/**
	 * 联系人手机
	 * 
	 * @return String
	 */
	public String getContactMobileNo();
	
	/**
	 * 联系人固定电话
	 * 
	 * @return String
	 */
	public String getContactLandLineNumber();
	
	/**
	 * 联系人邮箱
	 * 
	 * @return String
	 */
	public String getContactEmail();

	/**
	 * 是否已经关联
	 * 
	 * @return boolean
	 */
	public boolean isUsed();
	

}
