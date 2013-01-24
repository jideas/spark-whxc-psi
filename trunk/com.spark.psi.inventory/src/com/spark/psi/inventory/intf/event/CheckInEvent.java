/**
 * 
 */
package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;

/**
 * @author 97 ȷ�����
 * 
 */
public class CheckInEvent extends Event {

	public CheckInEvent(GUID checkingInId) {
		this.checkingInId = checkingInId;
	}

	private GUID checkingInId;
	private GUID relaOrderId;// ��ض���id
	private CheckingInType type;// ��ⵥ����
	private GUID checkInLogId;

	public void setRelaOrderId(GUID relaOrderId) {
		this.relaOrderId = relaOrderId;
	}

	public void setType(CheckingInType type) {
		this.type = type;
	}

	/**
	 * @return the relaOrderId
	 */
	public GUID getRelaOrderId() {
		return relaOrderId;
	}

	public GUID getCheckingInId() {
		return checkingInId;
	}

	/**
	 * @return the type
	 */
	public CheckingInType getType() {
		return type;
	}

	public void setCheckInLogId(GUID checkInLogId) {
		this.checkInLogId = checkInLogId;
	}

	public GUID getCheckInLogId() {
		return checkInLogId;
	}
}
