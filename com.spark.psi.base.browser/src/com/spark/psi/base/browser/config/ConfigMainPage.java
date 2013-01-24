package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.AdapterPageProcessor;
import com.spark.common.components.pages.AdapterPageRender;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class ConfigMainPage extends Page {

	public class But {
		String title;
		String img;
		ActionListener actionListener;
		Auth auth;

		public But(String title, Auth auth, String img, ActionListener actionListener) {
			this.title = title;
			this.img = "images/config/" + (StringUtils.isEmpty(img) ? "but.jpg" : img);
			this.actionListener = actionListener;
			this.auth = auth;
		}
	}

	public ConfigMainPage(Composite parent) {
		super(parent);
		this.setLayout(new GridLayout(1));
		final Composite cmp = new Composite(this);
		GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL
				| GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_CENTER);
		gridData.widthHint = 100 + 50 + 100 + 50 + 100;
		gridData.heightHint = 130 + 130 + 50;
		cmp.setLayoutData(gridData);
		GridLayout gridLayout = new GridLayout(3);
		gridLayout.horizontalSpacing = 50;
		gridLayout.verticalSpacing = 30;// 50
		cmp.setLayout(gridLayout);
		init(cmp);
	}

	private void init(Composite cmp) {
		initButton(cmp);
	}

	private void initButton(Composite composite) {
		// String[] buts = {"个人设置"}

		But buts[] = new ConfigMainPage.But[] {
		// new But("个人设置", "", new ActionListener(){
				//
				// public void actionPerformed(ActionEvent arg0){
				//
				// }
				// }),
				new But("企业设置", Auth.SubFunction_ConfigMange_Company, "qysz.png", new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						getContext().bubbleMessage(
								new MsgRequest(new PageControllerInstance("CompanyConfigPage"), "企业设置"));
					}
				}), new But("组织结构", Auth.SubFunction_ConfigMange_Department, "zzjg.png", new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						PageController adatperController = new PageController(AdapterPageProcessor.class,
								AdapterPageRender.class);
						getContext().bubbleMessage(
								new MsgRequest(new PageControllerInstance(adatperController, "PSI_DepartmentTreePage"),
										"组织结构"));
					}
				}), new But("员工管理", Auth.SubFunction_ConfigMange_Employee, "yggl.png", new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						getContext().bubbleMessage(
								new MsgRequest(new PageControllerInstance("EmployeeManagePage"), "员工管理"));
					}
				}),
				// new But("业务授权", Auth.SubFunction_ConfigMange_SalesmanCredit,
				// "ywsq.png", new ActionListener() {
				//
				// public void actionPerformed(ActionEvent arg0) {
				// getContext().bubbleMessage(
				// new MsgRequest(new
				// PageControllerInstance("SalesmanCreditConfigPage"), "业务授权"));
				// }
				// }),
				new But("短信设置", Auth.SubFunction_ConfigMange_Approval, "shsz.png", new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {

						WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
						style.setSize(360, 370);
						MsgRequest request = new MsgRequest(new PageControllerInstance("PSI_PhoneMsgConfigPage"),
								"短信设置", style);
						getContext().bubbleMessage(request);

						// getContext().bubbleMessage(
						// new MsgRequest(new
						// PageControllerInstance("PSI_PhoneMsgConfigPage"),
						// "短信设置"));
					}
				})
		// , new But("日志查询", Auth.SubFunction_ConfigMange_Log, "rzcx.png", new
		// ActionListener() {
		//
		// public void actionPerformed(ActionEvent arg0) {
		//
		// }
		// })
		};
		LoginInfo login = getContext().find(LoginInfo.class);
		for (int i = 0; i < buts.length; i++) {
			if (!login.hasAuth(buts[i].auth))
				continue;
			getBut(composite, buts[i].title, buts[i].img, buts[i].actionListener);
		}

	}

	public Composite getBut(Composite parent, String title, String img, final ActionListener actionListener) {
		Composite cmp = new Composite(parent);
		cmp.setLayoutData(new GridData(93, 123));
		// cmp.setLayout(new GridLayout(1));
		cmp.setLayout(new GridLayout(1));
		Composite imgCmp = new Composite(cmp);
		imgCmp.setLayoutData(new GridData(93, 93));
		imgCmp.setBackimage(BaseImages.getImage(img));
		imgCmp.addMouseClickListener(new MouseClickListener() {

			public void click(MouseEvent arg0) {
				actionListener.actionPerformed(null);
			}
		});
		imgCmp.setCursor(Cursor.HAND);
		Composite cmpLabel = new Composite(cmp);
		cmpLabel.setLayoutData(GridData.INS_FILL_BOTH);
		cmpLabel.setLayout(new GridLayout(1));
		Label label = new Label(cmpLabel);
		label.setText(title);
		label.setLayoutData(GridData.INS_CENTER_BOTH);
		label.addMouseClickListener(new MouseClickListener() {

			public void click(MouseEvent arg0) {
				actionListener.actionPerformed(null);
			}
		});
		label.setFont(new Font(10, "宋体", JWT.FONT_STYLE_BOLD));
		label.setForeground(new Color(0x0059af));
		label.setCursor(Cursor.HAND);
		return cmp;

	}

}
