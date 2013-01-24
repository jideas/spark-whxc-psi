package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

/**
 * 入库单明细列表视图
 */
public class RealGoodsCheckedInDetailRender extends ExtendSimpleSheetPageRender {

	private CheckInBaseInfo info;

	public void init(Situation context) {
		super.init(context);
		this.info = ((RealGoodsCheckedInDetailProcessor) this.processor).info;
	}

	@Override
	public STableColumn[] getColumns() {
		int columnsSize = 6;
		STableColumn[] columns = new STableColumn[columnsSize];
		columns[0] = new STableColumn(RealGoodsCheckedInDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "商品编号");
		columns[1] = new STableColumn(RealGoodsCheckedInDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "商品条码");
		columns[2] = new STableColumn(RealGoodsCheckedInDetailProcessor.Columns.GoodsName.name(), 100, JWT.LEFT, "商品名称");
		columns[2].setGrab(true);
		columns[3] = new STableColumn(RealGoodsCheckedInDetailProcessor.Columns.GoodsProperties.name(), 100, JWT.LEFT, "商品规格");
		columns[3].setGrab(true);
		columns[4] = new STableColumn(RealGoodsCheckedInDetailProcessor.Columns.GoodsUnit.name(), 120, JWT.CENTER, "单位");
		columns[5] = new STableColumn(RealGoodsCheckedInDetailProcessor.Columns.CheckedCount.name(), 150, JWT.RIGHT, "入库数量");
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof CheckInBaseInfoItem) {
			CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsCode();
			case 1:
				return item.getGoodsNo();
			case 2:
				return item.getGoodsName();
			case 3:
				return item.getGoodsSpec();
			case 4:
				return item.getUnit();
			case 5:
				return DoubleUtil.getRoundStr(item.getRealCount());
			}
		}
		return "";
	}

	/**
	 * 
	 */
	public int getDecimal(Object element, int columnIndex) {
		if (element instanceof CheckInBaseInfoItem) {
			CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
			switch (columnIndex) {
			case 4:
				return item.getScale();
			case 5:
				return item.getScale();
			case 6:
				return item.getScale();
			}
		}
		return -1;
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (row == 0) {
			if (column == 0) {
				Label label = new Label(baseInfoArea);  
				label.setID(RealGoodsCheckedInDetailProcessor.ID_Label_Store);
				if (CheckingInType.Purchase.equals(info.getSheetType())) {
					label = new Label(baseInfoArea);
					label.setID(RealGoodsCheckedInDetailProcessor.ID_Label_PlanDate);
				}

				label = new Label(baseInfoArea);
				label.setID(RealGoodsCheckedInDetailProcessor.ID_Label_RelatedNumber);
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
		sheetButtonArea.setID(RealGoodsCheckedInDetailProcessor.ID_RenderButtonArea);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
	}
}
