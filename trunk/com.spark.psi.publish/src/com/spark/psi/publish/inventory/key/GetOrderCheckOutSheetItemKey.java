package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ���ڲ�ѯָ�������ĳ��ⵥ��Ϣ��key
 * 
 */
public class GetOrderCheckOutSheetItemKey {

	/**
	 * ����id
	 */
	private GUID orderId;

	/**
	 * 
	 * @param orderId
	 */
	public GetOrderCheckOutSheetItemKey(GUID orderId) {
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
