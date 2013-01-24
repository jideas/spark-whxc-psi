package com.spark.psi.account.browser.balance;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.account.entity.BalanceInfoItem;

/**
 * 供应商往来明细列表视图
 */
public class SupplierDealingsListRender extends PSIListPageRender {
	@Override
	protected void afterFooterRender() {
		
		super.afterFooterRender();
		//上左
		new Label(headerLeftArea).setID(SupplierDealingsListProcessor.ID_LABEL_SUPPLIERNAME);	
		//下右
		Button adjustButton = new Button(footerRightArea, JWT.APPEARANCE3);
		adjustButton.setID(SupplierDealingsListProcessor.ID_BUTTON_ADJUST);
		adjustButton.setText(" 应付款调整 ");
	}
	
	@Override
	protected void beforeTableRender() {
		
		super.beforeTableRender();
		
		Composite tempCmp = new Composite(contentArea);
		tempCmp.setLayout(new GridLayout(2));
		GridData h_gd = new GridData(GridData.FILL_HORIZONTAL);
		h_gd.heightHint = 28;
		tempCmp.setLayoutData(h_gd);
		
		Composite leftCmp = new Composite(tempCmp);
		Composite rightCmp = new Composite(tempCmp);
		
		GridData h_gd1 = new GridData(GridData.VERTICAL_ALIGN_END | GridData.HORIZONTAL_ALIGN_BEGINNING);
		h_gd1.heightHint = h_gd.heightHint;
		
		GridData h_gd2 = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		h_gd2.heightHint = h_gd.heightHint;
		
		leftCmp.setLayout(new GridLayout(2));
		leftCmp.setLayoutData(h_gd1);
		
		rightCmp.setLayout(new GridLayout(7));
		rightCmp.setLayoutData(h_gd2);
		
		GridData gdLabel = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_CENTER);
		
		Label label0 = new Label(leftCmp);
		label0.setText("应付余额：");
		label0.setLayoutData(gdLabel);
		Label label1 = new Label(leftCmp);
		label1.setID(SupplierDealingsListProcessor.ID_LABEL_DUEAMOUNT);
		label1.setLayoutData(gdLabel);
		new Label(rightCmp).setText("开始日期：");
		new SDatePicker(rightCmp).setID(SupplierDealingsListProcessor.ID_DATE_BEGIN);		
		new Label(rightCmp).setText(" ");		
		new Label(rightCmp).setText("结束日期：");
		new SDatePicker(rightCmp).setID(SupplierDealingsListProcessor.ID_DATE_END);
		new Label(rightCmp).setText(" ");
		Button btnSearch = new Button(rightCmp, JWT.APPEARANCE3);
		btnSearch.setID(SupplierDealingsListProcessor.ID_BUTTON_SEARCH);
		btnSearch.setText(" 查询 ");
		
		leftCmp.layout();
		rightCmp.layout();
		tempCmp.layout();
	}



	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[8];
		int index = 0;
		columns[index++] = new STableColumn("occorDate", 200, JWT.CENTER, "发生日期");
		columns[index++] = new STableColumn("relatedNumerber", 115, JWT.CENTER, "单据编号");
		columns[index++] = new STableColumn("acountBillsNo", 115, JWT.CENTER, "财务单据编号");
		columns[index++] = new STableColumn("dealingsTypeName", 115, JWT.CENTER, "往来类型");
		columns[index++] = new STableColumn("dealingsWayName", 115, JWT.CENTER, "付款方式");
		columns[index++] = new STableColumn("dueAmount", 115, JWT.RIGHT, "应付金额");
		columns[index++] = new STableColumn("doneAmount", 115, JWT.RIGHT, "实付金额");
		columns[index++] = new STableColumn("balanceAmount", 95, JWT.RIGHT, "余额");
		
		for(int columnIndex = 0; columnIndex < columns.length; columnIndex++) {
			columns[columnIndex].setGrab(true);
		}
		return columns;
	}

	public STableStyle getTableStyle() {
		return new STableStyle();
	}

	public String getText(Object element, int columnIndex) {
		BalanceInfoItem item = (BalanceInfoItem) element;
		switch (columnIndex) {
		case 0:
			return DateUtil.dateFromat(item.getCreateDate());
		case 1:
			if(item.isShowLink()) {
				return StableUtil.toLink(Action.Detail.name(), "", item.getBillsNo());
			} else {
				return item.getBillsNo() == null ? "-" : item.getBillsNo();
			}
		case 2:
			if(CheckIsNull.isEmpty(item.getAccountBillsId())) {
				return item.getAccountBillsNo() == null ? "-" : item.getAccountBillsNo();
			} else {
				if (getContext().find(Boolean.class, Auth.Account) || getContext().find(Boolean.class, Auth.AccountManager)
						|| getContext().find(Boolean.class, Auth.Boss))
				return StableUtil.toLink(Action.AccountDetail.name(), "", item.getAccountBillsNo());
				else
					return item.getAccountBillsNo() == null ? "-" : item.getAccountBillsNo();
			}
		case 3:
			return item.getBillsType()== null ? "-" : item.getBillsType().getName();
		case 4:
			return item.getReceiptType() == null ? "-" : item.getReceiptType().getName();
		case 5:
			if (item.getPlanAmount() == 0) {
				return "-";
			}
			return DoubleUtil.getRoundStr(item.getPlanAmount());
		case 6:
			if (item.getRealAmount() == 0) {
				return "-";
			}
			return DoubleUtil.getRoundStr(item.getRealAmount());
		case 7:
//			if (item.getBalanceAmount() == 0) {
//				return "-";
//			}
			return DoubleUtil.getRoundStr(item.getBalance());
		default:
			return "";
		}
	}
}
