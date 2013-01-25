package com.spark.order.intf.event;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;

/**
 * <p>����ģ�鵥�ݷ����仯�¼�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public class SalesOrderChangedEvent extends OrderChangedEvent{
	
	public SalesOrderChangedEvent(){}

	public SalesOrderChangedEvent(GUID id, OrderAction action) {
		super(id, action);
	}
	
	public SalesOrderChangedEvent(GUID id, ChangedType type) {
		super(id, type);
	}
}
