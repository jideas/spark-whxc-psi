package com.spark.psi.inventory.browser.split;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseListPageRender;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.PSIActionCommon;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageRender;
import com.spark.psi.inventory.browser.split.GoodsSelectProcessor.GoodsItemListProcessor;
import com.spark.psi.inventory.browser.split.GoodsSelectProcessor.Item;
import com.spark.psi.inventory.browser.split.GoodsSelectProcessor.SelectedGoodsItemListProcessor;
import com.spark.psi.inventory.browser.split.SplitGoodsSelectProcessor.SelectedItem;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.inventory.entity.InventoryItem;

/**
 * 商品选择界面视图
 * 
 */
public class GoodsSelectRender extends GoodsCategoryFramePageRender {

	protected void doRender() {
		super.doRender();
	}

	/**
	 * 商品条目视图
	 */
	public final static class GoodsItemListRender extends
			PSIGoodsListPageRender {

		@Override
		protected void afterFooterRender() {
			super.afterFooterRender();

			// 已选商品区域
			Composite selectedGoodsArea = new Composite(headerLeftArea);
			selectedGoodsArea.setID(GoodsItemListProcessor.ID_Area_Selected);
			GridLayout gl = new GridLayout();
			gl.numColumns = 3;
			gl.horizontalSpacing = 0;
			selectedGoodsArea.setLayout(gl);
			Label label = new Label(selectedGoodsArea);
			label.setText("已选商品");
			label.setCursor(Cursor.HAND);
			label.setForeground(Color.COLOR_BLUE);
			label = new Label(selectedGoodsArea, JWT.CENTER);
			label.setID(GoodsItemListProcessor.ID_Label_SelectedCount);
			label.setCursor(Cursor.HAND);
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER
					| GridData.GRAB_HORIZONTAL);
			gd.widthHint = 30;
			label.setLayoutData(gd);
			label.setForeground(Color.COLOR_RED);
			label = new Label(selectedGoodsArea);
			label.setForeground(Color.COLOR_BLUE);
			label.setText("件");
			label.setCursor(Cursor.HAND);

			//
			Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
			button.setID(GoodsItemListProcessor.ID_Button_NewGoods);
			button.setText(" 新增商品 ");
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setID(GoodsItemListProcessor.ID_Button_ConfirmSelect);
			button.setText(" 完成选择 ");
			new Label(footerRightArea).setText(" ");
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setID(GoodsItemListProcessor.ID_Button_CancelSelect);
			button.setText(" 取消选择 ");
		}

		public STableColumn[] getColumns() {
			STableColumn[] columns = new STableColumn[2];
			columns[0] = new STableColumn("GoodsName", 100, JWT.LEFT, "商品名称");
			columns[1] = new STableColumn("GoodsProperties", 100, JWT.CENTER,
					"商品规格");
			columns[0].setGrab(true);
			columns[1].setGrab(true);
			return columns;
		}

		public STableStyle getTableStyle() {
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(true);
			tableStyle.setSelectionMode(SSelectionMode.Row);
			return tableStyle;
		}

		public String getText(Object element, int columnIndex) {
			if (element instanceof Item) {
				Item item = ((Item) element);
				switch (columnIndex) {
				case 0:
					return item.goodsInfo.getName();
				case 1:
					return item.itemData.getSpec();
				}
			} else if (element instanceof InventoryItem) {
				InventoryItem item = (InventoryItem) element;
				switch (columnIndex) {
				case 0:
					return item.getName();
				case 1:
					return getPropertiesAndUnit(item.getProperties(), item.getUnit());
				}
			}
			return "";
		}
		
		/**
		 * 组装商品属性和单位
		 * @param properties 属性值
		 * @param unit 单位
		 * @return [properties] / [unit]
		 */
		private String getPropertiesAndUnit(String properties, String unit){
			if(StringHelper.isNotEmpty(properties) && StringHelper.isNotEmpty(unit)){
				return properties + "/" + "[" + unit + "]";
			}
			if(StringHelper.isNotEmpty(unit)){
				return "[" + unit + "]";
			}
			return properties;
		}
	}

	/**
	 * 已选商品条目视图
	 */
	public final static class SelectedGoodsItemListRender extends
			BaseListPageRender {

		@Override
		protected void beforeTableRender() {
			Composite headerArea = new Composite(contentArea);
			GridLayout gl = new GridLayout();
			gl.numColumns = 2;
			headerArea.setLayout(gl);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.heightHint = 25;
			headerArea.setLayoutData(gd);

			// Label label = new Label(headerArea);
			// label.setText("已选择件 0 商品");
			// label.setLayoutData(gd);

			Label label = new Label(headerArea);
			label.setID(SelectedGoodsItemListProcessor.ID_Label_Clear);
			label.setText("清空");
			label.setCursor(Cursor.HAND);
			label.setFont(new Font(9, "宋体", JWT.FONT_STYLE_UNDERLINE));
			label.setForeground(Color.COLOR_BLUE);
			label.setLayoutData(GridData.INS_CENTER_VERTICAL);
		}

		public STableColumn[] getColumns() {
			STableColumn[] columns = new STableColumn[4];
			columns[0] = new STableColumn("GoodsName", 100, JWT.LEFT, "商品名称");
			columns[1] = new STableColumn("GoodsProperties", 100, JWT.CENTER,
					"规格");
			columns[2] = new STableColumn("unit", 100, JWT.CENTER,
			"单位");
			columns[3] = new STableColumn("count", 100, JWT.CENTER,
			"数量");
			columns[0].setGrab(true);
//			columns[2] = new STableColumn("GoodsProperties", 100, JWT.CENTER,
//			"数量");
//			columns[2].setGrab(true);
			return columns;
		}

		public STableStyle getTableStyle() {
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			tableStyle.setSelectionMode(SSelectionMode.Row);
			return tableStyle;
		}

		public String getText(Object element, int columnIndex) {
			SelectedItem item = ((SelectedItem) element);
			switch (columnIndex) {
			case 0:
				return item.goodsItem.getBaseInfo().getName();
			case 1:
				return  item.goodsItem.getItemData().getSpec();
			case 2:
				return  item.goodsItem.getItemData().getUnit();
			case 3:
				return  item.getCount()+"";
//			case 2:
//				return item.count+"";
			}
			return "";
		}

		protected boolean hideFooterArea() {
			return true;
		}

		@Override
		public SActionInfo getActionInfo(String actionId) {
			return PSIActionCommon.getActionInfo(actionId);
		}
		

	}

}
