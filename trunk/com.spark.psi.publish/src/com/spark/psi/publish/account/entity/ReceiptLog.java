package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ReceiptLog {

	
	public GUID getId();
	public GUID getReceiptsId();
	public String getReceiptNo();
	public GUID getCheckoutSheetId();
	public String getSheetNo();
	public GUID getRelevantBillId();
	public String getRelevantBillNo();
	public long getCheckinDate();
	public double getAmount();
	public double getMolingAmount();
	public GUID getReceiptPersonId();
	public String getReceiptPersonName();
	public long getReceiptDate();
}
