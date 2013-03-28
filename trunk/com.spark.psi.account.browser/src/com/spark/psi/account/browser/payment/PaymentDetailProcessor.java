package com.spark.psi.account.browser.payment;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.Direction;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.account.browser.DealingsWaySource;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PaymentStatus;
import com.spark.psi.publish.PaymentType;
import com.spark.psi.publish.account.entity.PaymentInfo;
import com.spark.psi.publish.account.entity.PaymentInfoItem;
import com.spark.psi.publish.account.entity.PaymentLog;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;
import com.spark.psi.publish.account.task.PayTask;
import com.spark.psi.publish.account.task.UpdatePaymentStatusTask;
import com.spark.psi.publish.account.task.UpdatePaymentTask;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.key.GetReceiptingInventorySheetKey;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnInfo;

public class PaymentDetailProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	public final static String ID_Label_Partner = "Label_Partner";
	public final static String ID_Date_Date = "Date_Date";
	public final static String ID_List_Type = "Label_Type";
	public final static String ID_List_Way = "List_Way";
	public final static String ID_Label_Log = "Label_Log";

	public static final String ID_Table = "Table";
	public final static String ID_Text_Remark = "Text_Remark";
	public static final String ID_Text_TotalAmount = "Label_TotalAmount";

	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Submit = "Button_Submit";

	public final static String ID_Button_Approval = "Button_Approval";
	public final static String ID_Button_Deny = "Button_Deny";

	public final static String ID_Button_Pay = "Button_Pay";

	public enum Columns {
		checkDate, sheetNo, relateSheetNo, amount, askedAmount, applyAmount, paidAmount, molingedAmount, unpaidAmount, currentPayAmount, molingAmount
	}

	public static enum TableExtraValueName {
		checkinSheetId, sheetNo, relevantBillId, relevantBillNo, checkinDate, askedAmount, amount
	}

	private PaymentInfo info = null;
	private LoginInfo loginInfo = null;

	private Label partnerLabel;
	private SDatePicker datePicker;
	private LWComboList payTypeList;
	private LWComboList wayList;
	private Text amountText;
	private Text memoText;
	private Label createDateLabel;

	@Override
	public void init(Situation context) {
		super.init(context);
		GUID id = (GUID) getArgument();
		if (null != id) {
			info = context.find(PaymentInfo.class, id);
		}
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		partnerLabel = createControl(ID_Label_Partner, Label.class);
		datePicker = createControl(ID_Date_Date, SDatePicker.class);
		payTypeList = createControl(ID_List_Type, LWComboList.class);
		wayList = createControl(ID_List_Way, LWComboList.class);
		amountText = createControl(ID_Text_TotalAmount, Text.class);
		memoText = createMemoText();

		amountText.setEnabled(false);

		partnerLabel.setText(info.getPartnerName());
		memoText.setText(info.getRemark());
		createDateLabel = createLabelControl(ID_Label_Label_ExtraInfo);

		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "Account.handleTableDataChanged");
		if (info.getStatus() == PaymentStatus.Paying) {
			table.addClientNotifyListener(new ClientNotifyListener() {

				public void notified(ClientNotifyEvent e) {
					if (info.getStatus() == PaymentStatus.Paying) {
						calTotalPayAmount();
					} else {
						calTotalApplyAmount();
					}
				}
			});
		} else if (PaymentStatus.Deny == info.getStatus() || PaymentStatus.Submitting == info.getStatus()) {
			table.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					if (info.getStatus() == PaymentStatus.Paying) {
						calTotalPayAmount();
					} else {
						calTotalApplyAmount();
					}
				}
			});

			table.addClientNotifyListener(new ClientNotifyListener() {

				public void notified(ClientNotifyEvent e) {
					if (info.getStatus() == PaymentStatus.Paying) {
						calTotalPayAmount();
					} else {
						calTotalApplyAmount();
					}
				}
			});
		}

		if (info.getStatus() != PaymentStatus.Paying) {
			amountText.setText(DoubleUtil.getRoundStr(info.getAmount()));
		}
		memoText.setText(info.getRemark());
		createDateLabel.setText("制单：" + DateUtil.dateFromat(info.getCreateDate()));
		if (PaymentStatus.Submitting == info.getStatus() || PaymentStatus.Deny == info.getStatus()) {
			wayList.getList().setSource(new DealingsWaySource());
			wayList.getList().setInput(null);
			wayList.setSelection(DealingsWay.Cash.getCode());

			payTypeList.getList().setSource(new ReasonSource());
			payTypeList.getList().setInput(null);
			payTypeList.setSelection(info.getPaymentType().getCode());

			datePicker.setEnabled(true);
			payTypeList.setEnabled(true);
			wayList.setEnabled(true);
			memoText.setEnabled(true);
			// table.setEnabled(true);
			datePicker.setDate(DateUtil.convertLongToDate(info.getPayDate()));
			payTypeList.setSelection(info.getPaymentType().getCode());
			wayList.setSelection(info.getDealingsWay().getCode());
		} else {
			memoText.setEnabled(false);
		}
		// else {
		// datePicker.setEnabled(false);
		// payTypeList.setEnabled(false);
		// wayList.setEnabled(false);
		// memoText.setEnabled(false);
		// // table.setEnabled(false);
		// }

		initFormActions();
	}

	@Override
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
				PageControllerInstance pci = new PageControllerInstance("PSI_CheckoutDetailPages", info, sheetId);
				MsgRequest request = new MsgRequest(pci, "出库单详情");
				getContext().bubbleMessage(request);
			}
		} else if ((Action.Detail.name() + "2").equals(actionName)) {
			// 打开详情界面
			String[] extraValues = table.getExtraData(rowId, TableExtraValueName.relevantBillId.name(),
					TableExtraValueName.relevantBillNo.name());
			String sheetId = extraValues[0];
			String sheetNo = extraValues[1]; 
			if (CheckIsNull.isEmpty(sheetNo)) {
				return;
			}
			if (sheetNo.startsWith("CGD")) {
				PurchaseOrderInfo info = getContext().find(PurchaseOrderInfo.class, GUID.valueOf(sheetId));
				PageControllerInstance pci = new PageControllerInstance("Psi_PruchaseOrderDetailPages", info);
				MsgRequest request = new MsgRequest(pci, "采购明细");
				getContext().bubbleMessage(request);
			} else if (sheetNo.startsWith("XTD")) {
				SalesReturnInfo info = getContext().find(SalesReturnInfo.class, GUID.valueOf(sheetId));
				PageControllerInstance pci = new PageControllerInstance("Psi_SalesReturnOrderDetailPages", info,
						sheetId);
				MsgRequest request = new MsgRequest(pci, "销售退货单明细");
				getContext().bubbleMessage(request);
			}
		}
	}

	private void initFormActions() {
		Button button = null;
		Label label = null;
		switch (info.getStatus()) {
		case Submitting:
		case Deny:
			button = createButtonControl(ID_Button_Save);
			addSaveAction(button);
			button = createButtonControl(ID_Button_Submit);
			addSubmitAction(button);
			break;
		case Submitted:
			Button approvalButton = createButtonControl(ID_Button_Approval);
			Button denyButton = createButtonControl(ID_Button_Deny);
			if (loginInfo.hasAuth(Auth.SubFunction_PaymentManage_Approval)) {
				addApprovalAction(approvalButton);
				addDenyAction(denyButton);
			} else {
				approvalButton.dispose();
				denyButton.dispose();
			}

			break;
		case Paying:
			button = createButtonControl(ID_Button_Pay);
			if (loginInfo.hasAuth(Auth.SubFunction_PaymentManage_Pay)) {
				addPayAction(button);
			} else {
				button.dispose();
			}
			label = createLabelControl(ID_Label_Log);
			addShowLogAction(label);
			break;
		case Paid:
			label = createLabelControl(ID_Label_Log);
			addShowLogAction(label);
			break;
		}
	}

	private void addSaveAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 保存
				doSave();
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private void addSubmitAction(Button button) {
		button.addActionListener(new ActionListener() {

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
	}

	private void addApprovalAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 批准
				UpdatePaymentStatusTask task = new UpdatePaymentStatusTask(info.getId());
				try {
					getContext().handle(task, UpdatePaymentStatusTask.Method.Approve);
					getContext().bubbleMessage(new MsgResponse(true));
				} catch (Throwable th) {
					alert(th.getMessage());
					th.printStackTrace();
				}
			}
		});
	}

	private void addDenyAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 退回
				MsgRequest request = CommonSelectRequest.createCommonDenyRequest(false);
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (returnValue2 != null) {
							UpdatePaymentStatusTask task = new UpdatePaymentStatusTask(info.getId());
							task.setDenyReason((String) returnValue2);
							getContext().handle(task, UpdatePaymentStatusTask.Method.Deny);
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addPayAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 确认收款
				if (!validateInput_Paying())
					return;
				PayTask task = new PayTask(info.getId(), info.getPaymentNo());
				String[] selectedRowIds = table.getAllRowId();
				// PayTask.Item[] items = new
				// PayTask.Item[selectedRowIds.length];
				List<PayTask.Item> itemList = new ArrayList<PayTask.Item>();
				PayTask.Item item = null;
				for (int rowIndex = 0; rowIndex < selectedRowIds.length; rowIndex++) {
					String rowId = selectedRowIds[rowIndex];
					String amountStr = table.getEditValue(rowId, Columns.currentPayAmount.name())[0];
					String molingAmountStr = table.getEditValue(rowId, Columns.molingAmount.name())[0];
					if (StringUtils.isEmpty(amountStr) && StringUtils.isEmpty(molingAmountStr)) {
						continue;
					}
					GUID checkinSheetId = GUID.tryValueOf(rowId);
					PaymentInfoItem infoItem = getPaymentInfoItemByCheckinSheetId(checkinSheetId);
					double molingAmount = StringUtils.isEmpty(molingAmountStr) ? 0 : DoubleUtil
							.strToDouble(molingAmountStr);
					double amount = StringUtils.isEmpty(amountStr) ? 0 : DoubleUtil.strToDouble(amountStr);

					item = task.new Item(infoItem.getCheckinSheetId(), infoItem.getCheckinSheetId(), infoItem
							.getSheetNo(), infoItem.getRelevantBillId(), infoItem.getRelevantBillNo(), infoItem
							.getCheckinDate(), amount, molingAmount);
					// items[rowIndex] = item;
					itemList.add(item);
				}
				task.setItems(itemList.toArray(new PayTask.Item[0]));
				getContext().handle(task);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private void addShowLogAction(Label label) {
		// 显示付款记录
		SMenuWindow menuWindow = new SMenuWindow(null, label, Direction.Down);
		menuWindow.bindTargetControl(label);
		menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW, "Receipt.onReceiveLog");
		Composite windowArea = menuWindow.getContentArea();
		windowArea.setLayout(new GridLayout());
		final ScrolledPanel area = new ScrolledPanel(windowArea);
		GridData gd = new GridData();
		gd.widthHint = 400;
		gd.heightHint = 280;
		area.setLayoutData(gd);
		final Composite contentArea = area.getComposite();
		menuWindow.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				contentArea.clear();
				GridLayout glContent = new GridLayout();
				glContent.marginTop = 10;
				// glContent.verticalSpacing = 10;
				contentArea.setLayout(glContent);
				showLog(contentArea);
				contentArea.layout();
			}
		});
	}

	private void showLog(Composite contentArea) {
		STableColumn[] columns = new STableColumn[4];
		columns[0] = new STableColumn("date", 100, JWT.CENTER, "付款日期");
		columns[1] = new STableColumn("amount", 100, JWT.RIGHT, "付款金额");
		columns[2] = new STableColumn("moling", 80, JWT.RIGHT, "抹零金额");
		columns[3] = new STableColumn("person", 100, JWT.LEFT, "付款人");
		STableStyle tableStyle = new STableStyle();
		tableStyle.setPageAble(false);
		STable table = new STable(contentArea, columns, tableStyle);
		table.setLayoutData(GridData.INS_FILL_BOTH);
		table.setContentProvider(new SContentProvider() {

			public boolean isSelected(Object element) {
				return false;
			}

			public boolean isSelectable(Object element) {
				return false;
			}

			public Object[] getElements(Context context, STableStatus tablestatus) {
				return info.getLogs();
			}

			public String getElementId(Object element) {
				PaymentLog log = (PaymentLog) element;
				return log.getId().toString();
			}
		});

		table.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}

			public String getText(Object element, int columnIndex) {
				PaymentLog log = (PaymentLog) element;
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(log.getPayDate());
				case 1:
					return DoubleUtil.getRoundStr(log.getAmount());
				case 2:
					return DoubleUtil.getRoundStr(log.getMolingAmount());
				case 3:
					return log.getPayPersonName();
				}
				return null;
			}

			public Color getForeground(Object element, int columnIndex) {
				return null;
			}

			public Color getBackground(Object element, int columnIndex) {
				return null;
			}
		});
		table.render();
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

	private void calTotalPayAmount() {
		double totalAmount = 0.0;
		String[] selectedRowIds = table.getAllRowId();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			totalAmount = 0.0;
		} else {
			for (String rowId : selectedRowIds) {
				// String molingAmountStr = table.getEditValue(rowId,
				// Columns.molingAmount.name())[0];
				String currentAmountStr = table.getEditValue(rowId, Columns.currentPayAmount.name())[0];

				double currentAmount = 0.0;
				// double molingAmount = 0.0;
				if (StringUtils.isNotEmpty(currentAmountStr)) {
					currentAmount = DoubleUtil.strToDouble(currentAmountStr);
				}
				// if (StringUtils.isNotEmpty(molingAmountStr)) {
				// molingAmount = DoubleUtil.strToDouble(molingAmountStr);
				// }
				totalAmount += currentAmount;
			}
		}
		amountText.setText(DoubleUtil.getRoundStr(totalAmount));
	}

	private GUID doSave() {
		if (!validateInput_Edit()) {
			return null;
		}
		UpdatePaymentTask task = new UpdatePaymentTask();
		task.setId(info.getId());
		task.setPaymentType(PaymentType.getPaymentType(payTypeList.getList().getSeleted()));
		task.setDealingsWay(wayList.getList().getSeleted());
		task.setPayDate(datePicker.getDate().getTime());
		task.setAmount(DoubleUtil.strToDouble(amountText.getText()));
		task.setRemark(memoText.getText());
		String[] selectedRowIds = table.getSelections();
		UpdatePaymentTask.Item item = null;
		UpdatePaymentTask.Item[] items = new UpdatePaymentTask.Item[selectedRowIds.length];
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

	private boolean validateInput_Edit() {
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

	private boolean validateInput_Paying() {
		String[] selectedRowIds = table.getAllRowId();
		// if (null == selectedRowIds || selectedRowIds.length < 1) {
		// alert("请先选择入库单。");
		// return false;
		// }
		int emptyCount = 0;
		for (String rowId : selectedRowIds) {
			String currentPayAmountStr = table.getEditValue(rowId, Columns.currentPayAmount.name())[0];
			String molingAmountStr = table.getEditValue(rowId, Columns.molingAmount.name())[0];
			if (StringUtils.isEmpty(currentPayAmountStr) && StringUtils.isEmpty(molingAmountStr)) {
				emptyCount++;
				continue;
			}
			// if (StringUtils.isEmpty(currentPayAmountStr)) {
			// alert("此次付款金额不能为空。");
			// return false;
			// }
			PaymentInfoItem infoItem = getPaymentInfoItemByCheckinSheetId(GUID.tryValueOf(rowId));

			double currentPayAmount = StringUtils.isEmpty(currentPayAmountStr) ? 0.0 : DoubleUtil
					.strToDouble(currentPayAmountStr);
			double molingAmount = StringUtils.isEmpty(molingAmountStr) ? 0.0 : DoubleUtil.strToDouble(molingAmountStr);

			// if (currentPayAmount <= 0) {
			// alert("本次付款金额必须大于0");
			// return false;
			// }
			// if (molingAmount >= currentPayAmount) {
			// alert("抹零金额必须小于本次付款金额。");
			// return false;
			// }
			if (currentPayAmount + molingAmount + infoItem.getPaidAmount() + infoItem.getMolingAmount() > infoItem
					.getAskAmount()) {
				alert("付款金额加抹零金额不能大于申请金额。");
				return false;
			}
		}
		if (emptyCount == selectedRowIds.length) {
			alert("请填写金额。");
			return false;
		}
		return true;
	}

	private class ReasonSource extends ListSourceAdapter {

		public String getElementId(Object element) {
			PaymentType payType = (PaymentType) element;
			return payType.getCode();
		}

		public Object getElementById(String id) {
			return PaymentType.getPaymentType(id);
		}

		public String getText(Object element) {
			PaymentType payType = (PaymentType) element;
			return payType.getName();
		}

		public Object[] getElements(Object inputElement) {
			return new PaymentType[] { PaymentType.PAY_CGFK, PaymentType.PAY_XSTK };
		}

	}

	private PaymentInfoItem getPaymentInfoItemByCheckinSheetId(GUID id) {
		for (PaymentInfoItem item : info.getItems()) {
			if (item.getCheckinSheetId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getRemark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetApprovalInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return info.getPaymentNo();
	}

	@Override
	protected String getSheetTitle() {
		return "付款单";
	}

	@Override
	protected String[] getSheetType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getStopCause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initSheetData() {
		// TODO Auto-generated method stub

	}

	public SNameValue[] getExtraData(Object element) {
		if (element instanceof ReceiptingOrPayingItem) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			return new SNameValue[] {
					new SNameValue(TableExtraValueName.checkinSheetId.name(), item.getSheetId().toString()),
					new SNameValue(TableExtraValueName.sheetNo.name(), item.getSheetNo()),
					new SNameValue(TableExtraValueName.relevantBillId.name(), item.getRelaBillsId().toString()),
					new SNameValue(TableExtraValueName.relevantBillNo.name(), item.getRelaBillsNo()),
					new SNameValue(TableExtraValueName.checkinDate.name(), String.valueOf(item.getCheckInOrOutDate())),
					new SNameValue(TableExtraValueName.askedAmount.name(), String.valueOf(item.getAskedAmount())),
					new SNameValue(TableExtraValueName.amount.name(), String.valueOf(item.getAmount())) };
		} else {
			return null;
		}
	}

	public String getValue(Object element, String columnName) {
		if (PaymentStatus.Submitting.equals(info.getStatus()) || PaymentStatus.Deny.equals(info.getStatus())
				|| PaymentStatus.Paying.equals(info.getStatus())) {
			if (Columns.applyAmount.name().equals(columnName)) {
				ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
				PaymentInfoItem pItem = getPaymentInfoItemByCheckinSheetId(item.getSheetId());
				if (null == pItem)
					return null;
				return DoubleUtil.getRoundStr(pItem.getAskAmount());
			}
			if (Columns.currentPayAmount.name().equals(columnName) || Columns.molingAmount.name().equals(columnName)) {
				return "";
			}
		}

		return null;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof PaymentInfoItem) {
			PaymentInfoItem item = (PaymentInfoItem) element;
			return null == item.getCheckinSheetId() ? item.getId().toString() : item.getCheckinSheetId().toString();
		} else if (element instanceof ReceiptingOrPayingItem) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			return item.getSheetId().toString();
		} else {
			return null;
		}
	}

	public boolean isSelectable(Object element) {
		if (element instanceof ReceiptingOrPayingItem)
			return true;
		if (info.getStatus() == PaymentStatus.Paying) {
			return true;
		}
		return false;
	}

	public boolean isSelected(Object element) {
		if (element instanceof ReceiptingOrPayingItem) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			if (isContainsCheckinSheet(item.getSheetId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (PaymentStatus.Submitting.equals(info.getStatus()) || PaymentStatus.Deny.equals(info.getStatus())) {
			PaymentType payType = PaymentType.getPaymentType(payTypeList.getList().getSeleted());
			GetReceiptingInventorySheetKey key = new GetReceiptingInventorySheetKey(info.getPartnerId(), payType);
			List<ReceiptingOrPayingItem> list = context.getList(ReceiptingOrPayingItem.class, key);
			return list.toArray();
		} else {
			return info.getItems();
		}
	}

	private boolean isContainsCheckinSheet(GUID sheetId) {
		for (PaymentInfoItem item : info.getItems()) {
			if (item.getCheckinSheetId().equals(sheetId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + info.getPaymentNo() + ".xls",
				"application/vnd.ms-excel", 1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("付款单号", info.getPaymentNo());
						bw.addLabel("付款对象", info.getPartnerName());
						bw.addLabel("付款日期", DateUtil.dateFromat(info.getPayDate()));
						bw.addLabel("付款类型", info.getPaymentType().getName());
						bw.addLabel("付款方式", info.getDealingsWay().getName());
						bw.addLabel("备注", info.getRemark());
						String[] head = new String[] { "入库时间", "入库单号", "相关单据", "入库金额", "已申请金额", "付款金额" };
						List<String[]> list = new ArrayList<String[]>();
						for (Object obj : getElements(context, null)) {
							PaymentInfoItem item = (PaymentInfoItem) obj;
							list.add(new String[] { DateUtil.dateFromat(item.getCheckinDate()), item.getSheetNo(),
									item.getRelevantBillNo(), DoubleUtil.getRoundStr(item.getAmount()),
									DoubleUtil.getRoundStr(item.getAskAmount()),
									DoubleUtil.getRoundStr(item.getPaidAmount() + item.getMolingAmount()) });
						}
						bw.setTable(head, list);
						bw.setTotalLabel("总额", DoubleUtil.getRoundStr(info.getPaidAmount()));
						try {
							bw.write(info.getPaymentNo());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

	}

	@Override
	protected boolean isNeedExport() {
		if (this.info.getStatus() == PaymentStatus.Paid) {
			return true;
		}
		return false;
	}
}
