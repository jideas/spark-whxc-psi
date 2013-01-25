package com.spark.psi.publish.account.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ReceiptingOrPayingItem {

	public GUID getSheetId();

	public String getSheetNo();

	public double getAmount();

	public double getAskedAmount();

	public GUID getRelaBillsId();

	public String getRelaBillsNo();

	public long getCheckInOrOutDate();
}
