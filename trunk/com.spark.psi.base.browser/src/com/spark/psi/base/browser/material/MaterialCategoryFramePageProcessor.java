package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.situation.Situation;
import com.spark.psi.base.browser.mdcommon.CategoryFramePageProcessor;

public class MaterialCategoryFramePageProcessor extends
		CategoryFramePageProcessor {

	@Override
	public void process(Situation context) {
		super.process(context);
		context.broadcastMessage(new MaterialCategorySelectionMsg(null));
	}
	
}
