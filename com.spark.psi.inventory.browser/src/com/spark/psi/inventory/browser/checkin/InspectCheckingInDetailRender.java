 package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.entity.CheckingInInfo;

/**
 * 入库单明细列表视图
 */
public class InspectCheckingInDetailRender extends ExtendSimpleSheetPageRender {

	private CheckingInInfo info;

	public void init(Situation context) {
		super.init(context);
		this.info = ((InspectCheckingInDetailProcessor) this.processor).info;
	}

	@Override
	public STableColumn[] getColumns() {
		int columnsSize = 9;
		if (isStopedOrFinished()) {
			columnsSize = 6;
		}
		STableColumn[] columns = new STableColumn[columnsSize];
		columns[0] = new STableColumn(InspectCheckingInDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "材料编号");
		columns[1] = new STableColumn(InspectCheckingInDetailProcessor.Columns.GoodsNo.name(), 150, JWT.LEFT, "材料条码");
		columns[2] = new STableColumn(InspectCheckingInDetailProcessor.Columns.GoodsName.name(), 100, JWT.LEFT, "材料名称");
		columns[2].setGrab(true);
		columns[3] = new STableColumn(InspectCheckingInDetailProcessor.Columns.GoodsProperties.name(), 100, JWT.LEFT, "材料规格");
		columns[3].setGrab(true);
		columns[4] = new STableColumn(InspectCheckingInDetailProcessor.Columns.GoodsUnit.name(), 120, JWT.CENTER, "单位");
		if (!isStopedOrFinished()) {
			if (null != info && CheckingInType.Return.equals(info.getType())) {
				columns[5] = new STableColumn(InspectCheckingInDetailProcessor.Columns.CheckingCount.name(), 150, JWT.RIGHT, "销售退货数量");
			} else {
				columns[5] = new STableColumn(InspectCheckingInDetailProcessor.Columns.CheckingCount.name(), 150, JWT.RIGHT, "采购数量");
			}
			columns[6] = new STableColumn(InspectCheckingInDetailProcessor.Columns.CheckedCount.name(), 150, JWT.RIGHT, "已入库数量");
			columns[7] = new STableColumn(InspectCheckingInDetailProcessor.Columns.InspectCount.name(), 150, JWT.RIGHT, "待检数量");
			columns[8] = new SNumberEditColumn(InspectCheckingInDetailProcessor.Columns.CheckCount.name(), 150, JWT.RIGHT, "本次操作数量");
		} else {
			columns[5] = new STableColumn(InspectCheckingInDetailProcessor.Columns.CheckedCount.name(), 150, JWT.RIGHT, "入库数量");
		}
		return columns;
	}

	private boolean isStopedOrFinished() {
		return null != info
				&& (CheckingInStatus.Stop.equals(info.getStatus()) || CheckingInStatus.Finish.equals(info.getStatus()));
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof CheckingGoodsItem) {
			CheckingGoodsItem item = (CheckingGoodsItem) element;
			MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsItemId());
			if (!isStopedOrFinished()) {
				switch (columnIndex) {
				case 0:
					return goods.getBaseInfo().getCode();
				case 1:
					return goods.getItemData().getMaterialNo();
				case 2:
					return goods.getBaseInfo().getName();
				case 3:
					return goods.getItemData().getPropertiesWithoutUnit();
				case 4:
					return goods.getItemData().getPropertyValues()[0];
				case 5:
					return DoubleUtil.getRoundStr(item.getCheckingCount());
				case 6:
					return DoubleUtil.getRoundStr(item.getCheckedCount());
				case 7:
					return DoubleUtil.getRoundStr(item.getInspectCount());
				case 8:
					return DoubleUtil.getRoundStr(item.getCheckCount());
				}
			} else {
				switch (columnIndex) {
				case 0:
					return goods.getBaseInfo().getCode();
				case 1:
					return goods.getItemData().getMaterialNo();
				case 2:
					return goods.getBaseInfo().getName();
				case 3:
					return goods.getItemData().getPropertiesWithoutUnit();
				case 4:
					return goods.getItemData().getPropertyValues()[0];
				case 5:
					return DoubleUtil.getRoundStr(item.getCheckedCount());
				}
			}
		}
		return "";
	} 

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (row == 0) {
			if (column == 0) {
				Label label = new Label(baseInfoArea);
				label.setID(InspectCheckingInDetailProcessor.ID_Label_Supplier);
				label = new Label(baseInfoArea);
				label.setID(InspectCheckingInDetailProcessor.ID_Label_Store);
				if (CheckingInType.Purchase.equals(info.getType())) {
					label = new Label(baseInfoArea);
					label.setID(InspectCheckingInDetailProcessor.ID_Label_PlanDate);
				}

				label = new Label(baseInfoArea);
				label.setID(InspectCheckingInDetailProcessor.ID_Label_RelatedNumber);
			} else if (column == 1) {
				addProcessing(baseInfoArea);
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
		sheetButtonArea.setID(InspectCheckingInDetailProcessor.ID_RenderButtonArea);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
	}
}
