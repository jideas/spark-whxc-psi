package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ���ڲ�ѯָ����������ⵥ��Ϣ��key
 * 
 */
public class GetOrderCheckInSheetItemKey {

	/**
	 * ����id
	 */
	private GUID orderId;

	/**
	 * 
	 * @param orderId
	 */
	public GetOrderCheckInSheetItemKey(GUID orderId) {
		super();
		this.orderId = orderId;
	}

	/**
	 * @return the orderId
	 */
	public GUID getOrderId() {
		return orderId;
	}

}
