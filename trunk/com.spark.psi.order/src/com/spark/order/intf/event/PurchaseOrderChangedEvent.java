package com.spark.order.intf.event;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;

/**
 * <p>
 * 采购模块单据发生变化事件
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-8
 */
public class PurchaseOrderChangedEvent extends OrderChangedEvent {
	
	public PurchaseOrderChangedEvent(){
		super();
	}

	public PurchaseOrderChangedEvent(GUID id, OrderAction action) {
		super(id, action);
	}
	
	public PurchaseOrderChangedEvent(GUID id, ChangedType type) {
		super(id, type);
	}
}
