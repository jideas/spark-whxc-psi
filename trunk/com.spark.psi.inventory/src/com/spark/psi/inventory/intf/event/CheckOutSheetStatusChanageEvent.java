package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>���ⵥ״̬�ı��¼�</p>
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
