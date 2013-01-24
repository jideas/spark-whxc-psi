package com.spark.psi.mainpage;

import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.spi.application.Session;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.ActiveMode;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.Direction;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.DataImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.FrameWindow;
import com.spark.portal.browser.MainWindow;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.SMenuWindow;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.MsgCompanyInfoChanged;
import com.spark.psi.base.browser.MsgUserInfoChanged;
import com.spark.psi.base.browser.config.PhoneEditProcessor;
import com.spark.psi.base.browser.config.PhoneEditRender;
import com.spark.psi.base.browser.config.UserConfigProcessor;
import com.spark.psi.base.browser.config.UserConfigRender;
import com.spark.psi.mainpage.utils.SMessageShowUtil;
import com.spark.psi.mainpage.utils.StringArray;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.config.entity.CompanyInfo;
import com.spark.psi.publish.base.config.entity.UserHeadInfo;
import com.spark.psi.publish.base.config.task.UpdateUserConfigTask;
import com.spark.psi.publish.base.index.entity.IndexTool;
import com.spark.psi.publish.base.index.entity.Note;
import com.spark.psi.publish.base.start.key.FindSystemEnabledKey;
import com.spark.psi.publish.smessage.entity.SMessageItem;
import com.spark.psi.publish.smessage.entity.SMessageMonitorItem;

public class MainPage extends Page {

	private Label logoImgLabel;

	private Label logoTitleLabel;

	private Label userImgLabel;

	private Label userNameLabel;

	private Label mobileLabel;

	public MainPage(Composite parent) {
		super(parent);

		//
		MainWindow.init(this.getDisplay().getMainShell());
		LoginInfo loginInfo = null;
		try {
			loginInfo = getContext().find(LoginInfo.class);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("index_style", loginInfo.getEmployeeInfo().getStyle());
			this.setCustomObject("theme", jsonObject);
		} catch (Exception e) {
			getDisplay().openUrl("/", "_self");
			return;
		}
		this.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY, "MainPageDna.MainPageNotify");
		UserFunction[] functions = UserFunctionProvider.getUserFunctions(getContext());

		JSONObject jsonObject = new JSONObject();
		this.setCustomObject("icons", jsonObject);

