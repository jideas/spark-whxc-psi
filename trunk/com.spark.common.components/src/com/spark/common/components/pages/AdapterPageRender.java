package com.spark.common.components.pages;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Composite;

public class AdapterPageRender extends AbstractBoxPageRender {

	protected void beforeFooterRender() {
		Composite centerArea = new Composite(contentArea);
		centerArea.setLayoutData(GridData.INS_FILL_BOTH);
		Object arg1 = this.getArgument();
		Object arg2 = this.getArgument2();
		if (arg1 != null && arg1 instanceof String) {
			String pageName = (String) arg1;
			if (arg2 == null) {
				centerArea.showPage(pageName);
			} else {
				centerArea.showPage(pageName, arg2);
			}
		}
	}

	protected void afterFooterRender() {

	}

}
