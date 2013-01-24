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
 * 销售人员信用配置界面视图
 * 
 */
public class SalesmanCreditConfigRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("员工数量：");
		new Label(headerLeftArea)
				.setID(SalesmanCreditConfigProcessor.ID_Label_Count);
		Button cleanButton = new Button(footerLeftArea, JWT.APPEARANCE2);
		cleanButton.setID(SalesmanCreditConfigProcessor.ID_Button_Clean);
		cleanButton.setText(" 批量清空 ");

		Button saveButton = new Button(footerRightArea, JWT.APPEARANCE3);
		saveButton.setID(SalesmanCreditConfigProcessor.ID_Button_Save);
		saveButton.setText("   保存   ");
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		columns[0] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.EmployeeName.name(), 80,
				JWT.CENTER, "姓名");
		columns[1] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.Department.name(), 100,
				JWT.LEFT, "部门");
		columns[2] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.CustomerCreditAmountUpperLimit.name(), 105,
				JWT.RIGHT, "客户信用额度");
		columns[3] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.TotalCreditAmount.name(), 105,
				JWT.RIGHT, "累计信用额度");
		columns[4] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.AllocatedCount.name(), 130,
				JWT.RIGHT, "已设信用额度客户数");
		columns[5] = new STableColumn(
				SalesmanCreditConfigProcessor.Columns.AllocatedAmount.name(), 110,
				JWT.RIGHT, "已分配信用额度");
		columns[6] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.CustomerCreditDayUpperLimit.name(), 110,
				JWT.RIGHT, "客户最长账期(天)");
		columns[7] = new SNumberEditColumn(
				SalesmanCreditConfigProcessor.Columns.OrderApprovalUpperLimit.name(), 110,
				JWT.RIGHT, "订单审批限额");
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
