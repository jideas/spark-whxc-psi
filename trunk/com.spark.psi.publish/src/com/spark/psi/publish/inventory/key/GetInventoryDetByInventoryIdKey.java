package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
/**
 * 
 * ���ݿ��ID��ѯ�����ϸ
 *
 */
public class GetInventoryDetByInventoryIdKey {

	private GUID inventoryId;

	public GUID getInventoryId() {
		return inventoryId;
	}

	/**
	 * 
	 * @param inventoryId ���ID
	 */
	public GetInventoryDetByInventoryIdKey(GUID inventoryId) {
		super();
		this.inventoryId = inventoryId;
	}
	
}
