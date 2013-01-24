package com.spark.psi.base.browser.material;

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
import com.spark.psi.base.browser.PSIActionCommon;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.material.MaterialsSelectProcessor.Item;
import com.spark.psi.base.browser.material.MaterialsSelectProcessor.MaterialItemListProcessor;
import com.spark.psi.base.browser.material.MaterialsSelectProcessor.SelectedMaterialItemListProcessor;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.entity.InventoryItem;

public class MaterialsSelectRender extends MaterialCategoryFramePageRender {
	protected void doRender() {
		super.doRender();
	}

	/**
	 * ��Ʒ��Ŀ��ͼ
	 */
	public final static class MaterialItemListRender extends
			PSIGoodsListPageRender {

		@Override
		protected void afterFooterRender() {
			super.afterFooterRender();

			// ��ѡ��Ʒ����
			Composite selectedGoodsArea = new Composite(headerLeftArea);
			selectedGoodsArea.setID(MaterialItemListProcessor.ID_Area_Selected);
			GridLayout gl = new GridLayout();
			gl.numColumns = 3;
			gl.horizontalSpacing = 0;
			selectedGoodsArea.setLayout(gl);
			Label label = new Label(selectedGoodsArea);
			label.setText("��ѡ����");
			label.setCursor(Cursor.HAND);
			label.setForeground(Color.COLOR_BLUE);
			label = new Label(selectedGoodsArea, JWT.CENTER);
			label.setID(MaterialItemListProcessor.ID_Label_SelectedCount);
			label.setCursor(Cursor.HAND);
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER
					| GridData.GRAB_HORIZONTAL);
			gd.widthHint = 30;
			label.setLayoutData(gd);
			label.setForeground(Color.COLOR_RED);
			label = new Label(selectedGoodsArea);
			label.setForeground(Color.COLOR_BLUE);
			label.setText("��");
			label.setCursor(Cursor.HAND);

			//
			Button button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setID(MaterialItemListProcessor.ID_Button_ConfirmSelect);
			button.setText(" ���ѡ�� ");
			new Label(footerRightArea).setText(" ");
			button = new Button(footerRightArea, JWT.APPEARANCE3);
			button.setID(MaterialItemListProcessor.ID_Button_CancelSelect);
			button.setText(" ȡ��ѡ�� ");
		}

		public STableColumn[] getColumns() {
			STableColumn[] columns = new STableColumn[2];
			columns[0] = new STableColumn("GoodsName", 100, JWT.LEFT, "��������");
			columns[1] = new STableColumn("GoodsProperties", 100, JWT.CENTER,
					"���");
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
					return item.itemData.getMaterialSpec();
				}
			} else if (element instanceof InventoryItem) {
				InventoryItem item = (InventoryItem) element;
				switch (columnIndex) {
				case 0:
					return item.getName();
				case 1:
					return item.getSpec();
				}
			}
			return "";
		}
		
		/**
		 * ��װ��Ʒ���Ժ͵�λ
		 * @param properties ����ֵ
		 * @param unit ��λ
		 * @return [properties] / [unit]
		 */
//		private String getPropertiesAndUnit(String properties, String unit){
//			if(StringHelper.isNotEmpty(properties) && StringHelper.isNotEmpty(unit)){
//				return properties + "/" + "[" + unit + "]";
//			}
//			if(StringHelper.isNotEmpty(unit)){
//				return "[" + unit + "]";
//			}
//			return properties;
//		}
	}

	/**
	 * ��ѡ��Ʒ��Ŀ��ͼ
	 */
	public final static class SelectedMaterialItemListRender extends
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
			// label.setText("��ѡ��� 0 ��Ʒ");
			// label.setLayoutData(gd);

			Label label = new Label(headerArea);
			label.setID(SelectedMaterialItemListProcessor.ID_Label_Clear);
			label.setText("���");
			label.setCursor(Cursor.HAND);
			label.setFont(new Font(9, "����", JWT.FONT_STYLE_UNDERLINE));
			label.setForeground(Color.COLOR_BLUE);
			label.setLayoutData(GridData.INS_CENTER_VERTICAL);
		}

		public STableColumn[] getColumns() {
			STableColumn[] columns = new STableColumn[2];
			columns[0] = new STableColumn("GoodsName", 100, JWT.LEFT, "��������");
			columns[1] = new STableColumn("GoodsProperties", 100, JWT.CENTER,
					"���");
			columns[0].setGrab(true);
			columns[1].setGrab(true);
			return columns;
		}

		public STableStyle getTableStyle() {
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			tableStyle.setSelectionMode(SSelectionMode.Row);
			return tableStyle;
		}

		public String getText(Object element, int columnIndex) {
			MaterialsItemInfo item = ((MaterialsItemInfo) element);
			switch (columnIndex) {
			case 0:
				return item.getBaseInfo().getName();
			case 1:
				return item.getItemData().getPropertiesWithUnit();
			}
			return "";
		}

		public SActionInfo getActionInfo(String actionId) {
			return PSIActionCommon.getActionInfo(actionId);
		}
		
		protected boolean hideFooterArea() {
			return true;
		}

	}
}
