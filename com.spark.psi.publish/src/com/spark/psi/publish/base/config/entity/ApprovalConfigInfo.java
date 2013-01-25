package com.spark.psi.publish.base.config.entity;

/**
 * 审批配置信息<br>
 * 查询方法：直接查询ApprovalConfigInfo对象
 */
public interface ApprovalConfigInfo {
	/**
	 * 获取销售订单审核开启金额
	 * 
	 * @return the salesApprovalLimit
	 */
	public double getSalesApprovalLimit();

	/**
	 * 获取采购订单审核开启金额
	 * 
	 * @return the purchaseApprovalLimit
	 */
	public double getPurchaseApprovalLimit();

	/**
	 * 获取销售退货订单审核开启金额
	 * 
	 * @return the salesReturnApprovalLimit
	 */
	public double getSalesReturnApprovalLimit();
	

	/**
	 * 获取采购退货订单审核开启金额
	 * 
	 * @return the purchaseReturnApprovalLimit
	 */
	public double getPurchaseReturnApprovalLimit();

	/**
	 * 获取是否开启库存调拨审批
	 * 
	 * @return the allocateNeedApproval
	 */
	public boolean isAllocateNeedApproval();

	/**
	 * 获取是否开始销售订单审批流程
	 * 
	 * @return boolean
	 */
	public boolean isSalesOrderNeedApproval();
	/**
	 * 获取是否开启采购订单审批流程
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseOrderNeedApproval();

	/**
	 * 获取销售退货是否开启审批流程
	 * 
	 * @return boolean
	 */
	public boolean isSalesReturnNeedApproval();
	
	/**
	 * 获取采购退货是否开启审批流程
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseReturnNeedApproval();
	

}
