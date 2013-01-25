package com.spark.psi.publish.base.config.task;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * 保存审批配置信息
 */
public class SaveApprovalConfigTask extends SimpleTask {


	/**
	 * 销售订单审核开启金额
	 */
	protected double salesApprovalLimit;
	
	/**
	 * 销售订单是否开启审核
	 */
	protected boolean salesOrderNeedApproval;

	/**
	 * 采购订单审核开启金额
	 */
	protected double purchaseApprovalLimit;
	
	/**
	 * 采购订单是否开启审核
	 */
	protected boolean purchaseOrderNeedApproval;

	/**
	 * 销售退货订单审核开启金额
	 */
	protected double salesReturnApprovalLimit;
	
	/**
	 * 销售退货是否开启审核
	 */
	protected boolean salesReturnNeedApproval;

	/**
	 * 采购退货订单审核开启金额
	 */
	protected double purchaseReturnApprovalLimit;

	/**
	 * 采购退货是否开启审核
	 */
	protected boolean purchaseReturnNeedApproval;

	/**
	 * 是否开启调拨审批
	 */
	private boolean allocateNeedApproval;

	/**
	 * @return the salesApprovalLimit
	 */
	public double getSalesApprovalLimit() {
		return salesApprovalLimit;
	}

	/**
	 * @param salesApprovalLimit
	 *            the salesApprovalLimit to set
	 */
	public void setSalesApprovalLimit(double salesApprovalLimit) {
		this.salesApprovalLimit = salesApprovalLimit;
	}

	/**
	 * @return the purchaseApprovalLimit
	 */
	public double getPurchaseApprovalLimit() {
		return purchaseApprovalLimit;
	}

	/**
	 * @param purchaseApprovalLimit
	 *            the purchaseApprovalLimit to set
	 */
	public void setPurchaseApprovalLimit(double purchaseApprovalLimit) {
		this.purchaseApprovalLimit = purchaseApprovalLimit;
	}

	/**
	 * @return the salesReturnApprovalLimit
	 */
	public double getSalesReturnApprovalLimit() {
		return salesReturnApprovalLimit;
	}

	/**
	 * @param salesReturnApprovalLimit
	 *            the salesReturnApprovalLimit to set
	 */
	public void setSalesReturnApprovalLimit(double salesReturnApprovalLimit) {
		this.salesReturnApprovalLimit = salesReturnApprovalLimit;
	}

	/**
	 * @return the purchaseReturnApprovalLimit
	 */
	public double getPurchaseReturnApprovalLimit() {
		return purchaseReturnApprovalLimit;
	}

	/**
	 * @param purchaseReturnApprovalLimit
	 *            the purchaseReturnApprovalLimit to set
	 */
	public void setPurchaseReturnApprovalLimit(
			double purchaseReturnApprovalLimit) {
		this.purchaseReturnApprovalLimit = purchaseReturnApprovalLimit;
	}

	/**
	 * @return the allocateNeedApproval
	 */
	public boolean isAllocateNeedApproval() {
		return allocateNeedApproval;
	}

	/**
	 * @param allocateNeedApproval
	 *            the allocateNeedApproval to set
	 */
	public void setAllocateNeedApproval(boolean allocateNeedApproval) {
		this.allocateNeedApproval = allocateNeedApproval;
	}

	public boolean isSalesOrderNeedApproval(){
    	return salesOrderNeedApproval;
    }

	public void setSalesOrderNeedApproval(boolean salesOrderNeedApproval){
    	this.salesOrderNeedApproval = salesOrderNeedApproval;
    }

	public boolean isPurchaseOrderNeedApproval(){
    	return purchaseOrderNeedApproval;
    }

	public void setPurchaseOrderNeedApproval(boolean purchaseOrderNeedApproval){
    	this.purchaseOrderNeedApproval = purchaseOrderNeedApproval;
    }

	public boolean isSalesReturnNeedApproval(){
    	return salesReturnNeedApproval;
    }

	public void setSalesReturnNeedApproval(boolean salesReturnNeedApproval){
    	this.salesReturnNeedApproval = salesReturnNeedApproval;
    }

	public boolean isPurchaseReturnNeedApproval(){
    	return purchaseReturnNeedApproval;
    }

	public void setPurchaseReturnNeedApproval(boolean purchaseReturnNeedApproval){
    	this.purchaseReturnNeedApproval = purchaseReturnNeedApproval;
    }

	
}
