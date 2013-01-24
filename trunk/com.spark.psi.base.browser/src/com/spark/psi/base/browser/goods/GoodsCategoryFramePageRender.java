package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.psi.base.browser.mdcommon.CategoryFramePageRender;

public class GoodsCategoryFramePageRender extends CategoryFramePageRender {

	/**
	 * �����Ƿ���ʾ�������ఴť
	 * 
	 * @param isShowAdd
	 */
	protected boolean isShowAdd() {
		return false;
	}
	
	protected boolean hideFooterArea() {
		return false;
	}

	@Override
	protected void rendHeader(Composite headerArea) {
		new GoodsCategoryBarPage(headerArea, isShowAdd());
	}

	@Override
	protected void rendLeft(Composite leftArea) {
		new GoodsCategoryListPage(leftArea, isShowAdd());
	}

}
