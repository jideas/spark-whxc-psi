package com.spark.psi.order.browser.online;

import com.spark.psi.base.browser.PSIListPageProcessor;

public abstract class OnlinOrderListProcessor<Item> extends PSIListPageProcessor<Item> {

	@Override
	protected String getExportFileTitle() {
		return "Õ¯…œ∂©µ•";
	}

}
