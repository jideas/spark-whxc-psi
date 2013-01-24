package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

public class PhoneEditRender extends BaseFormPageRender{

	@Override
	protected void renderButton(Composite buttonArea) {
		Button buttonSave = new Button(buttonArea, JWT.APPEARANCE3);
		buttonSave.setID(PhoneEditProcessor.ID_Button_Confirm_New);
		buttonSave.setText("    确定    ");
		buttonSave = new Button(buttonArea, JWT.APPEARANCE3);
		buttonSave.setID(PhoneEditProcessor.ID_Button_GiveUp_New);
		buttonSave.setText("    取消    ");
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		
		Composite cmp = new Composite(formArea);
		cmp.setLayout(new GridLayout(3));
		cmp.setLayoutData(GridData.INS_FILL_BOTH);
		
		//1r
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL|GridData.GRAB_HORIZONTAL);
		layoutData.horizontalSpan = 3;
		layoutData.horizontalAlignment = JWT.BEGINNING;
		layoutData.heightHint = 28;
		layoutData.verticalAlignment = JWT.CENTER;
		
		Label lblTip = new Label(cmp);
		lblTip.setText("说明：若30秒内没有收到验证码，请重新点击“获取验证码”！");
		lblTip.setForeground(Color.COLOR_RED);
		lblTip.setVisible(false);
		lblTip.setLayoutData(layoutData);
		lblTip.setID(PhoneEditProcessor.ID_Label_Tip);
		
		//2r
		GridData layoutData_02 = new GridData();
		layoutData_02.widthHint = 70;
		
		Label lblNewPhone = new Label(cmp,JWT.RIGHT);
		lblNewPhone.setText("新手机号码：");
		lblNewPhone.setLayoutData(layoutData_02);
		
		GridData layoutData_04 = new GridData();
		layoutData_04.widthHint = 180;
		Text txtNewPhone = new Text(cmp,JWT.APPEARANCE3);
		txtNewPhone.setMaximumLength(11);
		txtNewPhone.setLayoutData(layoutData_04);
		txtNewPhone.setID(PhoneEditProcessor.ID_Text_NewPhone);
		
		Button btnCheckCode = new Button(cmp,JWT.APPEARANCE2);
		btnCheckCode.setText(" 获取验证码 ");
		btnCheckCode.setID(PhoneEditProcessor.ID_Button_GetCheckCode);
		//3r
		
		GridData layoutData2 = new GridData();
		layoutData2.horizontalSpan = 3;
		layoutData2.horizontalAlignment = JWT.FILL;
		
		Composite checkCodeComposite = new Composite(cmp);
		checkCodeComposite.setLayout(new GridLayout(3));
		checkCodeComposite.setLayoutData(layoutData2);
		
		Label lblCheckCode = new Label(checkCodeComposite);
		lblCheckCode.setText("    验证码：");
		lblCheckCode.setLayoutData(layoutData_02);
		
		GridData layoutData_03 = new GridData();
		layoutData_03.widthHint = 80;
		Text txtCheckCode = new Text(checkCodeComposite,JWT.APPEARANCE3);
		txtCheckCode.setLayoutData(layoutData_03);
		txtCheckCode.setID(PhoneEditProcessor.ID_Text_CheckCode);
		
		Label lblTipCheckCode = new Label(checkCodeComposite);
		lblTipCheckCode.setText("(?秒后)重新获取验证码");
		lblTipCheckCode.setVisible(false);
		lblTipCheckCode.setID(PhoneEditProcessor.ID_Label_TipCheckCode);
		lblTipCheckCode.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

}
