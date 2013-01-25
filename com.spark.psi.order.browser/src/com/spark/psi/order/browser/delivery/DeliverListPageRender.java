package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.psi.base.browser.PSIListPageRender;

public abstract class DeliverListPageRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("单据数量：");
		new Label(headerLeftArea).setID(DeliverListPageProcessor.ID_Label_Count);
		
		new SSearchText2(headerRightArea).setID(DeliverListPageProcessor.ID_Search);
//		Button button = new Button(headerRightArea, JWT.APPEARANCE3);
//		button.setText(" 高级搜索 ");
//		button.setID(DeliverListPageProcessor.ID_Button_AdvanceSearch);
	}
}
