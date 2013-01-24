package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class SalesManCreditOrmEntity {

	/**
	 * 单个客户可设置的信用额度上限
	 */
	protected double customerCreditLimit;

	/**
	 * 可设置的客户最长账期
	 */
	protected int customerCreditDayLimit;

	/**
	 * 可设置的总信用额度
	 */
	protected double availableTotalCreditLimit;

	/**
	 * 已设置客户数
	 */
	protected int customerCountUsed;

	/**
	 * 已设置的客户信用额度
	 */
	protected double customerCreditUsed;

	/**
	 * 订单审批限额
	 */
	protected double orderApprovalLimit;
	
	/**
	 * 用户id
	 */
	private GUID id;

	private GUID tenantId;

	public double getCustomerCreditLimit(){
    	return customerCreditLimit;
    }

	public void setCustomerCreditLimit(double customerCreditLimit){
    	this.customerCreditLimit = customerCreditLimit;
    }

	public int getCustomerCreditDayLimit(){
    	return customerCreditDayLimit;
    }

	public void setCustomerCreditDayLimit(int customerCreditDayLimit){
    	this.customerCreditDayLimit = customerCreditDayLimit;
    }

	public double getAvailableTotalCreditLimit(){
    	return availableTotalCreditLimit;
    }

	public void setAvailableTotalCreditLimit(double availableTotalCreditLimit){
    	this.availableTotalCreditLimit = availableTotalCreditLimit;
    }

	public int getCustomerCountUsed(){
    	return customerCountUsed;
    }

	public void setCustomerCountUsed(int customerCountUsed){
    	this.customerCountUsed = customerCountUsed;
    }

	public double getCustomerCreditUsed(){
    	return customerCreditUsed;
    }

	public void setCustomerCreditUsed(double customerCreditUsed){
		if(customerCreditUsed<0)customerCreditUsed = 0;
    	this.customerCreditUsed = customerCreditUsed;
    }

	public double getOrderApprovalLimit(){
    	return orderApprovalLimit;
    }

	public void setOrderApprovalLimit(double orderApprovalLimit){
    	this.orderApprovalLimit = orderApprovalLimit;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }
	
	
	
}
