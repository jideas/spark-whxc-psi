/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.order.browser.delivery.DeliverDetailPageProcessor;
import com.spark.psi.order.browser.util.OrderDetailProcessor.View;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.order.entity.SalesOrderInfo;

/**
 * 
 *
 */
public class SalesOrderDetailRender extends AbstractSalesOrderDetailRender {

	protected SalesOrderInfo salesOrderInfo;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		this.salesOrderInfo = ((SalesOrderDetailProcessor)this.processor).orderInfo;
	}
	
	
	@Override
	protected int getBaseInfoAreaRowCount() {
		return 2;
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
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		Label creditInfoLabel = null;
		if (row == 0 && column == 0) {
			GridLayout gl = new GridLayout();
			baseInfoArea.setLayout(gl);
			//
			Composite customerInfoArea = new Composite(baseInfoArea);
			customerInfoArea.setID(SalesOrderDetailProcessor.ID_CustomerInfo_Area);
			//
			if (View.Look == viewEnum) {
				new Label(baseInfoArea).setText("  交货日期："+DateUtil.dateFromat(salesOrderInfo.getDeliveryDate()));
			} else {
				Label selectButton = new Label(baseInfoArea);
				selectButton.setID(SalesOrderDetailProcessor.ID_CustomerSelect_Button);
				selectButton.setText("  重新选择客户");
				selectButton.setCursor(Cursor.HAND);
				selectButton.setForeground(Color.COLOR_BLUE);
				
				Label label = new Label(baseInfoArea);
				label.setText("  请选择交货日期：");
				//
				SDatePicker datePicker = new SDatePicker(baseInfoArea);
				datePicker.setID(SalesOrderDetailProcessor.ID_DeliveryDate);
			}
			//
			gl.numColumns = baseInfoArea.getChildrenCount();
		} else if (row == 0 && column == 1) {
			//
//			if (View.Look == viewEnum) {
//				
//			} else {
			if(salesOrderInfo.getOrderStatus() == null || salesOrderInfo.getOrderStatus().isIn(OrderStatus.Submit,OrderStatus.Approval_Yes,OrderStatus.Approval_No,OrderStatus.Denied)){
				new Label(baseInfoArea).setID(SalesOrderDetailProcessor.ID_CreditAmountLabel);
				creditInfoLabel = new Label(baseInfoArea);
				creditInfoLabel.setID(SalesOrderDetailProcessor.ID_CreditInfoLabel);
			}
//			}
		} else if (row == 1 && column == 0) {
			baseInfoArea.setID(SalesOrderDetailProcessor.ID_DeliveryInfo_Area);
		} else if (row == 1 && column == 1) {
			if (View.Look == viewEnum) {
				baseInfoArea.setLayout(new GridLayout(2));
				Label label = new Label(baseInfoArea);
				label.setID(SalesOrderDetailProcessor.ID_OrderStatusLabel);
				label  = new Label(baseInfoArea);
				label.setID(SalesOrderDetailProcessor.ID_CheckInfoLabel);
				label.setCursor(Cursor.HAND);
				label.setForeground(new Color(0x77A3DD));
			} else if(null == creditInfoLabel){
				new Label(baseInfoArea).setID(SalesOrderDetailProcessor.ID_CreditInfoLabel);
			}	
		}
	}

}
