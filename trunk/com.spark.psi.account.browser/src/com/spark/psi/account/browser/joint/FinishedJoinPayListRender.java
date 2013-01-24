package com.spark.psi.account.browser.joint;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementItem;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;

public class FinishedJoinPayListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("单据数量：");
		new Label(headerLeftArea).setID(FinishedJoinPayListProcessor.ID_Label_Count);
		new SSearchText2(headerRightArea).setID(FinishedJoinPayListProcessor.ID_Search);
	}
	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.sheetNo.name(), 150, 
				JWT.LEFT, FinishedJoinPayListProcessor.ColumnName.sheetNo.getTitle());
		columns[1] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.supplierName.name(), 100, 
				JWT.LEFT, FinishedJoinPayListProcessor.ColumnName.supplierName.getTitle());
		columns[2] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.beginDate.name(), 100, 
				JWT.CENTER, FinishedJoinPayListProcessor.ColumnName.beginDate.getTitle());
		columns[3] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.endDate.name(), 100, 
				JWT.CENTER, FinishedJoinPayListProcessor.ColumnName.endDate.getTitle());
		columns[4] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.saleAmount.name(), 120, 
				JWT.RIGHT, FinishedJoinPayListProcessor.ColumnName.saleAmount.getTitle());
		columns[5] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.percentageAmount.name(), 120, 
				JWT.RIGHT, FinishedJoinPayListProcessor.ColumnName.percentageAmount.getTitle());
		columns[6] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.adjustAmount.name(), 120, 
				JWT.RIGHT, FinishedJoinPayListProcessor.ColumnName.adjustAmount.getTitle());
		columns[7] = new STableColumn(FinishedJoinPayListProcessor.ColumnName.status.name(), 120, 
				JWT.CENTER, FinishedJoinPayListProcessor.ColumnName.status.getTitle());
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		columns[2].setSortable(true);
		columns[3].setSortable(true);
		columns[4].setSortable(true);
		columns[5].setSortable(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		JointSettlementItem item = (JointSettlementItem)element;
		switch(columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", item.getSheetNo());
		case 1:
			return item.getShortName();
		case 2:
			return DateUtil.dateFromat(item.getBeginDate());
		case 3:
			return DateUtil.dateFromat(item.getEndDate());
		case 4:
			return DoubleUtil.getRoundStr(item.getSalesAmount());
		case 5:
			return DoubleUtil.getRoundStr(item.getPercentageAmount());
		case 6:
			return DoubleUtil.getRoundStr(item.getAdjustAmount());
		case 7:
			return item.getStatus().getName();
		}
		return null;
	}

}
