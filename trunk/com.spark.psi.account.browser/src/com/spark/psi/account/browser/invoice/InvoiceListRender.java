package com.spark.psi.account.browser.invoice;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.account.browser.internal.AccountImages;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.account.entity.InvoiceItem;

/**
 * 开票记录列表视图
 */
public class InvoiceListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		LWComboList deptList = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
		deptList.setID(InvoiceListProcessor.ID_COMBO_DEPARTMENT);
		GridData gdDeptList = new GridData();
		gdDeptList.widthHint = 100;
		deptList.setLayoutData(gdDeptList);
		
		new Label(headerLeftArea).setText("    ");

		LWComboList timeList = new LWComboList(headerLeftArea, JWT.APPEARANCE3);
		timeList.setID(InvoiceListProcessor.ID_COMBO_TIME);
		GridData gdTimeList = new GridData();
		gdTimeList.widthHint = 60;
		timeList.setLayoutData(gdTimeList);

		new Label(headerLeftArea).setText("    ");

		new Label(headerLeftArea).setText("开票数量：");

		new Label(headerLeftArea).setID(InvoiceListProcessor.ID_LABEL_COUNT);

		new Label(headerLeftArea).setText("    ");

		new Label(headerLeftArea).setText("开票金额：");

		new Label(headerLeftArea).setID(InvoiceListProcessor.ID_LABEL_AMOUNT);

		new SSearchText2(headerRightArea).setID(InvoiceListProcessor.ID_TEXT_SEARCHTEXT);
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STableColumn("", 150, JWT.LEFT, "开票日期");
		columns[1] = new STableColumn("", 100, JWT.CENTER, "客户名称");
		columns[1].setGrab(true);
		columns[2] = new STableColumn("", 150, JWT.CENTER, "发票类型");
		columns[3] = new STableColumn("", 120, JWT.CENTER, "开票金额");
		columns[4] = new STableColumn("", 120, JWT.CENTER, "开票人");
		columns[5] = new STableColumn("", 150, JWT.CENTER, "登记人");
		columns[6] = new STableColumn("", 120, JWT.CENTER, "发票号");
		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		InvoiceItem item = (InvoiceItem) element;
		switch (columnIndex) {
		case 0:
			if (item.isInvalided()) {
				ImageDescriptor image = AccountImages
					.getImage("images/saas_mark_waste.png");
				String imgTitle =  item.getInvalidReason() == null ? "" : item.getInvalidReason() + "; \n"
						+ item.getInvalidPerson() + "("
						+ DateUtil.dateFromat(item.getInvalidDate()) + ")";

				return StableUtil.toImg(image.getDNAURI(), "作废原因:" + imgTitle,15) + DateUtil.dateFromat(item.getDate());
			}
			return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + DateUtil.dateFromat(item.getDate());
		case 1:
			return item.getCustomerName();
		case 2:
			return item.getInvoiceTypeName();
		case 3:
			return DoubleUtil.getRoundStr(item.getAmount());
		case 4:
			return item.getDrawer();
		case 5:
			return item.getRecorder();
		case 6:
			return item.getInvoiceNumber();
		default:
			return "";
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		InvoiceItem item = (InvoiceItem) element;
		if (columnIndex == 1) {
			return item.getCustomerFullName();
		}
		return super.getToolTipText(element, columnIndex);
	}
	
	

}
