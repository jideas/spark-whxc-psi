package com.spark.psi.report.eventlistener;

import com.jiuqi.dna.core.service.Service;

/**
 * 监听财务模块事件服务
 * 
 */
public class AccountListener extends Service {

	protected AccountListener() {
		super("AccountListener");
	}

	/**
	 * 往来变化时间（初始化、实付、调整往来、应付）
	 * 往来变化时间（初始化、实收、 调整往来、应收 ）
	 */
	// @Publish
	// class PP extends EventListener<DealingAmountChanageEvent> {
	//
	// @Override
	// protected void occur(Context context,
	// DealingAmountChanageEvent event) throws Throwable {
	// context.handle(new QueueAddTask(event));
	// }
	//
	// }
}
