package com.spark.psi.account.browser.payment;

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
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.account.entity.PaymentInfo;
import com.spark.psi.publish.account.entity.PaymentInfoItem;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;
import com.spark.psi.publish.inventory.checkin.key.LoadReceiptingInventorySheetKey;

public class PaymentDetailRender extends SimpleSheetPageRender {

	private static GridData gdLabel;
	private static GridData gdInput;
	
	static  {
		gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gdInput = new GridData(GridData.FILL_HORIZONTAL);
	}
	
	private PaymentInfo info    = null;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		GUID id = (GUID)getArgument();
		if (null != id) {
			info = context.find(PaymentInfo.class, id);
		}
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
		if (row == 0 && column == 0) {
			Label label0 = new Label(baseInfoArea);
			label0.setText("�������");
			label0.setLayoutData(gdLabel);
			Label parterLabel = new Label(baseInfoArea);
			parterLabel.setID(PaymentDetailProcessor.ID_Label_Partner);
			parterLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
	
			new Label(baseInfoArea).setText("      ");
			
			if (PaymentStatus.Submitting == info.getStatus()
					|| PaymentStatus.Deny == info.getStatus()) {
				Label label4 = new Label(baseInfoArea);
				label4.setText("�������ڣ�");
				label4.setLayoutData(gdLabel);
				SDatePicker receiptDate = new SDatePicker(baseInfoArea);
				receiptDate.setID(PaymentDetailProcessor.ID_Date_Date);
				receiptDate.setLayoutData(gdInput);

				new Label(baseInfoArea).setText("      ");
				
				Label label5 = new Label(baseInfoArea);
				label5.setText("�������ͣ�");
				label5.setLayoutData(gdLabel);
				LWComboList reasonList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
				reasonList.setID(PaymentDetailProcessor.ID_List_Type);
				reasonList.setLayoutData(gdInput);
		
				new Label(baseInfoArea).setText("      ");
				
				Label label7 = new Label(baseInfoArea);
				label7.setText("  ���ʽ��");
				label7.setLayoutData(gdLabel);
				LWComboList wayList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
				wayList.setID(PaymentDetailProcessor.ID_List_Way);
				wayList.setLayoutData(gdInput);
			} else {
				Label label = new Label(baseInfoArea);
				label.setText("�������ڣ�" + DateUtil.dateFromat(info.getPayDate()));
				label = new Label(baseInfoArea);
				label.setText("    �������ͣ�" + info.getPaymentType().getName());
				label = new Label(baseInfoArea);
				label.setText("    ���ʽ��" + info.getDealingsWay().getName());
			}
		} else if (row == 0 && column == 1) {
			if (PaymentStatus.Paying == info.getStatus()
					|| PaymentStatus.Paid == info.getStatus()) {
				Label label = new Label(baseInfoArea);
				label.setText("�����¼");
				label.setID(PaymentDetailProcessor.ID_Label_Log);
				label.setForeground(Color.COLOR_BLUE);
			}
		}
		
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		Label label = new Label(dataInfoArea);
		if (info.getStatus() == PaymentStatus.Paying) { 
			label.setText("  ���θ����ܶ");
		} else {
			label.setText("  �ܶ");
		}
		label.setLayoutData(gdLabel);

