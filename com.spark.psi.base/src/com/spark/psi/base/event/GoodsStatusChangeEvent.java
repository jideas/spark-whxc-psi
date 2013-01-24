package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 商品状态变化事件
 * @author Administrator
 */
public class GoodsStatusChangeEvent extends Event {
	
	public final GUID goodsItemId;//商品条码ID
	
	public GoodsStatusChangeEvent(GUID goodsItemId){
		this.goodsItemId = goodsItemId;
	}
	
}
