package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取租户满足条件的服务列表
 * 
 *
 */
public class GetOrderServiceItemByTenantKey {

	/**
	 * 租户id
	 */
	private GUID tenantId;
	
	/**
	 * 运行状态
	 */
	private int runstatus;
	
	/**
	 * 结束类型
	 */
	private String endtype;
	
	/**
	 * 订单状态
	 */
	private String serverstatus;
	

	public GUID getTenantId() {
		return tenantId;
	}

	public int getRunstatus() {
		return runstatus;
	}

	public String getEndtype() {
		return endtype;
	}

	public String getServerstatus() {
		return serverstatus;
	}

	public GetOrderServiceItemByTenantKey(GUID tenantId, int runstatus,
			String endtype, String serverstatus) {
		super();
		this.tenantId = tenantId;
		this.runstatus = runstatus;
		this.endtype = endtype;
		this.serverstatus = serverstatus;
	}
}