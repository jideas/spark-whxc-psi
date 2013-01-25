/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;

/**
 * 待提交销售退货单列表视图
 * 
 */
public abstract class OnlineReturnSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
//		new SSearchText2(headerRightArea).setID(OnlineReturnSheetListProcessor.ID_TEXT_SEARCH);
//		new Label(headerLeftArea).setText("单据数量：");
//		new Label(headerLeftArea).setID(OnlineReturnSheetListProcessor.ID_LABEL_COUNT);
	}

	public STableStyle getTableStyle() {
		STableStyle tabStyle = new STableStyle();
		tabStyle.setSelectionMode(getSelectionMode());
		return tabStyle;
	}

	public abstract SSelectionMode getSelectionMode();
}