package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;
/**
 * 
 * ���ݴ��ID��ѯ�����ϸ
 *
 */
public class GetInventoryDetByStockIdKey {

	private GUID stockId;
	private InventoryType inventoryType;

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public GUID getStockId() {
		return stockId;
	}

	/**
	 * 
	 * @param stockId ���ID
	 * @param inventoryType �������
	 */
	public GetInventoryDetByStockIdKey(GUID stockId,InventoryType inventoryType) {
		super();
		this.stockId = stockId;
		this.inventoryType = inventoryType;
	}
	
}
