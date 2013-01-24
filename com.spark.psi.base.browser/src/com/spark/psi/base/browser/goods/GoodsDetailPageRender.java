package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboTree;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.pages.AbstractBoxPageRender;

public class GoodsDetailPageRender extends AbstractBoxPageRender {

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
		gdRightArea.heightHint = 34; 
		
		GridData gdButton = new GridData(GridData.FILL_VERTICAL);
		
		rightButtonArea.setLayoutData(gdRightArea);
		rightButtonArea.setLayout(new GridLayout(2));

		Button saveButton = new Button(rightButtonArea, JWT.APPEARANCE3);
		saveButton.setID(GoodsDetailPageProcessor.ID_Button_Save);
		saveButton.setText("  保 存  ");
		saveButton.setLayoutData(gdButton);

		Button save2Button = new Button(rightButtonArea, JWT.APPEARANCE3);
		save2Button.setID(GoodsDetailPageProcessor.ID_Button_SaveAndNew);
		save2Button.setText("  保存并新建  ");
		save2Button.setLayoutData(gdButton);
		
	}

	@Override
	protected void beforeFooterRender() {
		Composite formArea = new Composite(contentArea);
		formArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		formArea.setLayout(glFormArea);
		
//		Composite busInfoArea = new Composite(formArea);
//		GridData gdBusInfo = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
//		gdBusInfo.horizontalSpan = 4;
//		gdBusInfo.heightHint = 23;
//		busInfoArea.setLayoutData(gdBusInfo);
//		GridLayout glBusInfo = new GridLayout(5);
//		glBusInfo.horizontalSpacing = 0;
//		busInfoArea.setLayout(glBusInfo);
//		busInfoArea.setID(GoodsDetailPageProcessor.ID_Area_BusInfo);

		new SAsteriskLabel(formArea, "商品分类：").setLayoutData(gdLabel);
		LWComboTree comboTree = new LWComboTree(formArea, JWT.APPEARANCE3);
		comboTree.setLayoutData(gdInput);
		comboTree.setID(GoodsDetailPageProcessor.ID_List_Category);
		comboTree.setHint("请选择最末一级分类");

		Label label = new Label(formArea);
		label.setText("    编号：");
		label.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gdInput);
		text.setID(GoodsDetailPageProcessor.ID_Text_Code);

		new SAsteriskLabel(formArea, "商品名称：").setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gdInput);
		text.setID(GoodsDetailPageProcessor.ID_Text_Name);
//		text.setRegExp(TextRegexp.ENGLISH_AND_NUMBER);

		label = new Label(formArea);
		label.setText("统一价格：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gdInput);
		text.setID(GoodsDetailPageProcessor.ID_Text_Price);
		text.setRegExp(TextRegexp.POSITIVE_DOUBLE_LIMIT);
		
		new SAsteriskLabel(formArea, "保 质 期：").setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gdInput);
		text.setID(GoodsDetailPageProcessor.ID_Text_ShelfLife);
		text.setRegExp(TextRegexp.NUMBER);

		new SAsteriskLabel(formArea, "预警天数：").setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setLayoutData(gdInput);
		text.setID(GoodsDetailPageProcessor.ID_Text_AlarmDays);
		text.setRegExp(TextRegexp.POSITIVE_DOUBLE_LIMIT);
		
//		label = new Label(formArea);
//		label.setText("联营商品：");
//		label.setLayoutData(gdLabel);
//		Button jointCheck = new Button(formArea, JWT.CHECK);
//		jointCheck.setLayoutData(gdInput);
//		jointCheck.setID(GoodsDetailPageProcessor.ID_Check_JointGoods);
//
//		label = new Label(formArea);
//		label.setText("供 应 商：");
//		label.setLayoutData(gdLabel);
//		text = new Text(formArea, JWT.BUTTON | JWT.APPEARANCE3);
//		text.setLayoutData(gdInput);
//		text.setID(GoodsDetailPageProcessor.ID_Text_Supplier);
//		text.setButtonVisible(true);

		//
		Composite tableArea = new Composite(contentArea);
		tableArea.setLayoutData(gdTable);
		tableArea.setID(GoodsDetailPageProcessor.ID_Area_Table);
		tableArea.setLayout(new FillLayout());
		//
		Composite memoArea = new Composite(contentArea);
		memoArea.setBorder(border);
		memoArea.setLayoutData(gdMemoArea);
		memoArea.setLayout(glMemoArea);

		label = new Label(memoArea);
		label.setText("备注：");
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		Text memoText = new Text(memoArea, JWT.APPEARANCE3 | JWT.MULTI
				| JWT.V_SCROLL | JWT.WRAP);
		memoText.setLayoutData(GridData.INS_FILL_BOTH);
		memoText.setID(GoodsDetailPageProcessor.ID_Text_Memo);
	}


}
