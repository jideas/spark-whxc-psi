package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
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
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.psi.base.browser.PSIActionCommon;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.goods.GoodsSelectProcessor.GoodsItemListProcessor;
import com.spark.psi.base.browser.material.MaterialCategoryFramePageRender;
import com.spark.psi.order.browser.purchase.PurchasingGoodsSelectProcessor.SelectedPurchaseGoodsItemListProcessor;
import com.spark.psi.order.browser.purchase.PurchasingGoodsSelectProcessor.SelectedPurchaseItem;
import com.spark.psi.order.browser.purchase.PurchasingGoodsSelectProcessor.StoreAndGoodsItemListProcessor;
import com.spark.psi.order.browser.purchase.PurchasingGoodsSelectProcessor.StoreAndGoodsItemListProcessor.Item;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

/**
 * ����ѡ�������ͼ
 * 
 */
public class PurchasingGoodsSelectRender extends MaterialCategoryFramePageRender {

	@Override
	protected void doRender() {
		super.doRender();
	}

	/**
	 * ������Ŀ��ͼ
	 */
	public final static class StoreAndGoodsItemListRender extends PSIGoodsListPageRender {

		private LWComboList storeList;

		@Override
		protected void afterFooterRender() {
			super.afterFooterRender();

			Label label = new Label(headerLeftArea);
			label.setText("���ֿ⣺");
			// �ֿ�ѡ��
			storeList = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
			storeList.setID(StoreAndGoodsItemListProcessor.ID_List_Store);

			new Label(headerLeftArea).setText("  ");

			// ��ѡ��������
			Composite selectedGoodsArea = new Composite(headerLeftArea);
			selectedGoodsArea.setID(GoodsItemListProcessor.ID_Area_Selected);
			GridLayout gl = new GridLayout();
			gl.numColumns = 3;
			gl.horizontalSpacing = 0;
			selectedGoodsArea.setLayout(gl);
			label = new Label(selectedGoodsArea);
			label.setText("��ѡ����");
			label.setCursor(Cursor.HAND);
			label.setForeground(Color.COLOR_BLUE);
			label = new Label(selectedGoodsArea, JWT.CENTER);
			label.setID(StoreAndGoodsItemListProcessor.ID_Label_SelectedCount);
			label.setCursor(Cursor.HAND);
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL);
			gd.widthHint = 30;
			label.setLayoutData(gd);
			label.setForeground(Color.COLOR_RED);
			label = new Label(selectedGoodsArea);
			label.setForeground(Color.COLOR_BLUE);
			label.setText("��");
			label.setCursor(Cursor.HAND);

			//
			Button button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setID(StoreAndGoodsItemListProcessor.ID_Button_ConfirmSelect);
			button.setText(" ���ѡ�� ");
			new Label(footerRightArea).setText(" ");
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setID(GoodsItemListProcessor.ID_Button_CancelSelect);
			button.setText(" ȡ��ѡ�� ");
		}

		@Override
		public STableColumn[] getColumns() {
			STableColumn[] columns = new STableColumn[PurchasingGoodsSelectProcessor.Columns.values().length];
			int index = 0;
			for (PurchasingGoodsSelectProcessor.Columns column : PurchasingGoodsSelectProcessor.Columns.values()) {
				if (PurchasingGoodsSelectProcessor.Columns.PurchaseCount == column) {
					columns[index] = new SNumberEditColumn(column.name(), column.getWidth(), column.getAlign(), column.getTitle());
					columns[index++].setGrab(column.isGrab());
					continue;
				}
				columns[index] = new STableColumn(column.name(), column.getWidth(), column.getAlign(), column.getTitle());
				columns[index++].setGrab(column.isGrab());
			}
			// columns[0] = new STableColumn("GoodsName", 100, JWT.LEFT,
			// "��������");
			// columns[1] = new STableColumn("GoodsProperties", 100, JWT.CENTER,
			// "���Ϲ��");
			// columns[2] = new SNumberEditColumn("PurchaseCount", 100,
			// JWT.RIGHT,
			// "�ɹ�����");
			// columns[0].setGrab(true);
			// columns[1].setGrab(true);
			// columns[2].setGrab(true);
			return columns;
		}

		@Override
		public STableStyle getTableStyle() {
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			tableStyle.setSelectionMode(SSelectionMode.Row);
			return tableStyle;
		}

