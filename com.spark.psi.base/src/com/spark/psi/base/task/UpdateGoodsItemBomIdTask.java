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
	 * @param goodsItemId ��Ʒ��ĿID
	 * @param bomId bom��Id
	 */
	public UpdateGoodsItemBomIdTask(GUID goodsItemId,GUID bomId) {
		this.bomId = bomId;
		this.goodsItemId = goodsItemId;
	}

}
