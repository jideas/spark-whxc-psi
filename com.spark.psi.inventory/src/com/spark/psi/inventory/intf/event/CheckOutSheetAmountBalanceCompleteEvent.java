package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

/**
 * 
 * <p>
 * 出库单金额抵减完成事件
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author 周利均
 * @version 2012-2-29
 */
public class CheckOutSheetAmountBalanceCompleteEvent extends Event {
	private GUID relaOrderId;// 相关订单id
	private CheckingOutType type;// 出库单类型

	public CheckOutSheetAmountBalanceCompleteEvent(GUID relaOrderId, CheckingOutType type) {
		this.relaOrderId = relaOrderId;
		this.type = type;
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
}
