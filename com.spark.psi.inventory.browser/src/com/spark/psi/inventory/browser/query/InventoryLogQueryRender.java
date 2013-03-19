package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.material.MaterialCategoryFramePageRender;
import com.spark.psi.inventory.browser.query.InventoryLogQueryProcessor.InventoryLogQueryListProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.inventory.entity.InventoryLogItem;

/**
 * 
 */
public class InventoryLogQueryRender extends MaterialCategoryFramePageRender {

	public final static class InventoryLogQueryListRender extends PSIGoodsListPageRender {

		private boolean isStoreKeeper = false;
		@Override
		protected void afterFooterRender() {
			super.afterFooterRender();
			//
			new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(InventoryLogQueryListProcessor.ID_COMBOLIST_STORE);

			//
			new Label(headerLeftArea).setText("  从：");
			// 
			new SDatePicker(headerLeftArea).setID(InventoryLogQueryListProcessor.ID_DATE_BEGIN);
			new Label(headerLeftArea).setText(" 到：");
			new SDatePicker(headerLeftArea).setID(InventoryLogQueryListProcessor.ID_DATE_End);
			new Button(headerLeftArea).setID(InventoryLogQueryListProcessor.ID_BUTTON_QueryButton);
			//
			new Label(headerLeftArea).setText("  记录数量：");
			//
			Label label = new Label(headerLeftArea);
			label.setID(InventoryLogQueryListProcessor.ID_LABEL_INVENTORYLOG_COUNT);
			GridData gd = new GridData();
			gd.widthHint = 100;
			label.setLayoutData(gd);
			isStoreKeeper = isStoreKeeper();
		}

		public STableColumn[] getColumns() {

			int size = 12;
			if (isStoreKeeper()) {
				size = 10;
			}

			STableColumn[] columns = new STableColumn[size];

			columns[0] = new STableColumn(InventoryLogQueryProcessor.Columns.Date.name(), 100, JWT.CENTER, "日期");
			columns[1] = new STableColumn(InventoryLogQueryProcessor.Columns.RelatedNumber.name(), 150, JWT.LEFT,
					"单据编号");
			columns[2] = new STableColumn(InventoryLogQueryProcessor.Columns.GoodsCode.name(), 150, JWT.LEFT, "商品编号");
			columns[3] = new STableColumn(InventoryLogQueryProcessor.Columns.GoodsNumber.name(), 150, JWT.LEFT, "商品条码");
			columns[4] = new STableColumn(InventoryLogQueryProcessor.Columns.GoodsName.name(), 120, JWT.LEFT, "商品名称");

			columns[5] = new STableColumn(InventoryLogQueryProcessor.Columns.Properties.name(), 100, JWT.LEFT, "商品规格");

			columns[6] = new STableColumn(InventoryLogQueryProcessor.Columns.Unit.name(), 50, JWT.CENTER, "单位");

			columns[7] = new STableColumn(InventoryLogQueryProcessor.Columns.Type.name(), 100, JWT.CENTER, "流水类型");

			if (isStoreKeeper) {
				columns[8] = new STableColumn(InventoryLogQueryProcessor.Columns.CheckedInCount.name(), 100, JWT.RIGHT,
						"入库数量");
				columns[9] = new STableColumn(InventoryLogQueryProcessor.Columns.CheckedOutCount.name(), 100,
						JWT.RIGHT, "出库数量");
			} else {
				columns[8] = new STableColumn(InventoryLogQueryProcessor.Columns.CheckedInCount.name(), 100, JWT.RIGHT,
						"入库数量");
				columns[9] = new STableColumn(InventoryLogQueryProcessor.Columns.CheckedInAmount.name(), 100,
						JWT.RIGHT, "入库金额");
				columns[10] = new STableColumn(InventoryLogQueryProcessor.Columns.CheckedOutCount.name(), 100,
						JWT.RIGHT, "出库数量");
				columns[11] = new STableColumn(InventoryLogQueryProcessor.Columns.CheckedOutAmount.name(), 100,
						JWT.RIGHT, "出库金额");
			}

			return columns;
		}

		private boolean isStoreKeeper() {
			return getContext().find(Boolean.class, Auth.StoreKeeper) && !getContext().find(Boolean.class, Auth.Boss)
					&& !getContext().find(Boolean.class, Auth.StoreKeeperManager);
		}

