package com.spark.psi.account.browser.receipt;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
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
 * 收款记录视图
 * 
 */
public class ReceiptLogListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {

		super.afterFooterRender();

		// ComboList timeCombo = new ComboList(headerLeftArea);
		// timeCombo.setID(ReceiptLogListProcessor.ID_COMBO_TIME);
		LWComboList deptList = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
		deptList.setID(ReceiptLogListProcessor.ID_COMBO_TIME);
		GridData gdDeptList = new GridData();
		gdDeptList.widthHint = 100;
		deptList.setLayoutData(gdDeptList);

		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("收款记录：");
		new Label(headerLeftArea).setID(ReceiptLogListProcessor.ID_LABEL_COUNT);
		new Label(headerLeftArea).setText("条");
		new Label(headerLeftArea).setText("    ");
		new Label(headerLeftArea).setText("收款金额：");
		new Label(headerLeftArea).setID(ReceiptLogListProcessor.ID_LABEL_AMOUNT);
		new Label(headerLeftArea).setText("元");

		new SSearchText2(headerRightArea).setID(ReceiptLogListProcessor.ID_Search);
	}

	public STableColumn[] getColumns() {

		STableColumn[] columns = new STableColumn[6];
		columns[0] = new STableColumn(ReceiptLogListProcessor.ColumnName.receiptDate.name(), 150, JWT.CENTER, "收款日期");
		columns[1] = new STableColumn(ReceiptLogListProcessor.ColumnName.sheetNo.name(), 100, JWT.LEFT, "记录编号");
		columns[2] = new STableColumn(ReceiptLogListProcessor.ColumnName.partnerName.name(), 100, JWT.LEFT, "收款对象");
		columns[3] = new STableColumn(ReceiptLogListProcessor.ColumnName.receiptType.name(), 100, JWT.CENTER, "收款类型");
		columns[4] = new STableColumn(ReceiptLogListProcessor.ColumnName.receiptWay.name(), 100, JWT.CENTER, "收款方式");
		columns[5] = new STableColumn(ReceiptLogListProcessor.ColumnName.amount.name(), 100, JWT.RIGHT, "收款金额");
//		columns[6] = new STableColumn(ReceiptLogListProcessor.ColumnName.receiptPerson.name(), 120, JWT.CENTER, "收款人（确认人）");
		columns[0].setGrab(true);
		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		columns[5].setGrab(true);
		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
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
			return item.getReceiptMode().getName();
		case 5:
			return DoubleUtil.getRoundStr(item.getAmount());
		default:
			return "";
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		return null;
	}

}
