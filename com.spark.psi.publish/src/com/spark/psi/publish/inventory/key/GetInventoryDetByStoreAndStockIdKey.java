package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
/**
 * 
 * ���ݲֿ�ID+���ID��ѯ�����ϸ
 *
 */
public class GetInventoryDetByStoreAndStockIdKey {

	private GUID stockId;
	private GUID storeId;
	public GUID getStoreId() {
		return storeId;
	}

	private InventoryType inventoryType;

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public GUID getStockId() {
		return stockId;
	}

	/**
	 * @param storeId �ֿ�ID
	 * @param stockId ���ID
	 * @param inventoryType �������
	 */
	public GetInventoryDetByStoreAndStockIdKey(GUID storeId,GUID stockId,InventoryType inventoryType) {
		super();
		this.storeId = storeId;
		this.stockId = stockId;
		this.inventoryType = inventoryType;
	}
	
}
