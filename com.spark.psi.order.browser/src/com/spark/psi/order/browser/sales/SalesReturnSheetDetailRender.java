/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.order.browser.util.OrderDetailProcessor.View;

/**
 * 销售退货单明细视图
 * 
 */
public class SalesReturnSheetDetailRender extends
		AbstractSalesReturnSheetDetailRender {

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 1;
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0 && column == 0) {
			if (viewEnum == View.Look) {
				baseInfoArea
						.setID(SalesReturnSheetDetailProcessor.ID_CustomerInfo_Area);
			} else {
				baseInfoArea.setLayout(new GridLayout(2));
				//
				new Composite(baseInfoArea).setID(SalesReturnSheetDetailProcessor.ID_CustomerInfo_Area);
				//
				Label selectButton = new Label(baseInfoArea);
				selectButton.setID(SalesOrderDetailProcessor.ID_CustomerSelect_Button);
				selectButton.setText("  重新选择客户");
				selectButton.setCursor(Cursor.HAND);
				selectButton.setForeground(Color.COLOR_BLUE);
			}

		} else if (row == 0 && column == 1) {
			// 状态和入库记录
			if (View.Look == viewEnum) {
				baseInfoArea.setLayout(new GridLayout(2));
				Label label = new Label(baseInfoArea);
				label.setID(SalesReturnSheetDetailProcessor.ID_OrderStatusLabel);
				label  = new Label(baseInfoArea);
				label.setID(SalesReturnSheetDetailProcessor.ID_CheckInfoLabel);
				label.setCursor(Cursor.HAND);
				label.setForeground(new Color(0x77A3DD));
			}	
		} else if (row == 1 && column == 0) { 
		}
	}

}
