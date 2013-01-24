package com.spark.psi.base.browser.config;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.publish.phonemessage.entity.MsgAccountBalance;
import com.spark.psi.publish.phonemessage.entity.PhoneMessageConfig;
import com.spark.psi.publish.phonemessage.key.GetMessageBalanceKey;
import com.spark.psi.publish.phonemessage.task.UpdatePhoneMsgConfigTask;

/**
 * ������ý��洦����
 */
public class PhoneMsgConfigProcessor extends BaseFormPageProcessor implements IDataProcessPrompt {

	public static String ID_BUTTON_SAVEBUTTON = "Button_SaveButton";
	public static String ID_BUTTON_TESTBUTTON = "BUTTON_TESTBUTTON";
	public static String ID_BUTTON_Active = "BUTTON_Active";
	public static String ID_TEXT_BalanceUrl = "ID_TEXT_BalanceUrl";
	public static String ID_TEXT_SubmitUrl = "ID_TEXT_SubmitUrl";
	public static String ID_TEXT_UserNameKey = "ID_TEXT_UserNameKey";
	public static String ID_TEXT_PasswordKey = "ID_TEXT_PasswordKey";
	public static String ID_TEXT_PhoneNumberKey = "ID_TEXT_PhoneNumberKey";
	public static String ID_TEXT_MsgContentKey = "ID_TEXT_MsgContentKey";
	public static String ID_TEXT_CompAccount = "ID_TEXT_CompAccount";
	public static String ID_TEXT_CompAccountKey = "ID_TEXT_CompAccountKey";
	public static String ID_TEXT_UserName = "ID_TEXT_UserName";
	public static String ID_TEXT_Password = "ID_TEXT_Password";
	public static String ID_TEXT_Password1 = "ID_TEXT_Password1";
	public static String ID_TEXT_Secretkey = "ID_TEXT_Secretkey";
	public static String ID_TEXT_Secretkey1 = "ID_TEXT_Secretkey1";

	private Text TEXT_BalanceUrl, TEXT_SubmitUrl, TEXT_UserNameKey, TEXT_PasswordKey, TEXT_PhoneNumberKey,
			TEXT_MsgContentKey, TEXT_UserName, TEXT_Password, TEXT_Secretkey, TEXT_Password1, TEXT_Secretkey1,
			TEXT_CompAccount, TEXT_CompAccountKey;
	private Button checkbox;
	private PhoneMessageConfig config;

