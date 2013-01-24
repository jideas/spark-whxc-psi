package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.partner.PartnerInfoProcessor;

/**
 * 客户信用额度调整界面视图
 */
public class CustomerCreditAdjustRender extends BaseFormPageRender {

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout(3);
		gl.marginLeft = gl.marginTop = 5;
		gl.verticalSpacing = 6;
		formArea.setLayout(gl);

		Label label = new Label(formArea);
		label.setText("信用额度：");
		SNumberText numberText = new SNumberText(formArea, 2);
		numberText.setID(PartnerInfoProcessor.ID_Text_CreditAmount);
//		numberText.setLayoutData(gd);
		numberText.setMaximumLength(19);
		numberText.setValue(0);

//		Text text = new Text(formArea, JWT.APPEARANCE3);
//		text.setID(CustomerCreditAdjustProcessor.ID_Text_CreditAmount);
//		text.setRegExp(Reg.NUM_EIGHT_TWO);
		
		label = new Label(formArea);
		label.setText("元");

		label = new Label(formArea);
		label.setText("账期天数：");

		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(CustomerCreditAdjustProcessor.ID_Text_CreditDa);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setMaximumLength(5);
		label = new Label(formArea);
		label.setText("天");

		new SSeparator(formArea, JWT.HORIZONTAL, 3);

		label = new Label(formArea);
		label.setText("账期提前");

		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(CustomerCreditAdjustProcessor.ID_Text_RemindDay);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setMaximumLength(5);
		label = new Label(formArea);
		label.setText("天 提醒");

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(CustomerCreditAdjustProcessor.ID_Button_Save);
		saveButton.setText(" 确定调整 ");
		Button cancelButton = new Button(buttonArea, JWT.APPEARANCE3);
		cancelButton.setID(CustomerCreditAdjustProcessor.ID_Button_Cancel);
		cancelButton.setText(" 取消调整 ");
	}

}
