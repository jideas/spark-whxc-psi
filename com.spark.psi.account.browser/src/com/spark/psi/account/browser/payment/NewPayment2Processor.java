package com.spark.psi.account.browser.payment;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.StableUtil;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.account.browser.DealingsWaySource;
import com.spark.psi.account.browser.PartnerSearchMsg;
import com.spark.psi.account.browser.PartnerSelectPage;
import com.spark.psi.account.browser.PartnerSelectionMsg;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;
import com.spark.psi.publish.account.task.CreatePaymentTask;
import com.spark.psi.publish.account.task.UpdatePaymentStatusTask;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.key.GetReceiptingInventorySheetKey;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnInfo;

/**
 * 新增付款界面处理器（需要选择付款对象）
 */
public class NewPayment2Processor extends PageProcessor {

	public final static String ID_Search = "Search";
	public final static String ID_Label_Partner = "Label_Partner";
	public final static String ID_Date_Date = "Date_Date";
	public final static String ID_Label_Type = "Label_Type";
	public final static String ID_List_Way = "List_Way";

	public static final String ID_Table = "Table";
	public final static String ID_Text_Remark = "Text_Remark";
	public static final String ID_Text_TotalAmount = "Label_TotalAmount";

	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Submit = "Button_Submit";
	public final static String ID_PartnerPage = "PartnerPage";

	public enum Columns {
		checkDate, sheetNo, relateSheetNo, amount, askedAmount, applyAmount
	}

	public static enum TableExtraValueName {
		checkinSheetId, sheetNo, relevantBillId, relevantBillNo, checkinDate, askedAmount, amount
	}

	private Label partnerLabel;
	private SDatePicker datePicker;
	// private LWComboList payTypeList;
	private Label payTypeLabel;
	private LWComboList wayList;
	private SEditTable table = null;
	private Text amountText;
	private Text memoText;
	private PartnerSelectPage partnerPage;

	private PartnerInfo partnerInfo = null;
	private PaymentType payType = null;