		Text value = new Text(dataInfoArea);
		value.setID(PaymentDetailProcessor.ID_Text_TotalAmount);
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
		return 1;
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Button button = null;
		switch(info.getStatus()) {
		case Submitting:
		case Deny:
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" �� �� ");
			button.setID(PaymentDetailProcessor.ID_Button_Save);
			
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" �� �� ");
			button.setID(PaymentDetailProcessor.ID_Button_Submit);
			return;
		case Submitted:
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" �� ׼ ");
			button.setID(PaymentDetailProcessor.ID_Button_Approval);
			
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" �� �� ");
			button.setID(PaymentDetailProcessor.ID_Button_Deny);
			return;
		case Paying:
			button = new Button(sheetButtonArea, JWT.APPEARANCE3);
			button.setText(" ȷ�ϸ��� ");
			button.setID(PaymentDetailProcessor.ID_Button_Pay);
			return;
		}
		
	}
	
	@Override
	public STableStyle getTableStyle() {
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setAutoAddRow(false);
		tableStyle.setNoScroll(true);
		if (PaymentStatus.Deny == info.getStatus()
				|| PaymentStatus.Submitting == info.getStatus()) {
			tableStyle.setSelectionMode(SSelectionMode.Multi);
		}
		return tableStyle;
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		
	}

	@Override
	public STableColumn[] getColumns() {
		STableColumn[] columns = null;
		if (PaymentStatus.Paying == info.getStatus()) {
			columns = new STableColumn[10];
			columns[0] = new STableColumn(PaymentDetailProcessor.Columns.checkDate.name(), 100, JWT.CENTER, "���ʱ��");
			columns[1] = new STableColumn(PaymentDetailProcessor.Columns.sheetNo.name(), 150, JWT.LEFT, "��ⵥ��");
			columns[2] = new STableColumn(PaymentDetailProcessor.Columns.relateSheetNo.name(), 150, JWT.LEFT, "��ص���");
			columns[3] = new STableColumn(PaymentDetailProcessor.Columns.amount.name(), 100, JWT.RIGHT, "�����");
			columns[4] = new STableColumn(PaymentDetailProcessor.Columns.askedAmount.name(), 100, JWT.RIGHT, "������");
			columns[5] = new STableColumn(PaymentDetailProcessor.Columns.paidAmount.name(), 100, JWT.RIGHT, "�Ѹ�����");
			columns[6] = new STableColumn(PaymentDetailProcessor.Columns.molingedAmount.name(), 100, JWT.RIGHT, "��Ĩ����");
			columns[7] = new STableColumn(PaymentDetailProcessor.Columns.unpaidAmount.name(), 100, JWT.RIGHT, "δ�����");
			columns[8] = new SNumberEditColumn(PaymentDetailProcessor.Columns.currentPayAmount.name(), 100, JWT.RIGHT, "���θ�����");
			columns[9] = new SNumberEditColumn(PaymentDetailProcessor.Columns.molingAmount.name(), 100, JWT.RIGHT, "Ĩ����");
		} else {
			columns = new STableColumn[6];
			columns[0] = new STableColumn(PaymentDetailProcessor.Columns.checkDate.name(), 100, JWT.CENTER, "���ʱ��");
			columns[1] = new STableColumn(PaymentDetailProcessor.Columns.sheetNo.name(), 150, JWT.LEFT, "��ⵥ��");
			columns[2] = new STableColumn(PaymentDetailProcessor.Columns.relateSheetNo.name(), 150, JWT.LEFT, "��ص���");
			columns[3] = new STableColumn(PaymentDetailProcessor.Columns.amount.name(), 100, JWT.RIGHT, "�����");
			columns[4] = new STableColumn(PaymentDetailProcessor.Columns.askedAmount.name(), 100, JWT.RIGHT, "��������");
			columns[5] = new SNumberEditColumn(PaymentDetailProcessor.Columns.applyAmount.name(), 100, JWT.RIGHT, "���θ�����");
		}
		for (STableColumn column : columns) {
			column.setGrab(true);
		}
		return columns;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if (element instanceof PaymentInfoItem) {
			PaymentInfoItem item = (PaymentInfoItem)element;
			if (PaymentStatus.Paying == info.getStatus()) {
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(item.getCheckinDate());
				case 1:
					return item.getSheetNo();
				case 2:
					return item.getRelevantBillNo();
				case 3:
					return DoubleUtil.getRoundStr(item.getAmount());
				case 4:
					return DoubleUtil.getRoundStr(item.getAskAmount());
				case 5:
					return DoubleUtil.getRoundStr(item.getPaidAmount());
				case 6:
					return DoubleUtil.getRoundStr(item.getMolingAmount());
				case 7:
					return DoubleUtil.getRoundStr((item.getAskAmount() - item.getPaidAmount() - item.getMolingAmount()));
				}
			} else {
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(item.getCheckinDate());
				case 1:
					return item.getSheetNo();
				case 2:
					return item.getRelevantBillNo();
				case 3:
					return DoubleUtil.getRoundStr(item.getAmount());
				case 4:
					LoadReceiptingInventorySheetKey key = new LoadReceiptingInventorySheetKey(item.getCheckinSheetId(), info.getPaymentType());
					ReceiptingOrPayingItem rItem = getContext().find(ReceiptingOrPayingItem.class, key);
					if (rItem == null) return null;
					return DoubleUtil.getRoundStr(rItem.getAskedAmount());
				case 5:
					if (PaymentStatus.Paying == info.getStatus()) {
						return "";
					}
					return DoubleUtil.getRoundStr(item.getAskAmount());
				}
			}
		} else if (element instanceof ReceiptingOrPayingItem) {
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
			case 4:
				return DoubleUtil.getRoundStr(item.getAskedAmount());
			case 5:
				PaymentInfoItem pItem = getPaymentInfoItemByCheckinSheetId(item.getSheetId());
				if (pItem == null) return null;
				return DoubleUtil.getRoundStr(pItem.getAskAmount());
			}
		}
		return null;
	}
	private PaymentInfoItem getPaymentInfoItemByCheckinSheetId(GUID id) {
		for (PaymentInfoItem item : info.getItems()) {
			if (item.getCheckinSheetId().equals(id)) {
				return item;
			}
		}
		return null;
	}
}
