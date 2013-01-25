package com.spark.b2c.publish.JointVenture.entity;

import com.jiuqi.dna.core.type.GUID;

public interface JointSettlementLog {

	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
	public GUID getId();
	public GUID getSheetId();
	public GUID getPaymentId();
	public String getPaymentNo();
	public double getAmount();
	public double getPaidAmount();
	public double getMolingAmount();
}
