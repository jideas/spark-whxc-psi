package com.spark.psi.account.browser.joint;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.pages.BaseFormPageRender;

public class PayRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		button.setID(PayProcessor.ID_Button_Confirm);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 取 消 ");
		button.setID(PayProcessor.ID_Button_Cancel);
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 2;
		formArea.setLayout(glForm);
		
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER);
		
		Label label = new Label(formArea);
		label.setText("应付金额：");
		label.setLayoutData(gdLabel);
		label = new Label(formArea);
		label.setID(PayProcessor.ID_Label_Amount);
//		GridData gdOne = new GridData();
//		gdOne.horizontalSpan = 3; 
//		label.setLayoutData(gdOne);
		
		label = new Label(formArea);
		label.setText("已抹零金额：");
		label.setLayoutData(gdLabel);
		label = new Label(formArea);
		label.setID(PayProcessor.ID_Label_MolingAmount);
		
		label = new Label(formArea);
		label.setText("已付金额：");
		label.setLayoutData(gdLabel);
		label = new Label(formArea);
		label.setID(PayProcessor.ID_Label_PaidAmount);
		
		label = new Label(formArea);
		label.setText("本次抹零金额：");
		label.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(PayProcessor.ID_Text_CurrMolingAmount);
		text.setRegExp(TextRegexp.NUMBER_EIGHT_TWO);
		
		label = new Label(formArea);
		label.setText("本次付款金额：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(PayProcessor.ID_Text_PayAmount);
		text.setRegExp(TextRegexp.NUMBER_EIGHT_TWO);
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}
	
}
