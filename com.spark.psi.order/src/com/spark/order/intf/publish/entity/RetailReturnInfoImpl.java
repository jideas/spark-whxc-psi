package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;

/**
 * 
 * <p>零售退货维护实体</p>
 *


 *
 
 * @version 2012-3-6
 */
public class RetailReturnInfoImpl implements com.spark.psi.publish.order.entity.RetailReturnInfo{
	
	/**
	 * 零售单id
	 */
	private GUID id;// GUID
	
	/**
	 * 零售单据编号
	 */
	private String orderNumber;// 单据编号
	
	private String customerName; //客户名称
	
	private String retrunProof;  //退货凭据
	
	private long deliveryDate;// 交货日期 
	
	private String payPersonLabel;// 退款人及日期
	
	private String createPersonLabel; //制单人及日期
	
	private DealingsWay dealingsWay; //支付方式
	
	private double amount;	 //订单金额	
	
	private String memo;// 备注
	
	private GUID customerId;
	
	private SalesReturnGoodsItemImpl[] salesReturnGoodsItems; //退货商品明细

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

	public String getRetrunProof(){
    	return retrunProof;
    }

	public long getDeliveryDate(){
    	return deliveryDate;
    }

	public String getPayPersonLabel(){
    	return payPersonLabel;
    }

	public String getCreatePersonLabel(){
    	return createPersonLabel;
    }

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public double getAmount(){
    	return amount;
    }

	public String getRemark(){
    	return memo;
    }

	public SalesReturnGoodsItemImpl[] getSalesReturnGoodsItems(){
    	return salesReturnGoodsItems;
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
	 * @param retrunProof the retrunProof to set
	 */
	public void setRetrunProof(String retrunProof) {
		this.retrunProof = retrunProof;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @param payPersonLabel the payPersonLabel to set
	 */
	public void setPayPersonLabel(String payPersonLabel) {
		this.payPersonLabel = payPersonLabel;
	}

	/**
	 * @param createPersonLabel the createPersonLabel to set
	 */
	public void setCreatePersonLabel(String createPersonLabel) {
		this.createPersonLabel = createPersonLabel;
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
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @param salesReturnGoodsItems the salesReturnGoodsItems to set
	 */
	public void setSalesReturnGoodsItems(
			SalesReturnGoodsItemImpl[] salesReturnGoodsItems) {
		this.salesReturnGoodsItems = salesReturnGoodsItems;
	}
	

	

	
}
