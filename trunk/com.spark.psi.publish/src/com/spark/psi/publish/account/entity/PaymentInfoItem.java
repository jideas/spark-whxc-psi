package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

public interface PaymentInfoItem {

	public GUID getId();
	public GUID getPaymentId();
	public GUID getCheckinSheetId();
	public String getSheetNo();
	public GUID getRelevantBillId();
	public String getRelevantBillNo();
	public long getCheckinDate();
	public double getAmount();
	public double getAskAmount();
	public double getPaidAmount();
	public double getPayingAmount();
	public double getMolingAmount();
}
