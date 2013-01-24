package com.spark.psi.base.browser.store;

import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.pages.SMessageAlertWindow;
import com.spark.portal.browser.MsgCancel;

public class NotFoundStoreAlterWindowProcessor extends PageProcessor {

	@Override
	public void process(Situation context) {
		new SMessageAlertWindow(true, getArgument().toString(), null);
		getContext().bubbleMessage(new MsgCancel());
	}

}