	@Override
	public void process(final Situation situation) {

		partnerPage = createControl(ID_PartnerPage, PartnerSelectPage.class);
		partnerLabel = createControl(ID_Label_Partner, Label.class);
		datePicker = createControl(ID_Date_Date, SDatePicker.class);
		// payTypeList = createControl(ID_Label_Type, LWComboList.class);
		payTypeLabel = createLabelControl(ID_Label_Type);
		wayList = createControl(ID_List_Way, LWComboList.class);
		table = createControl(ID_Table, SEditTable.class);
		amountText = createControl(ID_Text_TotalAmount, Text.class);
		memoText = createControl(ID_Text_Remark, Text.class, JWT.MULTI | JWT.VERTICAL | JWT.WRAP | JWT.APPEARANCE3);
		final Button saveButton = createButtonControl(ID_Button_Save);
		final Button subitButton = createButtonControl(ID_Button_Submit);

		wayList.getList().setSource(new DealingsWaySource());
		wayList.getList().setInput(null);
		wayList.setSelection(DealingsWay.Cash.getCode());

		// payTypeList.getList().setSource(new ReasonSource());
		// payTypeList.getList().setInput(null);
		// payTypeList.setSelection(PaymentType.PAY_CGFK);

		datePicker.setDate(new Date());

		table.setContentProvider(new TableContentProvider());
		table.setLabelProvider(new TableLabelProvider());

		amountText.setEnabled(false);

		initDataShow(partnerPage.getDefaultSelectId());

		situation.regMessageListener(PartnerSelectionMsg.class, new MessageListener<PartnerSelectionMsg>() {

			public void onMessage(Situation context, PartnerSelectionMsg message,
					MessageTransmitter<PartnerSelectionMsg> transmitter) {
				initDataShow(message.getPartnerId());
			}
		});

		final SSearchText2 search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				situation.broadcastMessage(new PartnerSearchMsg(search.getText()));
				initDataShow(partnerPage.getDefaultSelectId());
			}
		});

		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 保存
				GUID sheetId = doSave();
				if (null != sheetId) {
					getContext().bubbleMessage(new MsgResponse(true));
				}
			}
		});

		subitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 提交
				GUID sheetId = doSave();
				if (null != sheetId) {
					UpdatePaymentStatusTask task = new UpdatePaymentStatusTask(sheetId);
					getContext().handle(task, UpdatePaymentStatusTask.Method.Submit);
					getContext().bubbleMessage(new MsgResponse(true));
				}
			}
		});

		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "Account.handleTableDataChanged");
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				calTotalApplyAmount();
			}
		});
		table.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				calTotalApplyAmount();
			}
		});
		table.addActionListener(new SActionListener() {

			public void actionPerformed(String rowId, String actionName, String actionValue) {

				if ((Action.Detail.name() + "1").equals(actionName)) {
					String[] extraValues = table.getExtraData(rowId, TableExtraValueName.checkinSheetId.name(),
							TableExtraValueName.sheetNo.name());
					String sheetId = extraValues[0];
					String sheetNo = extraValues[1];
					if (CheckIsNull.isEmpty(sheetNo)) {
						return;
					}
					if (sheetNo.startsWith("RKD")) {
						CheckInBaseInfo info = getContext().find(CheckInBaseInfo.class, GUID.valueOf(sheetId));
						PageControllerInstance pci = new PageControllerInstance("PSI_CheckInDetailPages", info, sheetId);
						MsgRequest request = new MsgRequest(pci, "入库单详情");
						getContext().bubbleMessage(request);
					} else if (sheetNo.startsWith("CKD")) {
						CheckOutBaseInfo info = getContext().find(CheckOutBaseInfo.class, GUID.valueOf(sheetId));
						PageControllerInstance pci = new PageControllerInstance("PSI_CheckoutDetailPages", info,
								sheetId);
						MsgRequest request = new MsgRequest(pci, "出库单详情");
						getContext().bubbleMessage(request);
					}
				} else if ((Action.Detail.name() + "2").equals(actionName)) {
					// 打开详情界面
					String[] extraValues = table.getExtraData(rowId, TableExtraValueName.relevantBillId.name(),
							TableExtraValueName.relevantBillNo.name());
					String sheetId = extraValues[0];
					String sheetNo = extraValues[1];
					if (sheetNo.startsWith("CGD")) {
						PurchaseOrderInfo info = getContext().find(PurchaseOrderInfo.class, GUID.valueOf(sheetId));
						PageControllerInstance pci = new PageControllerInstance("Psi_PruchaseOrderDetailPages", info);
						MsgRequest request = new MsgRequest(pci, "采购明细");
						getContext().bubbleMessage(request);
					} else if (sheetNo.startsWith("XTD")) {
						SalesReturnInfo info = getContext().find(SalesReturnInfo.class, GUID.valueOf(sheetId));
						PageControllerInstance pci = new PageControllerInstance("Psi_SalesReturnOrderDetailPages",
								info, sheetId);
						MsgRequest request = new MsgRequest(pci, "销售退货单明细");
						getContext().bubbleMessage(request);
					}
				}
			}
		});
	}

	private void calTotalApplyAmount() {
		double totalAmount = 0.0;
		String[] selectedRowIds = table.getSelections();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			totalAmount = 0.0;
		} else {
			for (String rowId : selectedRowIds) {
				// String amountStr = table.getExtraData(rowId,
				// TableExtraValueName.amount.name())[0];
				String applyAmountStr = table.getEditValue(rowId, Columns.applyAmount.name())[0];
				if (StringUtils.isNotEmpty(applyAmountStr)) {
					totalAmount += DoubleUtil.strToDouble(applyAmountStr);
				}
			}
		}
		amountText.setText(DoubleUtil.getRoundStr(totalAmount));
	}

	private GUID doSave() {
		if (!validateInput_This()) {
			return null;
		}
		CreatePaymentTask task = new CreatePaymentTask();
		task.setId(GUID.randomID());
		task.setPartnerName(partnerInfo.getName());
		task.setPartnerId(partnerInfo.getId());
		// task.setPaymentType(payTypeList.getList().getSeleted());
		task.setPaymentType(payType.getCode());
		task.setPayDate(datePicker.getDate().getTime());
		task.setAmount(DoubleUtil.strToDouble(amountText.getText()));
		task.setRemark(memoText.getText());
		task.setDealingsWay(wayList.getList().getSeleted());
		String[] selectedRowIds = table.getSelections();
		CreatePaymentTask.Item item = null;
		CreatePaymentTask.Item[] items = new CreatePaymentTask.Item[selectedRowIds.length];
		int rowIndex = 0;
		for (String rowId : selectedRowIds) {
			String[] extraValues = table.getExtraData(rowId, TableExtraValueName.checkinSheetId.name(),
					TableExtraValueName.sheetNo.name(), TableExtraValueName.relevantBillId.name(),
					TableExtraValueName.relevantBillNo.name(), TableExtraValueName.checkinDate.name(),
					TableExtraValueName.amount.name());
			String askedAmountStr = table.getEditValue(rowId, Columns.applyAmount.name())[0];
			item = task.new Item(GUID.tryValueOf(extraValues[0]), extraValues[1], GUID.tryValueOf(extraValues[2]),
					extraValues[3], Long.parseLong(extraValues[4]), DoubleUtil.strToDouble(extraValues[5]), DoubleUtil
							.strToDouble(askedAmountStr), 0);
			items[rowIndex] = item;
			rowIndex++;
		}
		task.setItems(items);
		getContext().handle(task);
		return task.getId();
	}

	private boolean validateInput_This() {
		String[] selectedRowIds = table.getSelections();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			alert("请先选择入库单。");
			return false;
		}
		for (String rowId : selectedRowIds) {
			String applyAmountStr = table.getEditValue(rowId, Columns.applyAmount.name())[0];
			if (StringUtils.isEmpty(applyAmountStr)) {
				alert("申请金额不能为空。");
				return false;
			}
			String amountStr = table.getExtraData(rowId, TableExtraValueName.amount.name())[0];
			String askedAmountStr = table.getExtraData(rowId, TableExtraValueName.askedAmount.name())[0];
			if (DoubleUtil.strToDouble(applyAmountStr) + DoubleUtil.strToDouble(askedAmountStr) > DoubleUtil
					.strToDouble(amountStr)) {
				alert("申请金额不能大于入库金额。");
				return false;
			}
		}
		return true;
	}

	private void initDataShow(GUID partnerId) {
		if (partnerId == null)
			return;
		partnerInfo = getContext().find(PartnerInfo.class, partnerId);
		if (PartnerType.Customer.equals(partnerInfo.getPartnerType())) {
			payTypeLabel.setText(PaymentType.PAY_XSTK.getName());
			payType = PaymentType.PAY_XSTK;
		} else {
			payTypeLabel.setText(PaymentType.PAY_CGFK.getName());
			payType = PaymentType.PAY_CGFK;
		}

		partnerLabel.setText(partnerInfo.getName());
		table.render();
	}

	// private class ReasonSource extends ListSourceAdapter {
	//
	// public String getElementId(Object element) {
	// PaymentType payType = (PaymentType)element;
	// return payType.getCode();
	// }
	//
	// public Object getElementById(String id) {
	// return PaymentType.getPaymentType(id);
	// }
	//
	// public String getText(Object element) {
	// PaymentType payType = (PaymentType)element;
	// return payType.getName();
	// }
	//
	// public Object[] getElements(Object inputElement) {
	// return new PaymentType[] {PaymentType.PAY_CGFK, PaymentType.PAY_XSTK};
	// }
	//		
	// }

	private class TableContentProvider implements SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			return new SNameValue[] {
					new SNameValue(TableExtraValueName.checkinSheetId.name(), item.getSheetId().toString()),
					new SNameValue(TableExtraValueName.sheetNo.name(), item.getSheetNo()),
					new SNameValue(TableExtraValueName.relevantBillId.name(), item.getRelaBillsId().toString()),
					new SNameValue(TableExtraValueName.relevantBillNo.name(), item.getRelaBillsNo()),
					new SNameValue(TableExtraValueName.checkinDate.name(), String.valueOf(item.getCheckInOrOutDate())),
					new SNameValue(TableExtraValueName.askedAmount.name(), String.valueOf(item.getAskedAmount())),
					new SNameValue(TableExtraValueName.amount.name(), String.valueOf(item.getAmount())) };
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			if (Columns.applyAmount.name().equals(columnName)) {
				return "";
			}
			return null;
		}

		public String getElementId(Object element) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			return item.getSheetId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (null == partnerInfo) {
				return null;
			} else {
				// PaymentType payType =
				// PaymentType.getPaymentType(payTypeList.getList().getSeleted());
				GetReceiptingInventorySheetKey key = new GetReceiptingInventorySheetKey(partnerInfo.getId(), payType);
				List<ReceiptingOrPayingItem> list = context.getList(ReceiptingOrPayingItem.class, key);
				return list.toArray();
			}
		}

		public boolean isSelectable(Object element) {
			return true;
		}

		public boolean isSelected(Object element) {
			return false;
		}
	}

	private class TableLabelProvider implements SLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getText(Object element, int columnIndex) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			switch (columnIndex) {
			case 0:
				return DateUtil.dateFromat(item.getCheckInOrOutDate());

				// case 1:
				// return item.getSheetNo();
				// case 2:
				// return item.getRelaBillsNo();

			case 1:
				return StableUtil.toLink(Action.Detail.name() + "1", "", item.getSheetNo());
			case 2:
				return StableUtil.toLink(Action.Detail.name() + "2", "", item.getRelaBillsNo());
			case 3:
				return DoubleUtil.getRoundStr(item.getAmount());
			case 4:
				return DoubleUtil.getRoundStr(item.getAskedAmount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
