/**
 * 
 */
package com.spark.psi.inventory.intf.task.pub;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 加锁、解锁库存(零售,调拨)
 *
 */
public class LockInventoryTask extends SimpleTask {
	
	private GUID storeId,goodsId;
	/**
	 * 锁定数量，正值加锁，负值解锁
	 */
	private double count;
	/**
	 * 
	 * @param storeGuid 仓库ID
	 * @param goodsGuid 商品ID
	 */
	public LockInventoryTask(GUID storeId,GUID goodsId)
	{
		this.storeId = storeId;
		this.goodsId = goodsId;
	}
	
	public GUID getStoreId() {
		return storeId;
	}

	public GUID getGoodsId() {
		return goodsId;
	}

	public void setCount(double count) {
		this.count = count;
	}
	public double getCount() {
		return count;
	}
	
}
