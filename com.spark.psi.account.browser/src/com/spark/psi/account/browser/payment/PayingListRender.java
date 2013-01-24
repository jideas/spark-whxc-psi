package com.spark.psi.account.browser.payment;

import com.jiuqi.dna.ui.common.constants.JWT;
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

/**
 * �������¼��ͼ
 * 
 */
public class PayingListRender extends PSIListPageRender {
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		//����
		new Label(headerLeftArea).setText("����������");		
		new Label(headerLeftArea).setID(PayingListProcessor.ID_LABEL_COUNT);		
		//����
		new SSearchText2(headerRightArea).setID(PayingListProcessor.ID_SSearch);
	}
	
	public STableColumn[] getColumns() {
		
		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(PayingListProcessor.ColumnName.payDate.name(), 120, JWT.CENTER, "��������");
		columns[1] = new STableColumn(PayingListProcessor.ColumnName.sheetNo.name(), 150, JWT.LEFT, "���ݱ��");
		columns[2] = new STableColumn(PayingListProcessor.ColumnName.partnerName.name(), 120, JWT.LEFT, "�������");
		columns[3] = new STableColumn(PayingListProcessor.ColumnName.payType.name(), 120, JWT.CENTER, "��������");
		columns[4] = new STableColumn(PayingListProcessor.ColumnName.applyAmount.name(), 120, JWT.RIGHT, "������");
		columns[5] = new STableColumn(PayingListProcessor.ColumnName.applier.name(), 120, JWT.CENTER, "������");
		for (int index = 0; index < 5; index++) {
			columns[index].setGrab(true);
		}
		return columns;
	}

	
	@Override
	public STableStyle getTableStyle() {
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		return tableStyle;
	}

	
	
	@Override
	public String getToolTipText(Object element, int columnIndex) {
		PaymentItem item = (PaymentItem)element;
		switch(columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getPayDate());
		case 1:
			return null;
			//return StableUtil.toLink(Action.Detail.name(), "", item.getPaymentNo());
		case 2:
			return item.getPartnerName();
		case 3:
			return item.getPaymentType().getName();
		case 4:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 5:
			return item.getCreator();
		default:
			return null;
		}
	}

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
		case 5:
			return item.getCreator();
		default:
			return null;
		}
	}
}
