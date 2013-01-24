package com.spark.psi.inventory.browser.count;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.inventory.browser.checkin.PurchaseCheckingInListProcessor;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetItem;

/**
 * 库存盘点单列表视图
 * 
 */
public class InventoryCountSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		new SSearchText2(headerRightArea).setID(PurchaseCheckingInListProcessor.ID_TEXT_SEARCH);
		Button button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setID(InventoryCountSheetListProcessor.ID_BUTTON_INVENTORYCOUNTSHEET_NEW);
		button.setText(" 新增盘点 ");
		//
		new Label(headerLeftArea).setText("盘点单数量：");
		//
		new Label(headerLeftArea)
				.setID(InventoryCountSheetListProcessor.ID_LABEL_INVENTORYCOUNTSHEET_COUNT);
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[7];

		// 需要加SheetId 获取调拨单ID
		columns[0] = new STableColumn(
				InventoryCountSheetListProcessor.Columns.StartDate.name(), 100,
				JWT.CENTER, "盘点开始日期");
		columns[0].setGrab(true);

		columns[1] = new STableColumn(
				InventoryCountSheetListProcessor.Columns.EndDate.name(), 100,
				JWT.CENTER, "盘点结束日期");
		columns[1].setGrab(true);

		columns[2] = new STableColumn(
				InventoryCountSheetListProcessor.Columns.SheetNumber.name(),
				100, JWT.CENTER, "盘点单号");
		columns[2].setGrab(true);

		columns[3] = new STableColumn(
				InventoryCountSheetListProcessor.Columns.Sheetstatus.name(),
				100, JWT.CENTER, "单据状态");
		columns[3].setGrab(true);

		columns[4] = new STableColumn(
				InventoryCountSheetListProcessor.Columns.StoreName.name(), 200,
				JWT.CENTER, "盘点仓库");
		columns[4].setGrab(true);

		columns[5] = new STableColumn(
				InventoryCountSheetListProcessor.Columns.CountProfit.name(),
				100, JWT.CENTER, "盘盈材料数量");
		columns[5].setGrab(true);

		columns[6] = new STableColumn(
				InventoryCountSheetListProcessor.Columns.CountLoss.name(), 100,
				JWT.CENTER, "盘亏材料数量");
		columns[6].setGrab(true);

		return columns;
	}

	@Override
	public STableStyle getTableStyle() {
		STableStyle sTableStyle = new STableStyle();
		sTableStyle.setSortAll(true);
		return sTableStyle;
	}

	public String getText(Object element, int columnIndex) {
		InventoryCountSheetItem item = (InventoryCountSheetItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getStartDate());
		case 1:
			return DateUtil.dateFromat(item.getEndDate());
		case 2:
			return StableUtil.toLink(InventoryCountSheetListProcessor.ID_ACTION_EDIT, "", item.getSheetNumber());
		case 3:
			return item.getSheetstatus().getName();
		case 4:
			return item.getStoreName();
		case 5:
			return String.valueOf(item.getCountProfit());
		case 6:
			return String.valueOf(item.getCountLoss());
		}
		return "";
	}
}