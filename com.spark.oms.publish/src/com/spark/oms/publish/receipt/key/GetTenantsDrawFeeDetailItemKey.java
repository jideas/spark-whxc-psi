package com.spark.oms.publish.receipt.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.DrawBillStatus;

/**
 * 开票记录表
 * @author mengyongfeng
 *
 */
public class GetTenantsDrawFeeDetailItemKey {
	/**
	 * 租户Id
	 */
	private GUID tenantsId;
	
	/**
	 * 开票状态
	 */
	private DrawBillStatus drawBillstatus;
	
	/**
	 * 开票标识
	 */
	private GUID drawBillId;
	
	public GUID getTenantsId() {
		return tenantsId;
	}

	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}

	public DrawBillStatus getDrawBillStatus() {
		return drawBillstatus;
	}

	public void setDrawBillStatus(DrawBillStatus drawBillstatus) {
		this.drawBillstatus = drawBillstatus;
	}

	public GUID getDrawBillId() {
		return drawBillId;
	}

	public void setDrawBillId(GUID drawBillId) {
		this.drawBillId = drawBillId;
	}
}
