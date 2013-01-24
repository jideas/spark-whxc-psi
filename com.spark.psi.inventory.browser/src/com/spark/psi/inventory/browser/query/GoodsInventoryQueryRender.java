package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageRender;
import com.spark.psi.inventory.browser.query.GoodsInventoryQueryProcessor.GoodsInventoryQueryListProcessor;
import com.spark.psi.publish.inventory.entity.InventoryItem;

public class GoodsInventoryQueryRender extends GoodsCategoryFramePageRender {

	/**
	 * 商品库存查询视图
	 */
	public final static class GoodsInventoryQueryListRender extends PSIGoodsListPageRender {

		@Override
		protected void afterFooterRender() {
			super.afterFooterRender();
			//
			// new LWComboList(headerLeftArea,
			// JWT.APPEARANCE3).setID(GoodsInventoryQueryListProcessor.ID_COMBOLIST_STORE);
			// //
			new Label(headerLeftArea).setText("  商品数量：");
			//
			Label label = new Label(headerLeftArea);
			label.setID(GoodsInventoryQueryListProcessor.ID_LABEL_GOODSINVENTORY_COUNT);
			GridData gd = new GridData();
			gd.widthHint = 100;
			label.setLayoutData(gd);
		}

		public STableColumn[] getColumns() {

			STableColumn[] columns = new STableColumn[6];

			// 需要加SheetId 获取调拨单ID
			columns[0] = new STableColumn(GoodsInventoryQueryProcessor.Cloumns.goodsCode.name(), 100, JWT.CENTER,
					"商品编号");
			columns[1] = new STableColumn(GoodsInventoryQueryProcessor.Cloumns.GoodNumber.name(), 100, JWT.CENTER,
					"商品条码");

			columns[2] = new STableColumn(GoodsInventoryQueryProcessor.Cloumns.GoodsName.name(), 0, JWT.CENTER, "商品名称");
			columns[2].setGrab(true);

			columns[3] = new STableColumn(GoodsInventoryQueryProcessor.Cloumns.GoodsAttribute.name(), 0, JWT.CENTER,
					"商品规格");
			columns[3].setGrab(true);

			columns[4] = new STableColumn(GoodsInventoryQueryProcessor.Cloumns.Unit.name(), 0, JWT.CENTER, "单位");
			columns[4].setGrab(true);

			columns[5] = new STableColumn(GoodsInventoryQueryProcessor.Cloumns.Count.name(), 0, JWT.CENTER, "商品数量");
			columns[5].setGrab(true);

			return columns;
		}

		@Override
		public STableStyle getTableStyle() {
			STableStyle sTableStyle = new STableStyle();
			sTableStyle.setSortAll(true);
			return sTableStyle;
		}

		public String getText(Object element, int columnIndex) {
			InventoryItem item = (InventoryItem) element;
			this.createControl(GoodsInventoryQueryListProcessor.ID_COMBOLIST_STORE, LWComboList.class, JWT.NO);
			switch (columnIndex) {
			case 0:
				return item.getCode();
			case 1:
				return item.getNumber();
			case 2:
				return item.getName();
			case 3:
				return item.getSpec();
			case 4:
				return item.getUnit();
			case 5:
				// GUID storeId = null;
				// if(CheckIsNull.isNotEmpty(storeList.getText())
				// && !GUID.valueOf(storeList.getText()).equals(GUID.emptyID))
				// {
				// storeId = GUID.valueOf(storeList.getText());
				// }
				// if(null == storeId && isBoss()){
				// return
				// StableUtil.toLink(GoodsInventoryQueryListProcessor.ID_viewInventory,
				// "", DoubleUtil.getRoundStr
				// (item.getCount(), item.getScale()));
				// }
				return DoubleUtil.getRoundStr(item.getCount());
			default:
				return "";
			}
		}

		/**
		 * 获得精度
		 */
		@Override
		public int getDecimal(Object element, int columnIndex) {
			if (element instanceof InventoryItem) {
				switch (columnIndex) {
				case 4:
					return 2;
				}
			}
			return -1;
		}
	}

}
