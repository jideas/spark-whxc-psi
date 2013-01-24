package com.spark.psi.inventory.browser.query;

import java.text.DecimalFormat;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.inventory.entity.KitInventoryDetail;

/**
 * 其他物品库存查询视图
 */
public class KitInventoryQueryRender extends PSIListPageRender {
	private LWComboList storeList;

	@Override
	public STableStyle getTableStyle() {
		STableStyle sTableStyle = new STableStyle();
//		sTableStyle.setSortAll(true);
		return sTableStyle;
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new LWComboList(headerLeftArea, JWT.APPEARANCE3)
				.setID(KitInventoryQueryProcessor.ID_COMBOLIST_STORE);
		new Label(headerLeftArea).setText("  物品数量：");
		// Label lbl = new Label(headerLeftArea);
		// lbl.setID(KitInventoryQueryProcessor.ID_LABEL_KITINVENTORYDETAIL_COUNT);
		// lbl.setText("  ");
		new Label(headerLeftArea)
				.setID(KitInventoryQueryProcessor.ID_LABEL_KITINVENTORYDETAIL_COUNT);
		new SSearchText2(headerRightArea)
				.setID(KitInventoryQueryProcessor.ID_TEXT_SEARCH);
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn(
				KitInventoryQueryProcessor.Columns.KitName.name(), 0, JWT.LEFT,
				"物品名称");
		columns[1] = new STableColumn(
				KitInventoryQueryProcessor.Columns.KitDesc.name(), 0, JWT.LEFT,
				"物品描述");
		columns[2] = new STableColumn(KitInventoryQueryProcessor.Columns.Unit
				.name(), 80, JWT.CENTER, "单位");
		columns[3] = new STableColumn(KitInventoryQueryProcessor.Columns.Count
				.name(), 120, JWT.RIGHT, "物品数量");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		return columns;
	}

	public String getText(Object element, int columnIndex) {
		if (element instanceof KitInventoryDetail) {
			KitInventoryDetail item = (KitInventoryDetail) element;
			switch (columnIndex) {
			case 0:
				return item.getKitName();
			case 1:
				return item.getKitDesc();
			case 2:
				return item.getUnit();
			case 3:
				storeList = this.createControl(
						KitInventoryQueryProcessor.ID_COMBOLIST_STORE,
						LWComboList.class, JWT.NO);
				GUID storeId = null;
				if (CheckIsNull.isNotEmpty(storeList.getText())
						&& !GUID.valueOf(storeList.getText()).equals(
								GUID.emptyID)) {
					storeId = GUID.valueOf(storeList.getText());
				}
				if (null == storeId && isBoss()) {
					return StableUtil.toLink(
							KitInventoryQueryProcessor.ID_viewInventory,
							"", DoubleUtil.getRoundStr(item.getCount()));
				}
				return getRoundStr(item.getCount());
			default:
				break;
			}
		}
		return "";
	}

	private boolean isBoss() {
		return getContext().find(Boolean.class, Auth.Boss);
	}

	@Override
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
		case 3:
			return 0;
		default:
			return -1;
		}
	}

	public static String getRoundStr(Double value) {
		if (null == value) {
			return "";
		}
		return new DecimalFormat("###,##0").format(value);
	}
}