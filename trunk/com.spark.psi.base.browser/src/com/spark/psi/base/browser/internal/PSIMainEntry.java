package com.spark.psi.base.browser.internal;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.UIEntry;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.MenuBar;
import com.jiuqi.dna.ui.wt.widgets.MenuItem;
import com.jiuqi.dna.ui.wt.widgets.Shell;
import com.spark.common.components.pages.MainFunction;
import com.spark.common.components.pages.MainFunctionGather;
import com.spark.portal.browser.MainWindow;
import com.spark.portal.browser.PortalImages;
import com.spark.portal.browser.SExceptionHandler;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.config.task.LoginTask;
import com.spark.psi.publish.base.config.task.LogoutTask;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;

public class PSIMainEntry implements UIEntry {

	public void createUI(String[] args, Shell shell) {
		//
		shell.getDisplay().setExceptionHandler(SExceptionHandler.INSTANCE);

		//
		GUID tenantId = null;
		GUID userId = null;
		try {
			tenantId = GUID.valueOf(args[0]);
			userId = GUID.valueOf(args[1]);
			String credential = args[2];
			shell.getContext().handle(
					new LoginTask(tenantId, userId, credential));
		} catch (Throwable t) {
			// t.printStackTrace();
			shell.getDisplay().openUrl("/", "_self");
			return;
		}
		TenantInfo tenantInfo = shell.getContext().get(TenantInfo.class,
				tenantId);
		EmployeeInfo employee = shell.getContext().get(EmployeeInfo.class,
				userId);

		//
		shell.setTitle("鱼儿多中小企业业务平台");
		GridLayout gl = new GridLayout();
		gl.marginTop = 5;
		gl.marginLeft = gl.marginRight = 9;
		shell.setLayout(gl);
		MenuBar menuBar = new MenuBar(shell);
		menuBar.setLayoutData(GridData.INS_FILL_HORIZONTAL);

		ImageDescriptor defaultIcon = PortalImages
				.getImage("icons/QueSheng_22.png");

		MainFunction[] functions = MainFunctionGather
				.getAllFunctions("psi/functions");
		Map<String, MenuItem> groupItems = new HashMap<String, MenuItem>();
		for (int i = 0; i < functions.length; i++) {
			MainFunction function;
			try {
				function = functions[i];
				String group = function.getGroup();
				if (group == null) {
					group = "其他";
				}
				MenuItem groupItem = groupItems.get(group);
				if (groupItem == null) {
					groupItem = new MenuItem(menuBar, JWT.CASCADE);
					groupItem.setText(group);
					groupItem.setImage(defaultIcon);
					groupItems.put(group, groupItem);
				}
				MenuItem item = new MenuItem(groupItem);
				item.setText(function.getTitle());
				item.setImage(function.getTitleIcon());
				item.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
						"SaasNavigator.onMenuClick");
				JSONObject functionData = new JSONObject();
				functionData.put("name", function.getCode());
				functionData.put("title", function.getTitle());
				functionData.put("titleIcon", function.getTitleIcon());
				item.setClientObject("functionData", functionData);
			} catch (Throwable t) {
			}
		}

		MenuItem exitItem = new MenuItem(menuBar);
		exitItem.setText("注销[" + tenantInfo.getTitle() + "："
				+ employee.getName() + "]");
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Display.getCurrent().getSituation().handle(new LogoutTask());
				Display.getCurrent().refresh();
			}
		});

		//
		Label logoLabel = new Label(shell, JWT.IMAGE);
		logoLabel.setImage(PortalImages.getImage("logo_hb.jpg"));
		//
		MainWindow.init(shell);
	}
}
