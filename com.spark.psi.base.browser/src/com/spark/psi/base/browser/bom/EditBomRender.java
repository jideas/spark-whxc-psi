package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.psi.publish.Action;

public class EditBomRender extends AbstractBoxPageRender {

	private final static GridData gdButton1;
	private final static GridData gdButton2;

	static {
		gdButton1 = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		gdButton1.heightHint = 28;
		gdButton2 = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gdButton2.heightHint = 28;
	}

	private void renderRemarkInfo(Composite tableBottomArea) {
		GridData gdTableBottom = new GridData(GridData.FILL_HORIZONTAL);
		// gdTableBottom.heightHint = 85;
		gdTableBottom.verticalIndent = -1;
		GridLayout glTableBottom = new GridLayout();
		glTableBottom.verticalSpacing = 10;
		glTableBottom.marginTop = 5;
		glTableBottom.marginBottom = 5;
		glTableBottom.marginLeft = 10;
		glTableBottom.marginRight = 10;
		glTableBottom.numColumns = 3;
		tableBottomArea.setLayout(glTableBottom);
		tableBottomArea.setLayoutData(gdTableBottom);
		tableBottomArea.setBackground(Color.COLOR_WHITE);
		tableBottomArea.setBorder(new CBorder(1, JWT.BORDER_STYLE_SOLID, 0x78a9bf));

		Button button = new Button(tableBottomArea, JWT.APPEARANCE2);
		button.setText(" 选择子件 ");
		button.setID(EditBomProcessor.ID_Button_AddMaterial);
		GridData gdSubBtn = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gdSubBtn.heightHint = 28;
		button.setLayoutData(gdSubBtn);

		Label label = new Label(tableBottomArea);
		label.setText("  备注：");
		GridData gdMemo = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END);
		gdMemo.verticalIndent = 3;
		label.setLayoutData(gdMemo);

		Text memoText = new Text(tableBottomArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.V_SCROLL);
		memoText.setID(EditBomProcessor.ID_Text_Remark);
		GridData gdMemoText = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoText.heightHint = 50;
		memoText.setLayoutData(gdMemoText);
	}

	private void renderTableInfo(Composite composite) {
		GridLayout gl = new GridLayout(1);
		gl.marginTop = 0;
		gl.verticalSpacing = 0;
		composite.setLayout(gl);

		STableColumn[] columns = getColumns();
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setNoScroll(true);
		SEditTable table = new SEditTable(composite, columns, tableStyle, getActions());
		table.setID(EditBomProcessor.ID_Table_Bom);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		composite = new Composite(composite);
		renderRemarkInfo(composite);
	}

	protected SActionInfo[] getActions() {
		SActionInfo[] actions = { new SActionInfo(Action.Delete.name(), "删除", null, null) };
		return actions;
	}

	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[9];
		columns[0] = new STableColumn(EditBomProcessor.Column.goodsCode.name(), 150, JWT.LEFT, "子件编码");
		columns[1] = new STableColumn(EditBomProcessor.Column.goodsNo.name(), 150, JWT.LEFT, "子件条码");
		columns[2] = new STableColumn(EditBomProcessor.Column.goodsName.name(), 100, JWT.LEFT, "子件名称");
		columns[3] = new STableColumn(EditBomProcessor.Column.goodsSpec.name(), 100, JWT.LEFT, "子件规格");
		columns[4] = new STableColumn(EditBomProcessor.Column.goodsUnit.name(), 100, JWT.CENTER, "计量单位");
		columns[5] = new SNumberEditColumn(EditBomProcessor.Column.count.name(), 100, JWT.RIGHT, "基本数量");
		columns[6] = new STableColumn("officialLossRate", 100, JWT.RIGHT, "标准损耗率");
		columns[7] = new SNumberEditColumn(EditBomProcessor.Column.lossRate.name(), 100, JWT.RIGHT, "子件损耗率");
		columns[8] = new SNumberEditColumn(EditBomProcessor.Column.realCount.name(), 100, JWT.RIGHT, "需求数量");
		columns[2].setGrab(true);

		((SNumberEditColumn) columns[5]).setDecimal(2);
		((SNumberEditColumn) columns[7]).setDecimal(4);
		((SNumberEditColumn) columns[8]).setDecimal(2); 

		return columns;
	}

	protected void renderBaseInfo(Composite headerArea) {
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		final String tempStr = "         ";

		Label label = new Label(headerArea);
		label.setText("  母件名称：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(EditBomProcessor.ID_Label_Name);

		new Label(headerArea).setText(tempStr);

		label = new Label(headerArea);
		label.setText("  母件编码：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(EditBomProcessor.ID_Label_Code);

		new Label(headerArea).setText(tempStr);

		label = new Label(headerArea);
		label.setText("  母件条码：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(EditBomProcessor.ID_Label_ItemNumber);

		label = new Label(headerArea);
		label.setText("      规格：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(EditBomProcessor.ID_Label_Spec);

		new Label(headerArea).setText(tempStr);

		label = new Label(headerArea);
		label.setText("计量单位：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(EditBomProcessor.ID_Label_Unit);

		new Label(headerArea).setText(tempStr);

		label = new Label(headerArea);
		label.setText("   损耗率：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(EditBomProcessor.ID_Label_LossRate);
	}

	@Override
	protected void afterFooterRender() {
		GridLayout glFooter = new GridLayout();
		glFooter.numColumns = 2;
		glFooter.marginTop = 5;
		footerArea.setLayout(glFooter);

		renderButton(footerArea);

		GridLayout gl = new GridLayout();
		gl.numColumns = footerArea.getChildrenCount();
		this.footerArea.setLayout(gl);
		Control[] children = this.footerArea.getChildren();
		if (children != null) {
			for (int i = 0; i < children.length; i++) {
				if (i == 0) {
					children[0].setLayoutData(gdButton1);
				} else {
					children[i].setLayoutData(gdButton2);
				}
			}
		}
	}

	@Override
	protected void beforeFooterRender() {
		Composite headerArea = new Composite(contentArea);
		GridData gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		gdHeader.heightHint = 48;

		GridLayout glHeader = new GridLayout();
		glHeader.numColumns = 8;
		glHeader.verticalSpacing = 10;

		headerArea.setLayoutData(gdHeader);
		headerArea.setLayout(glHeader);

		renderBaseInfo(headerArea);

		renderTableInfo(contentArea);

	}

	protected void renderButton(Composite buttonArea) {

		Button button = new Button(footerArea, JWT.APPEARANCE3);
		button.setText("  保  存  ");
		button.setID(EditBomProcessor.ID_Button_Save);
		button.setLayoutData(gdButton2);

		button = new Button(footerArea, JWT.APPEARANCE3);
		button.setText("  提  交  ");
		button.setID(EditBomProcessor.ID_Button_Submit);
		button.setLayoutData(gdButton2);
	}
}
