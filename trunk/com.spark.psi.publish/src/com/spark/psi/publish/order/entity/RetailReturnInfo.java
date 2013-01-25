package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;

/**
 * 
 * <p>零售退货维护实体</p>
 *


 *
 
 * @version 2012-3-6
 */
public interface RetailReturnInfo{
	
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
//	private String retrunProof;  //退货凭据
//	
//	private long deliveryDate;// 交货日期 
//	
//	private String payPersonLabel;// 退款人及日期
//	
//	private String createPersonLabel; //制单人及日期
//	
//	private EnumEntity dealingsWay; //支付方式
//	
//	private double amount;	 //订单金额	
//	
//	private String memo;// 备注
//	
//	private SalesReturnGoodsItem[] salesReturnGoodsItems; //退货商品明细

	public GUID getId();

	public String getOrderNumber();

	public String getCustomerName();
	
	public GUID getCustomerId();

	public String getRetrunProof();

	public long getDeliveryDate();

	public String getPayPersonLabel();

	public String getCreatePersonLabel();

	public DealingsWay getDealingsWay();

	public double getAmount();

	public String getRemark();
	

	public SalesReturnGoodsItem[] getSalesReturnGoodsItems();
	
}
