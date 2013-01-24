package com.spark.psi.base.internal.entity;

import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;

public class ApprovalConfigImpl implements ApprovalConfig,ApprovalConfigInfo{

	/**
	 * ���۶�����˿������
	 */
	protected double salesApprovalLimit;
	
	/**
	 * ���۶����Ƿ������
	 */
	protected boolean salesOrderNeedApproval;

	/**
	 * �ɹ�������˿������
	 */
	protected double purchaseApprovalLimit;
	
	/**
	 * �ɹ������Ƿ������
	 */
	protected boolean purchaseOrderNeedApproval;

	/**
	 * �����˻�������˿������
	 */
	protected double salesReturnApprovalLimit;
	
	/**
	 * �����˻��Ƿ������
	 */
	protected boolean salesReturnNeedApproval;

	/**
	 * �ɹ��˻�������˿������
	 */
	protected double purchaseReturnApprovalLimit;

	/**
	 * �ɹ��˻��Ƿ������
	 */
	protected boolean purchaseReturnNeedApproval;
	
	/**
	 * �Ƿ�����������
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
