package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.checkin.entity.CheckInSheetShowItem;

/**
 * 已处理完成的入库单列表视图
 */
public class GoodsProcessedCheckingInListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea)
				.setID(ProcessedCheckingInListProcessor.ID_TEXT_SEARCH);
		// new
		// Button(headerRightArea).setID(ProcessedCheckingInListProcessor.ID_BUTTON_SEARCH);
		//
		new LWComboList(headerLeftArea, JWT.APPEARANCE3)
				.setID(ProcessedCheckingInListProcessor.ID_COMBOLIST_DATEITEM);
		new Label(headerLeftArea).setText("  入库单数量：");
		new Label(headerLeftArea)
				.setID(ProcessedCheckingInListProcessor.ID_LABEL_CheckingIn_COUNT);
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[5];

		// 需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.LastCheckinDate.name(),
				120, JWT.CENTER, "入库日期");
		columns[0].setSortable(true);

		columns[1] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.SheetNumber.name(),
				120, JWT.CENTER, "入库单号");
		columns[1].setGrab(true);
		columns[1].setSortable(true); 

		columns[2] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.RelatedNumber.name(),
				120, JWT.CENTER, "相关单据");
		columns[2].setGrab(true);
		columns[2].setSortable(true);

		columns[3] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.StoreName.name(), 120,
				JWT.CENTER, "仓库");
		columns[3].setGrab(true);
		columns[3].setSortable(true); 

		columns[4] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.CheckinEmployees
						.name(), 120, JWT.CENTER, "确认入库");
		columns[4].setGrab(true);
		columns[4].setSortable(true);

		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
			return "";
	}

	public String getText(Object element, int columnIndex) {
		CheckInSheetShowItem item = (CheckInSheetShowItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCheckinDate());
		case 1:
			return StableUtil.toLink( SalesReturnCheckingInListProcessor.ID_ACTION_EDIT, "", item.getSheetNo());
		case 2:
			return item.getRelaBillsNo();
		case 3:
			return item.getStoreName(); 
		case 4:
			return item.getCheckinPersonName();
		default:
			return "";
		}
	}
}