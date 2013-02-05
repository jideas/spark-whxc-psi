package com.spark.psi.inventory.service.split;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.inventory.intf.event.CheckInEvent;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.entity.AllreadyAmountAndCount;
import com.spark.psi.publish.inventory.key.GetBillsAllreadyAmountAndCountKey;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;
import com.spark.psi.publish.split.task.UpdateGoodsSplitStatusTask;

public class CheckInEventListener extends Service {

	protected CheckInEventListener() {
		super("com.spark.psi.inventory.service.split.CheckInEventListener");
	}

	/**
	 * 确认入库事件
	 */
	@Publish
	class P extends EventListener<CheckInEvent> {
		@Override
		protected void occur(Context context, CheckInEvent event) throws Throwable {
			if (CheckingInType.GoodsSplit != event.getType()) {
				return;
			}
			AllreadyAmountAndCount data = context.find(AllreadyAmountAndCount.class, GetBillsAllreadyAmountAndCountKey
					.getCheckInKey(event.getRelaOrderId()));
			if (data.getAllreadyCount() == data.getBillCount()) {
				context.handle(new UpdateGoodsSplitStatusTask(GoodsSplitStatus.Finished));
			} else {
				context.handle(new UpdateGoodsSplitStatusTask(GoodsSplitStatus.Checkingin));
			}
		}
	}
}
