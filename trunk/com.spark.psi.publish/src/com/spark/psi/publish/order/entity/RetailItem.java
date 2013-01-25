package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.OrderType;

/**
 * 
 * <p>零售订单记录</p>
 * 查询零售记录  RetailListEntity<RetailItem> + GetRetailListKey retailstatus = RetailStatus.Completed
 * 送货收款列表  RetailListEntity<RetailItem> + GetRetailListKey  retailstatus = RetailStatus.Delivery
 *


 *
 
 * @version 2012-3-6
 */
public interface RetailItem{
	
	
//	private GUID id;// GUID
//	
//	private String orderNumber;// 单据编号
//	
//	private OrderType orderType;// 类型
//	
//	private String creator;// 制单人
//	
//	private String operator; //操作人	
//	
//	private long createDate;// 发生日期
//	
//	private EnumEntity dealingsWay;  //结算方式
//	
//	private double amount;   //订单金额
//	
//	private String consignee;  //收获人
//	
//	private String deliveryAddress;  // 收获地址

	public GUID getId();

	public String getOrderNumber();

	public OrderType getOrderType();

	public String getCreator();

	public String getOperator();

	/**
	 * 反生日期，在送货上门是作为交货日期
	 * @return long
	 */
	public long getCreateDate();

	public DealingsWay getDealingsWay();

	public double getAmount();

	public String getConsignee();

	public String getDeliveryAddress();

}
