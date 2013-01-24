package com.spark.psi.base.internal.entity;

import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;

public class ApprovalConfigImpl implements ApprovalConfig,ApprovalConfigInfo{

	/**
	 * ÏúÊÛ¶©µ¥ÉóºË¿ªÆô½ð¶î
	 */
	protected double salesApprovalLimit;
	
	/**
	 * ÏúÊÛ¶©µ¥ÊÇ·ñ¿ªÆôÉóºË
	 */
	protected boolean salesOrderNeedApproval;

	/**
	 * ²É¹º¶©µ¥ÉóºË¿ªÆô½ð¶î
	 */
	protected double purchaseApprovalLimit;
	
	/**
	 * ²É¹º¶©µ¥ÊÇ·ñ¿ªÆôÉóºË
	 */
	protected boolean purchaseOrderNeedApproval;

	/**
	 * ÏúÊÛÍË»õ¶©µ¥ÉóºË¿ªÆô½ð¶î
	 */
	protected double salesReturnApprovalLimit;
	
	/**
	 * ÏúÊÛÍË»õÊÇ·ñ¿ªÆôÉóºË
	 */
	protected boolean salesReturnNeedApproval;

	/**
	 * ²É¹ºÍË»õ¶©µ¥ÉóºË¿ªÆô½ð¶î
	 */
	protected double purchaseReturnApprovalLimit;

	/**
	 * ²É¹ºÍË»õÊÇ·ñ¿ªÆôÉóºË
	 */
	protected boolean purchaseReturnNeedApproval;
	
	/**
	 * ÊÇ·ñ¿ªÆôµ÷²¦ÉóÅú
	 */
	protected boolean allocateNeedApproval;

	public double getSalesApprovalLimit(){
    	return salesApprovalLimit;
    }

	public void setSalesApprovalLimit(double salesApprovalLimit){
    	this.salesApprovalLimit = salesApprovalLimit;
    }

	public boolean isSalesOrderNeedApproval(){
    	return salesOrderNeedApproval;
    }

	public void setSalesOrderNeedApproval(boolean salesOrderNeedApproval){
    	this.salesOrderNeedApproval = salesOrderNeedApproval;
    }

	public double getPurchaseApprovalLimit(){
    	return purchaseApprovalLimit;
    }

	public void setPurchaseApprovalLimit(double purchaseApprovalLimit){
    	this.purchaseApprovalLimit = purchaseApprovalLimit;
    }

	public boolean isPurchaseOrderNeedApproval(){
    	return purchaseOrderNeedApproval;
    }

	public void setPurchaseOrderNeedApproval(boolean purchaseOrderNeedApproval){
    	this.purchaseOrderNeedApproval = purchaseOrderNeedApproval;
    }

	public double getSalesReturnApprovalLimit(){
    	return salesReturnApprovalLimit;
    }

	public void setSalesReturnApprovalLimit(double salesReturnApprovalLimit){
    	this.salesReturnApprovalLimit = salesReturnApprovalLimit;
    }

	public boolean isSalesReturnNeedApproval(){
    	return salesReturnNeedApproval;
    }

	public void setSalesReturnNeedApproval(boolean salesReturnNeedApproval){
    	this.salesReturnNeedApproval = salesReturnNeedApproval;
    }

	public double getPurchaseReturnApprovalLimit(){
    	return purchaseReturnApprovalLimit;
    }

	public void setPurchaseReturnApprovalLimit(double purchaseReturnApprovalLimit){
    	this.purchaseReturnApprovalLimit = purchaseReturnApprovalLimit;
    }

	public boolean isPurchaseReturnNeedApproval(){
    	return purchaseReturnNeedApproval;
    }

	public void setPurchaseReturnNeedApproval(boolean purchaseReturnNeedApproval){
    	this.purchaseReturnNeedApproval = purchaseReturnNeedApproval;
    }

	public boolean isAllocateNeedApproval(){
    	return allocateNeedApproval;
    }

	public void setAllocateNeedApproval(boolean allocateNeedApproval){
    	this.allocateNeedApproval = allocateNeedApproval;
    }

	
}
