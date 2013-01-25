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
 * <p>订单详情</p>
 *


 *
 
 * @version 2012-3-1
 */
public class OrderInfoImpl implements com.spark.psi.publish.order.entity.OrderInfo{
	
	
	@StructField
	protected GUID id;// GUID
	@StructField
	protected String orderNumber;// 单据编号
	@StructField
	protected PartnerInfo partnerInfo; //客户供应商对象
	@StructField
	protected String linkman;  //联系人
	@StructField
	protected String consignee;  //收货人信息
	@StructField
	protected OrderType orderType;// 类型
	@StructField
	protected EmployeeInfo creator;// 制单人
	@StructField
	protected long createDate;// 制单日期
	@StructField
	protected OrderStatus orderstatus;// 处理情况
	@StructField
	protected long deliveryDate;// 交货日期 
	@StructField
	protected double amount;	 //订单金额	
	@StructField
	protected String approverLabel;// 审核人及日期
	@StructField
	protected String creatorLabel; //制单人及日期
	@StructField
	protected String denyCause;// 退回原因
	@StructField
	protected String stopCause;// 中止原因
	@StructField
	protected String memo;// 备注
	@StructField
	protected GUID deptId; // 部门GUID
	@StructField
	protected boolean isStoped = false; // 是否已中止
	protected OrderAction[] actions;//操作权限
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
