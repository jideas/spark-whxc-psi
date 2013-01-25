/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.order.browser.purchase.PurchasingGoodsListProcessor.Columns;
import com.spark.psi.publish.order.entity.PurchaseGoodsItem;

/**
 * 待提交采购订单列表视图
 * 
 */
public class PurchasingGoodsListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		// 上左边
		Label label = new Label(headerLeftArea);
		label.setText("采购需求：");

		label = new Label(headerLeftArea);
		label.setID(PurchasingGoodsListProcessor.ID_LABEL_ORDER_COUNT);

		// 下左边
		label = new Label(footerLeftArea);
		label.setText("请从 ");
		label.setLayoutData(GridData.INS_CENTER_VERTICAL);

		Button button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" 库存预警 ");
		button.setID(PurchasingGoodsListProcessor.ID_BUTTON_INVENTORY_PREWARNING);
		label = new Label(footerLeftArea);
		label.setText(" 或 ");
		label.setLayoutData(GridData.INS_CENTER_VERTICAL);

		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" 全部材料 ");
		button.setID(PurchasingGoodsListProcessor.ID_BUTTON_All_GOODS);
		label = new Label(footerLeftArea);
		label.setText(" 中添加采购需求，然后 ");
		label.setLayoutData(GridData.INS_CENTER_VERTICAL);

		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" 指定供应商 ");
		button.setID(PurchasingGoodsListProcessor.ID_BUTTON_SET_PROVIDER);
		button.setEnabled(false);
		label = new Label(footerLeftArea);
		label.setText(" 最后 ");
		label.setLayoutData(GridData.INS_CENTER_VERTICAL);

		button = new Button(footerLeftArea, JWT.APPEARANCE3);
		button.setText(" 生成采购订单 ");
		button.setID(PurchasingGoodsListProcessor.ID_BUTTON_CREATE_ORADER);

	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[9];
		columns[0] = new STableColumn(Columns.GoodsCode.name(), 100, JWT.CENTER, "材料编号");
		columns[0].setGrab(true);
		columns[1] = new STableColumn(Columns.GoodsCode.name(), 100, JWT.CENTER, "材料条码");
		columns[1].setGrab(true);
		columns[2] = new STableColumn(Columns.GoodsName.name(), 100, JWT.CENTER, "材料名称");
		columns[2].setGrab(true);
		columns[3] = new STableColumn(Columns.GoodsProperties.name(), 100, JWT.CENTER, "材料规格");
		columns[3].setGrab(true);
		columns[4] = new STableColumn(Columns.GoodsUnit.name(), 50, JWT.CENTER, "单位");
		columns[4].setGrab(true);
		columns[5] = new STableColumn(Columns.PurchasingCount.name(), 60, JWT.CENTER, "采购数量");
		columns[5].setGrab(true);
		columns[6] = new STableColumn(Columns.StoreName.name(), 100, JWT.CENTER, "仓库");
		columns[6].setGrab(true);
		columns[7] = new STableColumn(Columns.Supplier.name(), 100, JWT.CENTER, "供应商");
		columns[7].setGrab(true);
		columns[8] = new SNumberEditColumn(Columns.PurchasePrice.name(), 60, JWT.RIGHT, "采购单价");
		columns[8].setGrab(true);
		return columns;
	}

	@Override
	public STableStyle getTableStyle() {
		// 设置复选
		STableStyle t = new STableStyle();
		t.setPageAble(false);
		t.setSelectionMode(SSelectionMode.Multi);
		return t;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		PurchaseGoodsItem item = (PurchaseGoodsItem) element;
		switch (columnIndex) {
		case 0:
			return item.getGoodsCode();
		case 1:
			return item.getGoodsNo();
		case 2:
			return item.getGoodsName();
		case 3:
			return item.getProperties();
		case 4:
			return item.getUnit();
		case 5:
			return DoubleUtil.getRoundStr(item.getCount());
		case 6:
			return item.getStoreName();
		case 7:
			return item.getSupplierShorName();
		case 8:
			return item.getPrice() < 0 ? "" : DoubleUtil.getRoundStr(item.getPrice());
		default:
			return "";
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 6:
			return ((PurchaseGoodsItem) element).getSupplierName();
		default:
			return super.getToolTipText(element, columnIndex);
		}
	}
}