	/**
	 * ��ʼ���ؼ�����������¼�
	 */
	private void initControl() {
		checkbox = this.createButtonControl(ID_BUTTON_Active);
		TEXT_BalanceUrl = this.createTextControl(ID_TEXT_BalanceUrl);
		TEXT_SubmitUrl = this.createTextControl(ID_TEXT_SubmitUrl);
		TEXT_UserNameKey = this.createTextControl(ID_TEXT_UserNameKey);
		TEXT_PasswordKey = this.createTextControl(ID_TEXT_PasswordKey);
		TEXT_PhoneNumberKey = this.createTextControl(ID_TEXT_PhoneNumberKey);
		TEXT_MsgContentKey = this.createTextControl(ID_TEXT_MsgContentKey);
		TEXT_UserName = this.createTextControl(ID_TEXT_UserName);
		TEXT_Password = this.createTextControl(ID_TEXT_Password);
		TEXT_Secretkey = this.createTextControl(ID_TEXT_Secretkey);
		TEXT_Password1 = this.createTextControl(ID_TEXT_Password1);
		TEXT_Secretkey1 = this.createTextControl(ID_TEXT_Secretkey1);
		TEXT_CompAccount = this.createTextControl(ID_TEXT_CompAccount);
		TEXT_CompAccountKey = this.createTextControl(ID_TEXT_CompAccountKey);
		this.checkbox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (checkbox.getSelection()) {
					TEXT_BalanceUrl.setEnabled(true);
					TEXT_SubmitUrl.setEnabled(true);
					TEXT_UserNameKey.setEnabled(true);
					TEXT_PasswordKey.setEnabled(true);
					TEXT_PhoneNumberKey.setEnabled(true);
					TEXT_MsgContentKey.setEnabled(true);
					TEXT_UserName.setEnabled(true);
					TEXT_Password.setEnabled(true);
					TEXT_Secretkey.setEnabled(true);
					TEXT_Password1.setEnabled(true);
					TEXT_Secretkey1.setEnabled(true);
					TEXT_CompAccount.setEnabled(true);
					TEXT_CompAccountKey.setEnabled(true);

				} else {
					TEXT_BalanceUrl.setEnabled(false);
					TEXT_SubmitUrl.setEnabled(false);
					TEXT_UserNameKey.setEnabled(false);
					TEXT_PasswordKey.setEnabled(false);
					TEXT_PhoneNumberKey.setEnabled(false);
					TEXT_MsgContentKey.setEnabled(false);
					TEXT_UserName.setEnabled(false);
					TEXT_Password.setEnabled(false);
					TEXT_Secretkey.setEnabled(false);
					TEXT_Password1.setEnabled(false);
					TEXT_Secretkey1.setEnabled(false);
					TEXT_CompAccount.setEnabled(false);
					TEXT_CompAccountKey.setEnabled(false);
				}
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_BalanceUrl, TEXT_BalanceUrl.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_SubmitUrl, TEXT_SubmitUrl.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_UserNameKey, TEXT_UserNameKey.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_PasswordKey, TEXT_PasswordKey.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		// registerInputValidator(new TextInputValidator(TEXT_UserNameKey,
		// TEXT_CompAccountKey.getHint() + "����Ϊ�գ�") {
		// @Override
		// protected boolean validateText(String text) {
		// return !StringUtils.isEmpty(text);
		// }
		// });
		// registerInputValidator(new TextInputValidator(TEXT_PasswordKey,
		// TEXT_CompAccount.getHint() + "����Ϊ�գ�") {
		// @Override
		// protected boolean validateText(String text) {
		// return !StringUtils.isEmpty(text);
		// }
		// });
		registerInputValidator(new TextInputValidator(TEXT_PhoneNumberKey, TEXT_PhoneNumberKey.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_MsgContentKey, TEXT_MsgContentKey.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_UserName, TEXT_UserName.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_Password, TEXT_Password.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_Password1, "���ظ�һ���û����룡") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_Secretkey, TEXT_Secretkey.getHint() + "����Ϊ�գ�") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
		registerInputValidator(new TextInputValidator(TEXT_Secretkey1, "���ظ�һ�����ݿ���Կ��") {
			@Override
			protected boolean validateText(String text) {
				return !StringUtils.isEmpty(text);
			}
		});
	}

	/**
	 * ��ʼ���ؼ�������״̬
	 */
	private void initData() {
		config = getContext().find(PhoneMessageConfig.class);
		if (null == config) {
			return;
		}
		if (config.isActiving()) {
			this.checkbox.setSelection(true);
		} else {
			this.checkbox.setSelection(false);
		}
		TEXT_BalanceUrl.setText(config.getBalanceUrl());
		TEXT_SubmitUrl.setText(config.getSubmitUrl());
		TEXT_UserNameKey.setText(config.getUserNameKey());
		TEXT_PasswordKey.setText(config.getPasswordKey());
		TEXT_PhoneNumberKey.setText(config.getPhoneNumberKey());
		TEXT_MsgContentKey.setText(config.getMsgContentKey());
		TEXT_UserName.setText(config.getUserName());
		TEXT_Password.setText(config.getPassword());
		TEXT_Secretkey.setText(config.getSecretkey());
		TEXT_Password1.setText(config.getPassword());
		TEXT_Secretkey1.setText(config.getSecretkey());
		TEXT_CompAccountKey.setText(config.getCompAccountKey());
		TEXT_CompAccount.setText(config.getCompAccount());
	}

