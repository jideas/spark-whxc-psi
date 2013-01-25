package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

public interface PaymentLog {

	public GUID getId();
	public GUID getPaymentId();
	public String getPaymentNo();
	public GUID getCheckinSheetId();
	public String getSheetNo();
	public GUID getRelevantBillId();
	public String getRelevantBillNo();
	public long getCheckinDate();
	public double getAmount();
	public double getMolingAmount();
	public GUID getPayPersonId();
	public String getPayPersonName();
	public long getPayDate();
}
