/**
 * 
 */
package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * @author 97 入库单创建事件
 *
 */
public class CheckingInEvent  extends Event{

	private GUID checkInSheetId;

	public void setCheckInSheetId(GUID checkInSheetId) {
		this.checkInSheetId = checkInSheetId;
	}

	public GUID getCheckInSheetId() {
		return checkInSheetId;
	}
}
