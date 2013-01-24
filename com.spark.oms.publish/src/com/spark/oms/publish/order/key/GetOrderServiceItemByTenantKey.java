package com.spark.oms.publish.order.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ȡ�⻧���������ķ����б�
 * 
 *
 */
public class GetOrderServiceItemByTenantKey {

	/**
	 * �⻧id
	 */
	private GUID tenantId;
	
	/**
	 * ����״̬
	 */
	private int runstatus;
	
	/**
	 * ��������
	 */
	private String endtype;
	
	/**
	 * ����״̬
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