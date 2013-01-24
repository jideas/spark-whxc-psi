package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.DocumentEvent;
import com.jiuqi.dna.ui.wt.events.DocumentListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;

/**
 * 密码修改控件
 */
public class UserPassEditor extends Composite {

	private enum MessageControl {
		Ok(0x01a710, "saas_green_ok.png"), War(0xff0000, "saas_red_warning.png");

		final Color color;
		final String img;

		public Color getColor() {
			return color;
		}

		public String getImg() {
			return img;
		}

		MessageControl(int color, String img) {
			this.color = new Color(color);
			this.img = img;
		}
	}

	private Button btnCheck;
	

	protected Text text_old;
	protected Text text_new;
	protected Text text_confirm;
	private Composite cmp_old;
	private Composite cmp_new;
	private Composite cmp_confirm;
	private Composite cmp;

	

	private String btnCheckLabelName;
	private String pluginId;
	private String imageFilePath;
	private String oldPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getBtnCheckLabelName() {
		return btnCheckLabelName;
	}

	public void setBtnCheckLabelName(String btnCheckLabelName) {
		this.btnCheckLabelName = btnCheckLabelName;
	}

	public UserPassEditor(Composite parent) {

		super(parent);
	}

	public void doinit() {
		initLayout();
		initEvent();
	}

	private void initEvent() {

		btnCheck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				cmp.setEnabled(btnCheck.getSelection());
			}
		});

		text_new.addClientEventHandler(JWT.CLIENT_EVENT_DOCUMENT,
				"PSIBase.UserConfig.passwordStrengthVerification");
		text_new.addClientEventHandler(JWT.CLIENT_EVENT_FOCUS_LOST,
				"PSIBase.UserConfig.labNewpwdFocusLost");
		text_new.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
				"PSIBase.UserConfig.passwordStrengthVerification");

		text_confirm.addDocumentListener(new DocumentListener() {

			public void documentUpdate(DocumentEvent arg0) {
				if (text_new.getText() != null
						&& text_new.getText().equals(text_confirm.getText())) {
					createMessage(cmp_confirm, "", MessageControl.Ok);
				} else {
					createMessage(cmp_confirm, "两次输入的密码不一致", MessageControl.War);
				}
			}
		});

		text_old.addDocumentListener(new DocumentListener() {

			public void documentUpdate(DocumentEvent arg0) {
//			
//				if (text_old.getText().equals(oldPassword)) {
//					createMessage(cmp_old, "", MessageControl.Ok);
//				} else {
//					createMessage(cmp_old, "输入的原始密码错误！", MessageControl.War);
//				}
			}
		});
	}

	private Composite createPwdMsgCmp(String id) {
		Composite cmp = new Composite(cmp_new);
		cmp.setID(id);
		cmp.setLocation(0, 0);
		cmp.setSize(200, 24);
		cmp.setVisible(false);
		return cmp;
	}

	private void createMessage(Composite parent, String msg, MessageControl mc) {
		parent.clear();
		parent.setLayout(new GridLayout(2));
		Composite imgCmp = new Composite(parent);
		imgCmp.setLayoutData(new GridData(24, 24));
		imgCmp.setBackimage(FileImageDescriptor.createImageDescriptor(pluginId,
				imageFilePath + mc.getImg()));
		Label label = new Label(parent);
		label.setText(msg);
		label.setForeground(mc.getColor());
		label.setLayoutData(GridData.INS_CENTER_VERTICAL);
		parent.layout();
	}

	private void initLayout() {

		GridLayout gridLayout = new GridLayout();
		gridLayout.marginTop = 5;
		gridLayout.marginBottom = 5;
		this.setLayout(gridLayout);

		btnCheck = new Button(this, JWT.CHECK | JWT.APPEARANCE3);
		btnCheck.setText(getBtnCheckLabelName());
		btnCheck.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL));

		GridData layoutData = new GridData();
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.grabExcessVerticalSpace = true;
		cmp = new Composite(this);
		cmp.setLayout(new GridLayout(3));

		GridData layoutData2 = new GridData();
		layoutData2.widthHint = 200;
		layoutData2.heightHint = 24;
		GridData layoutData3 = new GridData();
		layoutData3.widthHint = 210;
		GridData layoutData4 = new GridData();
		layoutData4.widthHint = 80;
		Label lbl_old = new Label(cmp, JWT.RIGHT);
		lbl_old.setText("原始密码：");
		lbl_old.setLayoutData(layoutData4);
		text_old = new Text(cmp, JWT.APPEARANCE3|JWT.PASSWORD);
		text_old.setLayoutData(layoutData3);
		cmp_old = new Composite(cmp);
		cmp_old.setLayoutData(layoutData2);

		Label lbl_new = new Label(cmp, JWT.RIGHT);
		lbl_new.setText("输入新密码：");
		lbl_new.setLayoutData(layoutData4);
		text_new = new Text(cmp, JWT.APPEARANCE3|JWT.PASSWORD);
		text_new.setLayoutData(layoutData3);
		cmp_new = new Composite(cmp);
		cmp_new.setLayoutData(layoutData2);

		Label lbl_confirm = new Label(cmp, JWT.RIGHT);
		lbl_confirm.setText("确认新密码：");
		lbl_confirm.setLayoutData(layoutData4);
		text_confirm = new Text(cmp, JWT.APPEARANCE3|JWT.PASSWORD);
		text_confirm.setLayoutData(layoutData3);
		cmp_confirm = new Composite(cmp);
		cmp_confirm.setLayoutData(layoutData2);

		cmp.setEnabled(false);

		for (int i = 0; i < 4; i++) {
			Composite cmp = createPwdMsgCmp("cmplv" + i);
			cmp.setLocation(3, 3);
			cmp.setLayout(new GridLayout(4));
			Label lab_1 = new Label(cmp);
			lab_1.setText("强度：");
			Label lab_2 = new Label(cmp);
			lab_2.setText("弱");
			lab_2.setForeground(new Color(0x01a710));
			Composite img = new Composite(cmp);
			img.setBackimage(FileImageDescriptor.createImageDescriptor(
					pluginId, imageFilePath + "saas_password_level_0" + i
							+ ".png"));
			img.setLayoutData(new GridData(120, 20));
			Label lab_3 = new Label(cmp);
			lab_3.setText("强");
			lab_3.setForeground(new Color(0xff0000));
		}

		createMessage(createPwdMsgCmp("pwdMsgOkCmp"), "密码强度：XXXX",
				MessageControl.Ok);
		createMessage(createPwdMsgCmp("pwdMsgWarCmp"), "密码长度应为6～20个字符",
				MessageControl.War);
	}
	
	/**
	 * 是否修改密码
	 * 
	 * @return boolean
	 */
	public boolean isChangePassword(){
		return btnCheck.getSelection();
	}
	
	/**
	 * 设置旧密码输入错误提示信息
	 *  void
	 */
	public void setOldPwdErrorMsg(){
		createMessage(cmp_old, "输入的原始密码错误！", MessageControl.War);
	}
	
	public void clearErrorMsg(){
		createMessage(cmp_old, "", MessageControl.Ok);
    }
	
	/**
	 * 获得选中控件	
	 * @return
	 */
	protected Button getBtnCheck() {
		return btnCheck;
	}
	
	/**
	 * 获得修改密码的父控件
	 * @return
	 */
	protected Composite getCmp() {
		return cmp;
	}
}