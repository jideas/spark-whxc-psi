package com.spark.psi.account.browser.receipt;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.common.components.table.edit.SEditTableStyle;

public class SubmittingReceiptListRender extends PSIListPageRender {

	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("单据数量：");
		new Label(headerLeftArea).setID(SubmittingReceiptListProcessor.ID_Label_Count);
		
		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setText(" 新增收款 ");
		button.setID(SubmittingReceiptListProcessor.ID_Button_New);
		
		new SSearchText2(headerRightArea).setID(SubmittingReceiptListProcessor.ID_Search);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(SubmittingReceiptListProcessor.ColumnName.planDate.name(), 120, JWT.CENTER, "预计收款日期");
		columns[1] = new STableColumn(SubmittingReceiptListProcessor.ColumnName.sheetNo.name(), 150, JWT.LEFT, "单据编号");
		columns[2] = new STableColumn(SubmittingReceiptListProcessor.ColumnName.partnerName.name(), 120, JWT.LEFT, "收款对象");
		columns[3] = new STableColumn(SubmittingReceiptListProcessor.ColumnName.receiptType.name(), 120, JWT.CENTER, "收款类型");
		columns[4] = new STableColumn(SubmittingReceiptListProcessor.ColumnName.receiptAmount.name(), 120, JWT.RIGHT, "收款金额");
		
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		ReceiptItem item = (ReceiptItem)element;
		switch(columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getReceiptDate());
		case 1:
			return StableUtil.toLink(Action.Detail.name(), "", item.getReceiptsNo());
		case 2:
			return item.getPartnerName();
		case 3:
			return item.getReceiptType().getName();
		case 4:
			return DoubleUtil.getRoundStr(item.getAmount()); 
		}
		return null;
	}

	public STableStyle getTableStyle() {
		SEditTableStyle style = new SEditTableStyle();
		style.setPageAble(false);
		return style;
	}
}
