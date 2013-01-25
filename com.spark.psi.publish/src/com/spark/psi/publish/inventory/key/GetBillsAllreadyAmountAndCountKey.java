package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

public class GetBillsAllreadyAmountAndCountKey {

	private GUID billsId;
	private boolean checkIn;

	private GetBillsAllreadyAmountAndCountKey() {
	}

	public static GetBillsAllreadyAmountAndCountKey getCheckInKey(GUID billsId) {
		GetBillsAllreadyAmountAndCountKey key = new GetBillsAllreadyAmountAndCountKey();
		key.billsId = billsId;
		key.checkIn = true;
		return key;
	}

	public static GetBillsAllreadyAmountAndCountKey getCheckOutKey(GUID billsId) {
		GetBillsAllreadyAmountAndCountKey key = new GetBillsAllreadyAmountAndCountKey();
		key.billsId = billsId;
		key.checkIn = false;
		return key;
	}

	public GUID getBillsId() {
		return billsId;
	}

	public boolean isCheckIn() {
		return checkIn;
	}
}
