package com.spark.order.util2;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.event.PromotionOrderChangedEvent;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.promotion.intf.PromotionTask2;
import com.spark.order.promotion.service.PromotionStatusTask;
import com.spark.order.util2.OrderUtilFactory2.OrderUtilFactoryException;
import com.spark.psi.publish.PromotionAction;

class OrderAction_Promotion2 extends OrderActionImpl2<PromotionAction, PromotionStatus2>{
	private String cause;
	private PromotionStatus2 newstatus;
	protected OrderAction_Promotion2(Context context) {
		super(context);
	}

	@Override
	protected boolean doAction(PromotionAction orderAction,
			boolean ignoredWarning)
			throws com.spark.order.util2.IOrderAction2.OrderActionLoseException {
		PromotionStatusTask task = null;
		boolean succeed = false;
		try {
			Promotion2 promotion = getEntity(Promotion2.class);
			PromotionStatus2 oldstatus = PromotionStatus2.getPromotionStatus2(promotion.getStatus());
			IProcessManage2<PromotionStatus2> process = OrderUtilFactory2.newProcessManage2(PromotionStatus2.class, getContext(), OrderEnum.Sales_Promotion);
			switch (orderAction) {
			case Submit:
//				newstatus = process.next(oldstatus);
//				task = new PromotionStatusTask(getOrderId(), promotion.getStatus(), newstatus);
//				break;
			case Approval:
				newstatus = process.next(oldstatus);
				task = new PromotionStatusTask(getOrderId(), promotion.getStatus(), newstatus);
				break;
			case Delete:
				PromotionTask2 del = new PromotionTask2();
				del.recid = getOrderId();
				getContext().handle(del, PromotionTask2.Method.DELETE);
				succeed = del.isSucceed();
				break;
			case Deny:
//				newstatus = process.prov(oldstatus);
//				task = new PromotionStatusTask(getOrderId(), promotion.getStatus(), newstatus, cause);
//				break;
			case Stop:
				newstatus = process.prov(oldstatus);
				task = new PromotionStatusTask(getOrderId(), promotion.getStatus(), newstatus, cause);
				break;
			default:
				throw new OrderActionLoseException("当前操作不存在，请扩展！");
			}
		} catch (OrderUtilFactoryException e) {
			throw new OrderActionLoseException(e.getMessage());
		}
		if(null != task){
			getContext().handle(task);
			succeed = task.isSucceed();
		}
		if(succeed){
			//发变法事件
			getContext().dispatch(new PromotionOrderChangedEvent(getOrderId(), orderAction));
		}
		return succeed;
	}

	public PromotionStatus2 getNewstatus() {
		return newstatus;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}
