package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;

/**
 * 客户供应商已完成交易列表视图
 */
public abstract class PartnerProcessedOrderListRender extends
		PartnerOrderListRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new LWComboList(headerLeftArea, JWT.APPEARANCE3)
				.setID(PartnerProcessedOrderListProcessor.ID_List_QueryTerm);
	}

	@Override
	protected void beforeTableRender() {
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 24;

		Composite infoArea = new Composite(contentArea);
		infoArea.setLayoutData(gd);
		infoArea.setLayout(new GridLayout(3));

		new Label(infoArea)
				.setID(PartnerProcessedOrderListProcessor.ID_Label_Name);
		new Label(infoArea).setText(" ");
		new Label(infoArea)
				.setID(PartnerProcessedOrderListProcessor.ID_Label_Info);

		//
		super.beforeTableRender();
	}

}
