package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.RetailStatus;


/**
 * 
 * <p>零售单维护实体</p>
 *


 *
 
 * @version 2012-3-6
 */
public interface RetailOrderInfo  {
	
	
//	/**
//	 * 零售单id
//	 */
//	private GUID id;// GUID
//	
//	/**
//	 * 零售单据编号
//	 */
//	private String orderNumber;// 单据编号
//	
//	private String customerName; //客户名称
//	
//	private String consigneeName;  //收货人
//	
//	private String deliveryAddress;  //送货地址
//	
//	private String mobileNumber;  //电话
//	
//	private long deliveryDate;// 交货日期 
//	
//	private String receiptPersonLabel;// 收款人及日期
//	
//	private String createPersonLabel; //制单人及日期
//	
//	private OrderType orderType;// 类型
//	
//	private EnumEntity dealingsWay; //支付方式
//	
//	private RetailStatus retailstatus;// 处理情况	
//	
//	private double amount;	 //订单金额	
//	
//	private String memo;// 备注
//	
//	private double discountAmount;//	整单折扣	N
//	
//	private SalesOrderGoodsItem[] salesOrderGoodsItems;  //销售商品明细	

	public GUID getId();

	public String getOrderNumber();
	
	public GUID getCustomerId();
	
	public String getCustomerName();

	public String getConsigneeName();

	public String getDeliveryAddress();

	public String getMobileNo();

	public long getDeliveryDate();

	public String getReceiptPersonLabel();

	public String getCreatePersonLabel();

	public OrderType getOrderType();

	public DealingsWay getDealingsWay();

	public RetailStatus getRetailStatus();

	public double getAmount();

	public String getRemark();

	public double getDiscountAmount();

	public SalesOrderGoodsItem[] getSalesOrderGoodsItems();
}
