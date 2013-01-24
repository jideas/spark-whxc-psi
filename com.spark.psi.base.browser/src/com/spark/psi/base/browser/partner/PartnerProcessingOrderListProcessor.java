package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.widgets.Label;

/**
 * �ͻ���Ӧ��δ��ɽ����б�����
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
