package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ReceiptInfoItem {

	public GUID getId();
	public GUID getReceiptsId();
	public GUID getCheckoutSheetId();
	public String getSheetNo();
	public GUID getRelevantBillId();
	public String getRelevantBillNo();
	public long getCheckoutDate();
	public double getAmount();
	public double getReceiptedAmount();
	public double getMolingAmount();
}
