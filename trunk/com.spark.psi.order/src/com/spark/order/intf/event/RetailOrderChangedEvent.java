package com.spark.order.intf.event;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>���۵��仯�¼�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-16
 */
public class RetailOrderChangedEvent extends OrderChangedEvent{
	public RetailOrderChangedEvent(){}

	public RetailOrderChangedEvent(GUID id, ChangedType type) {
		super(id, type);
	}

}
