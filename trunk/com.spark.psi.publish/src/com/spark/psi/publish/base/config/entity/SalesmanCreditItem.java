package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 销售人员信用额度信息列表项<br>
 * 查询方法：
 * (1):context.find(ListEntity<SalesmanCreditItem>,GetSalesManCreditListKey)
 * (2):context.find(SalesmanCreditItem,empid);
 * 
 */
public interface SalesmanCreditItem {

	/**
	 * 销售人员id
	 * @return the salesmanId
	 */
	public GUID getSalesmanId();

	/**
	 * 销售人员姓名
	 * @return the salesmanName
	 */
	public String getSalesmanName();

	/**
	 * 销售人员部门
	 * @return the departmentInfo
	 */
	public String getDepartmentInfo();

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
