package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.config.entity.SalesmanCreditItem;

/**
 * ������Ա�������ý�����ͼ
 * 
 */
public class SalesmanCreditConfigRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("Ա��������");
		new Label(headerLeftArea)
				.setID(SalesmanCreditConfigProcessor.ID_Label_Count);
		Button cleanButton = new Button(footerLeftArea, JWT.APPEARANCE2);
		cleanButton.setID(SalesmanCreditConfigProcessor.ID_Button_Clean);
		cleanButton.setText(" ������� ");

		Button saveButton = new Button(footerRightArea, JWT.APPEARANCE3);
		saveButton.setID(SalesmanCreditConfigProcessor.ID_Button_Save);
		saveButton.setText("   ����   ");
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.EmployeeName.name(), 80,
				JWT.CENTER, "����");
		columns[1] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.Department.name(), 100,
				JWT.LEFT, "����");
		columns[2] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.CustomerCreditAmountUpperLimit.name(), 105,
				JWT.RIGHT, "�ͻ����ö��");
		columns[3] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.TotalCreditAmount.name(), 105,
				JWT.RIGHT, "�ۼ����ö��");
		columns[4] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.AllocatedCount.name(), 130,
				JWT.RIGHT, "�������ö�ȿͻ���");
		columns[5] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.AllocatedAmount.name(), 110,
				JWT.RIGHT, "�ѷ������ö��");
		columns[6] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.CustomerCreditDayUpperLimit.name(), 110,
				JWT.RIGHT, "�ͻ������(��)");
		columns[7] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.OrderApprovalUpperLimit.name(), 110,
				JWT.RIGHT, "���������޶�");
		columns[1].setGrab(true);
		return columns;
	}
	
	@Override
	public int getDecimal(Object element, int columnIndex){
		switch (columnIndex) {
			case 2:
				return 2;
			case 3:
				return 2;
			case 5:
				return 2;
			case 7:
				return 2;
			default :
				return -1;
		}
	}

	public STableStyle getTableStyle() {
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.Multi);
		return tableStyle;
	}

	public String getText(Object element, int columnIndex) {
		// SalesmanCreditItem item = (SalesmanCreditItem) element;
		// switch (columnIndex) {
		// case 0:
		// return item.getSalesmanName();
		// case 1:
		// return item.getDepartmentInfo();
		// case 2:
		// return String.valueOf(item.getCustomerCreditLimit());
		// case 3:
		// return String.valueOf(item.getAvailableTotalCreditLimit());
		// case 4:
		// return String.valueOf(item.getCustomerCountUsed());
		// case 5:
		// return String.valueOf(item.getCustomerCreditUsed());
		// case 6:
		// return String.valueOf(item.getCustomerCreditDayLimit());
		// case 7:
		// return String.valueOf(item.getOrderApprovalLimit());
		// default:
		// return "";
		// }
		SalesmanCreditItem item = (SalesmanCreditItem) element;
		switch (columnIndex) {
		case 0:
			return item.getSalesmanName();
		case 1:
			return item.getDepartmentInfo();
		case 2:
			return DoubleUtil.getRoundStr(item.getCustomerCreditLimit());
		case 3:
			return DoubleUtil.getRoundStr(item.getAvailableTotalCreditLimit());
		case 4:
			return item.getCustomerCountUsed()+"";
		case 5:
			return DoubleUtil.getRoundStr(item.getCustomerCreditUsed());
		case 6:
			return item.getCustomerCreditDayLimit()+"";
		case 7:
			return DoubleUtil.getRoundStr(item.getOrderApprovalLimit());
		default:
			return "";
		}
	}
}
