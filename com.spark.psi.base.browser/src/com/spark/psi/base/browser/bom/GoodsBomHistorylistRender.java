package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.AbstractBoxPageRender;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;

public class GoodsBomHistorylistRender extends AbstractBoxPageRender {

	private STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STextEditColumn(GoodsBomHistorylistProcessor.Column.BomNo.name(), 150, JWT.CENTER, "BOM单号");
		columns[1] = new STextEditColumn(GoodsBomHistorylistProcessor.Column.Creator.name(), 100, JWT.CENTER, "制定人");
		columns[2] = new SNumberEditColumn(GoodsBomHistorylistProcessor.Column.Approvor.name(), 100, JWT.CENTER, "审核人");
		columns[3] = new STextEditColumn(GoodsBomHistorylistProcessor.Column.IneffectDate.name(), 100, JWT.CENTER, "启用日期");
		columns[4] = new STextEditColumn(GoodsBomHistorylistProcessor.Column.Outeffector.name(), 100, JWT.CENTER, "启用人");
		columns[5] = new STextEditColumn(GoodsBomHistorylistProcessor.Column.OuteffectDate.name(), 100, JWT.CENTER, "失效日期");
		columns[6] = new STextEditColumn(GoodsBomHistorylistProcessor.Column.Outeffector.name(), 100, JWT.CENTER, "取消人");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		columns[5].setGrab(true);
		columns[6].setGrab(true);
		return columns;
	}

	private void renderTableInfo(Composite composite) {
		STableColumn[] columns = getColumns();
		STableStyle tableStyle = new STableStyle();
		tableStyle.setNoScroll(true);
		STable table = new STable(composite, columns, tableStyle);
		table.setID(EditBomProcessor.ID_Table_Bom);
		GridData gdTable = GridData.INS_FILL_BOTH;
		table.setLayoutData(gdTable);
	}

	protected void renderBaseInfo(Composite headerArea) {
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		final String tempStr = "         ";
		
		Label label = new Label(headerArea);
		label.setText("  母件名称：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsBomHistorylistProcessor.ID_Label_Name);

		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("  母件编码：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsBomHistorylistProcessor.ID_Label_Code);
		
		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("  母件条码：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsBomHistorylistProcessor.ID_Label_ItemNumber);

		label = new Label(headerArea);
		label.setText("      规格：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsBomHistorylistProcessor.ID_Label_Spec);

		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("  计量单位：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsBomHistorylistProcessor.ID_Label_Unit);

		new Label(headerArea).setText(tempStr);
		
		label = new Label(headerArea);
		label.setText("   损耗率：");
		label.setLayoutData(gdLabel);
		new Label(headerArea).setID(GoodsBomHistorylistProcessor.ID_Label_LossRate);
	}

	@Override
	protected void afterFooterRender() {
		
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
	
}
