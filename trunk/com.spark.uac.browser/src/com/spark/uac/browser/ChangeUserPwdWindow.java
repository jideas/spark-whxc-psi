package com.spark.uac.browser;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.widgets.Window;
import com.spark.portal.browser.SFocusInfoWindow;
import com.spark.uac.publish.entity.UserInfo;
import com.spark.uac.publish.task.UpdateUserPasswordTask;

public class ChangeUserPwdWindow extends Window {

	private Situation context;

	private UserInfo user;

	private Text pwd_txt;

	private Text pwd_txt2;
	
	private String oldPwd;

	private ChangeUserPwdWindowCloseListener changeUserPwdWindowCloseListener;
	
	private SFocusInfoWindow focusInfoWindow;

	public interface ChangeUserPwdWindowCloseListener {
		public void windowClosing();
	}

	public ChangeUserPwdWindow(GUID userId,String oldPwd) {
		super(JWT.CLOSE);
		this.oldPwd = oldPwd;
		context = getContext();
		user = context.find(UserInfo.class, userId);
		if (user == null) {
			throw new IllegalArgumentException("用户不存在");
		}
		this.setTitle("设置密码");
		this.setSize(330, 130);
		initContril();
	}

	private void initContril() {

		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginTop = gl.marginBottom = 20;
		gl.marginLeft = gl.marginRight = 20;
		gl.verticalSpacing = 15;
		this.setLayout(gl);

		Label label = new Label(this);
		label.setText("输入新密码：");

		pwd_txt = new Text(this, JWT.PASSWORD | JWT.APPEARANCE3);
		pwd_txt.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
				"UACLogin.handleEnterKey");
		pwd_txt.setText("");
		pwd_txt.setMaximumLength(20);
		pwd_txt.setLayoutData(GridData.INS_FILL_HORIZONTAL);

		label = new Label(this);
		label.setText("确认新密码：");

		pwd_txt2 = new Text(this, JWT.PASSWORD | JWT.APPEARANCE3);
		pwd_txt2.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
				"UACLogin.handleEnterKey");
		pwd_txt2.setText("");
		pwd_txt2.setMaximumLength(20);
		pwd_txt2.setLayoutData(GridData.INS_FILL_HORIZONTAL);

		Button button = new Button(this, JWT.APPEARANCE3);
		button.setText("   确认   ");
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalSpan = 2;
		gd.heightHint = 28;
		button.setLayoutData(gd);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				savePwd();
			}
		});
		this.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				savePwd();
			}
		});

	}

	protected void savePwd() {
		String pwd = pwd_txt.getText();
		if (StringUtils.isEmpty(pwd)) {
			markInputErrorstatus(pwd_txt,"密码不能为空！");
			return;
		} else {
			markInputErrorstatus(pwd_txt,null);
		}
		if (pwd.length() < 6) {
			markInputErrorstatus(pwd_txt,"您输入的密码过短！");
			return;
		} else {
			markInputErrorstatus(pwd_txt,null);
		}
		if (!pwd.equals(pwd_txt2.getText())) {
			markInputErrorstatus(pwd_txt2,"两次输入密码内容必须一致！");
			return;
		} else {
			markInputErrorstatus(pwd_txt2,null);
		}
		UpdateUserPasswordTask task = new UpdateUserPasswordTask(
				user.getUserId(), pwd_txt.getText(),oldPwd);
		context.handle(task);
		closeWindow();
	}

	private void closeWindow() {
		if (this.changeUserPwdWindowCloseListener != null) {
			this.close();
			changeUserPwdWindowCloseListener.windowClosing();
		} else {
			close();
		}

	}

	public void addChangeUserPwdWindowCloseListener(
			ChangeUserPwdWindowCloseListener changeUserPwdWindowCloseListener) {
		this.changeUserPwdWindowCloseListener = changeUserPwdWindowCloseListener;
	}
	
	private void markInputErrorstatus(Text text, String hint) {
		if (focusInfoWindow == null) {
			focusInfoWindow = new SFocusInfoWindow(this);
		}
		if (StringUtils.isEmpty(hint)) {
			focusInfoWindow.unbindTargetControl(text);
			text.removeMode(JWT.MODE_ERROR);
		} else {
			focusInfoWindow.bindTargetControl(text, hint);
			text.applyMode(JWT.MODE_ERROR);
		}
		text.forceFocus();
	}

}
