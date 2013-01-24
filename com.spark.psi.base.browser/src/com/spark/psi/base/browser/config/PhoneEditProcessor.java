package com.spark.psi.base.browser.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.RandomCode;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.MsgUserInfoChanged;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.organization.key.ValidationMoblieNumberIsOnlyKey;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeMobileTask;
import com.spark.psi.publish.base.sms.task.SendMsgTask;

public class PhoneEditProcessor extends BaseFormPageProcessor {

	public final static String ID_Button_Confirm_New = "Button_Confirm_New";
	public final static String ID_Button_GiveUp_New = "Button_GiveUp_New";
	public final static String ID_Button_GetCheckCode = "Button_GetCheckCode";

	public final static String ID_Label_TipCheckCode = "Label_TipCheckCode";
	public final static String ID_Label_Tip = "Label_Tip";
	public final static String ID_Text_NewPhone = "Text_NewPhone";
	public final static String ID_Text_CheckCode = "Text_CheckCode";

	Label lblTipCheckCode;
	Label lblTip;
	Text txtNewPhone;
	Text txtCheckCode;
	Button btnCheckCode;

	GUID empId;
	String oldMobilePhone;

	private String checkCode;
	private long checkCodeTime;
 
	@Override
	public void process(final Situation context) {

		empId = (GUID) this.getArgument();
		oldMobilePhone = this.getArgument2().toString();

		txtNewPhone = this.createControl(ID_Text_NewPhone, Text.class);
		txtCheckCode = this.createControl(ID_Text_CheckCode, Text.class);
		lblTipCheckCode = this
				.createControl(ID_Label_TipCheckCode, Label.class);
		lblTip = this.createControl(ID_Label_Tip, Label.class);

		btnCheckCode = this.createControl(ID_Button_GetCheckCode, Button.class);
		btnCheckCode.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY,
				"PSIBase.UserConfig.saasEmployeeModfiyPhoneTip");
		btnCheckCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validateInput(txtNewPhone)) {
					lblTip.setVisible(true);
					btnCheckCode.notifyClientAction();
					checkCode = RandomCode.newCode(6);
					checkCodeTime = System.currentTimeMillis();
					getContext().handle(new SendMsgTask(txtNewPhone.getText(), "�޸��ֻ��������֤�룺"+checkCode, context.find(LoginInfo.class).getTenantId()));
					// TODO�����Ͷ��ţ���ʱֱ����ʾ
					txtCheckCode.setText(checkCode);
				} else {
					checkCode = null;
				}
			}
		});

		this.createControl(ID_Button_Confirm_New, Button.class)
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (validateInput()) {
							String phone = txtNewPhone.getText();
							// ����Ա���ֻ����淽��
							UpdateEmployeeMobileTask task = new UpdateEmployeeMobileTask(
									empId, phone);
							getContext().handle(task);
							// ����ɹ����ظ�������ҳ�棬������Ӫƽ̨�޸�
							context.bubbleMessage(new MsgUserInfoChanged());
							context.bubbleMessage(new MsgResponse(true, phone));
						}
					}
				});

		this.createControl(ID_Button_GiveUp_New, Button.class)
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						context.bubbleMessage(new MsgCancel());
					}
				});

		//
		this.registerInputValidator(new TextInputValidator(txtNewPhone, "") {
			@Override
			protected boolean validateText(String text) {
				if (!CheckIsNull.isEmpty(text)) {
					Pattern p = Pattern
							.compile("((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1} \\d{1}-?\\d{8}$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))");
					Matcher m = p.matcher(txtNewPhone.getText());
					if (m.matches()) {
						if (oldMobilePhone.equals(text)) {
							this.hint = "���ֻ�����������ԭ�ֻ������ظ������޸ģ�";
							return false;
						}
					} else {
						this.hint = "��������ȷ���ֻ����룡";
						return false;
					}
					
					LoginInfo loginInfo = getContext().find(LoginInfo.class);
					if(!getContext().get(Boolean.class,new ValidationMoblieNumberIsOnlyKey(loginInfo.getEmployeeInfo().getId(), text))) {
						this.hint = "���ֻ���������Ա���ֻ����ظ������޸ģ�";
						return false;
					}
				} else {
					this.hint = "�������ֻ����룡";
					return false;
				}
				return true;
			}
		});
		this.registerInputValidator(new TextInputValidator(txtCheckCode, "") {
			@Override
			protected boolean validateText(String text) {
				if (!CheckIsNull.isEmpty(text)) {
					if (text.equals(checkCode)) {
						if (System.currentTimeMillis() - checkCodeTime > 30 * 1000) {
							this.hint = "��֤���Ѿ����ڣ������»�ȡ��";
							return false;
						}
					} else {
						this.hint = "��֤��������޸ģ�";
						return false;
					}
				} else {
					this.hint = "��������֤�룡";
					return false;
				}
				return true;
			}
		});
	}
}