package com.spark.order.intf.event;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PromotionAction;

/**
 * <p>促销单变化事件</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-16
 */
public class PromotionOrderChangedEvent extends OrderChangedEvent{

	public PromotionOrderChangedEvent(GUID id, ChangedType type) {
		super(id, type);
	}

	public PromotionOrderChangedEvent(GUID id, PromotionAction action) {
		super(id, getType(action));
	}

	private static ChangedType getType(PromotionAction action) {
		ChangedType type = null;
		switch (action) {
		case Submit:
			type = ChangedType.Submit;
			break;
		case Approval:
			type = ChangedType.Approval;
			break;
		case Delete:
			type = ChangedType.Delete;
			break;
		case Deny:
			type = ChangedType.Deny;
			break;
		case Stop:
			type = ChangedType.Stop;
			break;
		default:
			break;
		}
		return type;
	}
}
