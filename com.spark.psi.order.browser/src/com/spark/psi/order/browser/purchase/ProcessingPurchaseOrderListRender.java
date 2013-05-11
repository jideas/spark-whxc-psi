/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;

/**
 * ���д�����Ĳɹ������б���ͼ
 *
 */
public class ProcessingPurchaseOrderListRender extends PurchaseOrderListRender {
	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		if(!isEmployee()){
			new LWComboList(headerLeftArea,JWT.APPEARANCE3).setID(ProcessingPurchaseOrderListProcessor.ID_COMBOLIST_TYPE);
			new Label(headerLeftArea).setText("  ");
		}
		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea).setID(ProcessingPurchaseOrderListProcessor.ID_LABEL_ORDER_COUNT);
		new Label(headerLeftArea).setText("  ");
		new Label(headerLeftArea).setText("�ɹ���");
		new Label(headerLeftArea).setID(ProcessingPurchaseOrderListProcessor.ID_LABEL_PURCHASE_AMOUNT);
		new Label(headerLeftArea).setText("  ");
		new Label(headerLeftArea).setText("�˻���");
		new Label(headerLeftArea).setID(ProcessingPurchaseOrderListProcessor.ID_LABEL_REJECTED_AMOUNT);
	}

	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName, Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate, Columns.status};
	}
	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}
}
