package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * ������ý�����ͼ
 */
public class PhoneMsgConfigRender extends BaseFormPageRender {

	Composite compositeTxt;

	@Override
	protected void renderFormArea(Composite formArea) {
		Composite composite = new Composite(formArea);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		new SAsteriskLabel(composite, "���ö��Ź��ܣ�").setLayoutData(gdLabel);
		new Button(composite, JWT.CHECK).setID(PhoneMsgConfigProcessor.ID_BUTTON_Active);

		new SAsteriskLabel(composite, "HTTP�������·����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_BalanceUrl);

		new SAsteriskLabel(composite, "HTTP�ύ����·����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_SubmitUrl);

		new SAsteriskLabel(composite, "��ҵ�˺��ֶ�����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_CompAccountKey);

		new SAsteriskLabel(composite, "�û������ֶ�����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_UserNameKey);

		new SAsteriskLabel(composite, "��½�����ֶ�����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_PasswordKey);

		new SAsteriskLabel(composite, "�ֻ������ֶ�����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_PhoneNumberKey);

		new SAsteriskLabel(composite, "���������ֶ�����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_MsgContentKey);

		new SAsteriskLabel(composite, "��ҵ�˺ţ�").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_CompAccount);

		new SAsteriskLabel(composite, "�û�����").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_UserName);

		new SAsteriskLabel(composite, "���룺").setLayoutData(gdLabel);
		Text text = new Text(composite, JWT.PASSWORD);
		text.setID(PhoneMsgConfigProcessor.ID_TEXT_Password);

		new SAsteriskLabel(composite, "�ظ����룺").setLayoutData(gdLabel);
		text = new Text(composite, JWT.PASSWORD);
		text.setID(PhoneMsgConfigProcessor.ID_TEXT_Password1);

		new SAsteriskLabel(composite, "���ݿ���Կ��").setLayoutData(gdLabel);
		text = new Text(composite, JWT.PASSWORD);
		text.setID(PhoneMsgConfigProcessor.ID_TEXT_Secretkey);
		text.setMaximumLength(16);
		new SAsteriskLabel(composite, "�ظ���Կ��").setLayoutData(gdLabel);
		new Text(composite, JWT.PASSWORD).setID(PhoneMsgConfigProcessor.ID_TEXT_Secretkey1);

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" �鿴��� ");
		button.setID(PhoneMsgConfigProcessor.ID_BUTTON_TESTBUTTON);

		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("   ����   ");
		button.setID(PhoneMsgConfigProcessor.ID_BUTTON_SAVEBUTTON);
	}
}