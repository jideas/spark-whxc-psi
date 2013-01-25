/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;

/**
 * ���ύ�����˻����б���ͼ
 * 
 */
public abstract class OnlineReturnSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
//		new SSearchText2(headerRightArea).setID(OnlineReturnSheetListProcessor.ID_TEXT_SEARCH);
//		new Label(headerLeftArea).setText("����������");
//		new Label(headerLeftArea).setID(OnlineReturnSheetListProcessor.ID_LABEL_COUNT);
	}

	public STableStyle getTableStyle() {
		STableStyle tabStyle = new STableStyle();
		tabStyle.setSelectionMode(getSelectionMode());
		return tabStyle;
	}

	public abstract SSelectionMode getSelectionMode();
}