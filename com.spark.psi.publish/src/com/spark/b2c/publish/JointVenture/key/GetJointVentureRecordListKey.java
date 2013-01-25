package com.spark.b2c.publish.JointVenture.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;

public class GetJointVentureRecordListKey extends LimitKey {

	public GetJointVentureRecordListKey(int offset, int count, boolean queryTotal, GUID partnerId) {
		super(offset, count, queryTotal);
		this.partnerId = partnerId;
	}

	private GUID partnerId;

	private boolean neverSettlement = false;
	
	private long beginTime,endTime;

	public GUID getPartnerId() {
		return partnerId;
	}

	public boolean isNeverSettlement() {
		return neverSettlement;
	}

	public void setNeverSettlement(boolean neverSettlement) {
		this.neverSettlement = neverSettlement;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

}
