package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.config.entity.SalesmanCreditInfo;
import com.spark.psi.publish.base.config.entity.SalesmanCreditItem;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

public class SalesManCreditItemImpl implements SalesmanCreditItem,SalesmanCreditInfo {

	/**
	 * ������Աid
	 */
	private GUID salesmanId;

	/**
	 * ������Ա����
	 */
	private String salesmanName;

	/**
	 * ���ڲ�����Ϣ
	 */
	private String departmentInfo;

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
	 * @return the salesmanId
	 */
	public GUID getSalesmanId() {
		return salesmanId;
	}

	/**
	 * @return the salesmanName
	 */
	public String getSalesmanName() {
		return salesmanName;
	}

	/**
	 * @return the departmentInfo
	 */
	public String getDepartmentInfo() {
		return departmentInfo;
	}

	/**
	 * @return the customerCreditLimit
	 */
	public double getCustomerCreditLimit() {
		return customerCreditLimit;
	}

	/**
	 * @return the customerCreditDayLimit
	 */
	public int getCustomerCreditDayLimit() {
		return customerCreditDayLimit;
	}

	/**
	 * @return the availableTotalCreditLimit
	 */
	public double getAvailableTotalCreditLimit() {
		return availableTotalCreditLimit;
	}

	/**
	 * @return the customerCountUsed
	 */
	public int getCustomerCountUsed() {
		return customerCountUsed;
	}

	/**
	 * @return the customerCreditUsed
	 */
	public double getCustomerCreditUsed() {
		return customerCreditUsed;
	}

	/**
	 * @return the orderApprovalLimit
	 */
	public double getOrderApprovalLimit() {
		return orderApprovalLimit;
	}

	public void setSalesmanId(GUID salesmanId){
    	this.salesmanId = salesmanId;
    }

	public void setSalesmanName(String salesmanName){
    	this.salesmanName = salesmanName;
    }

	public void setDepartmentInfo(String departmentInfo){
    	this.departmentInfo = departmentInfo;
    }

	public void setCustomerCreditLimit(double customerCreditLimit){
    	this.customerCreditLimit = customerCreditLimit;
    }

	public void setCustomerCreditDayLimit(int customerCreditDayLimit){
    	this.customerCreditDayLimit = customerCreditDayLimit;
    }

	public void setAvailableTotalCreditLimit(double availableTotalCreditLimit){
    	this.availableTotalCreditLimit = availableTotalCreditLimit;
    }

	public void setCustomerCountUsed(int customerCountUsed){
    	this.customerCountUsed = customerCountUsed;
    }

	public void setCustomerCreditUsed(double customerCreditUsed){
    	this.customerCreditUsed = customerCreditUsed;
    }

	public void setOrderApprovalLimit(double orderApprovalLimit){
    	this.orderApprovalLimit = orderApprovalLimit;
    }

	
}
