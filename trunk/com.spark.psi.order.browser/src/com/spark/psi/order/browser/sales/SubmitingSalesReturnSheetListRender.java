/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;

/**
 * 待提交销售退货单列表视图
 *
 */
public class SubmitingSalesReturnSheetListRender extends SalesOrderListRender {
	
	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();	
					
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingSalesReturnSheetListProcessor.ID_BUTTON_SALESRETURN);		
		new Label(footerLeftArea).setText(" ");
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingSalesReturnSheetListProcessor.ID_BUTTON_SUBMIT_SALESRETURN);		
		new Label(footerLeftArea,JWT.APPEARANCE3).setText(" ");
		new Button(footerLeftArea,JWT.APPEARANCE3).setID(SubmitingSalesReturnSheetListProcessor.ID_BUTTON_DELETE_SALESRETURN);		
	}

	public STableStyle getTableStyle() {
		STableStyle tabStyle = new STableStyle();
		tabStyle.setPageAble(false);
		tabStyle.setSelectionMode(SSelectionMode.Multi);//设置多选样式
		return tabStyle;
	}

	@Override
	protected Columns[] getColumnsEnumList() {
		return new Columns[]{Columns.CreateDate, Columns.OrderNumber, Columns.PartnerName,
				Columns.Amount, Columns.Creator, Columns.status};
	}
}