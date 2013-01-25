package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.OrderType;

/**
 * 
 * <p>零售订单记录</p>
 * 查询零售记录 RetailListEntity<RetailItem> + GetRetailListKey retailstatus = RetailStatus.Completed
 * 送货收款列表  RetailListEntity<RetailItem> + GetRetailListKey  retailstatus = RetailStatus.Delivery
 *


 *
 
 * @version 2012-3-6
 */
public class RetailItemImpl implements com.spark.psi.publish.order.entity.RetailItem{
	
	
	private GUID id;// GUID
	
	private String orderNumber;// 单据编号
	
	private OrderType orderType;// 类型
	
	private String creator;// 制单人
	
	private String operator; //操作人	
	
	private long createDate;// 发生日期
	
	private DealingsWay dealingsWay;  //结算方式
	
	private double amount;   //订单金额
	
	private String consignee;  //收获人
	
	private String deliveryAddress;  // 收获地址

	public GUID getId(){
    	return id;
    }

	public String getOrderNumber(){
    	return orderNumber;
    }

	public OrderType getOrderType(){
    	return orderType;
    }

	public String getCreator(){
    	return creator;
    }

	public String getOperator(){
    	return operator;
    }

	public long getCreateDate(){
    	return createDate;
    }

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public double getAmount(){
    	return amount;
    }

	public String getConsignee(){
    	return consignee;
    }

	public String getDeliveryAddress(){
    	return deliveryAddress;
    }

	/**
	 * @param id the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param dealingsWay the dealingsWay to set
	 */
	public void setDealingsWay(DealingsWay dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param consignee the consignee to set
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

}
