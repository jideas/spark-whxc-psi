package com.spark.psi.base.browser.mdcommon;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.PageProcessor;

public class CategoryFramePageProcessor extends PageProcessor {
	
	public final static String ID_Area_Right = "Area_Right";
	
	protected Composite rightArea;
	
	@Override
	public void process(Situation context) {
		rightArea = this.createControl(ID_Area_Right, Composite.class);
	}

}
