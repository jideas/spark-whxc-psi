package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;

/**
 * 销售人员选择界面视图
 */
public class SalesmanSelectRender extends PSIListPageRender {

	protected void afterFooterRender() {
		super.afterFooterRender();

		new SSearchText2(headerRightArea)
				.setID(SalesmanSelectProcessor.ID_Text_Search);

		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 确定分配 ");
		button.setID(SalesmanSelectProcessor.ID_Button_Confirm);

		new Label(footerRightArea).setText(" ");

		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" 取消分配 ");
		button.setID(SalesmanSelectProcessor.ID_Button_Cancel);

	}

	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setSelectionMode(SSelectionMode.Single);
		tableStyle.setPageAble(false);
		return tableStyle;
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[2];
		columns[0] = new STableColumn(
				SalesmanSelectProcessor.Columns.EmployeeName.name(), 120,
				JWT.LEFT, "员工姓名");
		columns[1] = new STableColumn(
				SalesmanSelectProcessor.Columns.Department.name(), 200,
				JWT.LEFT, "部门");
		columns[1].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((EmployeeItem) element).getName();
		case 1:
			return ((EmployeeItem) element).getDepartmentName();
		}
		return null;
	}

}
