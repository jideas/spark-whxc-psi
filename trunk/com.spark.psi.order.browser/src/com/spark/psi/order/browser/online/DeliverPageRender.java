package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.BaseFormPageRender;

public class DeliverPageRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确定生成 ");
		button.setID(DeliverPageProcessor.ID_Button_Confirm);
	}
	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(new GridLayout());
		formArea.setID(DeliverPageProcessor.ID_Form_Area);
		
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

}
