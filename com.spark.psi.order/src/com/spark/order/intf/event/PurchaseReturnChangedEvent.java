package com.spark.order.intf.event;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;

/**
 * <p>
 * �ɹ�ģ�鵥�ݷ����仯�¼�
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-8
 */
public class PurchaseReturnChangedEvent extends OrderChangedEvent {
	
	public PurchaseReturnChangedEvent(){
		super();
	}

	public PurchaseReturnChangedEvent(GUID id, OrderAction action) {
		super(id, action);
	}
	
	public PurchaseReturnChangedEvent(GUID id, ChangedType type) {
		super(id, type);
	}
}
