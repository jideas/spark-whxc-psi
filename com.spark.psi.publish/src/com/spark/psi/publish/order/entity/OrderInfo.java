package com.spark.psi.publish.order.entity;

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
public interface OrderInfo {
	
	/**
	 * @return GUID
	 */
	public GUID getId();
	/**
	 * 
	 * @return String
	 */
	public String getOrderNumber();
	/**
	 * 
	 * @return PartnerInfo
	 */
	public PartnerInfo getPartnerInfo();
	/**
	 * 
	 * @return ContactBookInfo
	 */
	public String getConsignee() ;
	/**
	 * 
	 * @return ContactBookInfo
	 */
	public String getLinkman();
	/**
	 * 
	 * @return OrderType
	 */
	public OrderType getOrderType();
	/**
	 * 
	 * @return EmployeeInfo
	 */
	public EmployeeInfo getCreator();
	/**
	 * 
	 * @return long
	 */
	public long getCreateDate();
	/**
	 * 
	 * @return OrderStatus
	 */
	public OrderStatus getOrderStatus();
	/**
	 * 
	 * @return long
	 */
	public long getDeliveryDate();
	/**
	 * 
	 * @return double
	 */
	public double getAmount();
	/**
	 * 
	 * @return String
	 */
	public String getApproverLabel();
	/**
	 * 
	 * @return String
	 */
	public String getCreatorLabel();
	/**
	 * 
	 * @return String
	 */
	public String getDenyCause();
	/**
	 * 
	 * @return String
	 */
	public String getStopCause();
	/**
	 * 
	 * @return String
	 */
	public String getRemark();
	/**
	 * 
	 * @return GUID
	 */
	public GUID getDeptId();
	/**
	 * 
	 * @return boolean
	 */
	public boolean isStoped();
	
	
	/**
	 * 获得可执行的操作列表
	 * 
	 * @return OrderAction[]
	 */
	public OrderAction[] getActions();

	
}
