package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboTree;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;

public class WizardAddGoodsStepRender extends WizardBaseStepRender {

	@Override
	protected void addInfoArea(Composite infoArea) {
		GridLayout glInfoArea = new GridLayout();
		glInfoArea.numColumns = 4;
		infoArea.setLayout(glInfoArea);
		//
		Label label = new Label(infoArea);
		label.setText("请选择商品分类：");
		//
		LWComboTree categoryList = new LWComboTree(infoArea, JWT.APPEARANCE3);
		categoryList.setID(WizardAddGoodsStepProcessor.ID_List_Category);
		//
		label = new Label(infoArea);
		label.setText("    商品数量：");
		//
		Label countLabel = new Label(infoArea);
		countLabel.setID(WizardAddGoodsStepProcessor.ID_Label_Count);
		//
		Composite tableArea = new Composite(infoArea);
		GridData gdTableArea = new GridData(GridData.FILL_BOTH);
		gdTableArea.horizontalSpan = 4;
		tableArea.setLayoutData(gdTableArea);
		tableArea.setLayout(new FillLayout());
		tableArea.setID(WizardAddGoodsStepProcessor.ID_Area_Goods);
		
//		STableStyle tableStyle = new STableStyle();
//		tableStyle.setSelectionMode(SSelectionMode.Multi);
//		SEditTable table = new SEditTable(infoArea, getColumns(), tableStyle, null);
//		GridData gdTable = new GridData(GridData.FILL_BOTH);
//		gdTable.horizontalSpan = 4;
//		table.setLayoutData(gdTable);
		
	}

	@Override
	protected String getTitle() {
		return "添加商品";
	}

	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea) {
		GridLayout glLeftButtonArea = new GridLayout();
		glLeftButtonArea.numColumns = 20;
		glLeftButtonArea.horizontalSpacing = 0;
		leftButtonArea.setLayout(glLeftButtonArea);
		
		GridData gdButton = new GridData(GridData.FILL_VERTICAL);
		Button delButton = new Button(leftButtonArea, JWT.APPEARANCE3);
		delButton.setText(" 删除商品 ");
		delButton.setLayoutData(gdButton);
		delButton.setID(WizardAddGoodsStepProcessor.ID_Button_Del);
	}

}
