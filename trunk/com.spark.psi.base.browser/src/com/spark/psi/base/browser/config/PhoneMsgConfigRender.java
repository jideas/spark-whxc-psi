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
 * 审核配置界面视图
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
		new SAsteriskLabel(composite, "启用短信功能：").setLayoutData(gdLabel);
		new Button(composite, JWT.CHECK).setID(PhoneMsgConfigProcessor.ID_BUTTON_Active);

		new SAsteriskLabel(composite, "HTTP余额请求路径：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_BalanceUrl);

		new SAsteriskLabel(composite, "HTTP提交请求路径：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_SubmitUrl);

		new SAsteriskLabel(composite, "企业账号字段名：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_CompAccountKey);

		new SAsteriskLabel(composite, "用户名称字段名：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_UserNameKey);

		new SAsteriskLabel(composite, "登陆密码字段名：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_PasswordKey);

		new SAsteriskLabel(composite, "手机号码字段名：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_PhoneNumberKey);

		new SAsteriskLabel(composite, "短信内容字段名：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_MsgContentKey);

		new SAsteriskLabel(composite, "企业账号：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_CompAccount);

		new SAsteriskLabel(composite, "用户名：").setLayoutData(gdLabel);
		new Text(composite, JWT.APPEARANCE3).setID(PhoneMsgConfigProcessor.ID_TEXT_UserName);

		new SAsteriskLabel(composite, "密码：").setLayoutData(gdLabel);
		Text text = new Text(composite, JWT.PASSWORD);
		text.setID(PhoneMsgConfigProcessor.ID_TEXT_Password);

		new SAsteriskLabel(composite, "重复密码：").setLayoutData(gdLabel);
		text = new Text(composite, JWT.PASSWORD);
		text.setID(PhoneMsgConfigProcessor.ID_TEXT_Password1);

		new SAsteriskLabel(composite, "数据库秘钥：").setLayoutData(gdLabel);
		text = new Text(composite, JWT.PASSWORD);
		text.setID(PhoneMsgConfigProcessor.ID_TEXT_Secretkey);
		text.setMaximumLength(16);
		new SAsteriskLabel(composite, "重复秘钥：").setLayoutData(gdLabel);
		new Text(composite, JWT.PASSWORD).setID(PhoneMsgConfigProcessor.ID_TEXT_Secretkey1);

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 查看余额 ");
		button.setID(PhoneMsgConfigProcessor.ID_BUTTON_TESTBUTTON);

		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("   保存   ");
		button.setID(PhoneMsgConfigProcessor.ID_BUTTON_SAVEBUTTON);
	}
}