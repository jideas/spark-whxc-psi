package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;

/**
 * 
 * <p>
 * ��ⵥ���ּ�����¼�
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * @author ������
 * @version 2012-2-29
 */
public class CheckInSheetAmountBalanceCompleteEvent extends Event {
	private GUID relaOrderId;// ��ض���id
	private CheckingInType type;// ��ⵥ����

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
