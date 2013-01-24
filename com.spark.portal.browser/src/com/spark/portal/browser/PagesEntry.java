package com.spark.portal.browser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.UIEntry;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.MenuBar;
import com.jiuqi.dna.ui.wt.widgets.MenuItem;
import com.jiuqi.dna.ui.wt.widgets.Shell;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SaaSPageGather;
import com.spark.common.components.pages.SaaSPageInfo;
import com.spark.portal.browser.FramePage;

public class PagesEntry implements UIEntry {

	private Composite contentArea;
	private Label titleLabel;

	public void createUI(String[] args, Shell shell) {
		shell.setTitle("系统页面列表");
		GridLayout gl = new GridLayout();
		gl.marginTop = 5;
		gl.marginLeft = gl.marginRight = 9;
		gl.marginBottom = 10;
		gl.verticalSpacing = 10;
		shell.setLayout(gl);
		MenuBar menuBar = new MenuBar(shell);
		menuBar.setLayoutData(GridData.INS_FILL_HORIZONTAL);

		ImageDescriptor defaultIcon = PortalImages
				.getImage("icons/QueSheng_22.png");

		SaaSPageInfo[] pages = SaaSPageGather.getAllPageInfos();
		List<String> groups = new ArrayList<String>();
		for (int i = 0; i < pages.length; i++) {
			if (!groups.contains(pages[i].getSpace())) {
				groups.add(pages[i].getSpace());
			}
		}
		Collections.sort(groups);
		Map<String, MenuItem> groupItems = new HashMap<String, MenuItem>();
		for (String group : groups) {
			MenuItem groupItem = new MenuItem(menuBar, JWT.CASCADE);
			groupItem.setText(group);
			groupItems.put(group, groupItem);
			groupItem.setData(new Integer(0));
		}
		for (int i = 0; i < pages.length; i++) {
			SaaSPageInfo pageInfo = pages[i];
			MenuItem groupItem = groupItems.get(pageInfo.getSpace());
			groupItem.setData((Integer) groupItem.getData() + 1);
			MenuItem item = new MenuItem(groupItem);
			item.setText(pageInfo.getTitle());
			item.setImage(defaultIcon);
			item.setData(pageInfo);

			//
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentArea.clear();
					SaaSPageInfo pageInfo = (SaaSPageInfo) e.widget.getData();
					new FramePage(contentArea,
							new BaseFunction(new PageControllerInstance(
									pageInfo.getController()), pageInfo
									.getTitle()));
					contentArea.layout();
					titleLabel.setText(pageInfo.getTitle() + "：");
				}
			});
		}

		for (String group : groups) {
			MenuItem groupItem = groupItems.get(group);
			groupItem.setText(groupItem.getText() + "(" + groupItem.getData()
					+ ")");
		}

		MenuItem closeItem = new MenuItem(menuBar);
		closeItem.setText("关闭");
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentArea.clear();
				showLogo();
				titleLabel.setText("");
				contentArea.layout();
			}
		});

		titleLabel = new Label(shell);
		titleLabel.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		contentArea = new Composite(shell);
		contentArea.setLayoutData(GridData.INS_FILL_BOTH);
		contentArea.setLayout(new FillLayout());
		//
		showLogo();
	}

	private void showLogo() {
		Label logoLabel = new Label(contentArea, JWT.IMAGE);
		logoLabel.setImage(PortalImages.getImage("logo_hb.jpg"));
	}
}
