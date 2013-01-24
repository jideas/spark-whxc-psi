package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

/**
 * 入库单明细列表视图
 */
public class JointCheckedInDetailRender extends ExtendSimpleSheetPageRender {

	public void init(Situation context) {
		super.init(context); 
	}

	@Override
	public STableColumn[] getColumns() {
		int columnsSize = 6;
		STableColumn[] columns = new STableColumn[columnsSize];
		columns[0] = new STableColumn(JointCheckedInDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "材料编号");
		columns[1] = new STableColumn(JointCheckedInDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "材料条码");
		columns[2] = new STableColumn(JointCheckedInDetailProcessor.Columns.GoodsName.name(), 100, JWT.LEFT, "材料名称");
		columns[2].setGrab(true);
		columns[3] = new STableColumn(JointCheckedInDetailProcessor.Columns.GoodsProperties.name(), 100, JWT.LEFT, "材料规格");
		columns[3].setGrab(true);
		columns[4] = new STableColumn(JointCheckedInDetailProcessor.Columns.GoodsUnit.name(), 120, JWT.CENTER, "单位");
		columns[5] = new STableColumn(JointCheckedInDetailProcessor.Columns.CheckedCount.name(), 150, JWT.RIGHT, "入库数量");
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
				label.setID(JointCheckedInDetailProcessor.ID_Label_Store);
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
		sheetButtonArea.setID(JointCheckedInDetailProcessor.ID_RenderButtonArea);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
	}
}
