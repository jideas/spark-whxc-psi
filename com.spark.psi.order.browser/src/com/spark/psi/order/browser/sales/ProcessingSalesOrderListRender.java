/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;

/**
 * ���д���������۶����б���ͼ
 *
 */
public class ProcessingSalesOrderListRender extends SalesOrderListRender {

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//���󲿷�
		if(!isEmployee()){
			new LWComboList(headerLeftArea,JWT.APPEARANCE3).setID(ProcessingSalesOrderListProcessor.ID_COMBOLIST_TYPE);
			new Label(headerLeftArea).setText("   ");
		}
		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea).setID(ProcessingSalesOrderListProcessor.ID_LABEL_ORDER_COUNT);
		new Label(headerLeftArea).setText("   ");
		new Label(headerLeftArea).setText("���۽�");
		new Label(headerLeftArea).setID(ProcessingSalesOrderListProcessor.ID_LABEL_SALES_AMOUNT);
		new Label(headerLeftArea).setText("   ");
		new Label(headerLeftArea).setText("�˻���");
		new Label(headerLeftArea).setID(ProcessingSalesOrderListProcessor.ID_LABEL_REJECTED_AMOUNT);
	}

	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName,
				Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate, Columns.status};
	}
}
