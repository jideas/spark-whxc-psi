package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;
import com.spark.common.components.table.edit.SEditTableStyle;

public class SubmittedReportLossListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Label label = new Label(headerLeftArea);
		label.setText("报损单数量：");
		label = new Label(headerLeftArea);
		label.setID(SubmittingReportLossListProcessor.ID_Label_Count);
	}
	
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(SubmittedReportLossListProcessor.ColumnName.submittingDate.name(), 150, JWT.CENTER, "申请日期");
		columns[1] = new STableColumn(SubmittedReportLossListProcessor.ColumnName.sheetCode.name(), 150, JWT.LEFT, "报损单号");
		columns[2] = new STableColumn(SubmittedReportLossListProcessor.ColumnName.storeName.name(), 120, JWT.LEFT, "仓库名称");
		columns[3] = new STableColumn(SubmittedReportLossListProcessor.ColumnName.reporterName.name(), 150, JWT.CENTER, "填报人");
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
			return DateUtil.dateFromat(info.getApplyDate());
		case 1:
			return StableUtil.toLink(Action.Detail.name(), "", info.getSheetNo());
		case 2:
			return info.getStoreName();
		case 3:
			return DateUtil.dateFromat(info.getCreateDate());
		}
		return null;
	}

	public STableStyle getTableStyle() {
		SEditTableStyle style = new SEditTableStyle();
		style.setPageAble(false);
		return style;
	}
}
