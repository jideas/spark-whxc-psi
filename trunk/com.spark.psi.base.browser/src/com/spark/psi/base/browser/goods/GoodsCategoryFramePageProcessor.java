package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.situation.Situation;
import com.spark.psi.base.browser.mdcommon.CategoryFramePageProcessor;

public class GoodsCategoryFramePageProcessor extends CategoryFramePageProcessor {

	@Override
	public void process(Situation context) {
		super.process(context);
		context.broadcastMessage(new GoodsCategorySelectionMsg(null));
	}
}
