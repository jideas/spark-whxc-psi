package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ⵥ״̬�������ı��¼�</p>
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
