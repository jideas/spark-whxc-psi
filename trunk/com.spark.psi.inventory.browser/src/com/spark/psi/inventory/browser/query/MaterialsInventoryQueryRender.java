package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.material.MaterialCategoryFramePageRender;
import com.spark.psi.inventory.browser.query.MaterialsInventoryQueryProcessor.MaterialInventoryQueryListProcessor;
import com.spark.psi.publish.inventory.entity.InventoryItem;

public class MaterialsInventoryQueryRender extends MaterialCategoryFramePageRender {

	/**
	 * ��Ʒ����ѯ��ͼ
	 */
	public final static class MaterialInventoryQueryListRender extends PSIGoodsListPageRender {

		@Override
		protected void afterFooterRender() {
			super.afterFooterRender();
			//
			new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(MaterialInventoryQueryListProcessor.ID_COMBOLIST_STORE);
			//
			new Label(headerLeftArea).setText("  ����������");
			//
			Label label = new Label(headerLeftArea);
			label.setID(MaterialInventoryQueryListProcessor.ID_LABEL_GOODSINVENTORY_COUNT);
			GridData gd = new GridData();
			gd.widthHint = 100;
			label.setLayoutData(gd);
		}

		public STableColumn[] getColumns() {

			STableColumn[] columns = new STableColumn[7];

			// ��Ҫ��SheetId ��ȡ������ID
			columns[0] = new STableColumn(MaterialsInventoryQueryProcessor.Cloumns.materialCode.name(), 150, JWT.LEFT, "���");
			columns[1] = new STableColumn(MaterialsInventoryQueryProcessor.Cloumns.materialNumber.name(), 150, JWT.LEFT, "����");
			columns[2] = new STableColumn(MaterialsInventoryQueryProcessor.Cloumns.materialName.name(), 150, JWT.LEFT, "��������");
			columns[3] = new STableColumn(MaterialsInventoryQueryProcessor.Cloumns.unit.name(), 100, JWT.CENTER, "��λ");
			columns[4] = new STableColumn(MaterialsInventoryQueryProcessor.Cloumns.shelfLife.name(), 100, JWT.CENTER, "������");
			columns[5] = new STableColumn(MaterialsInventoryQueryProcessor.Cloumns.spec.name(), 100, JWT.LEFT, "���");
			columns[6] = new STableColumn(MaterialsInventoryQueryProcessor.Cloumns.count.name(), 100, JWT.RIGHT, "��������");
			//			
			columns[2].setGrab(true);
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
			switch (columnIndex) {
			case 0:
				return item.getCode();
			case 1:
				return item.getNumber();
			case 2:
				return item.getName();
			case 3:
				return item.getUnit();
			case 4:
				return item.getShelfLife() + "��";
			case 5:
				return item.getSpec();
			case 6:
					return StableUtil.toLink(MaterialInventoryQueryListProcessor.ID_viewInventory, "", DoubleUtil.getRoundStr(item.getCount(), 2));
			default:
				return "";
			}
		}

		@Override
		public String getToolTipText(Object element, int columnIndex) {
			InventoryItem item = (InventoryItem) element;
			switch (columnIndex) {
			case 2:
				return item.getName();
			case 5:
				return item.getSpec();
			default:
				return null;
			}
		}

		/**
		 * ��þ���
		 */
		@Override
		public int getDecimal(Object element, int columnIndex) {
			if (element instanceof InventoryItem) {
				InventoryItem item = (InventoryItem) element;
				switch (columnIndex) {
				case 6:
					return item.getScale();
				}
			}
			return -1;
		} 
	}

}
