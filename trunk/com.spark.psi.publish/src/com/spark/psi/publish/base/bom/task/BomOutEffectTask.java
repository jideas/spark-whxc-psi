package com.spark.psi.publish.base.bom.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

public class BomOutEffectTask extends SimpleTask {

	private GUID bomId;
	private GUID goodsItemId;

	public BomOutEffectTask(GUID bomId, GUID goodsItemId) {
		this.bomId = bomId;
		this.goodsItemId = goodsItemId;
	}

	public GUID getBomId() {
		return bomId;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

}