		// 至少都有6个快捷功能
		JSONArray shortcutIconsjsonArray = new JSONArray();
		try {
			jsonObject.put("shortcutIcons", shortcutIconsjsonArray);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		// 除快捷图标之外的所有图标
		JSONArray funjsonArray = new JSONArray();
		try {
			jsonObject.put("funIcons", funjsonArray);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}

		for (int i = 0; i < functions.length; i++) {
			try {
				UserFunction userFunction = functions[i];

				if (shortcutIconsjsonArray.length() == 6) {
					// 前6个为快捷图标,不用关心,后面的才是需要放到主功能区的图标
					JSONObject funIconsObject = new JSONObject();
					try {
						funIconsObject.put("code", userFunction.getCode());
						funIconsObject.put("name", userFunction.getTitle());
						funIconsObject.put("largeNormalIcon", userFunction.getLargeNormalIcon().getDNAURI());
						funIconsObject.put("largeHoverIcon", userFunction.getLargeHoverIcon().getDNAURI());
						funIconsObject.put("middleNormalIcon", userFunction.getMiddleNormalIcon().getDNAURI());
						funIconsObject.put("middleHoverIcon", userFunction.getMiddleHoverIcon().getDNAURI());
						funIconsObject.put("x", userFunction.getXindex());
						funIconsObject.put("y", userFunction.getYindex());
						funIconsObject.put("isPutMain", userFunction.isPutMain());
						funIconsObject.put("group", userFunction.getGroup());
						funjsonArray.put(funIconsObject);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}

				if (!userFunction.getGroup().equals("06") && shortcutIconsjsonArray.length() < 6) {
					JSONObject shortcutIconsObject = new JSONObject();
					shortcutIconsObject.put("code", userFunction.getCode());
					shortcutIconsObject.put("name", userFunction.getTitle());
					shortcutIconsObject.put("middleNormalIcon", userFunction.getMiddleNormalIcon().getDNAURI());
					shortcutIconsObject.put("middleHoverIcon", userFunction.getMiddleHoverIcon().getDNAURI());
					shortcutIconsObject.put("group", userFunction.getGroup());
					shortcutIconsjsonArray.put(shortcutIconsObject);
				}

			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		addBubbleMessage();
		addMonitorOption();
		//
		GridLayout gl = new GridLayout(3);
		gl.marginLeft = 20;
		gl.horizontalSpacing = 15;
		this.setLayout(gl);
		logoImgLabel = new Label(this);
		logoImgLabel.setLayoutData(GridData.INS_CENTER_VERTICAL);

		logoTitleLabel = new Label(this);
		logoTitleLabel.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		logoTitleLabel.setForeground(Color.COLOR_WHITE);
		Font font =
		// new Font(20, "黑体", JWT.NONE);
		new Font(20, "幼圆", JWT.FONT_STYLE_BOLD);
		logoTitleLabel.setFont(font);

		Composite userInfoArea = new Composite(this);
		gl = new GridLayout(3);
		gl.marginRight = 20;
		gl.horizontalSpacing = 15;
		userInfoArea.setLayout(gl);

		userImgLabel = new Label(userInfoArea);
		GridData gd = new GridData();
		gd.verticalSpan = 2;
		userImgLabel.setLayoutData(gd);
		// userImgLabel.setCursor(Cursor.HAND);

		userNameLabel = new Label(userInfoArea);
		userNameLabel.setForeground(Color.COLOR_WHITE);
		// userNameLabel.setCursor(Cursor.HAND);
		// userNameLabel.setHoverForeground(Color.COLOR_LIGHTGRAY);

		Label pwdLabel = new Label(userInfoArea);
		pwdLabel.setText("[修改密码]");
		pwdLabel.setForeground(Color.COLOR_WHITE);
		pwdLabel.setCursor(Cursor.HAND);
		pwdLabel.setHoverForeground(Color.COLOR_LIGHTGRAY);
		// new ModifyPasswordWindow(pwdLabel).bindTargetControl(pwdLabel);
		pwdLabel.addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(530, 170);
				PageController pc = new PageController(UserConfigProcessor.class, UserConfigRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				new FrameWindow(null, userNameLabel, new BaseFunction[] { new BaseFunction(pci, "") }, "修改密码",
						windowStyle, new ResponseHandler() {
							public void handle(Object returnValue, Object returnValue2, Object returnValue3,
									Object returnValue4) {
							}
						}, null);
			}
		});

		mobileLabel = new Label(userInfoArea);
		mobileLabel.setForeground(Color.COLOR_WHITE);
		mobileLabel.setHoverForeground(Color.COLOR_LIGHTGRAY);
		mobileLabel.setCursor(Cursor.HAND);
		mobileLabel.addMouseClickListener(new MouseClickListener() {
			public void click(MouseEvent e) {
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(370, 160);
				PageController pc = new PageController(PhoneEditProcessor.class, PhoneEditRender.class);
				LoginInfo loginInfo = getContext().find(LoginInfo.class);
				PageControllerInstance pci = new PageControllerInstance(pc, loginInfo.getEmployeeInfo().getId(),
						loginInfo.getEmployeeInfo().getMobileNo());
				new FrameWindow(null, userNameLabel, new BaseFunction[] { new BaseFunction(pci, "") }, "修改手机号",
						windowStyle, new ResponseHandler() {
							public void handle(Object returnValue, Object returnValue2, Object returnValue3,
									Object returnValue4) {
								mobileLabel.setText((String) returnValue);
							}
						}, null);
			}
		});

		Label exitLabel = new Label(userInfoArea);
		exitLabel.setText("[安全退出]");
		exitLabel.setForeground(Color.COLOR_WHITE);
		exitLabel.setCursor(Cursor.HAND);
		exitLabel.setHoverForeground(Color.COLOR_LIGHTGRAY);
		SMenuWindow exitConfirmWindow = new SMenuWindow(null, exitLabel, Direction.Down, ActiveMode.Click);
		exitConfirmWindow.bindTargetControl(exitLabel);
		Composite exitContentArea = exitConfirmWindow.getContentArea();
		gl = new GridLayout(2);
		gl.marginBottom = gl.marginTop = 5;
		gl.marginRight = gl.marginLeft = 10;
		gl.horizontalSpacing = 5;
		exitContentArea.setLayout(gl);
		gd = new GridData();
		gd.heightHint = 28;
		getDisplay().getBrowserVersion();
		Button button = new Button(exitContentArea, JWT.APPEARANCE2);
		button.setText("  确定退出  ");
		button.setLayoutData(gd);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDisplay().reset();
				((Session) getContext().getLogin()).dispose(100);
			}
		});
		button = new Button(exitContentArea, JWT.APPEARANCE3);
		button.setText("  取消  ");
		button.setLayoutData(gd);
		button.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "SaasMainPage.handleCloseWindow");

		//
		fillCompanyInfo();
		fillUserInfo();

		this.getDisplay().getMainShell().addClientEventHandler(JWT.CLIENT_EVENT_RESIZE, "SaasMainPage.handleResize");

		this.addClientNotifyListener(new MainPageClientNotifyListener(this, getContext()));
		//
		getDisplay().getSituation().regMessageListener(MsgCompanyInfoChanged.class,
				new MessageListener<MsgCompanyInfoChanged>() {
					public void onMessage(Situation context, MsgCompanyInfoChanged message,
							MessageTransmitter<MsgCompanyInfoChanged> transmitter) {
						fillCompanyInfo();
						layout();
					}
				});
		getDisplay().getSituation().regMessageListener(MsgUserInfoChanged.class,
				new MessageListener<MsgUserInfoChanged>() {
					public void onMessage(Situation context, MsgUserInfoChanged message,
							MessageTransmitter<MsgUserInfoChanged> transmitter) {
						fillUserInfo();
						layout();
					}
				});

		List<IndexTool> indexTools = getContext().getList(IndexTool.class, loginInfo.getEmployeeInfo().getId());
		JSONObject Toolsobject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			Toolsobject.put("indexTools", jsonArray);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		for (IndexTool indexTool : indexTools) {
			if (indexTool.getName() != null && indexTool.getName().equals("note")) {
				try {
					JSONObject noteObject = getNote(loginInfo);
					Toolsobject.put("noteObject", noteObject);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			} else if (indexTool.getName() != null && indexTool.getName().equals("monitor")) {
				try {
					JSONObject jsonMonitorObject = getMonitor();
					Toolsobject.put("jsonMonitorObject", jsonMonitorObject);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
			jsonArray.put(indexTool.getName());
		}
		this.setCustomObject("tool", Toolsobject);
	}

	public JSONObject getNote(LoginInfo loginInfo) throws JSONException {
		Note fNote = getContext().find(Note.class, loginInfo.getEmployeeInfo().getId());
		JSONObject jsonObject = new JSONObject();
		String text = null;
		if (fNote != null) {
			text = fNote.getNoteText();
		}
		jsonObject.put("text", text);
		return jsonObject;
	}

	/**
	 * 添加监控配置的数据
	 * 
	 * @return
	 * @throws JSONException
	 */
	public void addMonitorOption() {
		try {
			// 判断当前用户有哪些权限
			JSONObject jsonObject = getMonitor();
			MainPage.this.setCustomObject("monitorOption", jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject getMonitor() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonObject.put("monitors", jsonArray);
		List<SMessageMonitorItem> list = getContext().getList(SMessageMonitorItem.class);
		for (SMessageMonitorItem messageTypeItem : list) {
			JSONObject json = new JSONObject();
			json.put("name", messageTypeItem.getCode());
			Integer number = getContext().find(Integer.class, messageTypeItem.getType());
			json.put("number", number == null ? 0 : number);
			json.put("isShow", messageTypeItem.isShowMonitor());
			json.put("title", messageTypeItem.getType().getTitle());
			json.put("code", messageTypeItem.getType().getCode());
			jsonArray.put(json);
		}
		return jsonObject;
	}

	/**
	 * 获取未处理信息
	 */
	public int addBubbleMessage() {
		// 取到有多少条未处理信息，给予用户弹出小气泡提示
		List<SMessageItem> sMessageItems = getContext().getList(SMessageItem.class);
		if (sMessageItems.size() > 0) {
			try {
				JSONObject messagejson = new JSONObject();
				messagejson.put("bofore", "共有待处理信息");
				messagejson.put("count", sMessageItems.size());
				messagejson.put("after", "条");
				messagejson.put("type", "01");
				JSONArray jsonArray = new JSONArray();
				messagejson.put("messageArray", jsonArray);
				for (SMessageItem messageItem : sMessageItems) {
					JSONObject jobject = new JSONObject();
					if (messageItem.isShowFlag()) {
						continue;
					}
					jobject.put("bofore", "  ");
					StringArray array = SMessageShowUtil.getShowStr(messageItem);
					jobject.put("count", array.getText());
					if (array.getArray() != null && array.getArray().length > 0) {
						jobject.put("textID", array.getArray()[2]);
						jobject.put("textName", array.getArray()[1]);
						jobject.put("textCode", array.getArray()[0]);
					}
					jobject.put("after", "");
					jobject.put("type", "02");
					jobject.put("id", messageItem.getRECID());
					jsonArray.put(jobject);
				}
				this.setCustomObject("bubbleMessage", messagejson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sMessageItems == null ? 0 : sMessageItems.size();
	}

	private void fillCompanyInfo() {
		// TenantInfo tenantInfo = getContext().find(TenantInfo.class);
		CompanyInfo companyInfo = getContext().find(CompanyInfo.class);
		ImageDescriptor logoImage = MainPageImages.getImage("images/logo.png");
		String logoTitle = "柒号生活馆进销存管理系统";
		if (companyInfo != null) {
			byte[] logoData = companyInfo.getLogo();
			if (logoData != null && logoData.length > 0) {
				// LoginInfo loginInfo = getContext().find(LoginInfo.class);
				String imageId = GUID.MD5Of(new String(logoData)).toString();
				logoImage = DataImageDescriptor.createImageDescriptor(imageId, logoData);
			}
			if (!StringUtils.isEmpty(companyInfo.getSystemName())) {
				logoTitle = companyInfo.getSystemName();
			}
		}
		logoImgLabel.setImage(logoImage);
		logoTitleLabel.setText(logoTitle);

	}

	private void fillUserInfo() {
		LoginInfo loginInfo = getContext().find(LoginInfo.class);
		UserHeadInfo userHeadInfo = getContext().find(UserHeadInfo.class);
		ImageDescriptor userImage = MainPageImages.getImage("images/default_man_head.png");
		if (userHeadInfo != null && userHeadInfo.getImg() != null) {
			userImage = DataImageDescriptor.createImageDescriptor(userHeadInfo.getId().toString(), userHeadInfo
					.getImg());
		}
		mobileLabel.setText("手机：" + loginInfo.getEmployeeInfo().getMobileNo());
		userNameLabel.setText("欢迎您：" + loginInfo.getEmployeeInfo().getName());
		userImgLabel.setImage(userImage);
		userImgLabel.setCursor(Cursor.HAND);
		SMenuWindow headImageWindow = new SMenuWindow(null, userImgLabel, Direction.Down, ActiveMode.Click);
		headImageWindow.addClientEventHandler("beforeShow", "SaasMainPage.handleHeadImageWindowBeforeShow");
		headImageWindow.bindTargetControl(userImgLabel);
		Composite headImageContentArea = headImageWindow.getContentArea();
		GridLayout gl = new GridLayout();
		gl.marginBottom = gl.marginTop = 5;
		gl.horizontalSpacing = 5;
		headImageContentArea.setLayout(gl);
		// ===============================更换头像区域部分开始===============================
		Composite iconsComposite = new Composite(headImageContentArea);
		GridLayout layout_04 = new GridLayout(5);
		layout_04.marginTop = 5;
		layout_04.marginBottom = 5;
		layout_04.marginLeft = 5;
		layout_04.marginRight = 5;
		layout_04.horizontalSpacing = 5;
		layout_04.verticalSpacing = 5;
		iconsComposite.setLayout(layout_04);
		// 测试
		List<UserHeadInfo> list = getContext().getList(UserHeadInfo.class);
		for (final UserHeadInfo head : list) {
			Label lbl = new Label(iconsComposite);
			lbl.setID(head.getId().toString());
			lbl.setImage(DataImageDescriptor.createImageDescriptor(head.getId().toString(), head.getImg()));
			lbl.setCursor(Cursor.HAND);
			lbl.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY, "SaasMainPage.handleCloseWindow");
			lbl.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent e) {
					Label lbl = (Label) e.widget;
					userImgLabel.setImage(lbl.getImage());
					userImgLabel.setData(head.getId());

					// 保存到数据库
					UpdateUserConfigTask task = new UpdateUserConfigTask();
					task.setId(getContext().find(LoginInfo.class).getEmployeeInfo().getId());
					task.setLogo(head.getId());
					getContext().handle(task);
					lbl.notifyClientAction();
				}
			});

		}
		// ===============================更换头像区域部分结束===============================
	}
}
