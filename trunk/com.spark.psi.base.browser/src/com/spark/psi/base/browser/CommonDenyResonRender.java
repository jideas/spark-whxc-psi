package com.spark.psi.base.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

public class CommonDenyResonRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		button.setID(CommonDenyResonProcessor.ID_Button_Confirm);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 取 消 ");
		button.setID(CommonDenyResonProcessor.ID_Button_Cancel);
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 2;
		formArea.setLayout(glForm);
		
		Label label = new Label(formArea);
		label.setText("退回原因：");
		GridData gdLabel = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING);
		label.setLayoutData(gdLabel);
		label.setID(CommonDenyResonProcessor.ID_Label_ReasonLabel);
		
		Text text = new Text(formArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.V_SCROLL);
		text.setID(CommonDenyResonProcessor.ID_Text_ReasonText);
		text.setLayoutData(GridData.INS_FILL_BOTH);
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}
	
	

}