		@Override
		public STableStyle getTableStyle() {
			STableStyle sTableStyle = new STableStyle();
			sTableStyle.setSortAll(true);
			return sTableStyle;
		}

		/**
		 * 获得精度
		 */
		@Override
		public int getDecimal(Object element, int columnIndex) {
			if (element instanceof InventoryLogItem) {
				if (isStoreKeeper) {
					switch (columnIndex) {
					case 7:
					case 8:
						return 2;
					}
				} else {
					switch (columnIndex) {
					case 7:
					case 9:
						return 2;
					}
				}
			}
			return -1;
		}

		public String getText(Object element, int columnIndex) {
			InventoryLogItem item = (InventoryLogItem) element;
			if (isStoreKeeper) {
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(item.getOccorDate());
				case 1:
					return item.getRelatedNumber();
				case 2:
					return item.getGoodsItemCode();
				case 3:
					return item.getGoodsItemNumber();
				case 4:
					return item.getGoodsItemName();
				case 5:
					return item.getGoodsItemProperties();
				case 6:
					return item.getGoodsItemUnit();
				case 7:
					return item.getLogType().getName();
				case 8:
					StringBuffer checkedInCount = new StringBuffer();
					if (0 == item.getCheckedInCount() && 0 == item.getCheckedInAmount()) {
						checkedInCount.append("-");
					} else {
						checkedInCount.append(DoubleUtil.getRoundStr(item.getCheckedInCount()));
					}
					return checkedInCount.toString();
				case 9:
					StringBuffer checkedOutCount = new StringBuffer();
					if (0 == item.getCheckedOutCount() && 0 == item.getCheckedOutAmount()) {
						checkedOutCount.append("-");
					} else {
						checkedOutCount.append(DoubleUtil.getRoundStr(item.getCheckedOutCount()));
					}
					return checkedOutCount.toString();
				default:
					return "";
				}
			} else {
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(item.getOccorDate());
				case 1:
					return item.getRelatedNumber();
				case 2:
					return item.getGoodsItemCode();
				case 3:
					return item.getGoodsItemNumber();
				case 4:
					return item.getGoodsItemName();
				case 5:
					return item.getGoodsItemProperties();
				case 6:
					return item.getGoodsItemUnit();
				case 7:
					if (null == item.getLogType()) {
						return "类型缺失";
					}
					return item.getLogType().getName();
				case 8:
					StringBuffer checkedInCount = new StringBuffer();
					if (0 == item.getCheckedInCount() && 0 == item.getCheckedInAmount()) {
						checkedInCount.append("-");
					} else {
						checkedInCount.append(DoubleUtil.getRoundStr(item.getCheckedInCount()));
					}
					return checkedInCount.toString();
				case 9:
					StringBuffer checkedInAmount = new StringBuffer();
					if (0 == item.getCheckedInCount() && 0 == item.getCheckedInAmount()) {
						checkedInAmount.append("-");
					} else {
						checkedInAmount.append(DoubleUtil.getRoundStr(item.getCheckedInAmount()));
					}
					return checkedInAmount.toString();
				case 10:
					StringBuffer checkedOutCount = new StringBuffer();
					if (0 == item.getCheckedOutCount() && 0 == item.getCheckedOutAmount()) {
						checkedOutCount.append("-");
					} else {
						checkedOutCount.append(DoubleUtil.getRoundStr(item.getCheckedOutCount()));
					}
					return checkedOutCount.toString();
				case 11:
					StringBuffer checkedOutAmount = new StringBuffer();
					if (0 == item.getCheckedOutCount() && 0 == item.getCheckedOutAmount()) {
						checkedOutAmount.append("-");
					} else {
						checkedOutAmount.append(DoubleUtil.getRoundStr(item.getCheckedOutAmount()));
					}
					return checkedOutAmount.toString();
				default:
					return "";
				}
			}
		}

		@Override
		public String getToolTipText(Object element, int columnIndex) {
			InventoryLogItem item = (InventoryLogItem) element;
			if (isStoreKeeper) {
				switch (columnIndex) {
				case 3:
					return item.getGoodsItemName();
				case 4:
					return item.getGoodsItemProperties();
				default:
					return null;
				}
			} else {
				switch (columnIndex) {
				case 4:
					return item.getGoodsItemName();
				case 5:
					return item.getGoodsItemProperties();
				default:
					return "";
				}
			}
		}

	}
}
