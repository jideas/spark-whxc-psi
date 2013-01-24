package com.spark.psi.account.browser.receipt;

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
import com.spark.psi.publish.account.entity.ReceiptItem;

/**
 * �����е��տ�֪ͨ��
 */
public class ReceiptsListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new SSearchText2(headerRightArea).setID(ReceiptsListProcessor.ID_Search);
		new Label(headerLeftArea).setText("����������");
		new Label(headerLeftArea).setID(ReceiptsListProcessor.ID_Label_Count);
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[5];
		columns[0] = new STableColumn(ReceiptsListProcessor.ColumnName.planDate.name(), 120, JWT.CENTER, "Ԥ���տ�����");
		columns[1] = new STableColumn(ReceiptsListProcessor.ColumnName.sheetNo.name(), 120, JWT.LEFT, "���ݱ��");
		columns[2] = new STableColumn(ReceiptsListProcessor.ColumnName.partnerName.name(), 120, JWT.LEFT, "�տ����");
		columns[3] = new STableColumn(ReceiptsListProcessor.ColumnName.receiptType.name(), 120, JWT.CENTER, "�տ�����");
		columns[4] = new STableColumn(ReceiptsListProcessor.ColumnName.receiptAmount.name(), 120, JWT.RIGHT, "�տ���");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		ReceiptItem item = (ReceiptItem) element;
		switch (columnIndex) {
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
		default:
			return "";
		}
	}
}
