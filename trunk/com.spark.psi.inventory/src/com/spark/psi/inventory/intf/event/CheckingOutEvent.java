/**
 * 
 */
package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * @author 97 �����м�����¼�
 *
 */
public class CheckingOutEvent extends Event {

	private GUID checkOutSheetId;

	public void setCheckOutSheetId(GUID checkOutSheetId) {
		this.checkOutSheetId = checkOutSheetId;
	}

	public GUID getCheckOutSheetId() {
		return checkOutSheetId;
	}
}
