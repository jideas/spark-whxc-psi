package com.spark.order.intf.publish.entity;

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
public class RetailOrderInfoImpl implements com.spark.psi.publish.order.entity.RetailOrderInfo {
	
	
	/**
	 * 零售单id
	 */
	private GUID id;// GUID
	
	/**
	 * 零售单据编号
	 */
	private String orderNumber;// 单据编号
	
	private String customerName; //客户名称
	
	private String consigneeName;  //收货人
	
	private String deliveryAddress;  //送货地址
	
	private String mobileNumber;  //电话
	
	private long deliveryDate;// 交货日期 
	
	private String receiptPersonLabel;// 收款人及日期
	
	private String createPersonLabel; //制单人及日期
	
	private OrderType orderType;// 类型
	
	private DealingsWay dealingsWay; //支付方式
	
	private RetailStatus retailstatus;// 处理情况	
	
	private double amount;	 //订单金额	
	
	private String memo;// 备注
	
	private double discountAmount;//	整单折扣	N
	
	private GUID customerId;
	
	private SalesOrderGoodsItemImpl[] salesOrderGoodsItems;  //销售商品明细	

	
	public GUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}

	public GUID getId(){
    	return id;
    }

	public String getOrderNumber(){
    	return orderNumber;
    }

	public String getCustomerName(){
    	return customerName;
    }

	public String getConsigneeName(){
    	return consigneeName;
    }

	public String getDeliveryAddress(){
    	return deliveryAddress;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }

	public long getDeliveryDate(){
    	return deliveryDate;
    }

	public String getReceiptPersonLabel(){
    	return receiptPersonLabel;
    }

	public String getCreatePersonLabel(){
    	return createPersonLabel;
    }

	public OrderType getOrderType(){
    	return orderType;
    }

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public RetailStatus getRetailStatus(){
    	return retailstatus;
    }

	public double getAmount(){
    	return amount;
    }

	public String getRemark(){
    	return memo;
    }

	public double getDiscountAmount(){
    	return discountAmount;
    }

	public SalesOrderGoodsItemImpl[] getSalesOrderGoodsItems(){
    	return salesOrderGoodsItems;
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
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @param consigneeName the consigneeName to set
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNo(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @param receiptPersonLabel the receiptPersonLabel to set
	 */
	public void setReceiptPersonLabel(String receiptPersonLabel) {
		this.receiptPersonLabel = receiptPersonLabel;
	}

	/**
	 * @param createPersonLabel the createPersonLabel to set
	 */
	public void setCreatePersonLabel(String createPersonLabel) {
		this.createPersonLabel = createPersonLabel;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/**
	 * @param dealingsWay the dealingsWay to set
	 */
	public void setDealingsWay(DealingsWay dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	/**
	 * @param retailstatus the retailstatus to set
	 */
	public void setRetailStatus(RetailStatus retailstatus) {
		this.retailstatus = retailstatus;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @param salesOrderGoodsItems the salesOrderGoodsItems to set
	 */
	public void setSalesOrderGoodsItems(
			SalesOrderGoodsItemImpl[] salesOrderGoodsItems) {
		this.salesOrderGoodsItems = salesOrderGoodsItems;
	}

	
}
