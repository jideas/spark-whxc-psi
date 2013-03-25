package com.spark.psi.account.browser.receipt;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.account.entity.ReceiptInfo;
import com.spark.psi.publish.account.entity.ReceiptInfoItem;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;

public class ReceiptDetailRender extends SimpleSheetPageRender {

	private ReceiptInfo info = null;
	
	private static GridData gdLabel;
	private static GridData gdInput;

	static {
		gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gdInput = new GridData(GridData.VERTICAL_ALIGN_END | GridData.FILL_HORIZONTAL);
	}
	
	@Override
	public void init(Situation context) {
		super.init(context);
		GUID sheetId = (GUID)getArgument();
		if (null != sheetId) {
			info = getContext().find(ReceiptInfo.class, sheetId);
		}
	}
	@Override
	public STableStyle getTableStyle() {
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setAutoAddRow(false);
		tableStyle.setNoScroll(true);
		if (ReceiptStatus.Submitting == info.getStatus()) {
			tableStyle.setSelectionMode(SSelectionMode.Multi);
		}
		return tableStyle;
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = null; 
		if (ReceiptStatus.Submitting == info.getStatus()) {
			columns = new STableColumn[4];
			columns[0] = new STableColumn(ReceiptDetailProcessor.Columns.checkDate.name(), 100, JWT.CENTER, "出库时间");
			columns[1] = new STableColumn(ReceiptDetailProcessor.Columns.billsNo.name(), 100, JWT.LEFT, "出库单号");
			columns[2] = new STableColumn(ReceiptDetailProcessor.Columns.relaBillsNo.name(), 100, JWT.LEFT, "相关单据");
			columns[3] = new STableColumn(ReceiptDetailProcessor.Columns.checkedAmount.name(), 100, JWT.RIGHT, "出库金额");
			// columns[4] = new SNumberEditColumn(ReceiptDetailProcessor.Columns.molingAmount.name(), 100, JWT.RIGHT, "抹零金额");
		} else if (ReceiptStatus.Receipting == info.getStatus()){
			columns = new STableColumn[8];
			columns[0] = new STableColumn(ReceiptDetailProcessor.Columns.checkDate.name(), 100, JWT.CENTER, "出库时间");
			columns[1] = new STableColumn(ReceiptDetailProcessor.Columns.billsNo.name(), 100, JWT.LEFT, "出库单号");
			columns[2] = new STableColumn(ReceiptDetailProcessor.Columns.relaBillsNo.name(), 100, JWT.LEFT, "相关单据");
			columns[3] = new STableColumn(ReceiptDetailProcessor.Columns.checkedAmount.name(), 100, JWT.RIGHT, "出库金额");
			columns[4] = new STableColumn(ReceiptDetailProcessor.Columns.receiptedAmount.name(), 100, JWT.RIGHT, "已收金额");
			columns[5] = new STableColumn(ReceiptDetailProcessor.Columns.molingedAmount.name(), 100, JWT.RIGHT, "已抹零金额");
			columns[6] = new SNumberEditColumn(ReceiptDetailProcessor.Columns.currentReceiptAmount.name(), 100, JWT.RIGHT, "本次收款");
			columns[7] = new SNumberEditColumn(ReceiptDetailProcessor.Columns.molingAmount.name(), 100, JWT.RIGHT, "抹零金额");
		} else if (ReceiptStatus.Receipted == info.getStatus()) {
			columns = new STableColumn[6];
			columns[0] = new STableColumn(ReceiptDetailProcessor.Columns.checkDate.name(), 100, JWT.CENTER, "出库时间");
			columns[1] = new STableColumn(ReceiptDetailProcessor.Columns.billsNo.name(), 100, JWT.LEFT, "出库单号");
			columns[2] = new STableColumn(ReceiptDetailProcessor.Columns.relaBillsNo.name(), 100, JWT.LEFT, "相关单据");
			columns[3] = new STableColumn(ReceiptDetailProcessor.Columns.checkedAmount.name(), 100, JWT.RIGHT, "出库金额");
			columns[4] = new SNumberEditColumn(ReceiptDetailProcessor.Columns.molingAmount.name(), 100, JWT.RIGHT, "抹零金额");
			columns[5] = new STableColumn(ReceiptDetailProcessor.Columns.receiptedAmount.name(), 100, JWT.RIGHT, "收款金额");
		}
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof ReceiptingOrPayingItem) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem)element;
			switch (columnIndex) {
			case 0:
				return DateUtil.dateFromat(item.getCheckInOrOutDate());
			case 1:
				return item.getSheetNo();
			case 2:
				return item.getRelaBillsNo();
			case 3:
				return DoubleUtil.getRoundStr(item.getAmount());
			}
		} else if (element instanceof ReceiptInfoItem) {
			ReceiptInfoItem item = (ReceiptInfoItem)element;
			if (ReceiptStatus.Receipting == info.getStatus()) {
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(item.getCheckoutDate());
				case 1:
					return item.getSheetNo();
				case 2:
					return item.getRelevantBillNo();
				case 3:
					return DoubleUtil.getRoundStr(item.getAmount());
				case 4:
					return DoubleUtil.getRoundStr(item.getReceiptedAmount());
				case 5:
					return DoubleUtil.getRoundStr(item.getMolingAmount());
//				case 6:
//					if (info.getStatus() == ReceiptStatus.Receipted) {
//						return DoubleUtil.getRoundStr(item.getMolingAmount()); 
//					}
				}
			} else  if (ReceiptStatus.Receipted == info.getStatus()) {
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(item.getCheckoutDate());
				case 1:
					return item.getSheetNo();
				case 2:
					return item.getRelevantBillNo();
				case 3:
					return DoubleUtil.getRoundStr(item.getAmount());
				case 4:
					return DoubleUtil.getRoundStr(item.getMolingAmount()); 
				case 5:
					return DoubleUtil.getRoundStr(item.getReceiptedAmount());
				}
			}
			
		}
		return null;
	}


	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0 && column == 0) {
			Label label0 = new Label(baseInfoArea);
			label0.setText("收款对象：");
			label0.setLayoutData(gdLabel);
			Label parterLabel = new Label(baseInfoArea);
			parterLabel.setID(ReceiptDetailProcessor.ID_Label_Partner);
			parterLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
	
			String tempStr = "      ";
			new Label(baseInfoArea).setText(tempStr);
			
			Label label4 = new Label(baseInfoArea);
			label4.setText("收款日期：");
			label4.setLayoutData(gdLabel);
			SDatePicker receiptDate = new SDatePicker(baseInfoArea);
			receiptDate.setID(ReceiptDetailProcessor.ID_Date_Date);
			receiptDate.setLayoutData(gdInput);
			
			new Label(baseInfoArea).setText(tempStr);
			
			Label label5 = new Label(baseInfoArea);
			label5.setText("收款原因：");
			label5.setLayoutData(gdLabel);
			LWComboList reasonList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
			reasonList.setID(ReceiptDetailProcessor.ID_List_Type);
			reasonList.setLayoutData(gdInput);
	
			new Label(baseInfoArea).setText(tempStr);
			
			Label label7 = new Label(baseInfoArea);
			label7.setText("  收款方式：");
			label7.setLayoutData(gdLabel);
			LWComboList wayList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
			wayList.setID(ReceiptDetailProcessor.ID_List_Way);
			wayList.setLayoutData(gdInput);
		} else if (row == 0 && column == 1){
			if (info.getStatus() == ReceiptStatus.Receipting
					|| info.getStatus() == ReceiptStatus.Receipted) {
				Label label = new Label(baseInfoArea);
				label.setText("收款记录");
				label.setID(ReceiptDetailProcessor.ID_Label_Log);
				label.setForeground(Color.COLOR_BLUE);
			}
		}
		
	}


	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		Label label = new Label(dataInfoArea);
		label.setText("  本次收款总额：");
		Text value = new Text(dataInfoArea);
		value.setID(ReceiptDetailProcessor.ID_Text_TotalAmount);
		value.setText("0.00");
		GridData valuegd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		valuegd.widthHint = 100;
		value.setEnabled(false);
		value.setLayoutData(valuegd);
	}


	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected int getBaseInfoAreaRowCount() {
		// TODO Auto-generated method stub
		return 1;
	}


	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Button button = null;
		if (info.getStatus() == ReceiptStatus.Submitting) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 保 存 ");
			button.setID(ReceiptDetailProcessor.ID_Button_Save);
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 提 交 ");
			button.setID(ReceiptDetailProcessor.ID_Button_Submit);
		} else if (info.getStatus() == ReceiptStatus.Receipting) {
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" 确认收款 ");
			button.setID(ReceiptDetailProcessor.ID_Button_Receipt);
		}
	}


	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		// TODO Auto-generated method stub
		
	}


}
