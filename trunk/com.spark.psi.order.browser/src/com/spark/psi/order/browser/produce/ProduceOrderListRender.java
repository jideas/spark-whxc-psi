package com.spark.psi.order.browser.produce;

import com.spark.common.components.controls.text.SSearchText2;
import com.spark.psi.base.browser.PSIListPageRender;

public abstract class ProduceOrderListRender extends PSIListPageRender {
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new SSearchText2(headerRightArea).setID(ProduceOrderListProcessor.ID_Search);
	} 

}
