package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;

/**
 * 
 * <p>��������</p>
 *


 *
 
 * @version 2012-3-1
 */
public class OrderInfoImpl implements com.spark.psi.publish.order.entity.OrderInfo{
	
	
	@StructField
	protected GUID id;// GUID
	@StructField
	protected String orderNumber;// ���ݱ��
	@StructField
	protected PartnerInfo partnerInfo; //�ͻ���Ӧ�̶���
	@StructField
	protected String linkman;  //��ϵ��
	@StructField
	protected String consignee;  //�ջ�����Ϣ
	@StructField
	protected OrderType orderType;// ����
	@StructField
	protected EmployeeInfo creator;// �Ƶ���
	@StructField
	protected long createDate;// �Ƶ�����
	@StructField
	protected OrderStatus orderstatus;// �������
	@StructField
	protected long deliveryDate;// �������� 
	@StructField
	protected double amount;	 //�������	
	@StructField
	protected String approverLabel;// ����˼�����
	@StructField
	protected String creatorLabel; //�Ƶ��˼�����
	@StructField
	protected String denyCause;// �˻�ԭ��
	@StructField
	protected String stopCause;// ��ֹԭ��
	@StructField
	protected String memo;// ��ע
	@StructField
	protected GUID deptId; // ����GUID
	@StructField
	protected boolean isStoped = false; // �Ƿ�����ֹ
	protected OrderAction[] actions;//����Ȩ��
	public GUID getId(){
    	return id;
    }
	public String getOrderNumber(){
    	return orderNumber;
    }
	public PartnerInfo getPartnerInfo(){
    	return partnerInfo;
    } 
	public OrderType getOrderType(){
    	return orderType;
    }
	public EmployeeInfo getCreator(){
    	return creator;
    }
	public long getCreateDate(){
    	return createDate;
    }
	public OrderStatus getOrderStatus(){
    	return orderstatus;
    }
	public long getDeliveryDate(){
    	return deliveryDate;
    }
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public double getAmount(){
    	return amount;
    }
	public String getApproverLabel(){
    	return approverLabel;
    }
	public String getCreatorLabel(){
    	return creatorLabel;
    }
	public String getDenyCause(){
    	return denyCause;
    }
	public String getStopCause(){
    	return stopCause;
    }
	public String getRemark(){
    	return memo;
    }
	public GUID getDeptId(){
    	return deptId;
    }
	public boolean isStoped(){
    	return isStoped;
    }
	/**
	 * @param id the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @param partnerInfo the partnerInfo to set
	 */
	public void setPartnerInfo(PartnerInfo partnerInfo) {
		this.partnerInfo = partnerInfo;
	} 
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(EmployeeInfo creator) {
		this.creator = creator;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param orderstatus the orderstatus to set
	 */
	public void setOrderStatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @param approverLabel the approverLabel to set
	 */
	public void setApproverLabel(String approverLabel) {
		this.approverLabel = approverLabel;
	}
	/**
	 * @param creatorLabel the creatorLabel to set
	 */
	public void setCreatorLabel(String creatorLabel) {
		this.creatorLabel = creatorLabel;
	}
	/**
	 * @param denyCause the denyCause to set
	 */
	public void setDenyCause(String denyCause) {
		this.denyCause = denyCause;
	}
	/**
	 * @param stopCause the stopCause to set
	 */
	public void setStopCause(String stopCause) {
		this.stopCause = stopCause;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}
	/**
	 * @param isStoped the isStoped to set
	 */
	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}
	public OrderAction[] getActions() {
		return actions;
	}
	public void setActions(OrderAction[] actions) {
		this.actions = actions;
	}
	
}
