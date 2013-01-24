package com.spark.psi.base.browser.material;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.custom.combo.LWComboTree;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.psi.base.browser.EnumEntitySource;
import com.spark.psi.publish.EnumType;

public class MaterialDetailPageRender extends AbstractBoxPageRender {

	private final static GridData gdLabel;
	private final static GridData gdInput;
	private final static GridData gdTable;
	private final static GridData gdMemoArea;
	private final static CBorder border;
	private final static GridLayout glFormArea;
	private final static GridLayout glMemoArea;
	private final static GridLayout glButtonArea;
	private final static GridData gdButton1;
	private final static GridData gdButton2;

	static {
		gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.VERTICAL_ALIGN_CENTER);
		gdInput = new GridData(GridData.FILL_HORIZONTAL);

		gdTable = new GridData(GridData.FILL_BOTH);
		gdTable.verticalIndent = 10;

		gdMemoArea = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoArea.heightHint = 60;
		gdMemoArea.verticalIndent = -1;

		//
		glFormArea = new GridLayout();
		glFormArea.numColumns = 4;

		glMemoArea = new GridLayout();
		glMemoArea.numColumns = 2;
		glMemoArea.marginLeft = glMemoArea.marginRight = glMemoArea.marginBottom = glMemoArea.marginTop = 3;

		border = new CBorder();
		border.setColor(0x78a9bf);

		glButtonArea = new GridLayout();
		glButtonArea.numColumns = 2;

