package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.entity.CheckingInItem;

/**
 * �ɹ���ⵥ�б���ͼ
 */
public class PurchaseCheckingInListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(PurchaseCheckingInListProcessor.ID_BUTTON_NEW_LXCG);
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(PurchaseCheckingInListProcessor.ID_BUTTON_NEW_TZRK);
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(PurchaseCheckingInListProcessor.ID_BUTTON_NEW_LYRK);
		new Button(footerLeftArea, JWT.APPEARANCE2).setID(PurchaseCheckingInListProcessor.ID_BUTTON_NEW_GIFT);
		new SSearchText2(headerRightArea).setID(PurchaseCheckingInListProcessor.ID_TEXT_SEARCH);
		new Label(headerLeftArea).setText("�������������");
		new Label(headerLeftArea).setID(PurchaseCheckingInListProcessor.ID_LABEL_CheckingIn_COUNT);
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[5];

		// ��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(PurchaseCheckingInListProcessor.Columns.PlanCheckinDate.name(), 100, JWT.CENTER, "Ԥ���������");
		columns[0].setSortable(true);

		columns[1] = new STableColumn(PurchaseCheckingInListProcessor.Columns.RelatedNumber.name(), 100, JWT.CENTER, "��ص���");
		columns[1].setGrab(true);
		columns[1].setSortable(true);

		columns[2] = new STableColumn(PurchaseCheckingInListProcessor.Columns.StoreName.name(), 100, JWT.CENTER, "�ֿ�");
		columns[2].setGrab(true);
		columns[2].setSortable(true);

		columns[3] = new STableColumn(PurchaseCheckingInListProcessor.Columns.status.name(), 100, JWT.CENTER, "����״̬");
		columns[3].setGrab(true);
		columns[3].setSortable(true);

		columns[4] = new STableColumn(PurchaseCheckingInListProcessor.Columns.CreateDate.name(), 150, JWT.CENTER, "����ʱ��");
		columns[4].setGrab(true);
		columns[4].setSortable(true);

		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		CheckingInItem item = (CheckingInItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getPlanCheckinDate());
		case 1:
			return item.getRelaBillsNo();
		case 2:
			return item.getStoreName();
		case 3:
			return item.getStatus().getName();
		case 4:
			return DateUtil.dateFromat(item.getCreateDate());
		default:
			return "";
		}
	}
}