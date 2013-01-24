package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.partner.PartnerOrderListRender;

/**
 * 客户供应商未完成交易列表视图
 */
public abstract class PartnerProcessingOrderListRender extends
		PartnerOrderListRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea)
				.setID(PartnerProcessingOrderListProcessor.ID_LABEL_NAME);

		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setID(PartnerProcessingOrderListProcessor.ID_BUTTON_NEW);

		new Label(footerLeftArea).setText(" ");

		button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setID(PartnerProcessingOrderListProcessor.ID_BUTTON_NEWRETURN);
	}

}
