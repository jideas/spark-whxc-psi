package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

public class ConfirmFinishRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		button.setID(ConfirmFinishProcessor.ID_Button_Confirm);

		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 取 消 ");
		button.setID(ConfirmFinishProcessor.ID_Button_Cancel);
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 3;
		formArea.setLayout(glForm);

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_BEGINNING);

		Label label = new Label(formArea);
		label.setText("验证码：");
		label.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(ConfirmFinishProcessor.ID_Text_Code);

		Button button = new Button(formArea, JWT.CHECK);
		button.setText("无验证码");
		button.setID(ConfirmFinishProcessor.ID_Check_NoCode);

		label = new Label(formArea);
		label.setText("原因：");
		label.setLayoutData(gdLabel);
		label.setID(ConfirmFinishProcessor.ID_Label_ReasonLabel);
		
		text = new Text(formArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.V_SCROLL);
		text.setID(ConfirmFinishProcessor.ID_Text_Reason);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = JWT.FILL;
		gd.heightHint = 60;
		text.setLayoutData(gd);
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

}
