package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.lightweight.LWTree;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.spark.common.components.pages.AbstractBoxPageRender;

public class DepartmentConfigRender extends AbstractBoxPageRender {

	@Override
	protected void beforeFooterRender() {
		LWTree tree = new LWTree(contentArea,JWT.NONE);
		tree.setID(DepartmentConfigProcessor.ID_TREE);
		tree.setLayoutData(GridData.INS_FILL_BOTH);
	}

	@Override
	protected void afterFooterRender() {
	}
}
