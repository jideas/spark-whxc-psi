package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 仓库状态变化事件
 * @author Administrator
 */
public class StoreStatusChangeEvent extends Event {
	
	/**
	 * 仓库ID
	 * 
	 */
	public final GUID storeId;
	
	public StoreStatusChangeEvent(GUID storeId){
		this.storeId = storeId;
	}
	
}
