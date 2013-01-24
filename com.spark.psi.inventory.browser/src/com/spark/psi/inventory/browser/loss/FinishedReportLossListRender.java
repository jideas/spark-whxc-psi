package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;

public class FinishedReportLossListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();

		LWComboList deptList = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
		deptList.setID(FinishedReportLossListProcessor.ID_List_Time);
		GridData gdDeptList = new GridData();
		gdDeptList.widthHint = 100;
		deptList.setLayoutData(gdDeptList);

		Label label = new Label(headerLeftArea);
		label.setText("   报损单数量：");
		label = new Label(headerLeftArea);
		label.setID(FinishedReportLossListProcessor.ID_Label_Count);

		new SSearchText2(headerRightArea).setID(FinishedReportLossListProcessor.ID_Search);
	}
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(FinishedReportLossListProcessor.ColumnName.sheetCode.name(), 150, JWT.LEFT, "报损单号");
		columns[1] = new STableColumn(FinishedReportLossListProcessor.ColumnName.finishDate.name(), 150, JWT.CENTER, "报损日期");
		columns[2] = new STableColumn(FinishedReportLossListProcessor.ColumnName.storeName.name(), 120, JWT.LEFT, "仓库名称");
		columns[3] = new STableColumn(FinishedReportLossListProcessor.ColumnName.reporterName.name(), 150, JWT.CENTER, "填报人");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		columns[2].setSortable(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ReportLossInfo info = (ReportLossInfo)element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", info.getSheetNo());
		case 1:
			return DateUtil.dateFromat(info.getApprovalDate());
		case 2:
			return info.getStoreName();
		case 3:
			return info.getCreateor();
		}
		return null;
	}

}
