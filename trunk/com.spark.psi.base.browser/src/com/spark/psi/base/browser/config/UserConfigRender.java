package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * ������Ϣ���ý�����ͼ
 * 
 */
public class UserConfigRender extends BaseFormPageRender {

	Label iconLabel;

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		// ===============================�޸����벿��===================================
		GridData layoutData_07 = new GridData();
		layoutData_07.horizontalSpan = 2;
		layoutData_07.grabExcessHorizontalSpace = true;
		layoutData_07.horizontalAlignment = JWT.FILL;
		layoutData_07.verticalAlignment = JWT.FILL;
		Composite modifyPasswordComposite = new Composite(formArea);
		modifyPasswordComposite.setLayout(new FillLayout());
		modifyPasswordComposite.setLayoutData(layoutData_07);
		modifyPasswordComposite
				.setID(UserConfigProcessor.ID_Composite_Password);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("    ȷ��    ");
		button.setID(UserConfigProcessor.ID_Button_Save);
		Button close = new Button(buttonArea, JWT.APPEARANCE3);
		close.setText("    �˳�    ");
		close.setID(UserConfigProcessor.ID_Button_Close);
	}
}