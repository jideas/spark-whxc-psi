package com.spark.psi.base.browser.station;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.psi.publish.base.station.entity.StationManagerItem;

public class StationManagerSelectRender extends BaseFormPageRender
		implements SLabelProvider {

	public String getText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((StationManagerItem) element).getName();
		case 1:
			return ((StationManagerItem) element).getDeptName();
		}
		return "";
	}

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(new FillLayout()); //
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.Single);

		STableColumn[] columns = new STableColumn[2];
		columns[0] = new STableColumn("name", 150, JWT.LEFT, "员工姓名");
		columns[1] = new STableColumn("department", 200, JWT.CENTER, "部门");
		columns[1].setGrab(true);
		SEditTable table = new SEditTable(formArea, columns, tableStyle, null);
		table.setID(StationManagerSelectProcessor.ID_Table);
		table.setLayoutData(GridData.INS_FILL_BOTH);
		table.setLabelProvider(this);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button selectButton = new Button(buttonArea, JWT.APPEARANCE3);
		selectButton.setID(StationManagerSelectProcessor.ID_Button_Select);
		selectButton.setText(" 确定指定 ");

		Button cancelButton = new Button(buttonArea, JWT.APPEARANCE3);
		cancelButton.setID(StationManagerSelectProcessor.ID_Button_Cancel);
		cancelButton.setText(" 取消指定  ");
	}

	public String getToolTipText(Object element, int columnIndex) {
		return null;
	}

	public Color getBackground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getForeground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}


}
