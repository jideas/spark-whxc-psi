package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.order.entity.SalesDistributeOrderItem;

/**
 * <p>销售待配货订单列表</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-16
 */
public class SalesDistributeOrderItemImpl implements SalesDistributeOrderItem{
	private OrderAction[] actions;
	private String address;
	private GUID customerId;
	private GUID id;
	private String orderNumber;
	private String customerName;
	private String customerShortName;
	private long deliveryDate;
	public long getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @return the customerShortName
	 */
	public String getCustomerShortName() {
		return customerShortName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @param customerShortName the customerShortName to set
	 */
	public void setCustomerShortName(String customerShortName) {
		this.customerShortName = customerShortName;
	}
	/**
	 * @return the actions
	 */
	public OrderAction[] getActions() {
		return actions;
	}
	/**
	 * @param actions the actions to set
	 */
	public void setActions(OrderAction... actions) {
		this.actions = actions;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the customerId
	 */
	public GUID getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}
	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
