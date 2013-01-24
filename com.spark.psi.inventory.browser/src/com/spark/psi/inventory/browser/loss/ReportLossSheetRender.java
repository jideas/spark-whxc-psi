package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItem;

public abstract class ReportLossSheetRender extends SimpleSheetPageRender {

	protected ReportLossInfo reportLossInfo = null;

	@Override
	public void init(Situation context) {
		super.init(context);
		GUID infoId = (GUID) getArgument();
		if (null != infoId) {
			reportLossInfo = context.get(ReportLossInfo.class, infoId);
		}
	}

	@Override
	protected final void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (row == 0 && column == 0) {
			renderBaseInfo(baseInfoArea);
		} else if (row == 0 && column == 1) {
			if (null != reportLossInfo) {
				new Label(baseInfoArea).setText("单据状态：" + reportLossInfo.getStatus().getTitle());
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
	protected final int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {

	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {

	}

	@Override
	public final STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn(ReportLossSheetProcessor.ColumnName.materialCode.name(), 150, JWT.LEFT, "材料编号");
		columns[1] = new STableColumn(ReportLossSheetProcessor.ColumnName.materialNo.name(), 150, JWT.LEFT, "材料条码");
		columns[2] = new STableColumn(ReportLossSheetProcessor.ColumnName.materialName.name(), 120, JWT.LEFT, "名称");
		columns[3] = new STableColumn(ReportLossSheetProcessor.ColumnName.spec.name(), 150, JWT.CENTER, "规格");
		columns[4] = new STableColumn(ReportLossSheetProcessor.ColumnName.unit.name(), 150, JWT.CENTER, "单位");
		columns[5] = new SNumberEditColumn(ReportLossSheetProcessor.ColumnName.count.name(), 150, JWT.CENTER, "报损数量");
		columns[6] = new STextEditColumn(ReportLossSheetProcessor.ColumnName.reason.name(), 150, JWT.CENTER, "报损原因");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		return columns;
	}

	@Override
	public final String getText(Object element, int columnIndex) {
		if (element instanceof MaterialsItemInfo) {
			MaterialsItemInfo item = (MaterialsItemInfo) element;
			switch (columnIndex) {
			case 0:
				return item.getBaseInfo().getCode();
			case 1:
				return item.getItemData().getMaterialNo();
			case 2:
				return item.getBaseInfo().getName();
			case 3:
				return item.getItemData().getMaterialSpec();
			case 4:
				return item.getItemData().getUnit();
			}
		} else if (element instanceof ReportLossInfoItem) {
			ReportLossInfoItem item = (ReportLossInfoItem) element;
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
				return item.getGoodsUnit();
			case 5:
				return DoubleUtil.getRoundStr(item.getReportCount(), item.getScale());
			case 6:
				return item.getReportReason();
			}
		}
		return null;
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		if (element instanceof ReportLossInfoItem) {
			ReportLossInfoItem item = (ReportLossInfoItem) element;
			if (columnIndex == 6) {
				return item.getReportReason();
			}
		}
		return super.getToolTipText(element, columnIndex);
	}

	@Override
	public int getDecimal(Object element, int columnIndex) {
		if (5 == columnIndex) {
			if (element instanceof MaterialsItemInfo) {
				MaterialsItemInfo item = (MaterialsItemInfo) element;
				return item.getBaseInfo().getCategory().getScale();
			} else if (element instanceof ReportLossInfoItem) {
				ReportLossInfoItem item = (ReportLossInfoItem) element;
				return item.getScale();
			}

		}
		return super.getDecimal(element, columnIndex);
	}

	protected abstract void renderBaseInfo(Composite baseInfoArea);

}
