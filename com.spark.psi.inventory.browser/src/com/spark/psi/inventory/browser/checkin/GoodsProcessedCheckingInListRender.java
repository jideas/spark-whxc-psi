package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.inventory.checkin.entity.CheckInSheetShowItem;

/**
 * �Ѵ�����ɵ���ⵥ�б���ͼ
 */
public class GoodsProcessedCheckingInListRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();
		//
		new SSearchText2(headerRightArea)
				.setID(ProcessedCheckingInListProcessor.ID_TEXT_SEARCH);
		// new
		// Button(headerRightArea).setID(ProcessedCheckingInListProcessor.ID_BUTTON_SEARCH);
		//
		new LWComboList(headerLeftArea, JWT.APPEARANCE3)
				.setID(ProcessedCheckingInListProcessor.ID_COMBOLIST_DATEITEM);
		new Label(headerLeftArea).setText("  ��ⵥ������");
		new Label(headerLeftArea)
				.setID(ProcessedCheckingInListProcessor.ID_LABEL_CheckingIn_COUNT);
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[5];

		// ��Ҫ��SheetId ��ȡ������ID
		columns[0] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.LastCheckinDate.name(),
				120, JWT.CENTER, "�������");
		columns[0].setSortable(true);

		columns[1] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.SheetNumber.name(),
				120, JWT.CENTER, "��ⵥ��");
		columns[1].setGrab(true);
		columns[1].setSortable(true); 

		columns[2] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.RelatedNumber.name(),
				120, JWT.CENTER, "��ص���");
		columns[2].setGrab(true);
		columns[2].setSortable(true);

		columns[3] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.StoreName.name(), 120,
				JWT.CENTER, "�ֿ�");
		columns[3].setGrab(true);
		columns[3].setSortable(true); 

		columns[4] = new STableColumn(
				ProcessedCheckingInListProcessor.Columns.CheckinEmployees
						.name(), 120, JWT.CENTER, "ȷ�����");
		columns[4].setGrab(true);
		columns[4].setSortable(true);

		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
			return "";
	}

	public String getText(Object element, int columnIndex) {
		CheckInSheetShowItem item = (CheckInSheetShowItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCheckinDate());
		case 1:
			return StableUtil.toLink( SalesReturnCheckingInListProcessor.ID_ACTION_EDIT, "", item.getSheetNo());
		case 2:
			return item.getRelaBillsNo();
		case 3:
			return item.getStoreName(); 
		case 4:
			return item.getCheckinPersonName();
		default:
			return "";
		}
	}
}