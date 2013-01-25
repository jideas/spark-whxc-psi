package com.spark.order.intf.entity;

/**
 * <p>客户/供应商（已完单据页签）交易记录数据</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-7
 */
public class CuspBillsEntity {
	private double orderTotalAmount;//销售/采购总额
	private double orderReceipt;//销售/采购收款
	private double cancelAmount;//销售/采购退款
	private int orderTime;//销售/采购次数
	private int cancelTime;//销售/采购退货次数
	/**
	 * @return the orderTotalAmount
	 */
	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	/**
	 * @return the orderReceipt
	 */
	public double getOrderReceipt() {
		return orderReceipt;
	}
	/**
	 * @return the cancelAmount
	 */
	public double getCancelAmount() {
		return cancelAmount;
	}
	/**
	 * @return the orderTime
	 */
	public int getOrderTime() {
		return orderTime;
	}
	/**
	 * @return the cancelTime
	 */
	public int getCancelTime() {
		return cancelTime;
	}
	/**
	 * @param orderTotalAmount the orderTotalAmount to set
	 */
	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	/**
	 * @param orderReceipt the orderReceipt to set
	 */
	public void setOrderReceipt(double orderReceipt) {
		this.orderReceipt = orderReceipt;
	}
	/**
	 * @param cancelAmount the cancelAmount to set
	 */
	public void setCancelAmount(double cancelAmount) {
		this.cancelAmount = cancelAmount;
	}
	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(int orderTime) {
		this.orderTime = orderTime;
	}
	/**
	 * @param cancelTime the cancelTime to set
	 */
	public void setCancelTime(int cancelTime) {
		this.cancelTime = cancelTime;
	}
}
