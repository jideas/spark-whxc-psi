package com.spark.oms.publish.receipt.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.DrawBillStatus;

/**
 * ��Ʊ��¼��
 * @author mengyongfeng
 *
 */
public class GetTenantsDrawFeeDetailItemKey {
	/**
	 * �⻧Id
	 */
	private GUID tenantsId;
	
	/**
	 * ��Ʊ״̬
	 */
	private DrawBillStatus drawBillstatus;
	
	/**
	 * ��Ʊ��ʶ
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
