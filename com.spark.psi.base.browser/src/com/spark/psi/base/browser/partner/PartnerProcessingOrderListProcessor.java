package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.widgets.Label;

/**
 * 客户供应商未完成交易列表处理器
 * 
 */
public abstract class PartnerProcessingOrderListProcessor extends
		PartnerOrderListProcessor {

	final static String ID_LABEL_NAME = "Label_Name";
	public final static String ID_BUTTON_NEW = "Button_New";
	public final static String ID_BUTTON_NEWRETURN = "Button_NewReturn";

	@Override
	public void process(Situation situation) {
		super.process(situation);
		this.createControl(ID_LABEL_NAME, Label.class).setText(
				partnerInfo.getName());
	}

}
