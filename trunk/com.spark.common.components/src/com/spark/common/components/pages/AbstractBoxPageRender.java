package com.spark.common.components.pages;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.CommonImages;

public abstract class AbstractBoxPageRender extends PageRender {

	protected Composite footerArea;

	private final static GridData gdFooter;

	static {
		gdFooter = new GridData(GridData.FILL_HORIZONTAL);
		gdFooter.heightHint = 34;
	}

	@Override
	protected final void beforeRender() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
		contentArea.setLayout(gridLayout);
	}

	@Override
	protected final void doRender() {
		beforeFooterRender();
		if (!hideFooterArea()) {
			footerArea = new Composite(contentArea);
			footerArea.setBackimage(CommonImages
					.getImage("img/page/MTabsarea_bottom.png"));
			footerArea.setLayoutData(gdFooter);
		}
		afterFooterRender();
	}

	protected boolean hideFooterArea() {
		return false;
	}

	protected abstract void beforeFooterRender();

	protected abstract void afterFooterRender();

}
