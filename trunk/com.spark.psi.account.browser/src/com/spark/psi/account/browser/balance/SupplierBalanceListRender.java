package com.spark.psi.account.browser.balance;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.account.entity.BalanceItem;

/**
 * ��Ӧ�������б���ͼ
 */
public class SupplierBalanceListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		//���󲿷�
		new Label(headerLeftArea).setText("����");
		new Label(headerLeftArea).setID(SupplierBalanceListProcessor.ID_LABEL_SUPPLIER_COUNT);
		new Label(headerLeftArea).setText("�ҹ�Ӧ�̣�Ӧ���ܶ");
		new Label(headerLeftArea).setID(SupplierBalanceListProcessor.ID_LABEL_DUEAMOUNT);
		//���Ҳ���
		new SSearchText2(headerRightArea).setID(SupplierBalanceListProcessor.ID_TEXT_SEARCH);
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[2];
		columns[0] = new STableColumn("PartnerName", 200, JWT.LEFT, "��Ӧ������");		
		columns[1] = new STableColumn("Balance", 200, JWT.RIGHT, "Ӧ�����");		
		
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		
		columns[0].setSortable(true);
		columns[1].setSortable(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		return style;
	}

	public String getText(Object element, int columnIndex) {
		BalanceItem item = (BalanceItem) element;
		switch (columnIndex) {
		case 0:
			return item.getPartnerShortName();
		case 1:
			return DoubleUtil.getRoundStr(item.getAmount());
		default:
			return "";
		}
	}
	
	@Override
	public String getToolTipText(Object element, int columnIndex) {
		if (columnIndex == 0) {
			BalanceItem item = (BalanceItem) element;
			return item.getPartnerName();
		}
		return super.getToolTipText(element, columnIndex);
	}
}