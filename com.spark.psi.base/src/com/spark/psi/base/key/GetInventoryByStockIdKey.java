package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 
 * <p>�����Ʒ������</p>
 *
 */
public class GetInventoryByStockIdKey {

	private GUID stockId;
	private InventoryType inventoryType;
	
	/**
	 * ��ѯָ����Ʒ���ܿ�����
	 * @param stockId  ���id
	 */
	public GetInventoryByStockIdKey(GUID stockId,InventoryType inventoryType){
		this.stockId = stockId;
		this.inventoryType = inventoryType;
    }

	public GUID getStockId(){
    	return stockId;
    }

	public InventoryType getInventoryType() {
		return inventoryType;
	}
	

}
