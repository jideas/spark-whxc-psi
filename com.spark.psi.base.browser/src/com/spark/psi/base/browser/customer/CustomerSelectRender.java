package com.spark.psi.base.browser.customer;

import com.spark.psi.base.browser.partner.PartnerSelectRender;

/**
 * 客户选择界面视图
 * 
 */
public class CustomerSelectRender extends PartnerSelectRender {

	@Override
	protected String getPartnerType() {
		return "客户";
	}

}
