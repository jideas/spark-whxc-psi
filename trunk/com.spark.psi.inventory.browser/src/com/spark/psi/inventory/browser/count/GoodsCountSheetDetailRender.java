package com.spark.psi.inventory.browser.count;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SFormula;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;

/**
 * 材料库存盘点单明细界面视图
 * 
 */
public class GoodsCountSheetDetailRender extends SimpleSheetPageRender {

	private InventoryCountSheetInfo countSheet;

	public void init(Situation context) {
		super.init(context);
		if (null != this.getArgument()) {
			countSheet = (InventoryCountSheetInfo) this.getArgument();
		}
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[9];
		columns[0] = new STableColumn(GoodsCountSheetDetailProcessor.Columns.GoodsCode.name(), 100, JWT.LEFT, "材料编号");
		columns[1] = new STableColumn(GoodsCountSheetDetailProcessor.Columns.GoodsNo.name(), 100, JWT.LEFT, "材料条码条码");
		columns[2] = new STableColumn(GoodsCountSheetDetailProcessor.Columns.GoodsName.name(), 100, JWT.CENTER, "材料名称");
		columns[3] = new STableColumn(GoodsCountSheetDetailProcessor.Columns.GoodsProperties.name(), 100, JWT.CENTER,
				"材料规格");
		columns[4] = new STableColumn(GoodsCountSheetDetailProcessor.Columns.GoodsUnit.name(), 55, JWT.CENTER, "单位");
		columns[5] = new STableColumn(GoodsCountSheetDetailProcessor.Columns.Count.name(), 100, JWT.RIGHT, "账面数量");
		columns[6] = new SNumberEditColumn(GoodsCountSheetDetailProcessor.Columns.AcutalCount.name(), 100, JWT.RIGHT,
				"实盘数量");
		((SNumberEditColumn) columns[6]).setFormulas(new SFormula[] { new SAsignFormula("["
				+ GoodsCountSheetDetailProcessor.Columns.AcutalCount.name() + "]" + "-[#"
				+ GoodsCountSheetDetailProcessor.Columns.Count.name() + "]",
				GoodsCountSheetDetailProcessor.Columns.BalanceCount.name()) });
		columns[7] = new STableColumn(GoodsCountSheetDetailProcessor.Columns.BalanceCount.name(), 100, JWT.RIGHT, "差额");
		columns[8] = new STextEditColumn(GoodsCountSheetDetailProcessor.Columns.Memo.name(), 100, JWT.LEFT, "说明");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[8].setGrab(true);

		columns[0].setSearch(true);
		columns[1].setSearch(true);

		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof GoodsCountSheetDetailProcessor.Item) {
			GoodsCountSheetDetailProcessor.Item item = (GoodsCountSheetDetailProcessor.Item) element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsCode();
			case 1:
				return item.getGoodsNo();
			case 2:
				return item.getGoodsItemName();
			case 3:
				return item.getGoodsItemProperties();
			case 4:
				return item.getGoodsItemUnit();
			case 5:
				return String.valueOf(item.getCount());
			case 6:
				return String.valueOf(item.getActualCount());
			case 7:
				return String.valueOf(DoubleUtil.sub(item.getActualCount(), item.getCount()));
			case 8:
				return item.getRemark();
			default:
				return null;
			}
		}
		return null;
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		if (element instanceof GoodsCountSheetDetailProcessor.Item) {
			GoodsCountSheetDetailProcessor.Item item = (GoodsCountSheetDetailProcessor.Item) element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsCode();
			case 1:
				return item.getGoodsNo();
			case 2:
				return item.getGoodsItemName();
			case 3:
				return item.getGoodsItemProperties();
			case 4:
				return item.getGoodsItemUnit();
			case 5:
				return DoubleUtil.getRoundStr(item.getCount());
			case 6:
				return DoubleUtil.getRoundStr(item.getActualCount());
			case 7:
				return DoubleUtil.getRoundStr(DoubleUtil.sub(item.getActualCount(), item.getCount()));
			case 8:
				return item.getRemark();
			default:
				return null;
			}
		}
		return null;
	}

	/**
	 * 
	 */
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
		case 7:
			return 2;
		case 5:
			return 2;
		case 6:
			return 2;
		}
		return -1;
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (0 == row) {
			if (0 == column) {
				new Label(baseInfoArea).setID(GoodsCountSheetDetailProcessor.ID_Label_Store);
			} else {
			}
		}

	}

	@Override
	protected boolean isShowFind() {
		return true;
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		// 盘点中
		if (countSheet == null || countSheet.getSheetstatus() == InventoryCountStatus.Counting) {
			Button finishButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
			finishButton.setID(GoodsCountSheetDetailProcessor.ID_Button_Finish);
			finishButton.setText(" 结束盘点 ");
			Button saveButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
			saveButton.setID(GoodsCountSheetDetailProcessor.ID_Button_Save);
			saveButton.setText("   保存   ");
		}
		Button printButton = new Button(sheetButtonArea, JWT.APPEARANCE3);
		printButton.setID(GoodsCountSheetDetailProcessor.ID_Button_Print);
		printButton.setText("   打印   ");
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		// 盘点中才允许添加材料和导出
		if (countSheet == null || countSheet.getSheetstatus() == InventoryCountStatus.Counting) {
			Button addButton = new Button(tableButtonArea, JWT.APPEARANCE2);
			addButton.setID(GoodsCountSheetDetailProcessor.ID_Button_AddGoods);
			addButton.setText(" 添加材料 ");
		}
	}

	@Override
	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		return tableStyle;
	}

}
