package com.spark.order.intf.event;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�����˻��仯�¼�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-16
 */
public class RetailReturnChangedEvent extends OrderChangedEvent{

	public RetailReturnChangedEvent(GUID id, ChangedType type) {
		super(id, type);
	}
}