	@Override
	public void process(Situation situation) {

		initControl();
		initData();

		// �����������
		Button saveButton = this.createControl(ID_BUTTON_SAVEBUTTON, Button.class, JWT.NONE);
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				processData();
			}
		});

		Button testButton = this.createControl(ID_BUTTON_TESTBUTTON, Button.class, JWT.NONE);
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GetMessageBalanceKey key = new GetMessageBalanceKey();
				if (!viladate()) {
					return;
				}
				String url = TEXT_BalanceUrl.getText();
				String userNameKey = TEXT_UserNameKey.getText();
				String passwordKey = TEXT_PasswordKey.getText();
				String userName = TEXT_UserName.getText();
				String password = TEXT_Password.getText();
				String compAccountKey = TEXT_CompAccountKey.getText();
				String compAccount = TEXT_CompAccount.getText();
				key.setBalanceUrl(url);
				key.setPassword(password);
				key.setPasswordKey(passwordKey);
				key.setUserName(userName);
				key.setUserNameKey(userNameKey);
				key.setCompAccountKey(compAccountKey);
				key.setCompAccount(compAccount);
				MsgAccountBalance entity = getContext().find(MsgAccountBalance.class, key);
				if (null == entity) {

				} else {
					alert(entity.getMsg());
				}
			}
		});
	}

	public boolean processData() {
		if (!viladate()) {
			return false;
		}
		UpdatePhoneMsgConfigTask task = new UpdatePhoneMsgConfigTask();
		if (null != config) {
			task.setId(config.getId());
		}
		task.setActiving(this.checkbox.getSelection());
		task.setBalanceUrl(this.TEXT_BalanceUrl.getText());
		task.setMsgContentKey(this.TEXT_MsgContentKey.getText());
		task.setPassword(this.TEXT_Password.getText());
		task.setPasswordKey(this.TEXT_PasswordKey.getText());
		task.setPhoneNumberKey(this.TEXT_PhoneNumberKey.getText());
		task.setSecretkey(this.TEXT_Secretkey.getText());
		task.setSubmitUrl(this.TEXT_SubmitUrl.getText());
		task.setUserName(this.TEXT_UserName.getText());
		task.setUserNameKey(this.TEXT_UserNameKey.getText());
		task.setCompAccountKey(this.TEXT_CompAccountKey.getText());
		task.setCompAccount(this.TEXT_CompAccount.getText());
		context.handle(task);
		alert("����ɹ���");
		return true;
	}

	private boolean viladate() {
		String url = TEXT_BalanceUrl.getText();
		if (CheckIsNull.isEmpty(url)) {
			alert("��������ַ����Ϊ��,����!");
			return false;
		}
		String submitUrl = TEXT_SubmitUrl.getText();
		if (CheckIsNull.isEmpty(submitUrl)) {
			alert("�ύ�����ַ����Ϊ��,����!");
			return false;
		}
		// String compAccountKey = TEXT_CompAccountKey.getText();
		// if (CheckIsNull.isEmpty(compAccountKey)) {
		// alert("��ҵ�˺��ֶβ���Ϊ��,����!");
		// return false;
		// }
		// String compAccount = TEXT_CompAccountKey.getText();
		// if (CheckIsNull.isEmpty(compAccount)) {
		// alert("��ҵ�˺Ų���Ϊ��,����!");
		// return false;
		// }
		String userNameKey = TEXT_UserNameKey.getText();
		if (CheckIsNull.isEmpty(userNameKey)) {
			alert("�û������ֶβ���Ϊ��,����!");
			return false;
		}
		String passwordKey = TEXT_PasswordKey.getText();
		if (CheckIsNull.isEmpty(passwordKey)) {
			alert("��½�����ֶβ���Ϊ��,����!");
			return false;
		}
		String userName = TEXT_UserName.getText();
		if (CheckIsNull.isEmpty(userName)) {
			alert("�û�������Ϊ��,����!");
			return false;
		}
		String phoneNumberKey = TEXT_PhoneNumberKey.getText();
		if (CheckIsNull.isEmpty(phoneNumberKey)) {
			alert("�ֻ������ֶ�������Ϊ��,����!");
			return false;
		}

		String msgContentKey = TEXT_MsgContentKey.getText();
		if (CheckIsNull.isEmpty(msgContentKey)) {
			alert("���������ֶ�������Ϊ��,����!");
			return false;
		}
		String password = TEXT_Password.getText();
		if (CheckIsNull.isEmpty(password)) {
			alert("���벻��Ϊ��,����!");
			return false;
		}

		String password1 = TEXT_Password1.getText();
		if (CheckIsNull.isEmpty(password1)) {
			alert("����дһ�����룬��ȷ����ȷ��лл��");
			return false;
		}
		if (!password.equals(password1)) {
			alert("������������벻һ�£����飡");
			return false;
		}

		String secretkey = TEXT_Secretkey.getText();
		if (CheckIsNull.isEmpty(secretkey)) {
			alert("���ݿ���Կ����Ϊ��,����!");
			return false;
		}

		String secretkey1 = TEXT_Secretkey1.getText();
		if (CheckIsNull.isEmpty(secretkey1)) {
			alert("����дһ����Կ����ȷ����ȷ��лл��");
			return false;
		}
		if (!secretkey.equals(secretkey1)) {
			alert("�����������Կ��һ�£����飡");
			return false;
		}

		if (secretkey.length() > 30) {
			alert("��Ҫ���ù�������Կ,����Ӱ���ѯ�ٶ�");
			return false;
		}
		return true;
	}

	public String getPromptMessage() {
		return null;
	}
}