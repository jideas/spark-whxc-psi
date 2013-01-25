package com.spark.order.intf.event;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;

/**
 * <p>���������ı��¼�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
class OrderChangedEvent extends Event{
	protected GUID id;
	protected ChangedType type;
	final static Map<String, ChangedType> changedTypeMap = new HashMap<String, ChangedType>();
	
	
	/**
	 * 
	 * @param action
	 * @return ChangedType
	 */
	private ChangedType getType(OrderAction action) {
		switch (action) {
		case Submit:
			return ChangedType.Submit;
		case Approval:
			return ChangedType.Approval;
		case Deny:
			return ChangedType.Deny;
		case Stop:
			return ChangedType.Stop;
		case Execut:
			return ChangedType.Execut;
		case consignment:
			return ChangedType.consignment;
		}
		return null;
	}
	
	/**
	 * @param id
	 * @param type
	 */
	public OrderChangedEvent(GUID id, OrderAction action) {
		super();
		this.id = id;
		this.type = getType(action);
	}
	
	/**
	 * @param id
	 * @param type
	 */
	public OrderChangedEvent(GUID id, ChangedType type) {
		super();
		this.id = id;
		this.type = type;
	}

//	/**
//	 * @param id
//	 */
//	public OrderChangedEvent(GUID id) {
//		super();
//		this.id = id;
//	}


	public OrderChangedEvent(){ 
    }

	/**
	 * ����Id
	 * @return GUID
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * ����¼�����
	 * @return ChangedType
	 */
	public ChangedType getType() {
		return type;
	}
	
//	/**
//	 * �����¼�����
//	 * @param type void
//	 */
//	public void setType(ChangedType type) {
//		this.type = type;
//	}
//	
//	/**
//	 * �����¼�����
//	 * @param type void
//	 */
//	public void setType(OrderAction action) {
//		this.type = getType(action);
//	}
}
