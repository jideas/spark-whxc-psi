package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>商品条目库存上限下修改事件（不一定发生了实质性变化）</p>
 *


 *
 
 * @version 2011-6-7
 */
public class MaterialsItemThresholdChangeEvent extends Event{

	private GUID goodsItemId;
	
	public MaterialsItemThresholdChangeEvent(GUID goodsItemId){
		this.goodsItemId = goodsItemId;
    }

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }
	
}
