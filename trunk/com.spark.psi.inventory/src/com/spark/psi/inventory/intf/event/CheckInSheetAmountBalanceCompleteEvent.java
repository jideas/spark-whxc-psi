package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;

/**
 * 
 * <p>
 * 入库单金额抵减完成事件
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author 周利均
 * @version 2012-2-29
 */
public class CheckInSheetAmountBalanceCompleteEvent extends Event {
	private GUID relaOrderId;// 相关订单id
	private CheckingInType type;// 入库单类型

	public CheckInSheetAmountBalanceCompleteEvent(GUID relaOrderId, CheckingInType type) {
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
	public CheckingInType getType() {
		return type;
	}

}
