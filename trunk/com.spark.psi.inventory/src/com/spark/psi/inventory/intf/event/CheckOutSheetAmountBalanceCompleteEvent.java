package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

/**
 * 
 * <p>
 * ���ⵥ���ּ�����¼�
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * @author ������
 * @version 2012-2-29
 */
public class CheckOutSheetAmountBalanceCompleteEvent extends Event {
	private GUID relaOrderId;// ��ض���id
	private CheckingOutType type;// ���ⵥ����

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
