package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;

public interface OnlineOrderItem {

	/**
	 * 订单编号
	* @Title: getOrderNumber 
	* @param @return    参数 
	* @return String    返回类型 
	* @throws
	 */
	public String getOrderNumber();

	/**
	 * 供应商编号
	* @Title: getPartnerId 
	* @param @return    参数 
	* @return GUID    返回类型 
	* @throws
	 */
	public GUID getPartnerId();

	/**
	 * 供应商简称
	* @Title: getPartnerShortName 
	* @param @return    参数 
	* @return String    返回类型 
	* @throws
	 */
	public String getPartnerShortName();

	/**
	 * 供应商全称
	* @Title: getPartnerName 
	* @param @return    参数 
	* @return String    返回类型 
	* @throws
	 */
	public String getPartnerName();

	/**
	 * 订货日期
	* @Title: getCreateDate 
	* @param @return    参数 
	* @return long    返回类型 
	* @throws
	 */
	public long getCreateDate();

	/**
	 * 金额
	* @Title: getAmount 
	* @param @return    参数 
	* @return double    返回类型 
	* @throws
	 */
	public double getAmount();

	/**
	 * 明细
	* @Title: getOrderDetail 
	* @param @return    参数 
	* @return String    返回类型 
	* @throws
	 */
	public String getOrderDetail();

}
