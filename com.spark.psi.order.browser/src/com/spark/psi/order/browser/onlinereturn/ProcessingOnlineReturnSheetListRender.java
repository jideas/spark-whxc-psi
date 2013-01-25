/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.order.browser.onlinereturn.OnlineReturnSheetListProcessor.OlReturnColumns;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;

/**
 * 待提交销售退货单列表视图
 * 
 */
public class ProcessingOnlineReturnSheetListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender(); 
		new Label(headerLeftArea).setText("单据数量：");
		new Label(headerLeftArea).setID(ProcessingOnlineReturnSheetListProcessor.ID_Label_Count);
		new SSearchText2(headerRightArea).setID(ProcessingOnlineReturnSheetListProcessor.ID_Search);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn(OlReturnColumns.billsNo.name(), 150, JWT.LEFT, OlReturnColumns.billsNo .title());
		columns[1] = new STableColumn(OlReturnColumns.OnlineBillsNo.name(), 150, JWT.LEFT, OlReturnColumns.OnlineBillsNo.title());
		columns[2] = new STableColumn(OlReturnColumns.Customer.name(), 180, JWT.LEFT, OlReturnColumns.Customer .title());
		columns[3] = new STableColumn(OlReturnColumns.Amount.name(), 120, JWT.RIGHT, OlReturnColumns.Amount .title());
		columns[4] = new STableColumn(OlReturnColumns.CreateDate.name(), 120, JWT.CENTER, OlReturnColumns.CreateDate.title());
		columns[5] = new STableColumn(OlReturnColumns.Creator.name(), 120, JWT.CENTER, OlReturnColumns.Creator .title());
		columns[6] = new STableColumn(OlReturnColumns.Status.name(), 120, JWT.CENTER, OlReturnColumns.Status .title());
		columns[2].setGrab(true);
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		OnlineReturnItem item = (OnlineReturnItem) element;
		if (null == item) {
			return null;
		}
		switch (columnIndex) {
		case 0:
			return StableUtil.toLink(Action.Detail.name(), "", item.getBillsNo());
		case 1:
			return item.getOnlineBillsNo();
		case 2:
			return item.getCustomer();
		case 3:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 4:
			return DateUtil.dateFromat(item.getCreateDate());
		case 5:
			return item.getCreator();
		case 6:
			return item.getStatus().getTitle();
		
		}
		return "";
	}

	@Override
	public STableStyle getTableStyle() {
		STableStyle tabStyle = new STableStyle();
//		tabStyle.setSelectionMode(SSelectionMode.Multi);
		return tabStyle;
	}
}