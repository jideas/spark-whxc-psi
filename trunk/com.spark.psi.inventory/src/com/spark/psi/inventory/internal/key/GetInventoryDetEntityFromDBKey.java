package com.spark.psi.inventory.internal.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>从数据库查询一条库存明细记录</p>
 *
 */

public class GetInventoryDetEntityFromDBKey {

	private GUID shelfId;//	货位标识
	private int tiersNo;//	存货所在层数
	private GUID stockId;//	存货标识
	private long produceDate;//	生产日期
	private GUID storeId;//	仓库id
	
	public GUID getShelfId() {
		return shelfId;
	}

	public int getTiersNo() {
		return tiersNo;
	}

	public GUID getStockId() {
		return stockId;
	}

	public long getProduceDate() {
		return produceDate;
	}

	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * 
	 * @param shelfId       货位标识      
	 * @param tiersNo       存货所在层数    
	 * @param stockId       存货标识      
	 * @param produceDate   生产日期  
	 * @param storeId       仓库id      
	 */
	public GetInventoryDetEntityFromDBKey(GUID shelfId, int tiersNo, GUID stockId, long produceDate, GUID storeId) {
		super();
		this.shelfId = shelfId;
		this.tiersNo = tiersNo;
		this.stockId = stockId;
		this.produceDate = produceDate;
		this.storeId = storeId;
	}
	
	

}
