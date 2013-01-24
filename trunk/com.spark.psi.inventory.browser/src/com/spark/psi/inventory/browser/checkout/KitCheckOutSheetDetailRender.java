package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.psi.inventory.browser.checkin.ExtendSimpleSheetPageRender;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfoItem;

public class KitCheckOutSheetDetailRender extends ExtendSimpleSheetPageRender {// SimpleSheetPageRender
	// {

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(KitCheckOutSheetDetailProcessor.Columns.kitName.name(), 0, JWT.LEFT, "物品名称");
		columns[1] = new STableColumn(KitCheckOutSheetDetailProcessor.Columns.kitDescription.name(), 0, JWT.LEFT, "物品描述");
		columns[2] = new STableColumn(KitCheckOutSheetDetailProcessor.Columns.unit.name(), 110, JWT.CENTER, "单位");
		columns[3] = new STableColumn(KitCheckOutSheetDetailProcessor.Columns.count.name(), 110, JWT.RIGHT, "出库数量");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof String) {
			return "";
		}
		CheckOutBaseInfoItem item = (CheckOutBaseInfoItem) element;
		switch (columnIndex) {
		case 0:
			return item.getGoodsName();
		case 1:
			return item.getGoodsSpec();
		case 2:
			return item.getUnit();
		case 3:
			return String.valueOf(item.getRealCount());// DoubleUtil.getRoundStr(Double.valueOf(item.getCount()));
		default:
			return "";
		}
	}

	@Override
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
		case 3:
			return 0;
		default:
			return -1;
		}
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (row == 0) {
			if (column == 0) {
				Label label = new Label(baseInfoArea);
				label.setID(KitCheckOutSheetDetailProcessor.ID_Label_Store);
				label = new Label(baseInfoArea);
				label.setID(KitCheckOutSheetDetailProcessor.ID_Label_Source);
			}
		} else if (row == 1) {
			if (1 == row) {
				if (0 == column) {
					new Label(baseInfoArea).setText("提货人：");
					Label t = new Label(baseInfoArea);
					t.setID(KitCheckOutSheetDetailProcessor.ID_Text_DeliveryPerson);
					new Label(baseInfoArea).setText("    提货单位：");
					t = new Label(baseInfoArea);
					t.setID(KitCheckOutSheetDetailProcessor.ID_Text_DeliveryUnit);

					new Label(baseInfoArea).setText("    凭证号：");
					t = new Label(baseInfoArea);
					t.setID(KitCheckOutSheetDetailProcessor.ID_Text_VoucherNumber);

				} else {
					addProcessing(baseInfoArea);
				}

			}
		}
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {

	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {

	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 2;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {

	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {

	}
}