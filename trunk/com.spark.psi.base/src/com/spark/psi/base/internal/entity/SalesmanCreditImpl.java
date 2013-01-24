package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.publish.base.config.entity.SalesmanCreditInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * <p>信用额度及账期设置实体</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 
 * @version 2011-10-26
 */
@StructClass
public class SalesmanCreditImpl  implements SalesmanCredit,SalesmanCreditInfo {


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
	
	protected EmployeeInfo salesman;
	
	
	
	public EmployeeInfo getSalesman(){
    	return salesman;
    }


	public void setSalesman(EmployeeInfo salesman){
    	this.salesman = salesman;
    }


	/**
	 * 用户id
	 */
	private GUID id;
	
	private GUID tenantId;
	
	


	public GUID getTenantId(){
    	return tenantId;
    }


	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }


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


}
