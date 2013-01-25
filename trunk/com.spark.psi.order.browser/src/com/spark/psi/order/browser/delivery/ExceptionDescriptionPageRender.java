package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

public class ExceptionDescriptionPageRender extends BaseFormPageRender {

	private ExceptionDescriptionPageProcessor.Method method;
	@Override
	public void init(Situation context) {
		method = (ExceptionDescriptionPageProcessor.Method)getArgument();
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确定 ");
		button.setID(ExceptionDescriptionPageProcessor.ID_Button_Confrim);
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 2;
		formArea.setLayout(glForm);
		
		if (ExceptionDescriptionPageProcessor.Method.Exception == method) {
			new Label(formArea).setText("收货箱数：");
			Text text = new Text(formArea, JWT.APPEARANCE3);
			text.setID(ExceptionDescriptionPageProcessor.ID_Text_ReceivePackage);
//			GridData gdInput = new GridData();
//			gdInput.horizontalSpan = 3;
//			text.setLayoutData(gdInput);
			
			Label label = new Label(formArea);
			label.setText("异常描述：");
			GridData gdLabel = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING
					| GridData.HORIZONTAL_ALIGN_END);
			label.setLayoutData(gdLabel);
			text = new Text(formArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.V_SCROLL);
			text.setID(ExceptionDescriptionPageProcessor.ID_Text_Description);
			GridData gdInput1 = new GridData(GridData.FILL_BOTH);
//			gdInput1.horizontalSpan = 3;
			text.setLayoutData(gdInput1);
		} else {
			Label label = new Label(formArea);
			label.setText("处理方案：");
			GridData gdLabel = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING
					| GridData.HORIZONTAL_ALIGN_END);
			label.setLayoutData(gdLabel);
			Text text = new Text(formArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.V_SCROLL);
			text.setID(ExceptionDescriptionPageProcessor.ID_Text_Formula);
			GridData gdInput1 = new GridData(GridData.FILL_BOTH);
//			gdInput1.horizontalSpan = 3;
			text.setLayoutData(gdInput1);
		}
	}
	@Override
	protected boolean customizeFormLayout() {
		return true;
	}
	
}
