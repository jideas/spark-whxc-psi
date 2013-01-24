package com.spark.psi.publish.base.config.entity;

import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

/**
 * 
 * <p>销售人员授权</p>
 *


 *
 
 * @version 2012-5-16
 */
public interface SalesmanCreditInfo {

	/**
	 * 销售人员id
	 * @return the salesmanId
	 */
	public EmployeeInfo getSalesman();


	/**
	 * 可设置信用额度
	 * @return the customerCreditLimit
	 */
	public double getCustomerCreditLimit();

	/**
	 * 可设置账期天数
	 * @return the customerCreditDayLimit
	 */
	public int getCustomerCreditDayLimit();

	/**
	 * 可设置的累计信用额度
	 * @return the availableTotalCreditLimit
	 */
	public double getAvailableTotalCreditLimit();

	/**
	 * 已设置的客户数量
	 * @return the customerCountUsed
	 */
	public int getCustomerCountUsed();
	/**
	 * 已设置的信用额度累计值
	 * @return the customerCreditUsed
	 */
	public double getCustomerCreditUsed();

	/**
	 * 订单审核限额
	 * @return the orderApprovalLimit
	 */
	public double getOrderApprovalLimit();

}