package com.spark.uac.browser;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Cookie;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.portal.browser.SFocusInfoWindow;
import com.spark.uac.browser.ChangeUserPwdWindow.ChangeUserPwdWindowCloseListener;
import com.spark.uac.browser.SelectTenantWindow.SelectTenantListener;
import com.spark.uac.publish.UserStatus;
import com.spark.uac.publish.entity.UserInfo;
import com.spark.uac.publish.task.CreateCredentialTask;
import com.spark.uac.publish.task.LoginUserTask;
import com.spark.uac.publish.task.LoginUserTask.ErrType;

public class UACLoginPage extends Page {

	private Text mobileText;
	private Button rememberButton;
	private Text passwordText;
	private Text validateCodeText;

	private final static String Cookie_Mobile = "login.mobile";

	private VerificationCode verificationCode;

	private Label validateCodeLabel;

	/**
	 * 
	 */
	private SFocusInfoWindow focusInfoWindow;

	public UACLoginPage(Composite parent) {
		super(parent);
		this.setBackground(new Color(0xe8e8e8));
		GridLayout layout = new GridLayout(1);
		layout.marginTop = 20;
		layout.marginBottom = 20;
		this.setLayout(layout);
		this.setLayoutData(GridData.INS_FILL_BOTH);
		Composite composite = new Composite(this);
		//composite.setBackimage(UACImages.getImage("images/out-bg.gif"));
		composite.setBackimage(UACImages.getImage("images/login-back.png"));
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_CENTER | GridData.GRAB_VERTICAL);
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = JWT.CENTER;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = JWT.FILL;
		composite.setLayoutData(gd);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.verticalSpacing = 15;
		gl.horizontalSpacing = 10;
		composite.setLayout(gl);
		Composite compositeCenter = new Composite(composite);
		compositeCenter.setLayout(new GridLayout(2));
		GridData gridData = new GridData();
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = JWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = JWT.CENTER;
		compositeCenter.setLayoutData(gridData);
		//ImageDescriptor logoImage = UACImages.getImage("images/left-bg.jpg");
		ImageDescriptor logoImage = UACImages.getImage("images/login-left.png");
		Label logo = new Label(compositeCenter);
		GridData gdLogo = new GridData(GridData.VERTICAL_ALIGN_END);
		logo.setLayoutData(gdLogo);
		logo.setImage(logoImage);

		Composite loginArea = new Composite(compositeCenter);
		ImageDescriptor rightImageDescriptor = null;
		try {
			//rightImageDescriptor = UACImages.getImage("images/right-bg.jpg");
			rightImageDescriptor = UACImages.getImage("images/login-right.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		gd = new GridData();
		gd.heightHint = rightImageDescriptor.getHeight();
		gd.widthHint = rightImageDescriptor.getWidth();
		gd.verticalAlignment = JWT.TOP;
		loginArea.setLayoutData(gd);
		//loginArea.setBorder(CBorder.BORDER_NORMAL);
		
		Label label = new Label(loginArea);
		label.setText("手机号： ");
		//label.setLocation(25, 145);
		label.setLocation(25, 115);
		label.setSize(80, 22);

		mobileText = new Text(loginArea, JWT.APPEARANCE3 | JWT.MIDDLE);
		//mobileText.setLocation(110, 140);
		mobileText.setLocation(110, 110);
		mobileText.setSize(100, 22);
		mobileText.setID("mobileText");
		mobileText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN, "UACLogin.handleEnterKey");
		mobileText.setMaximumLength(11);
		mobileText.setRegExp(TextRegexp.MOBILE);
		try {
			Cookie cookie = getDisplay().getCookie();
			mobileText.setText((String) cookie.get(Cookie_Mobile));
		} catch (Throwable t) {
			mobileText.setText("");
		}

		label = new Label(loginArea);
		label.setText("密  码： ");
		//label.setLocation(25, 185);
		label.setLocation(25, 155);
		label.setSize(80, 22);

		passwordText = new Text(loginArea, JWT.PASSWORD | JWT.APPEARANCE3 | JWT.MIDDLE);
		//passwordText.setLocation(110, 180);
		passwordText.setLocation(110, 150);
		passwordText.setSize(100, 22);
		passwordText.setText("");
		passwordText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN, "UACLogin.handleEnterKey");

		final Label getPasswordButton = new Label(loginArea);
		//getPasswordButton.setLocation(230, 185);
		getPasswordButton.setLocation(230, 155);
		getPasswordButton.setSize(100, 22);
		getPasswordButton.setText("获取密码");
		getPasswordButton.setFont(new Font(9, "宋体", 0));
		getPasswordButton.setCursor(Cursor.HAND);
		// 如果登录方式选择的是企业名称，那么将企业名称带到获取密码页面
		new GetPasswordWindow(getPasswordButton, mobileText.getText(), null);

		label = new Label(loginArea);
		//label.setLocation(25, 225);
		label.setLocation(25, 195);
		label.setSize(80, 22);
		label.setText("验证码： ");

		validateCodeText = new Text(loginArea, JWT.APPEARANCE3 | JWT.MIDDLE);
		//validateCodeText.setLocation(110, 220);
		validateCodeText.setLocation(110, 190);
		validateCodeText.setSize(100, 22);
		validateCodeText.addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN, "UACLogin.handleEnterKey");

		validateCodeLabel = new Label(loginArea);
		validateCodeLabel.setBorder(CBorder.BORDER_NORMAL);
		//validateCodeLabel.setLocation(220, 220);
		validateCodeLabel.setLocation(220, 190);
		validateCodeLabel.setSize(70, 22);
		verificationCode = new VerificationCode(70, 22);
		validateCodeLabel.setImage(verificationCode.getImage());
		validateCodeLabel.setHoverCursor(Cursor.HAND);
		validateCodeLabel.setToolTipText("换一张");
		validateCodeLabel.addMouseClickListener(new MouseClickListener() {

			public void click(MouseEvent e) {
				verificationCode.change();
				validateCodeLabel.setImage(verificationCode.getImage());
			}
		});
