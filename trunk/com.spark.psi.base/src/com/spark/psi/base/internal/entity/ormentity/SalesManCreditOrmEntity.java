package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class SalesManCreditOrmEntity {

	/**
	 * �����ͻ������õ����ö������
	 */
	protected double customerCreditLimit;

	/**
	 * �����õĿͻ������
	 */
	protected int customerCreditDayLimit;

	/**
	 * �����õ������ö��
	 */
	protected double availableTotalCreditLimit;

	/**
	 * �����ÿͻ���
	 */
	protected int customerCountUsed;

	/**
	 * �����õĿͻ����ö��
	 */
	protected double customerCreditUsed;

	/**
	 * ���������޶�
	 */
	protected double orderApprovalLimit;
	
	/**
	 * �û�id
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
