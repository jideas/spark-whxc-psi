package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ȡ�ֿ��ʼ������б�</p>
 *
 */

public class GetInitedInventoryEntityKey {

	private GUID storeId;

	public GetInitedInventoryEntityKey(GUID storeId)
	{
		this.storeId = storeId;
	}
	public GUID getStoreId() {
		return storeId;
	}
}
