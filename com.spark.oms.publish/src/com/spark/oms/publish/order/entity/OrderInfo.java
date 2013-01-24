package com.spark.oms.publish.order.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

public interface OrderInfo {
	/**
	 * 订单Id
	 * @return
	 */
	public GUID getRECID() ;
	
	/**
	 * 租户RECID
	 * @return
	 */
	public GUID getTenantsRECID() ;
	
	/**
	 * 租户姓名
	 * @return
	 */
	public String getTenantsName() ;
	
	/**
	 * 销售RECID
	 * @return
	 */
	public GUID getSaleRECID() ;
	
	/**
	 * 销售姓名
	 * @return
	 */
	public String getSaleName() ;
	
	/**
	 * 订单金额
	 * @return
	 */
	public double getOrderAmount() ;
	
	/**
	 * 应收金额
	 * @return
	 */
	public double getDueAmount() ;
	
	/**
	 * 实收金额
	 * @return
	 */
	public double getDoneAmount();
	
	/**
	 * 创建时间
	 * @return
	 */
	public long getCreateTime() ;
	
	/**
	 * 订单序号
	 * @return
	 */
	public String getOrderNo() ;
	
	/**
	 * 订单序号
	 * @return
	 */
	public String getOrderType() ;
	
	/**
	 * 修改人RECID
	 * @return
	 */
	public GUID getChangerRECID();
	
	/**
	 * 修改人姓名
	 * @return
	 */
	public String getChanger();
	
	/**
	 * 修改时间
	 * @return
	 */
	public long getChangeTime();
	
	/**
	 * 数据是否进行了修改
	 * @return
	 */
	public String getIsChange();

	/**
	 * 订单交款
	 */
	public List<OrderServiceReceiptInfo> getOrderServiceReceiptInfoList() ;
}
