package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;

/**
 * 库存调拨单编辑和查看界面视图
 */
@Deprecated
public class AllocateSheetDetailRender extends SimpleSheetPageRender {

	private InventoryAllocateSheetInfo info;

	public void init(Situation context) {
		super.init(context);
		if (CheckIsNull.isNotEmpty(this.getArgument())) {
			String sheetId = (String) this.getArgument();
			info = (InventoryAllocateSheetInfo) this.getArgument2();
		}

	}

	@Override
	public STableColumn[] getColumns() {
		int length = 7;
		if (null != info
				&& (InventoryAllocateStatus.AllocateOut.equals(info.getStatus()) || InventoryAllocateStatus.AllocateIn
						.equals(info.getStatus()))) {
			length = 6;
		}
		//创建列
		STableColumn[] columns = new STableColumn[length];
		columns[0] = new STableColumn(AllocateSheetDetailProcessor.Columns.code.name(), 150, JWT.LEFT, "编号");
		columns[1] = new STableColumn(AllocateSheetDetailProcessor.Columns.number.name(), 150, JWT.LEFT, "条码");
		columns[2] = new STableColumn(AllocateSheetDetailProcessor.Columns.name.name(), 100, JWT.LEFT, "材料名称");
		columns[3] = new STableColumn(AllocateSheetDetailProcessor.Columns.spec.name(), 100, JWT.LEFT, "材料规格");
		columns[4] = new STableColumn(AllocateSheetDetailProcessor.Columns.unit.name(), 120, JWT.CENTER, "单位");
		if (null != info
				&& (InventoryAllocateStatus.AllocateOut.equals(info.getStatus()) || InventoryAllocateStatus.AllocateIn
						.equals(info.getStatus()))) {
			columns[5] =
		        new SNumberEditColumn(AllocateSheetDetailProcessor.Columns.allocateCount.name(), 150, JWT.RIGHT, "调拨数量");;
		}
		columns[5] =
	        new STableColumn(AllocateSheetDetailProcessor.Columns.availableCount.name(), 150, JWT.RIGHT, "可用库存");
		columns[6] =
		        new SNumberEditColumn(AllocateSheetDetailProcessor.Columns.allocateCount.name(), 150, JWT.RIGHT, "调拨数量");
		//自适应
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof AllocateShowItem) {
			AllocateShowItem item = (AllocateShowItem) element;
			switch(columnIndex){
			case 0:
				return item.getStockItemCode();
			case 1:
				return item.getStockItemNumber();
			case 2:
				return item.getStockItemName();
			case 3:
				return item.getStockSpec();
			case 4:
				return item.getStockItemUnit();
			case 5:
				if (null != info
						&& (InventoryAllocateStatus.AllocateOut.equals(info.getStatus()) || InventoryAllocateStatus.AllocateIn
								.equals(info.getStatus()))) {
					DoubleUtil.getRoundStr(item.getAllocateCount());
				} else {
					return DoubleUtil.getRoundStr(item.getAvailableCount());
				}
			case 6:
				return DoubleUtil.getRoundStr(item.getAllocateCount());
			}
		}
		return null;
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (0 == row) {
			if (0 == column) {
				baseInfoArea
						.setID(AllocateSheetDetailProcessor.ID_HeaderLeftArea);
			} else {
				baseInfoArea
						.setID(AllocateSheetDetailProcessor.ID_HeaderRightArea);
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
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		sheetButtonArea.setID(AllocateSheetDetailProcessor.ID_FooterRightArea);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		if (info == null
				|| info.getStatus() == InventoryAllocateStatus.Submitting
				|| info.getStatus() == InventoryAllocateStatus.Rejected) {
			Button selectGoodsButton = new Button(tableButtonArea,
					JWT.APPEARANCE2);
			selectGoodsButton
					.setID(AllocateSheetDetailProcessor.ID_Button_SelectGoods);
			selectGoodsButton.setText("添加材料");
		}
	}

}
