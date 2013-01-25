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
 * 待提交采购退货单列表视图
 *
 */
public class SubmitingPurchaseReturnSheetListRender extends PurchaseOrderListRender {
	
	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();	
					
		Button create = new Button(footerLeftArea,JWT.APPEARANCE3);
		create.setID(SubmitingPurchaseReturnSheetListProcessor.ID_BUTTON_APPLYRETURN);
		create.setText(" 申请退货 ");
		new Label(footerLeftArea).setText(" ");
		Button submit = new Button(footerLeftArea,JWT.APPEARANCE3);
		submit.setID(SubmitingPurchaseReturnSheetListProcessor.ID_BUTTON_SUBMIT_APPLYRETURN);
		submit.setText(" 提交申请 ");
		submit.setEnabled(false);
		new Label(footerLeftArea).setText(" ");
		Button delete = new Button(footerLeftArea,JWT.APPEARANCE3);
		delete.setID(SubmitingPurchaseReturnSheetListProcessor.ID_BUTTON_DELETE_APPLYRETURN);
		delete.setText(" 删除申请 ");
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
		tabStyle.setSelectionMode(SSelectionMode.Multi);//设置多选样式
		return tabStyle;
	}
}