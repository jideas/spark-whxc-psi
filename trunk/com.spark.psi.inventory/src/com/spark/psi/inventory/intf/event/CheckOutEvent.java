/**
 * 
 */
package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

/**
 * @author 97 ȷ�ϳ���
 * 
 */
public class CheckOutEvent extends Event {

	public CheckOutEvent(GUID checkingOutId) {
		this.checkingOutId = checkingOutId;
	}

	private GUID checkingOutId;// �м��id��
	private GUID relaOrderId;// ��ض���id
	private CheckingOutType type;// ��������
	private GUID checkOutLogId;

	public void setRelaOrderId(GUID relaOrderId) {
		this.relaOrderId = relaOrderId;
	}

	public void setType(CheckingOutType type) {
		this.type = type;
	}

	public GUID getCheckingOutId() {
		return checkingOutId;
	}

	public void setCheckingOutId(GUID checkingOutId) {
		this.checkingOutId = checkingOutId;
	}

	/**
	 * @return the relaOrderId
	 */
	public GUID getRelaOrderId() {
		return relaOrderId;
	}

	/**
	 * @return the type
	 */
	public CheckingOutType getType() {
		return type;
	}

	public void setCheckOutLogId(GUID checkOutLogId) {
		this.checkOutLogId = checkOutLogId;
	}

	/**
	 * ��ȡȷ�ϳ����¼Id
	 * 
	 * @return GUID
	 */
	public GUID getCheckOutLogId() {
		return checkOutLogId;
	}
}
