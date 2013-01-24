package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;

public class SelectedKitsItemListRender extends PSIListPageRender {
	
	@Override
	protected void beforeTableRender() {		
		Composite headerArea = new Composite(contentArea);
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		headerArea.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 25;
		headerArea.setLayoutData(gd);		
		GridData gd1 = new GridData();
		gd1.heightHint = 25;
		Label label = new Label(headerArea);
		label.setText("已选择");
		label.setLayoutData(gd1);
		label = new Label(headerArea);
		label.setText("0");
		label.setLayoutData(gd1);
		label.setID(SelectedKitsItemListProcessor.ID_Label_SelectedCount);
		label.setForeground(Color.COLOR_RED);
		label = new Label(headerArea);
		label.setText("件材料");
		label.setLayoutData(gd);
		Label lblClear = new Label(headerArea);
		lblClear.setID(SelectedKitsItemListProcessor.ID_Label_Clear);
		lblClear.setText("清空");
		lblClear.setCursor(Cursor.HAND);
		lblClear.setFont(new Font(9, "宋体", JWT.FONT_STYLE_UNDERLINE));
		lblClear.setForeground(Color.COLOR_BLUE);
		lblClear.setLayoutData(GridData.INS_CENTER_VERTICAL);
	}
	
	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setSelectionMode(SSelectionMode.Row);
		tableStyle.setPageAble(false);
		return tableStyle;
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn("name", 100, JWT.LEFT, "材料名称");
		columns[1] = new STableColumn("desc", 200, JWT.LEFT,"材料规格");
		columns[2] = new STableColumn("unit", 30, JWT.CENTER,"单位");
		return columns;
	}

	public String getText(Object element, int columnIndex) {
		Kit item = (Kit) element;
		switch (columnIndex) {
			case 0:
				return item.getKitName();
			case 1:
				return item.getKitDesc();
			case 2:
				return item.getKitUnit();
		}
		return "";
	}
}