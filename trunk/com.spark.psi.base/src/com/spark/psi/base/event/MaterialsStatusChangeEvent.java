package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 商品状态变化事件
 * @author Administrator
 */
public class MaterialsStatusChangeEvent extends Event {
	
	public final GUID materialItemId;//材料条码ID
	
	public MaterialsStatusChangeEvent(GUID materialItemId){
		this.materialItemId = materialItemId;
	}
	
}
