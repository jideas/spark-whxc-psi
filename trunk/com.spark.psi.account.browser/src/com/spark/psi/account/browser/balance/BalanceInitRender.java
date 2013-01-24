package com.spark.psi.account.browser.balance;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.AbstractBoxPageRender;

public class BalanceInitRender extends AbstractBoxPageRender {
	         
	@Override
	protected void afterFooterRender() {
		footerArea.setLayout(new GridLayout());
		
		Composite footerRightArea = new Composite(footerArea);
		GridData gdFright = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gdFright.heightHint = 29;
		footerRightArea.setLayoutData(gdFright);
		footerRightArea.setLayout(new GridLayout(2));
		
		GridData gdButton = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.FILL_VERTICAL);
		Button tempSaveBtn = new Button(footerRightArea, JWT.APPEARANCE3);
		tempSaveBtn.setLayoutData(gdButton);
		tempSaveBtn.setText(" 暂存数据 ");
		tempSaveBtn.setID(BalanceInitProcessor.ID_Button_TempSave);
		
		Button finishBtn = new Button(footerRightArea, JWT.APPEARANCE3);
		finishBtn.setLayoutData(gdButton);
		finishBtn.setText(" 完成设置 ");
		finishBtn.setID(BalanceInitProcessor.ID_Button_Finish);
	}

	@Override
	protected void beforeFooterRender() {
		Composite headerArea = new Composite(contentArea);
		GridData gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		gdHeader.heightHint = 24;
		headerArea.setLayoutData(gdHeader);
		headerArea.setLayout(new GridLayout(2));
		
		Composite headerLeftArea = new Composite(headerArea);
		GridData gdHeaderLeft = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gdHeaderLeft.heightHint = 24;
		headerLeftArea.setLayoutData(gdHeaderLeft);
		GridLayout glHeaderLeft = new GridLayout();
		glHeaderLeft.numColumns = 2;
		headerLeftArea.setLayout(glHeaderLeft);
		
		Label label0 = new Label(headerLeftArea);
		label0.setText("往来初始化   ");
		label0.setFont(new Font(10, "宋体", JWT.FONT_STYLE_BOLD));
		new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(BalanceInitProcessor.ID_List_PartnerType);
		
		Composite headerRightArea = new Composite(headerArea);
		GridData gdHeaderRight = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gdHeaderRight.heightHint = gdHeaderLeft.heightHint;
		headerRightArea.setLayoutData(gdHeaderRight);
		GridLayout glHeaderRight = new GridLayout();
		glHeaderRight.numColumns = 3;
		headerRightArea.setLayout(glHeaderRight);
		Text searchText = new Text(headerRightArea, JWT.APPEARANCE3);
		searchText.setHint("输入搜索内容");
		searchText.setID(BalanceInitProcessor.ID_Text_SearchText);
		Button unInitedButton = new Button(headerRightArea, JWT.APPEARANCE3);
		unInitedButton.setText(" 未初始 ");
		unInitedButton.setID(BalanceInitProcessor.ID_Button_SearchUnInited);
		Button initedButton = new Button(headerRightArea, JWT.APPEARANCE3);
		initedButton.setText(" 已初始 ");
		initedButton.setID(BalanceInitProcessor.ID_Button_SearchInited);
		
		Composite tableArea = new Composite(contentArea);
		tableArea.setLayoutData(GridData.INS_FILL_BOTH);
		GridLayout glTableArea = new GridLayout(2);
		glTableArea.horizontalSpacing = 40;
		glTableArea.marginTop = 15;
		tableArea.setLayout(glTableArea);
		
		Composite unInitedArea = new Composite(tableArea);
		unInitedArea.setLayoutData(GridData.INS_FILL_BOTH);
		unInitedArea.setLayout(new GridLayout());
		
		GridData gdLabel = new GridData();
		gdLabel.widthHint = 120;
		
		Label label = new Label(unInitedArea);
		label.setLayoutData(gdLabel);
		label.setID(BalanceInitProcessor.ID_Label_UnInitedCount);
		Composite unInitedTableArea = new Composite(unInitedArea);
		unInitedTableArea.setID(BalanceInitProcessor.ID_Area_UnInited);
		unInitedTableArea.setLayoutData(GridData.INS_FILL_BOTH);
		unInitedTableArea.setLayout(new FillLayout());
		
		
		Composite initedArea = new Composite(tableArea);
		initedArea.setID(BalanceInitProcessor.ID_Area_Inited);
		initedArea.setLayoutData(GridData.INS_FILL_BOTH);
		initedArea.setLayout(new GridLayout());
		
		label = new Label(initedArea);
		label.setLayoutData(gdLabel);
		label.setID(BalanceInitProcessor.ID_Label_InitedCount);
		Composite initedTableArea = new Composite(initedArea);
		initedTableArea.setID(BalanceInitProcessor.ID_Area_Inited);
		initedTableArea.setLayoutData(GridData.INS_FILL_BOTH);
		initedTableArea.setLayout(new FillLayout());
		
	}

}
