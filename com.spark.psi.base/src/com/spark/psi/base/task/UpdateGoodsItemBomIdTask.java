package com.spark.psi.base.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class UpdateGoodsItemBomIdTask extends SimpleTask {

	private GUID goodsItemId,bomId;
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
	public GUID getBomId() {
		return bomId;
	}
	/**
	 * 
	 * @param goodsItemId 商品条目ID
	 * @param bomId bom表Id
	 */
	public UpdateGoodsItemBomIdTask(GUID goodsItemId,GUID bomId) {
		this.bomId = bomId;
		this.goodsItemId = goodsItemId;
	}

}
