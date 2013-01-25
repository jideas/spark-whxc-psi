package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;

/**
 * 
 * <p>销售待配货订单列表</p>
 * 查询方法
 * 1:context.find(ListEntity<SalesDistributeOrderItem)>,GetSalesDistributeOrderListKey)
 *


 *
 
 * @version 2012-3-16
 */
public interface SalesDistributeOrderItem{

	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();
	/**
	 * 订单编号
	 * 
	 * @return String
	 */
	public String getOrderNumber();
	/**
	 * 客户id
	 * 
	 * @return GUID
	 */
	public GUID getCustomerId();
	/**
	 * 客户简称
	 * 
	 * @return String
	 */
	public String getCustomerShortName();
	/**
	 * 客户全称
	 * 
	 * @return String
	 */
	public String getCustomerName();
	/**
	 * 收货地址
	 * 
	 * @return String
	 */
	public String getAddress();
	/**
	 * 交货日期
	 * @return long
	 */
	public long getDeliveryDate();
	/**
	 * 可操作动作
	 * 
	 * @return OrderAction[]
	 */
	public OrderAction[] getActions();
	
}
