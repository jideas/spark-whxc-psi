package com.spark.psi.publish.base.config.entity;

/**
 * ����������Ϣ<br>
 * ��ѯ������ֱ�Ӳ�ѯApprovalConfigInfo����
 */
public interface ApprovalConfigInfo {
	/**
	 * ��ȡ���۶�����˿������
	 * 
	 * @return the salesApprovalLimit
	 */
	public double getSalesApprovalLimit();

	/**
	 * ��ȡ�ɹ�������˿������
	 * 
	 * @return the purchaseApprovalLimit
	 */
	public double getPurchaseApprovalLimit();

	/**
	 * ��ȡ�����˻�������˿������
	 * 
	 * @return the salesReturnApprovalLimit
	 */
	public double getSalesReturnApprovalLimit();
	

	/**
	 * ��ȡ�ɹ��˻�������˿������
	 * 
	 * @return the purchaseReturnApprovalLimit
	 */
	public double getPurchaseReturnApprovalLimit();

	/**
	 * ��ȡ�Ƿ�������������
	 * 
	 * @return the allocateNeedApproval
	 */
	public boolean isAllocateNeedApproval();

	/**
	 * ��ȡ�Ƿ�ʼ���۶�����������
	 * 
	 * @return boolean
	 */
	public boolean isSalesOrderNeedApproval();
	/**
	 * ��ȡ�Ƿ����ɹ�������������
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseOrderNeedApproval();

	/**
	 * ��ȡ�����˻��Ƿ�����������
	 * 
	 * @return boolean
	 */
	public boolean isSalesReturnNeedApproval();
	
	/**
	 * ��ȡ�ɹ��˻��Ƿ�����������
	 * 
	 * @return boolean
	 */
	public boolean isPurchaseReturnNeedApproval();
	

}
