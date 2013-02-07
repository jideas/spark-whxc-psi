package com.spark.psi.inventory.browser.split;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoGoodsItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoMaterialsItem;

public class NewGoodsSplitDetailRender extends AbstractGoodsSplitOrderRender {

	@Override
	protected void renderTable(Composite tableArea) {
		SEditTable goodsTable = null;
		SEditTable materialTable = null;
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		// if (ProduceOrderStatus.Producing.equals(orderInfo.getStatus())) {
		goodsTable = new SEditTable(tableArea, getEditableGoodsTableColumns(),
				tableStyle, null);
		goodsTable.setLabelProvider(new EditableGoodsTableLabelProvider());
		materialTable = new SEditTable(tableArea,
				getEditableMaterialTableColumns(), tableStyle, null);
		// materialTable.setLabelProvider(new
		// EditableMaterialTableLabelProvider());
		// } else if (ProduceOrderStatus.Finished.equals(orderInfo.getStatus()))
		// {
		// goodsTable = new SEditTable(tableArea, getGoodsTableColumns(),
		// tableStyle, null);
		// goodsTable.setLabelProvider(new GoodsTableLabelProvider());
		// materialTable = new SEditTable(tableArea,
		// getEditableMaterialTableColumns(), tableStyle, null);
		// materialTable.setLabelProvider(new
		// EditableMaterialTableLabelProvider());
		// } else {
		// goodsTable = new SEditTable(tableArea, getGoodsTableColumns(),
		// tableStyle, null);
		// goodsTable.setLabelProvider(new GoodsTableLabelProvider());
		// materialTable = new SEditTable(tableArea, getMaterialTableColumns(),
		// tableStyle, null);
		// materialTable.setLabelProvider(new MaterialTableLabelProvider());
		// }

		goodsTable.setID(NewGoodsSplitDetailProcessor.ID_Table_Goods);
		materialTable.setID(NewGoodsSplitDetailProcessor.ID_Table_Material);

		goodsTable.setLayoutData(GridData.INS_FILL_BOTH);
		materialTable.setLayoutData(GridData.INS_FILL_BOTH);
	}

	@Override
	protected void fillFooter() {
//		GridData gd = new GridData();
//		gd.widthHint = 150;
//		Label lb = new Label(footerLeftArea);
//		lb.setID(NewGoodsSplitDetailProcessor.ID_Label_Info);
//		lb.setLayoutData(gd);

		Button button = null;
		button = new Button(footerLeftArea,JWT.APPEARANCE2);
		button.setText("添加商品");
		button.setID(NewGoodsSplitDetailProcessor.ID_Button_AddGoods);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  提交  ");
		button.setID(NewGoodsSplitDetailProcessor.ID_Button_Submit);
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  保存  ");
		button.setID(NewGoodsSplitDetailProcessor.ID_Button_Save);

	}

	private STableColumn[] getGoodsTableColumns() {
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.goodsName.name(),
				200, JWT.LEFT, "商品名称");
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.spec.name(), 140,
				JWT.LEFT, "规格");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.count.name(), 140,
				JWT.RIGHT, "数量");
		columns[0].setGrab(true);
		return columns;
	}

	private STableColumn[] getMaterialTableColumns() {
		STableColumn[] columns = new STableColumn[3];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.materialName
						.name(), 200, JWT.LEFT, "材料名称");
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.count.name(),
				140, JWT.RIGHT, "数量");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.storeName
						.name(), 200, JWT.CENTER, "仓库");
		columns[0].setGrab(true);
		return columns;
	}

	private STableColumn[] getEditableGoodsTableColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.goodsName.name(),
				150, JWT.LEFT, "商品名称");
		columns[0].setGrab(true);
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.spec.name(), 90,
				JWT.LEFT, "规格");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.count.name(), 90,
				JWT.RIGHT, "数量");
		columns[3] = new STableColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.doneCount.name(),
				90, JWT.RIGHT, "已完成数量");
		columns[4] = new SNumberEditColumn(
				NewGoodsSplitDetailProcessor.GoodsColumnName.currentCount
						.name(), 90, JWT.RIGHT, "本次完成数量");
		((SNumberEditColumn) columns[4]).setDecimal(2);
		return columns;
	}

	private STableColumn[] getEditableMaterialTableColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.materialName
						.name(), 150, JWT.LEFT, "材料名称");
		columns[1] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.count.name(),
				90, JWT.RIGHT, "数量");
		columns[2] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.receivedCount
						.name(), 90, JWT.RIGHT, "已申请领料");
		columns[3] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.returnedCount
						.name(), 90, JWT.RIGHT, "已申请退料");
		// columns[3] = new
		// SNumberEditColumn(ProduceDetailProcessor.MaterialColumnName.currentCount.name(),
		// 100, JWT.RIGHT, "本次领取数量");
		columns[4] = new STableColumn(
				NewGoodsSplitDetailProcessor.MaterialColumnName.storeName
						.name(), 100, JWT.CENTER, "仓库");
		columns[0].setGrab(true);
		return columns;
	}

	private class GoodsTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			ProduceOrderInfoGoodsItem item = (ProduceOrderInfoGoodsItem) element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsName();
			case 1:
				return item.getGoodsSpec();
			case 2:
				return DoubleUtil.getRoundStr(item.getCount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			if (2 == columnIndex)
				return 2;
			return 0;
		}
	}

	private class MaterialTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			ProduceOrderInfoMaterialsItem item = (ProduceOrderInfoMaterialsItem) element;
			switch (columnIndex) {
			case 0:
				return item.getMaterialName();
			case 1:
				return DoubleUtil.getRoundStr(item.getCount());
			case 2:
				return item.getStoreName();
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}

	}

	private class EditableGoodsTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			ProduceOrderInfoGoodsItem item = (ProduceOrderInfoGoodsItem) element;
			switch (columnIndex) {
			case 0:
				return item.getGoodsName();
			case 1:
				return item.getGoodsSpec();
			case 2:
				return DoubleUtil.getRoundStr(item.getCount());
			case 3:
				return DoubleUtil.getRoundStr(item.getFinishedCount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}

	}

	private class EditableMaterialTableLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			ProduceOrderInfoMaterialsItem item = (ProduceOrderInfoMaterialsItem) element;
			switch (columnIndex) {
			case 0:
				return item.getMaterialName();
			case 1:
				return DoubleUtil.getRoundStr(item.getCount());
			case 2:
				return DoubleUtil.getRoundStr(item.getReceivedCount());
			case 3:
				return DoubleUtil.getRoundStr(item.getReturnedCount());
			case 4:
				return item.getStoreName();
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			return 2;
		}

	}
}
