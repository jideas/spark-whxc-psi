package com.spark.oms.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.OrderServiceFeeStatus;
import com.spark.oms.publish.OrderServiceRunStatus;
/**
 * 产品列表（运营，历史，变更）
 */

public interface OrderItem {
	
	/**
	 * 订单序号
	 * @return
	 */
	public String getSerialNumber();
	
	/**
	 * 签订日期
	 * @return
	 */
	public long getCreateTime();
	
	//签订人
	public String getCreator();
	
	//租户名称
	public String getTenantsName();
	
	//订单金额
	public double getOrderAmount();
	
	//服务运行状态
	public OrderServiceRunStatus getRunstatus();
	
	//费用状况
	public OrderServiceFeeStatus getFeestatus();
	
	//变更后订单编号
	public String getOrderChangeNo();
	
	public String getReceiptStatus();
	
	//变更原因
	public String getChangeReason();
	
	//变更时间
	public long getChangeDate();
	
	//变更人
	public String getModifyer();
	
	/**
	 * 订单编号
	 * @return
	 */
	public GUID getOrderRECID();
	
	/**
	 * 租户编号
	 * @return
	 */
	public GUID getTenantsRECID();
	
	public String getOrderSource();
	
	public String getProductCategory();
	
	public GUID getBeforeOrderId();
	
	public String getBeforeSerialNumber();
	
	public GUID getSalerId();
	
}
