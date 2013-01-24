package com.spark.psi.base.browser.material;

import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.psi.base.browser.mdcommon.CategoryFramePageRender;

public class MaterialCategoryFramePageRender extends CategoryFramePageRender {

	@Override
	protected void rendHeader(Composite headerArea) {
		new MaterialCategoryBarPage(headerArea, isShowAdd());
	}

	@Override
	protected void rendLeft(Composite leftArea) {
		new MaterialCategoryListPage(leftArea, isShowAdd());

	}
	
	/**
	 * �����Ƿ���ʾ�������ఴť
	 * 
	 * @param isShowAdd
	 */
	protected boolean isShowAdd() {
		return false;
	}

}
