package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.base.browser.PSIActionCommon;
import com.spark.psi.publish.Action;

public class GoodsItemBomlistRender extends AbstractBoxPageRender {
	
	private Composite headerArea;

	private void renderTableInfo(Composite composite) {
		STableColumn[] columns = getColumns();
		STableStyle tableStyle = new STableStyle();
		tableStyle.setNoScroll(true);
		SActionInfo[] actions = { PSIActionCommon.getActionInfo(Action.Active.name()),
				PSIActionCommon.getActionInfo(Action.Delete.name()), PSIActionCommon.getActionInfo(Action.Submit.name()),
				PSIActionCommon.getActionInfo(Action.Detail.name()) };
		SEditTable table = new SEditTable(composite, columns, tableStyle, actions);
		table.setID(GoodsItemBomlistProcessor.ID_Table_Bom);
		GridData gdTable = GridData.INS_FILL_BOTH;
		table.setLayoutData(gdTable);
	}

	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STextEditColumn(GoodsItemBomlistProcessor.Column.BomNo.name(), 150, JWT.LEFT, "BOM单号");
		columns[1] = new STextEditColumn(GoodsItemBomlistProcessor.Column.Creator.name(), 150, JWT.CENTER, "制定人");
		columns[2] = new SNumberEditColumn(GoodsItemBomlistProcessor.Column.Approvor.name(), 150, JWT.CENTER, "审核人");
		columns[3] = new STextEditColumn(GoodsItemBomlistProcessor.Column.CreateDate.name(), 150, JWT.CENTER, "制定日期");
		columns[4] = new STextEditColumn(GoodsItemBomlistProcessor.Column.Status.name(), 100, JWT.CENTER, "处理状态");
		columns[5] = new STextEditColumn(GoodsItemBomlistProcessor.Column.Status.name(), 100, JWT.LEFT, "备注");
		columns[5].setGrab(true);
		return columns;
	}

	private void renderBaseInfo(Composite headerArea) {
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		final String tempStr = "         ";
		
		Label label = new Label(headerArea);
		label.setText("  母件名称：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsItemBomlistProcessor.ID_Label_Name);

		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("  母件编码：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsItemBomlistProcessor.ID_Label_Code);

		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("  母件条码：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsItemBomlistProcessor.ID_Label_ItemNumber);

		label = new Label(headerArea);
		label.setText("      规格：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsItemBomlistProcessor.ID_Label_Spec);

		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("计量单位：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsItemBomlistProcessor.ID_Label_Unit);

		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("   损耗率：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsItemBomlistProcessor.ID_Label_LossRate);
	}

	@Override
	protected void afterFooterRender() {
		GridLayout glFooter = new GridLayout();
		glFooter.marginTop = 5;
		footerArea.setLayout(glFooter);
		
		Button button = new Button(footerArea, JWT.APPEARANCE2);
		button.setText("  新增BOM  ");
		button.setID(GoodsItemBomlistProcessor.ID_Button_Add);
		GridData buttonGd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		buttonGd.heightHint = 29;
		button.setLayoutData(buttonGd);
	}

	@Override
	protected void beforeFooterRender() {
		headerArea = new Composite(contentArea);
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
}
