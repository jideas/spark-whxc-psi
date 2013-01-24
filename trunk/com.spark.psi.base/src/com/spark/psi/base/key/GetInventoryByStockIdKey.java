package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 
 * <p>获得商品库存情况</p>
 *
 */
public class GetInventoryByStockIdKey {

	private GUID stockId;
	private InventoryType inventoryType;
	
	/**
	 * 查询指定商品的总库存情况
	 * @param stockId  存货id
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
