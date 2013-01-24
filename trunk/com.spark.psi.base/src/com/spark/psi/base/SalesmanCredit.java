package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

/**
 * 销售人员信用<br>
 * 查询方法：使用员工ID查询SalesmanCredit对象
 */
public interface SalesmanCredit {

	/**
	 * 可设置客户信用额度上限
	 * @return the customerCreditLimit
	 */
	public double getCustomerCreditLimit();

	/**
	 * 可设置客户账期上限
	 * @return the customerCreditDayLimit
	 */
	public int getCustomerCreditDayLimit();

	/**
	 * 可设置总信用额度
	 * @return the availableTotalCreditLimit
	 */
	public double getAvailableTotalCreditLimit();

	/**
	 * 已设置客户
	 * @return the customerCountUsed
	 */
	public int getCustomerCountUsed();

	/**
	 * 已设置客户信用额度
	 * @return the customerCreditUsed
	 */
	public double getCustomerCreditUsed();

	/**
	 * 订单审批上限
	 * @return the orderApprovalLimit
	 */
	public double getOrderApprovalLimit();
	
	public GUID getId();

}
