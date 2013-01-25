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
 * ���ύ�ɹ��˻����б���ͼ
 *
 */
public class SubmitingPurchaseReturnSheetListRender extends PurchaseOrderListRender {
	
	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();	
					
		Button create = new Button(footerLeftArea,JWT.APPEARANCE3);
		create.setID(SubmitingPurchaseReturnSheetListProcessor.ID_BUTTON_APPLYRETURN);
		create.setText(" �����˻� ");
		new Label(footerLeftArea).setText(" ");
		Button submit = new Button(footerLeftArea,JWT.APPEARANCE3);
		submit.setID(SubmitingPurchaseReturnSheetListProcessor.ID_BUTTON_SUBMIT_APPLYRETURN);
		submit.setText(" �ύ���� ");
		submit.setEnabled(false);
		new Label(footerLeftArea).setText(" ");
		Button delete = new Button(footerLeftArea,JWT.APPEARANCE3);
		delete.setID(SubmitingPurchaseReturnSheetListProcessor.ID_BUTTON_DELETE_APPLYRETURN);
		delete.setText(" ɾ������ ");
		delete.setEnabled(false);
	}
	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.CreateDate, Columns.OrderNumber, Columns.PartnerName, Columns.Amount, Columns.Creator, Columns.status};
	}

	@Override
	public STableStyle getTableStyle() {
		STableStyle tabStyle = new STableStyle();
		tabStyle.setPageAble(false);
		tabStyle.setSelectionMode(SSelectionMode.Multi);//���ö�ѡ��ʽ
		return tabStyle;
	}
}