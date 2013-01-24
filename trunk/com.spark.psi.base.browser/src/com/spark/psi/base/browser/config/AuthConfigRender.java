package com.spark.psi.base.browser.config;

import java.util.List;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.portal.browser.MsgCancel;
import com.spark.psi.publish.base.organization.entity.RoleInfo;

public class AuthConfigRender extends BaseFormPageRender {

	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(new GridLayout(2));
		GridData xmData = new GridData();
		xmData.widthHint = 71;
		Label labTitle = new Label(formArea, JWT.RIGHT);
		labTitle.setText("姓　　名：");
		labTitle.setLayoutData(xmData);
		new Label(formArea).setID(AuthConfigProcessor.ID_Label_NAME);
		GridData operData = new GridData();
		operData.widthHint = 71;
		operData.verticalAlignment = JWT.TOP;
		operData.verticalIndent = 5;
		Label labOper = new Label(formArea, JWT.RIGHT);
		labOper.setText("操作权限：");
		labOper.setLayoutData(operData);
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = JWT.FILL;
		gridData.verticalAlignment = JWT.FILL;
		final Composite comp = new Composite(formArea);
		comp.setLayoutData(gridData);
		comp.setLayout(new GridLayout(2));
		comp.setID(AuthConfigProcessor.ID_COMPOSITE_ROLE_OPTIONS);

		//

		List<RoleInfo> roleList = getContext().getList(RoleInfo.class);// ProductService
		int rows = 0;
		for (RoleInfo role : roleList) {
			if (rows++ == 1) {// 加一个横线
				Composite cmp = new Composite(comp);
				GridData gridData2 = new GridData(GridData.GRAB_HORIZONTAL
						| GridData.HORIZONTAL_ALIGN_FILL);
				gridData2.horizontalSpan = 2;// 占2列，因为grid布局时设置的是一行两列
				gridData2.heightHint = 1;
				cmp.setLayoutData(gridData2);
				cmp.setBackground(new Color(0x78a9bf));// 设置背景色
			}

			GridData btnData = new GridData(GridData.VERTICAL_ALIGN_CENTER
					| GridData.GRAB_VERTICAL);
			btnData.widthHint = 90;
			Button btn = new Button(comp, JWT.CHECK);
			btn.setText(role.getName());
			btn.setID(AuthConfigProcessor.RoleButtonID_Prefix
					+ String.valueOf(role.getCode()));
			btn.setLayoutData(btnData);

			GridData labelData = new GridData(GridData.VERTICAL_ALIGN_CENTER
					| GridData.GRAB_VERTICAL);
			labelData.verticalIndent = 3;
			Label labBoss = new Label(comp);
			labBoss.setText(role.getDescription());
			labBoss.setLayoutData(labelData);
		}
	}

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("  确 定  ");
		button.setID(AuthConfigProcessor.ID_BUTTON_CONFIRM);

		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("  取 消  ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});
	}
}