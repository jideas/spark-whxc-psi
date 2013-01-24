package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.inventory.browser.checkin.ExtendSimpleSheetPageRender;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.entity.CheckingOutInfo;

/**
 * 入库单明细列表视图
 */
public class CheckingOutDetailRender extends ExtendSimpleSheetPageRender {

	private CheckingOutInfo info;

	public void init(Situation context) {
		super.init(context);
		this.info = ((CheckingOutDetailProcessor) this.processor).info;
	}

	@Override
	public STableColumn[] getColumns() {
		int columnsSize = 8;
		if (isStopedOrFinished()) {
			columnsSize = 7;
		}
		STableColumn[] columns = new STableColumn[columnsSize];
		columns[0] = new STableColumn(CheckingOutDetailProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "材料编号");
		columns[1] = new STableColumn(CheckingOutDetailProcessor.Columns.GoodsNo.name(), 150, JWT.LEFT, "材料条码");
		columns[2] = new STableColumn(CheckingOutDetailProcessor.Columns.GoodsName.name(), 100, JWT.LEFT, "材料名称");

		columns[2].setGrab(true);

		columns[3] = new STableColumn(CheckingOutDetailProcessor.Columns.GoodsProperties.name(), 100, JWT.LEFT, "材料规格");
		columns[3].setGrab(true);
		columns[4] = new STableColumn(CheckingOutDetailProcessor.Columns.GoodsUnit.name(), 120, JWT.CENTER, "单位");
		if (null != info && CheckingOutType.Return.equals(info.getType())) {
			columns[5] = new STableColumn(CheckingOutDetailProcessor.Columns.CheckingCount.name(), 150, JWT.RIGHT,
					"采购退货数量");
		} else if (null != info && CheckingOutType.Sales.equals(info.getType())) {
			columns[5] = new STableColumn(CheckingOutDetailProcessor.Columns.CheckingCount.name(), 150, JWT.RIGHT,
					"销售数量");
		} else {
			columns[5] = new STableColumn(CheckingOutDetailProcessor.Columns.CheckingCount.name(), 150, JWT.RIGHT,
					"领取数量");
		}
		columns[6] = new STableColumn(CheckingOutDetailProcessor.Columns.CheckedCount.name(), 150, JWT.RIGHT, "已出库数量");

		if (!isStopedOrFinished()) {
			columns[7] = new SNumberEditColumn(CheckingOutDetailProcessor.Columns.CheckCount.name(), 150, JWT.RIGHT,
					"本次出库数量");
		}
		return columns;
	}

	private boolean isStopedOrFinished() {

		return null != info
				&& (CheckingOutStatus.Stop.equals(info.getStatus()) || CheckingOutStatus.Finish
						.equals(info.getStatus()));
	}

	@Override
	public Color getForeground(Object element, int columnIndex) {
		if (null != info && CheckingOutType.Mateiral_Return.equals(info.getType())) {
			switch (columnIndex) {
			case 5:
				return Color.COLOR_RED;
			case 6:
				return Color.COLOR_RED;
			case 7:
				return Color.COLOR_RED;
			}
		}
		return super.getForeground(element, columnIndex);
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof CheckingGoodsItem) {
			CheckingGoodsItem item = (CheckingGoodsItem) element;
			MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsItemId());
			switch (columnIndex) {
			case 0:
				return goods.getBaseInfo().getCode();
			case 1:
				return goods.getItemData().getMaterialNo();
			case 2:
				return goods.getBaseInfo().getName();
			case 3:
				return goods.getItemData().getMaterialSpec();
			case 4:
				return goods.getItemData().getUnit();
			case 5:
				String value = DoubleUtil.getRoundStr(item.getCheckingCount());
				return value;
			case 6:
				return DoubleUtil.getRoundStr(item.getCheckedCount());
			case 7:
				return DoubleUtil.getRoundStr(item.getCheckCount());
			}
		}
		return "";
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (0 == row) {
			if (0 == column) {
				Label label = new Label(baseInfoArea);
				if (!info.getType().isMaterialTakeOrReturn()) {
					label.setID(CheckingOutDetailProcessor.ID_Label_Customer);
					label = new Label(baseInfoArea);
				}
				label.setID(CheckingOutDetailProcessor.ID_Label_Store);
				if (CheckingOutType.Sales.equals(info.getType())) {
					label = new Label(baseInfoArea);
					label.setID(CheckingOutDetailProcessor.ID_Label_PlanDate);
				}
				label = new Label(baseInfoArea);
				label.setID(CheckingOutDetailProcessor.ID_Label_RelatedNumber);
			} else {
				addProcessing(baseInfoArea);
			}
		}
		if (1 == row) {
			if (0 == column) {
				GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_BEGINNING
						| GridData.VERTICAL_ALIGN_CENTER);
				new SAsteriskLabel(baseInfoArea, "提货人：").setLayoutData(gdLabel);
				Text t = new Text(baseInfoArea, JWT.APPEARANCE3);
				t.setID(CheckingOutDetailProcessor.ID_Text_DeliveryPerson);
				new Label(baseInfoArea).setText("    ");
				new SAsteriskLabel(baseInfoArea, "提货单位：").setLayoutData(gdLabel);
				t = new Text(baseInfoArea, JWT.APPEARANCE3);
				t.setID(CheckingOutDetailProcessor.ID_Text_DeliveryUnit);

				new Label(baseInfoArea).setText("    凭证号：");
				t = new Text(baseInfoArea, JWT.APPEARANCE3);
				t.setID(CheckingOutDetailProcessor.ID_Text_VoucherNumber);

			}
		}

	}

	@Override
	public int getDecimal(Object element, int columnIndex) {
		if (columnIndex > 4) {
			return 2;
		}
		return -1;
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 2;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		sheetButtonArea.setID(CheckingOutDetailProcessor.ID_FooterRightArea);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
	}

}
