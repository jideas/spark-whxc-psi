package com.spark.order.intf.event;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>零售单变化事件</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-16
 */
public class RetailOrderChangedEvent extends OrderChangedEvent{
	public RetailOrderChangedEvent(){}

	public RetailOrderChangedEvent(GUID id, ChangedType type) {
		super(id, type);
	}

}
