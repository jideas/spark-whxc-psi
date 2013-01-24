package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;

public class SubmittingReportLossListRender extends PSIListPageRender {
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Label label = new Label(headerLeftArea);
		label.setText("报损单数量：");
		label = new Label(headerLeftArea);
		label.setID(SubmittingReportLossListProcessor.ID_Label_Count);
		
		Button newBtn = new Button(footerLeftArea, JWT.APPEARANCE2);
		newBtn.setText(" 新增报损单 ");
		newBtn.setID(SubmittingReportLossListProcessor.ID_Button_New);
//		Button submitBtn = new Button(footerRightArea, JWT.APPEARANCE3);
//		submitBtn.setText("  提交  ");
//		submitBtn.setID(SubmittingReportLossListProcessor.ID_Button_Submit);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(SubmittingReportLossListProcessor.ColumnName.sheetCode.name(), 150, JWT.LEFT, "单据编号");
		columns[1] = new STableColumn(SubmittingReportLossListProcessor.ColumnName.storeName.name(), 150, JWT.CENTER, "仓库名称");
		columns[2] = new STableColumn(SubmittingReportLossListProcessor.ColumnName.createDate.name(), 120, JWT.CENTER, "填报日期");
		columns[3] = new STableColumn(SubmittingReportLossListProcessor.ColumnName.status.name(), 150, JWT.CENTER, "状态");
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
			return info.getStoreName();
		case 2:
			return DateUtil.dateFromat(info.getCreateDate());
		case 3:
			return info.getStatus().getTitle();
		}
		return null;
	}

	@Override
	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
//		tableStyle.setSelectionMode(SSelectionMode.Multi);
		tableStyle.setPageAble(false);
		return tableStyle;
	}
	
	

}
