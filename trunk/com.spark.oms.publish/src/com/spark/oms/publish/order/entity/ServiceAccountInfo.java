package com.spark.oms.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ServiceAccountInfo {
	
	/**
	 * 标识
	 */
	public GUID getRECID();
	
	/**
	 * 租户ID
	 */
	public GUID getTenantsRECID() ;
	
	/**
	 * 产品系统标识
	 */
	public String getProductSerials() ;
	
	/**
	 * 租赁账户余额
	 */
	public double getLeaseAccountBalance() ;
	
	/**
	 * 可支配的账户余额
	 */
	public double getLeaseAccountCanUseBalance();
	
	/**
	 * 应缴金额
	 */
	public double getPayAmount();

}
