package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.publish.base.config.entity.SalesmanCreditInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * <p>���ö�ȼ���������ʵ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author 
 * @version 2011-10-26
 */
@StructClass
public class SalesmanCreditImpl  implements SalesmanCredit,SalesmanCreditInfo {


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
	
	protected EmployeeInfo salesman;
	
	
	
	public EmployeeInfo getSalesman(){
    	return salesman;
    }


	public void setSalesman(EmployeeInfo salesman){
    	this.salesman = salesman;
    }


	/**
	 * �û�id
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
