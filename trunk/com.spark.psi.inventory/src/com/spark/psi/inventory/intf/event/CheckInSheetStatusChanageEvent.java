package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>入库单状态法发生改变事件</p>
 */
public class CheckInSheetStatusChanageEvent extends Event{

	private GUID checkInSheetId;

	public void setCheckInSheetId(GUID checkInSheetId) {
		this.checkInSheetId = checkInSheetId;
	}

	public GUID getCheckInSheetId() {
		return checkInSheetId;
	}
}
