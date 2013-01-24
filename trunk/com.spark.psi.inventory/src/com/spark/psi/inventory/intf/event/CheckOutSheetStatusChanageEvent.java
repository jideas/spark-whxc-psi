package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>出库单状态改变事件</p>
 *
 */
public class CheckOutSheetStatusChanageEvent extends Event{

	private GUID checkOutSheetId;

	public void setCheckOutSheetId(GUID checkOutSheetId) {
		this.checkOutSheetId = checkOutSheetId;
	}

	public GUID getCheckOutSheetId() {
		return checkOutSheetId;
	}
}