//		validateCodeText.setText(verificationCode.getCode());

		Button loginButton = new Button(loginArea, JWT.APPEARANCE2);
		loginButton.setText("登录");
		//loginButton.setLocation(110, 265);
		loginButton.setLocation(110, 235);
		loginButton.setSize(85, 30);
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				handleLogin();
			}
		});

		// Label createTenantButton = new Label(loginArea);
		// createTenantButton.setLocation(25, 330);
		// createTenantButton.setSize(90, 30);
		// createTenantButton.setLayoutData(gd);
		// createTenantButton.setText("新建租户 | ");
		// createTenantButton.setFont(new Font(9, "宋体", 0));
		// createTenantButton.setForeground(new Color(0x6DB260));
		// createTenantButton.setCursor(Cursor.HAND);
		// createTenantButton.addMouseClickListener(new MouseClickListener() {
		// public void click(MouseEvent e) {
		// // TODO: 打开新建租户界面
		// //getContext().bubbleMessage(request);
		// //Window window = new Window(JWT.MODAL);
		// //window.setBorder(CBorder.BORDER_NORMAL);
		// }
		// });
		rememberButton = new Button(loginArea, JWT.CHECK);
		//rememberButton.setLocation(25, 320);
		rememberButton.setLocation(25, 290);
		rememberButton.setSize(170, 30);
		rememberButton.setLayoutData(gd);
		rememberButton.setText("记住手机号码");
		rememberButton.setSelection(false);

		Label rimglabel = new Label(loginArea);
		//rimglabel.setImage(UACImages.getImage("images/right-bg.jpg"));
		rimglabel.setImage(UACImages.getImage("images/login-right.png"));
		rimglabel.setLocation(0, 0);
		rimglabel.setSize(rightImageDescriptor.getWidth(), rightImageDescriptor.getHeight());
	}

	private void handleLogin() {
		String vtext = validateCodeText.getText();
		if (!verificationCode.isValid()) {
			markInputErrorstatus(validateCodeText, "验证码已过期");
			verificationCode.change();
			validateCodeLabel.setImage(verificationCode.getImage());
			// validateCodeLabel.getParent().layout();
			return;
		} else {
			markInputErrorstatus(validateCodeText, null);
		}
		//
		if (StringUtils.isEmpty(vtext)) {
			markInputErrorstatus(validateCodeText, "请输入验证码");
			return;
		} else {
			markInputErrorstatus(validateCodeText, null);
		}

		//
		if (!verificationCode.validation(vtext)) {
			markInputErrorstatus(validateCodeText, "验证码错误");
			return;
		} else {
			markInputErrorstatus(validateCodeText, null);
		}

		//
		String mobileNumber = mobileText.getText();
		if (mobileNumber == null || mobileNumber.trim().length() == 0) {
			markInputErrorstatus(mobileText, "请输入手机号码");
			return;
		} else {
			markInputErrorstatus(mobileText, null);
		}

		//
		String password = passwordText.getText();
		if (password == null) {
			password = "";
		}

		LoginUserTask task = new LoginUserTask(mobileNumber, password);
		getContext().handle(task);
		if (!task.isSucceed()) {
			if (task.getErrType() == ErrType.MobileNo) {
				markInputErrorstatus(mobileText, task.getMsg());
				return;
			} else {
				markInputErrorstatus(mobileText, null);
			}
			if (task.getErrType() == ErrType.Activition) {
				markInputErrorstatus(mobileText, task.getMsg());
				return;
			} else {
				markInputErrorstatus(mobileText, null);
			}
			if (task.getErrType() == ErrType.Password) {
				markInputErrorstatus(passwordText, task.getMsg());
				return;
			} else {
				markInputErrorstatus(passwordText, null);
			}
			return;
		} else {
			if (task.getUser().length > 1) {
				new SelectTenantWindow(task.getUser()).addSelectTenantListener(new SelectTenantListener() {

					@Override
					public void onSelection(UserInfo user) {
						changePwd(user);
					}
				});

			} else {
				changePwd(task.getUser()[0]);
			}
		}
	}

	private void changePwd(final UserInfo userInfo) {
		if (userInfo.getStatus() == UserStatus.Activation) { // 如果用户处于激活中状态，则弹出修改密码界面强制用户修改密码
			final ChangeUserPwdWindow window = new ChangeUserPwdWindow(userInfo.getUserId(), passwordText.getText());
			window.addChangeUserPwdWindowCloseListener(new ChangeUserPwdWindowCloseListener() {

				public void windowClosing() {
					openPage(userInfo);
				}
			});
		} else {
			openPage(userInfo);
		}
	}

	private void openPage(final UserInfo userInfo) {
		if (rememberButton.getSelection()) {
			Cookie cookie = getDisplay().getCookie();
			cookie.put(Cookie_Mobile, userInfo.getMobileNo());
			getDisplay().setCookie(cookie);
		}
		String userId = userInfo.getUserId().toString();
		CreateCredentialTask task = new CreateCredentialTask(userId);
		getContext().handle(task);

		getDisplay().openUrl("/" + "main/sso.jsp?userId=" + userId + "&credential=" + task.getCredential(), "_self");

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
