/**
 * 
 */
package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

/**
 * @author 97 确认出库
 * 
 */
public class CheckOutEvent extends Event {

	public CheckOutEvent(GUID checkingOutId) {
		this.checkingOutId = checkingOutId;
	}

	private GUID checkingOutId;// 中间表id；
	private GUID relaOrderId;// 相关订单id
	private CheckingOutType type;// 单据类型
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
	 * 获取确认出库记录Id
	 * 
	 * @return GUID
	 */
	public GUID getCheckOutLogId() {
		return checkOutLogId;
	}
}