		gdButton1 = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		gdButton1.heightHint = 28;
		gdButton2 = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gdButton2.heightHint = 28;

	}
	
	@Override
	protected void afterFooterRender() {

		GridLayout gl = new GridLayout(2);
		gl.marginTop = 5;
		footerArea.setLayout(gl);

		Composite rightButtonArea = new Composite(footerArea);

		GridData gdRightArea = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END | GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END);
		GridData gdButton = new GridData(GridData.FILL_VERTICAL);
		gdRightArea.heightHint = 34;
		
		rightButtonArea.setLayoutData(gdRightArea);

		rightButtonArea.setLayout(new GridLayout(2));

		Button saveButton = new Button(rightButtonArea, JWT.APPEARANCE3);
		saveButton.setID(MaterialDetailPageProcessor.ID_Button_Save);
		saveButton.setText("  保 存  ");
		saveButton.setLayoutData(gdButton);

		Button save2Button = new Button(rightButtonArea, JWT.APPEARANCE3);
		save2Button.setID(MaterialDetailPageProcessor.ID_Button_SaveAndNew);
		save2Button.setText("  保存并新建  ");
		save2Button.setLayoutData(gdButton);

	}
	
	@Override
	protected void beforeFooterRender() {
		Composite formArea = new Composite(contentArea);
		formArea.setLayoutData(GridData.INS_FILL_BOTH);
		formArea.setLayout(glFormArea);
		formArea.setID(MaterialDetailPageProcessor.ID_Area_Property);
		
		SAsteriskLabel alabel = new SAsteriskLabel(formArea, "材料分类：");
		alabel.setLayoutData(gdLabel);
		LWComboTree categoryTree = new LWComboTree(formArea, JWT.APPEARANCE3);
		categoryTree.setID(MaterialDetailPageProcessor.ID_Tree_Category);
		categoryTree.setLayoutData(gdInput);
		
		Label label = new Label(formArea);
		label.setText("   材料编号：");
		label.setLayoutData(gdLabel);
		Text codeText = new Text(formArea, JWT.APPEARANCE3);
		codeText.setID(MaterialDetailPageProcessor.ID_Text_Code);
		codeText.setLayoutData(gdInput);
		
		alabel = new SAsteriskLabel(formArea, "材料名称：");
		alabel.setLayoutData(gdLabel);
		Text nameText = new Text(formArea, JWT.APPEARANCE3);
		nameText.setID(MaterialDetailPageProcessor.ID_Text_Name);
		nameText.setLayoutData(gdInput);
//		nameText.setRegExp(TextRegexp.ENGLISH_AND_NUMBER);
		
		alabel = new SAsteriskLabel(formArea, "材料条码：");
		alabel.setLayoutData(gdLabel);
		Text numberText = new Text(formArea, JWT.APPEARANCE3);
		numberText.setID(MaterialDetailPageProcessor.ID_Text_Number);
		numberText.setLayoutData(gdInput);
		numberText.setRegExp(TextRegexp.NUMBER);
		
		
		alabel = new SAsteriskLabel(formArea, "规格：");
		alabel.setLayoutData(gdLabel);
		Text specText = new Text(formArea, JWT.APPEARANCE3);
		specText.setID(MaterialDetailPageProcessor.ID_Text_Spec);
		specText.setLayoutData(gdInput);
//		specText.setRegExp(TextRegexp.ENGLISH_AND_NUMBER);
		
		label = new Label(formArea);
		label.setText("   库存策略：");
		label.setLayoutData(gdLabel);
		LWComboList strategyList = new LWComboList(formArea, JWT.APPEARANCE3);
		strategyList.setID(MaterialDetailPageProcessor.ID_List_InventoryStrategy);
		strategyList.setLayoutData(gdInput);
		
		label = new Label(formArea);
		label.setText("损耗率：");
		label.setLayoutData(gdLabel);
		Text lossRateText = new Text(formArea, JWT.APPEARANCE3);
		lossRateText.setID(MaterialDetailPageProcessor.ID_Text_LossRate);
		lossRateText.setText("0.00");
		lossRateText.setRegExp(TextRegexp.NUMBER_ONE_FOUR);
		lossRateText.setLayoutData(gdInput);
		
		label = new Label(formArea);
		label.setText("   平均采购价格：");
		label.setLayoutData(gdLabel);
		Text avgPriceText = new Text(formArea, JWT.APPEARANCE3);
		avgPriceText.setID(MaterialDetailPageProcessor.ID_Text_AvgBuyPrice);
		avgPriceText.setRegExp(TextRegexp.POSITIVE_DOUBLE);
		avgPriceText.setLayoutData(gdInput);
		
		alabel = new SAsteriskLabel(formArea, "标准价格：");
		// label.setText("标准价格：");
		alabel.setLayoutData(gdLabel);
		Text standardPriceText = new Text(formArea, JWT.APPEARANCE3);
		standardPriceText.setID(MaterialDetailPageProcessor.ID_Text_StandardPrice);
		standardPriceText.setRegExp(TextRegexp.POSITIVE_DOUBLE);
		standardPriceText.setLayoutData(gdInput);
		
		alabel = new SAsteriskLabel(formArea, "销售价格：");
		alabel.setLayoutData(gdLabel);
		Text salePriceText = new Text(formArea, JWT.APPEARANCE3);
		salePriceText.setID(MaterialDetailPageProcessor.ID_Text_SalePrice);
		salePriceText.setRegExp(TextRegexp.POSITIVE_DOUBLE);
		salePriceText.setLayoutData(gdInput);
		
		alabel = new SAsteriskLabel(formArea, "计划价格：");
		// label.setText("计划价格：");
		alabel.setLayoutData(gdLabel);
		Text planPriceText = new Text(formArea, JWT.APPEARANCE3);
		planPriceText.setID(MaterialDetailPageProcessor.ID_Text_PlanPrice);
		planPriceText.setRegExp(TextRegexp.POSITIVE_DOUBLE);
		planPriceText.setLayoutData(gdInput);
		
		alabel = new SAsteriskLabel(formArea, "保质期：");
		alabel.setLayoutData(gdLabel);
		Text shelfLifeText = new Text(formArea, JWT.APPEARANCE3);
		shelfLifeText.setID(MaterialDetailPageProcessor.ID_Text_ShelfLife);
		shelfLifeText.setRegExp(TextRegexp.NUMBER);
		shelfLifeText.setLayoutData(gdInput);
		
		alabel = new SAsteriskLabel(formArea, "预警天数：");
		alabel.setLayoutData(gdLabel);
		label.setLayoutData(gdLabel);
		Text warningText = new Text(formArea, JWT.APPEARANCE3);
		warningText.setID(MaterialDetailPageProcessor.ID_Text_WarningDay);
		warningText.setRegExp(TextRegexp.NUMBER);
		warningText.setLayoutData(gdInput);

	}
	
}
