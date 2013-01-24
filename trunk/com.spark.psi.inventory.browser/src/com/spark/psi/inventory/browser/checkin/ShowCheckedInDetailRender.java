package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

/**
 * 入库单明细列表视图
 */
public class ShowCheckedInDetailRender extends ExtendSimpleSheetPageRender {

	private CheckInBaseInfo info;

	public void init(Situation context) {
		super.init(context);
		this.info = ((ShowCheckedInDetailProcessor) this.processor).info;
	}

	@Override
	public STableColumn[] getColumns() {
		int columnsSize = 6;
		STableColumn[] columns = new STableColumn[columnsSize];
		columns[0] = new STableColumn(ShowCheckedInDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "材料编号");
		columns[1] = new STableColumn(ShowCheckedInDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "材料条码");
		columns[2] = new STableColumn(ShowCheckedInDetailProcessor.Columns.GoodsName.name(), 100, JWT.LEFT, "材料名称");
		columns[2].setGrab(true);
		columns[3] = new STableColumn(ShowCheckedInDetailProcessor.Columns.GoodsProperties.name(), 100, JWT.LEFT,
				"材料规格");
		columns[3].setGrab(true);
		columns[4] = new STableColumn(ShowCheckedInDetailProcessor.Columns.GoodsUnit.name(), 120, JWT.CENTER, "单位");
		if (CheckingInType.Adjustment.equals(info.getSheetType())) {
			columns[5] = new STableColumn(ShowCheckedInDetailProcessor.Columns.CheckedAmount.name(), 150, JWT.RIGHT,
					"入库金额");
		} else {
			columns[5] = new STableColumn(ShowCheckedInDetailProcessor.Columns.CheckedCount.name(), 150, JWT.RIGHT,
					"入库数量");
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof CheckInBaseInfoItem) {
			CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
			MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsId());

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
				if (CheckingInType.Adjustment.equals(info.getSheetType())) {
					return DoubleUtil.getRoundStr(item.getAmount());
				}
				return DoubleUtil.getRoundStr(item.getRealCount());
			}
		}
		return "";
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (row == 0) {
			if (column == 0) {
				Label label = new Label(baseInfoArea);
				label.setID(ShowCheckedInDetailProcessor.ID_Label_Supplier);
				label = new Label(baseInfoArea);
				label.setID(ShowCheckedInDetailProcessor.ID_Label_Store);
				if (CheckingInType.Purchase.equals(info.getSheetType())) {
					label = new Label(baseInfoArea);
					label.setID(ShowCheckedInDetailProcessor.ID_Label_PlanDate);
				}

				label = new Label(baseInfoArea);
				label.setID(ShowCheckedInDetailProcessor.ID_Label_RelatedNumber);
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
		sheetButtonArea.setID(ShowCheckedInDetailProcessor.ID_RenderButtonArea);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
	}
}
