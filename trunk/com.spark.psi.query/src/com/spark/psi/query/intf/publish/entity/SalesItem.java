package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderStatus;

/**
 * 销售订单查询实体<BR>
 * 查询方法：<BR>
 * SalesListEntity+GetSalesListKey;
 *
 */
public interface SalesItem {
	public String getCustomerNo();
	public String getCustomerName();
	public String getSheetNo();
	public long getCreateDate();
	public long getDeliveryDate();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public double getPrice();
	public double getCount();
	public double getAmount();
	public double getCheckedoutCount();
	public double getCheckedoutAmount();
	public double getCheckoutCount();
	public double getCheckoutAmount();
	
	public String getCustomerShortName();
	public GUID getCustomerId();
	public GUID getBillsId();
	public String getBillsNo();
	public GUID getGoodsId();
	public String getUnit();
	public OrderStatus getStatus();
//	private OrderStatus status;
}
