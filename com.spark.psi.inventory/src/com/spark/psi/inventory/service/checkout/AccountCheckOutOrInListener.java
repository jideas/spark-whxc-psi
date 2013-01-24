package com.spark.psi.inventory.service.checkout;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.account.intf.entity.dealing.DealingItem;
import com.spark.psi.account.intf.event.DealingAmountChanageEvent;
import com.spark.psi.inventory.internal.task.ChangeCheckinRealAmount;
import com.spark.psi.inventory.internal.task.ChangeCheckoutRealAmount;
import com.spark.psi.inventory.internal.task.CheckinAskAmountChangeTask;
import com.spark.psi.inventory.internal.task.TurnFlagOfCheckoutReceiptTask;
import com.spark.psi.publish.DealingsType;

public class AccountCheckOutOrInListener extends Service {

	protected AccountCheckOutOrInListener() {
		super("com.spark.psi.inventory.service.checkout.AccountCheckOutOrInListener");
	}

	@Publish
	protected class ReceiptListener extends EventListener<com.spark.psi.account.intf.event.DealingAmountChanageEvent> {

		@Override
		protected void occur(Context context, DealingAmountChanageEvent event) throws Throwable {
			DealingItem item = context.find(DealingItem.class, event.getDealingItemId());
			if (null == item) return ;
			if (item.getBillsType().equals(DealingsType.CUS_XSSK.getCode())) {
				context.handle(new ChangeCheckoutRealAmount(item.getBillsId(), item.getRealAmount()));
			} else if (item.getBillsType().equals(DealingsType.CUS_XSTK.getCode())) {
				context.handle(new ChangeCheckinRealAmount(item.getBillsId(), item.getRealAmount()));
			} else if (item.getBillsType().equals(DealingsType.PRO_CGFK.getCode())) {
				context.handle(new ChangeCheckinRealAmount(item.getBillsId(), item.getRealAmount()));
			} else if (item.getBillsType().equals(DealingsType.PRO_CGTK.getCode())) {
				context.handle(new ChangeCheckoutRealAmount(item.getBillsId(), item.getRealAmount()));
			}
		}
	}

	@Publish
	protected class ReceiptingListener extends EventListener<com.spark.psi.account.intf.event.ReceiptEffectiveEvent> {

		@Override
		protected void occur(Context context, com.spark.psi.account.intf.event.ReceiptEffectiveEvent event) throws Throwable {
			TurnFlagOfCheckoutReceiptTask task = new TurnFlagOfCheckoutReceiptTask(event.getBillsId());
			context.handle(task);
		}
	}

	@Publish
	protected class PayingListener extends EventListener<com.spark.psi.account.intf.event.PaymentEffectiveEvent> {

		@Override
		protected void occur(Context context, com.spark.psi.account.intf.event.PaymentEffectiveEvent event) throws Throwable {
			CheckinAskAmountChangeTask task = new CheckinAskAmountChangeTask();
			task.setAskAmount(event.getAmount());
			task.setId(event.getBillsId());
			context.handle(task);
		}
	}

}
