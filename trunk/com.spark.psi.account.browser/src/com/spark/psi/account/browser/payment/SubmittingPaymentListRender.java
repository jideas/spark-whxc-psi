package com.spark.psi.account.browser.payment;

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
import com.spark.psi.publish.account.entity.PaymentItem;
import com.spark.common.components.table.edit.SEditTableStyle;

public class SubmittingPaymentListRender extends PSIListPageRender {

	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("单据数量：");
		new Label(headerLeftArea).setID(SubmittingPaymentListProcessor.ID_Label_Count);
		new SSearchText2(headerRightArea).setID(SubmittingPaymentListProcessor.ID_Search);
		Button button = new Button(footerLeftArea, JWT.APPEARANCE2);
		button.setText(" 新增付款 ");
		button.setID(SubmittingPaymentListProcessor.ID_Button_New);
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(SubmittingPaymentListProcessor.ColumnName.payDate.name(), 120, JWT.CENTER, "付款日期");
		columns[1] = new STableColumn(SubmittingPaymentListProcessor.ColumnName.sheetNo.name(), 150, JWT.LEFT, "单据编号");
		columns[2] = new STableColumn(SubmittingPaymentListProcessor.ColumnName.partnerName.name(), 120, JWT.LEFT, "付款对象");
		columns[3] = new STableColumn(SubmittingPaymentListProcessor.ColumnName.payType.name(), 120, JWT.CENTER, "付款类型");
		columns[4] = new STableColumn(SubmittingPaymentListProcessor.ColumnName.applyAmount.name(), 120, JWT.RIGHT, "申请金额");
		columns[5] = new STableColumn(SubmittingPaymentListProcessor.ColumnName.applier.name(), 120, JWT.CENTER, "申请人");
		for (int index = 0; index < 5; index++) {
			columns[index].setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		PaymentItem item = (PaymentItem)element;
		switch(columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getPayDate());
		case 1:
			return StableUtil.toLink(Action.Detail.name(), "", item.getPaymentNo());
		case 2:
			return item.getPartnerName();
		case 3:
			return item.getPaymentType().getName();
		case 4:
			return DoubleUtil.getRoundStr(item.getAmount());
//		case 5:
//			return DoubleUtil.getRoundStr(item.get)
		case 5:
			return item.getCreator();
		}
		return null;
	}

	public STableStyle getTableStyle() {
		SEditTableStyle style = new SEditTableStyle();
		style.setPageAble(false);
		return style;
	}
}
