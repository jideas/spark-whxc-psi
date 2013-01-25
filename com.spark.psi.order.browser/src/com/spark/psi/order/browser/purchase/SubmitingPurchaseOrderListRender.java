/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;

/**
 * ���ύ�ɹ������б���ͼ
 *
 */
public class SubmitingPurchaseOrderListRender extends PurchaseOrderListRender{

	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		new Label(headerLeftArea, JWT.APPEARANCE3).setText("���ȴ������˻ض��������½��ɹ�����!");
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingPurchaseOrderListProcessor.ID_BUTTON_NEW_ORADER);
		new Label(footerLeftArea).setText("  ");
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingPurchaseOrderListProcessor.ID_BUTTON_SUBMIT_ORADER);
	}
//	
//	@Override
//	protected void afterHeaderRender() {
//		super.afterHeaderRender();
//		new Label(headerLeftArea, JWT.APPEARANCE3).setText("���ȴ������˻ض��������½��ɹ�����!");
//	}

	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.DeliveryDate, Columns.OrderNumber, Columns.PartnerName, Columns.Type, Columns.Amount, Columns.Creator, Columns.CreateDate};
	}

	@Override
	public STableStyle getTableStyle() {
		//���ø�ѡ
		STableStyle t = new STableStyle();
		t.setPageAble(false);
		t.setSelectionMode(SSelectionMode.Multi);
		return t;
	}
}
