/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.order.browser.delivery.DeliverDetailPageProcessor;
import com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns;
import com.spark.psi.order.browser.util.OrderDetailProcessor.View;
import com.spark.psi.order.browser.util.OrderDetailRender;
import com.spark.psi.publish.order.entity.PurchaseOrderGoodsItem;

/**
 * 采购退货明细视图
 * 
 */
public class PurchaseOrderDetailRender extends OrderDetailRender {

	@Override
	protected Cloumns[] getColumnsEnumList() {
		return new Cloumns[] { Cloumns.GoodsItemCode, Cloumns.GoodsNo, Cloumns.GoodsName, Cloumns.GoodsProperties,
				Cloumns.GoodsUnit, Cloumns.Count, Cloumns.Price, Cloumns.Amount };
	}

	/**
	 * 
	 */
	public int getDecimal(Object element, int columnIndex) {
		switch (columnIndex) {
		case 5:
			PurchaseOrderGoodsItem item = (PurchaseOrderGoodsItem) element;
			return item.getScale();
		case 6:
			return 2;
		case 7:
			return 2;
		}
		return -1;
	}
	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Composite hideArea = new Composite(sheetButtonArea);
		GridData gdHide = new GridData();
		gdHide.exclude = true;
		hideArea.setLayoutData(gdHide);
		hideArea.setVisible(false);
		hideArea.setID(DeliverDetailPageProcessor.ID_Area_Hide);
		super.renderSheetButtonArea(sheetButtonArea);
	}
	@Override
	protected String getText(Object element, Cloumns columnEnum) {
		return super.getText(element, columnEnum);
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 2;
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row, int column) {
		if (row == 0 && column == 0) {
			baseInfoArea.setLayout(new GridLayout(2));
			// 供应商信息和联系人信息区域
			Composite supplierInfoArea = new Composite(baseInfoArea);
			supplierInfoArea.setID(PurchaseOrderDetailProcessor.ID_SupplierInfo_Area);
			Label lb = new Label(baseInfoArea);
			lb.setID(PurchaseOrderDetailProcessor.ID_DeliveryDate_Label);
			
			GridData gd = new GridData();
			gd.widthHint = 180;
			lb.setLayoutData(gd);
			
		} else if (row == 1 && column == 0) {
			baseInfoArea.setLayout(new GridLayout(2));
			//
			new Label(baseInfoArea).setID(PurchaseOrderDetailProcessor.ID_SourceName_Label);
			// 收货地址区域
			Composite deliveryInfoArea = new Composite(baseInfoArea);
			deliveryInfoArea.setID(PurchaseOrderDetailProcessor.ID_DeliveryInfo_Area);
		} else if (row == 1 && column == 1) {
			// 状态和入库记录
			if (View.Look == viewEnum) {
				baseInfoArea.setLayout(new GridLayout(2));
				Label label = new Label(baseInfoArea);
				label.setID(PurchaseOrderDetailProcessor.ID_OrderStatusLabel);
				label = new Label(baseInfoArea);
				label.setID(PurchaseOrderDetailProcessor.ID_CheckInfoLabel);
				label.setCursor(Cursor.HAND);
				label.setForeground(new Color(0x77A3DD));
			}
		}
	}

	@Override
	protected String getOrderTotalAmountTitle() {
		return "订单金额";
	}
}