		@Override
		public String getText(Object element, int columnIndex) {
			Item item = ((Item) element);
			switch (columnIndex) {
			case 0:
				return item.goodsInfo.getName();
			case 1:
				return item.itemData.getMaterialSpec();
			case 2:
				String[] properties = item.itemData.getPropertyValues();
				return properties.length > 0 ? properties[0] : "";
			case 3:
				return storeList.getDescription();
			case 4:
				if (item.inventoryDetail != null) {
					return String.valueOf(item.inventoryDetail.getCount());
				}
				return "--";
			case 5:
				if (item.inventoryDetail != null) {
					return String.valueOf(item.inventoryDetail.getPurchasingCount());
				}
				return "--";
			case 6:
				if (item.inventoryDetail != null) {
					return String.valueOf(item.inventoryDetail.getDeliveryingCount());
				}
				return "--";
			case 7:
				return getInventoryLimit(item.inventoryDetail);
			case 8:
				return "";
			}
			return "";
		}

		/**
		 * 
		 */
		public int getDecimal(Object element, int columnIndex) {
			Item item = ((Item) element);
			switch (columnIndex) {
			case 4:
			case 5:
			case 6:
			case 8:
				return item.goodsInfo.getCategory().getScale();
			}
			return -1;
		}

	}

	private static String getInventoryLimit(InventoryInfo inventoryDetail) {
		if (null != inventoryDetail) {
			return SelectPurchaseWarningGoodsRender.getInventoryLimit(inventoryDetail.getUpperLimitCount(), inventoryDetail
					.getLowerLimitCount());
		}
		return SelectPurchaseWarningGoodsRender.getInventoryLimit(-1, -1);
	}

	/**
	 * ��ѡ�ɹ�������Ŀ��ͼ
	 */
	public final static class SelectedPurchaseGoodsItemListRender extends BaseListPageRender {

		@Override
		protected void beforeTableRender() {
			Composite headerArea = new Composite(contentArea);
			GridLayout gl = new GridLayout();
			gl.numColumns = 2;
			headerArea.setLayout(gl);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.heightHint = 25;
			headerArea.setLayoutData(gd);

			Label label = new Label(headerArea);
			label.setID(SelectedPurchaseGoodsItemListProcessor.ID_Label_Clear);
			label.setText("���");
			label.setCursor(Cursor.HAND);
			label.setFont(new Font(9, "����", JWT.FONT_STYLE_UNDERLINE));
			label.setForeground(Color.COLOR_BLUE);
			label.setLayoutData(GridData.INS_CENTER_VERTICAL);
		}

		@Override
		public STableColumn[] getColumns() {
			STableColumn[] columns = new STableColumn[4];
			columns[0] = new STableColumn("GoodsName", 100, JWT.LEFT, "��������");
			columns[1] = new STableColumn("GoodsProperties", 100, JWT.CENTER, "���Ϲ��");
			columns[2] = new STableColumn("StoreName", 100, JWT.CENTER, "���ֿ�");
			columns[3] = new STableColumn("Count", 100, JWT.RIGHT, "�ɹ�����");
			columns[0].setGrab(true);
			columns[1].setGrab(true);
			columns[2].setGrab(true);
			return columns;
		}

		@Override
		public STableStyle getTableStyle() {
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			tableStyle.setSelectionMode(SSelectionMode.Row);
			return tableStyle;
		}

		@Override
		public String getText(Object element, int columnIndex) {
			SelectedPurchaseItem item = ((SelectedPurchaseItem) element);
			switch (columnIndex) {
			case 0:
				return item.goodsItem.getBaseInfo().getName();
			case 1:
				return item.goodsItem.getItemData().getPropertiesWithUnit();
			case 2:
				return item.storeName;
			case 3:
				return String.valueOf(item.count);
			}
			return "";
		}

		@Override
		protected boolean hideFooterArea() {
			return true;
		}

		/**
		 * 
		 */
		public int getDecimal(Object element, int columnIndex) {
			SelectedPurchaseItem item = ((SelectedPurchaseItem) element);
			switch (columnIndex) {
			case 3:
				return item.getScale();
			}
			return -1;
		}

		@Override
		public SActionInfo getActionInfo(String actionId) {
			return PSIActionCommon.getActionInfo(actionId);
		}
		
		

	}

}
