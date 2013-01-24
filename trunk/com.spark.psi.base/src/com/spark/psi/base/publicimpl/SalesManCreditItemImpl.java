package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.config.entity.SalesmanCreditInfo;
import com.spark.psi.publish.base.config.entity.SalesmanCreditItem;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

public class SalesManCreditItemImpl implements SalesmanCreditItem,SalesmanCreditInfo {

	/**
	 * 销售人员id
	 */
	private GUID salesmanId;

	/**
	 * 销售人员名称
	 */
	private String salesmanName;

	/**
	 * 所在部门信息
	 */
	private String departmentInfo;

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
