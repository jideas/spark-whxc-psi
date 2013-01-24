package com.spark.psi.base.browser.config;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgCancel;
import com.spark.psi.base.browser.MsgUserInfoChanged;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.config.task.UpdateUserConfigTask;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.key.CheckUserPwdIsValidKey;

/**
 * 个人信息配置界面处理器
 */
public class UserConfigProcessor extends BaseFormPageProcessor {

	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Close = "Button_Close";
	public final static String ID_Composite_Password = "Composite_Password";

	Composite Composite_Password;
	UserPassEditor userPassEditor;
	Text txt_OldPwd;
	Text txt_Pwd;

	Situation context;

	EmployeeInfo emp;

	private void initEvent() {

		this.createControl(ID_Button_Save, Button.class).addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						UpdateUserConfigTask task = new UpdateUserConfigTask();
						task.setId(context.find(LoginInfo.class)
								.getEmployeeInfo().getId());
						task.setOldPwd(userPassEditor.text_old.getText());
						CheckUserPwdIsValidKey key = new CheckUserPwdIsValidKey(
								task.getId(), task.getOldPwd());
						if (!context.find(Boolean.class, key)) {
							userPassEditor.setOldPwdErrorMsg();
							return;
						} else {
							String pwdconfirm = userPassEditor.text_confirm
									.getText();
							String pwdNew = userPassEditor.text_new.getText();//
							if (pwdconfirm != null && pwdNew != null) {
								if (pwdNew.trim().length() >= 6
										&& pwdNew.trim().length() <= 20
										&& pwdconfirm.trim().equals(
												pwdNew.trim())) {
									task.setPwd(pwdNew);
									userPassEditor.clearErrorMsg();
									context.handle(task);
									hint("修改密码成功,请妥善保存!", new Runnable() {
										
										public void run() {
											context.bubbleMessage(new MsgUserInfoChanged());
											context.bubbleMessage(new MsgCancel());
										}
									});
								}
							}
						}

					}
				});
		this.createControl(ID_Button_Close, Button.class).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				context.bubbleMessage(new MsgCancel());
			}
		});
	}


	@Override
	public void process(Situation situation) {
		context = getContext();
		Composite_Password = this.createControl(ID_Composite_Password,
				Composite.class);
		Composite_Password.setWidth(530);
		Composite_Password.setHeight(150);
		userPassEditor = new IndexUserPassEditor(Composite_Password);
		userPassEditor.setBtnCheckLabelName("修改密码");
		userPassEditor.setPluginId(BaseImages.pluginId);
		userPassEditor.setImageFilePath("/images/config/");
		userPassEditor.setOldPassword("abc123");
		userPassEditor.doinit();
		initEvent();
	}
}