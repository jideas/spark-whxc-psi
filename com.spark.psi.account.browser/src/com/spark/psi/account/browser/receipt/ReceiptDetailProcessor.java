package com.spark.psi.account.browser.receipt;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
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
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.account.browser.DealingsWaySource;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.account.entity.ReceiptInfo;
import com.spark.psi.publish.account.entity.ReceiptInfoItem;
import com.spark.psi.publish.account.entity.ReceiptLog;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;
import com.spark.psi.publish.account.task.ReceiptTask;
import com.spark.psi.publish.account.task.SubmitReceiptTask;
import com.spark.psi.publish.account.task.UpdateReceiptTask;
import com.spark.psi.publish.inventory.checkin.key.GetReceiptingInventorySheetKey;

public class ReceiptDetailProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	public final static String ID_Label_Partner = "Label_Partner";
	public final static String ID_Date_Date = "Date_Date";
	public final static String ID_List_Type = "Label_Type";
	public final static String ID_List_Way = "List_Way";
	public final static String ID_Label_Log = "Label_Log";
	public static final String ID_Text_TotalAmount = "Label_TotalAmount";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Submit = "Button_Submit";
	public final static String ID_Button_Receipt = "Button_Receipt";

	public enum Columns {
		checkDate, billsNo, relaBillsNo, checkedAmount, // receiptAmount,thisTimeAmount,
		molingedAmount, receiptedAmount, currentReceiptAmount, molingAmount;
	}

	public static enum TableExtraValueName {
		checkoutSheetId, sheetNo, relevantBillId, relevantBillNo, checkoutDate, askedAmount, amount
	}

	private Label partnerLabel;
	private SDatePicker datePicker;
	private LWComboList receiptTypeList;
	private LWComboList wayList;
	private Text amountText;
	private Text memoText;

	private ReceiptInfo info = null;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		partnerLabel = createControl(ID_Label_Partner, Label.class);
		datePicker = createControl(ID_Date_Date, SDatePicker.class);
		receiptTypeList = createControl(ID_List_Type, LWComboList.class);
		wayList = createControl(ID_List_Way, LWComboList.class);
		amountText = createControl(ID_Text_TotalAmount, Text.class);
		memoText = createMemoText();

		wayList.getList().setSource(new DealingsWaySource());
		wayList.getList().setInput(null);
		wayList.setSelection(info.getReceiptMode().getCode());

		receiptTypeList.getList().setSource(new ReceiptTypeResource());
		receiptTypeList.getList().setInput(null);
		receiptTypeList.setSelection(info.getReceiptType().getCode());

		datePicker.setDate(new Date());
		partnerLabel.setText(info.getPartnerName());
		amountText.setEnabled(false);
		if (ReceiptStatus.Submitting == info.getStatus()
				|| ReceiptStatus.Receipted == info.getStatus()) {
			amountText.setText(DoubleUtil.getRoundStr(info.getAmount()));
		}
		memoText.setText(info.getRemark());

		if (info.getStatus() == ReceiptStatus.Submitting) {
			datePicker.setEnabled(true);
			memoText.setEnabled(true);
			wayList.setEnabled(true);
			receiptTypeList.setEnabled(true);
		} else {
			datePicker.setEnabled(false);
			memoText.setEnabled(false);
			wayList.setEnabled(false);
			receiptTypeList.setEnabled(false);
		}

		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "Account.handleTableDataChanged");
		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (info.getStatus() == ReceiptStatus.Submitting) {
					calTotalAmount();
				} else if (info.getStatus() == ReceiptStatus.Receipting) {
					calTotalCurrentAmount();
				}
			}
		});

		table.addClientNotifyListener(new ClientNotifyListener() {

			public void notified(ClientNotifyEvent e) {
				if (info.getStatus() == ReceiptStatus.Submitting) {
					calTotalAmount();
				} else if (info.getStatus() == ReceiptStatus.Receipting) {
					calTotalCurrentAmount();
				}
			}
		});

		initFormActions();
	}

	private void initFormActions() {
		Button button = null;
		if (info.getStatus() == ReceiptStatus.Submitting) {
			button = createButtonControl(ID_Button_Save);
			addSaveAction(button);
			button = createButtonControl(ID_Button_Submit);
			addSubmitAction(button);
		} else if (info.getStatus() == ReceiptStatus.Receipting) {
			button = createButtonControl(ID_Button_Receipt);
			addReceiptAction(button);
		}
		if (info.getStatus() == ReceiptStatus.Receipting || info.getStatus() == ReceiptStatus.Receipted) {
			Label label = createLabelControl(ID_Label_Log);
			addShowReceiptLogAction(label);
		}
	}

	private void addSaveAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 保存
				GUID sheetId = doSave();
				if (null != sheetId) {
					getContext().bubbleMessage(new MsgResponse(true));
				}
			}
		});
	}

	private void addSubmitAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 提交
				GUID sheetId = doSave();
				if (null != sheetId) {
					SubmitReceiptTask task = new SubmitReceiptTask(sheetId);
					try {
						getContext().handle(task);
						getContext().bubbleMessage(new MsgResponse(true));
					} catch (Throwable th) {
						alert(th.getMessage());
						th.printStackTrace();
					}
				}
			}
		});
	}

	private void addReceiptAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 确认收款
				if (!validateInput_Receipt())
					return;
				ReceiptTask task = new ReceiptTask(info.getId(), info.getReceiptsNo());
				String[] selectedRowIds = table.getAllRowId();
				ReceiptTask.Item[] items = new ReceiptTask.Item[selectedRowIds.length];
				ReceiptTask.Item item = null;
				for (int rowIndex = 0; rowIndex < selectedRowIds.length; rowIndex++) {
					String rowId = selectedRowIds[rowIndex];
					ReceiptInfoItem infoItem = getReceiptInfoItemBySheetId(GUID.tryValueOf(rowId));
					String currentAmountStr = table.getEditValue(rowId, Columns.currentReceiptAmount.name())[0];
					String molingAmountStr = table.getEditValue(rowId, Columns.molingAmount.name())[0];
					double molingAmount = StringUtils.isEmpty(molingAmountStr) ? 0 : DoubleUtil
							.strToDouble(molingAmountStr);

					item = task.new Item(infoItem.getCheckoutSheetId(), infoItem.getCheckoutSheetId(), infoItem
							.getSheetNo(), infoItem.getRelevantBillId(), infoItem.getRelevantBillNo(), infoItem
							.getCheckoutDate(), DoubleUtil.strToDouble(currentAmountStr), molingAmount);
					items[rowIndex] = item;
				}
				task.setItems(items);
				getContext().handle(task);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private void addShowReceiptLogAction(Label label) {
		// 显示收款记录
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
		columns[0] = new STableColumn("date", 100, JWT.CENTER, "收款日期");
		columns[1] = new STableColumn("amount", 100, JWT.RIGHT, "收款金额");
		columns[2] = new STableColumn("moling", 80, JWT.RIGHT, "抹零金额");
		columns[3] = new STableColumn("person", 100, JWT.LEFT, "收款人");
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
				ReceiptLog log = (ReceiptLog) element;
				return log.getId().toString();
			}
		});

		table.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}

			public String getText(Object element, int columnIndex) {
				ReceiptLog log = (ReceiptLog) element;
				switch (columnIndex) {
				case 0:
					return DateUtil.dateFromat(log.getReceiptDate());
				case 1:
					return DoubleUtil.getRoundStr(log.getAmount());
				case 2:
					return DoubleUtil.getRoundStr(log.getMolingAmount());
				case 3:
					return log.getReceiptPersonName();
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

	private GUID doSave() {
		if (!validateInput_This()) {
			return null;
		}
		UpdateReceiptTask task = new UpdateReceiptTask();
		task.setId(info.getId());
		task.setReceiptDate(datePicker.getDate().getTime());
		task.setReceiptMode(wayList.getList().getSeleted());
		task.setAmount(DoubleUtil.strToDouble(amountText.getText()));
		task.setRemark(memoText.getText());
		task.setReceiptType(receiptTypeList.getList().getSeleted());
		String[] selectedRowIds = table.getSelections();
		UpdateReceiptTask.Item[] items = new UpdateReceiptTask.Item[selectedRowIds.length];
		UpdateReceiptTask.Item item = null;
		int index = 0;
		for (String rowId : selectedRowIds) {
			String[] extraValues = table.getExtraData(rowId, TableExtraValueName.checkoutSheetId.name(),
					TableExtraValueName.sheetNo.name(), TableExtraValueName.relevantBillId.name(),
					TableExtraValueName.relevantBillNo.name(), TableExtraValueName.checkoutDate.name(),
					TableExtraValueName.amount.name());
			item = task.new Item(GUID.tryValueOf(extraValues[0]), extraValues[1], GUID.tryValueOf(extraValues[2]),
					extraValues[3], Long.parseLong(extraValues[4]), DoubleUtil.strToDouble(extraValues[5]));

			items[index++] = item;
		}
		task.setItems(items);
		getContext().handle(task);
		return task.getId();
	}

	private boolean validateInput_This() {
		String[] selectedRowIds = table.getSelections();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			alert("请先选择出库单。");
			return false;
		}
		return true;
	}

	private boolean validateInput_Receipt() {
		String[] selectedRowIds = table.getSelections();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			alert("请先选择出库单。");
			return false;
		}
		for (String rowId : selectedRowIds) {
			String currentPayAmountStr = table.getEditValue(rowId, Columns.currentReceiptAmount.name())[0];
			if (StringUtils.isEmpty(currentPayAmountStr)) {
				alert("本次收款金额不能为空。");
				return false;
			}
			ReceiptInfoItem infoItem = getReceiptInfoItemBySheetId(GUID.tryValueOf(rowId));
			String molingAmountStr = table.getEditValue(rowId, Columns.molingAmount.name())[0];

			double currentPayAmount = DoubleUtil.strToDouble(currentPayAmountStr);
			double molingAmount = StringUtils.isEmpty(molingAmountStr) ? 0.0 : DoubleUtil.strToDouble(molingAmountStr);

			if (currentPayAmount <= 0) {
				alert("本次收款金额必须大于0");
				return false;
			}
			if (molingAmount >= currentPayAmount) {
				alert("抹零金额必须小于本次收款金额。");
				return false;
			}
			if (currentPayAmount + molingAmount + infoItem.getMolingAmount() + infoItem.getReceiptedAmount() > infoItem
					.getAmount()) {
				alert("收款金额加抹零金额不能大于出库金额。");
				return false;
			}
		}
		return true;
	}

	private void calTotalAmount() {
		double totalAmount = 0.0;
		String[] selectedRowIds = table.getSelections();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			totalAmount = 0.0;
		} else {
			for (String rowId : selectedRowIds) {
				String amountStr = table.getExtraData(rowId, TableExtraValueName.amount.name())[0];

				double amount = 0.0;

				if (StringUtils.isNotEmpty(amountStr)) {
					amount = DoubleUtil.strToDouble(amountStr);
				}
				totalAmount += amount;
			}
		}
		amountText.setText(DoubleUtil.getRoundStr(totalAmount));
	}

	private void calTotalCurrentAmount() {
		double totalAmount = 0.0;
		String[] selectedRowIds = table.getAllRowId();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			totalAmount = 0.0;
		} else {
			for (String rowId : selectedRowIds) {
				// String molingAmountStr = table.getEditValue(rowId,
				// Columns.molingAmount.name())[0];
				String currentAmountStr = table.getEditValue(rowId, Columns.currentReceiptAmount.name())[0];

				// double molingAmount = 0.0;
				double currentAmount = 0.0;

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
		return info.getReceiptsNo();
	}

	@Override
	protected String getSheetTitle() {
		return "收款单";
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
		GUID sheetId = (GUID) getArgument();
		if (null != sheetId) {
			info = getContext().find(ReceiptInfo.class, sheetId);
		}
	}

	public SNameValue[] getExtraData(Object element) {
		if (element instanceof ReceiptingOrPayingItem) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			return new SNameValue[] {
					new SNameValue(TableExtraValueName.checkoutSheetId.name(), item.getSheetId().toString()),
					new SNameValue(TableExtraValueName.sheetNo.name(), item.getSheetNo()),
					new SNameValue(TableExtraValueName.relevantBillId.name(), item.getRelaBillsId().toString()),
					new SNameValue(TableExtraValueName.relevantBillNo.name(), item.getRelaBillsNo()),
					new SNameValue(TableExtraValueName.checkoutDate.name(), String.valueOf(item.getCheckInOrOutDate())),
					new SNameValue(TableExtraValueName.askedAmount.name(), String.valueOf(item.getAskedAmount())),
					new SNameValue(TableExtraValueName.amount.name(), String.valueOf(item.getAmount())) };
		} else {
			return null;
		}
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof ReceiptInfoItem) {
			ReceiptInfoItem item = (ReceiptInfoItem) element;
			return item.getCheckoutSheetId().toString();
		} else if (element instanceof ReceiptingOrPayingItem) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			return item.getSheetId().toString();
		} else {
			return null;
		}
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (ReceiptStatus.Submitting.equals(info.getStatus())) {
			ReceiptType receiptType = ReceiptType.getReceiptType(receiptTypeList.getList().getSeleted());
			GetReceiptingInventorySheetKey key = new GetReceiptingInventorySheetKey(info.getPartnerId(), receiptType);
			List<ReceiptingOrPayingItem> list = context.getList(ReceiptingOrPayingItem.class, key);
			return list.toArray();
		} else {
			return info.getItems();
		}
	}

	@Override
	public String getValue(Object element, String columnName) {
		if (info.getStatus() == ReceiptStatus.Receipted)
			return null;
		if (Columns.molingAmount.name().equals(columnName) || Columns.currentReceiptAmount.name().equals(columnName)) {
			return "";
		}
		return null;
	}

	public boolean isSelectable(Object element) {
		if (info.getStatus() == ReceiptStatus.Submitting || info.getStatus() == ReceiptStatus.Receipting) {
			return true;
		}
		return false;
	}

	public boolean isSelected(Object element) {
		if (element instanceof ReceiptingOrPayingItem) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem) element;
			if (isContainsCheckoutSheet(item.getSheetId())) {
				return true;
			}
		}
		return false;
	}

	private boolean isContainsCheckoutSheet(GUID sheetId) {
		for (ReceiptInfoItem item : info.getItems()) {
			if (item.getCheckoutSheetId().equals(sheetId)) {
				return true;
			}
		}
		return false;
	}

	private ReceiptInfoItem getReceiptInfoItemBySheetId(GUID checkOutSheetId) {
		for (ReceiptInfoItem item : info.getItems()) {
			if (item.getCheckoutSheetId().equals(checkOutSheetId)) {
				return item;
			}
		}
		return null;
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + info.getReceiptsNo() + ".xls",
				"application/vnd.ms-excel", 1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("收款单号", info.getReceiptsNo());
						bw.addLabel("收款对象", info.getPartnerName());
						bw.addLabel("收款日期", DateUtil.dateFromat(info.getReceiptDate()));
						bw.addLabel("收款原因", info.getReceiptType().getName());
						bw.addLabel("收款方式", info.getReceiptMode().getName());
						bw.addLabel("备注", info.getRemark());
						String[] head = new String[] { "出库时间", "处库单号", "相关单据", "出库金额", "抹零金额", "收款金额" };
						List<String[]> list = new ArrayList<String[]>();
						for (Object obj : getElements(context, null)) {
							ReceiptInfoItem item = (ReceiptInfoItem) obj;
							list.add(new String[] { DateUtil.dateFromat(item.getCheckoutDate()), item.getSheetNo(),
									item.getRelevantBillNo(), DoubleUtil.getRoundStr(item.getAmount()),
									DoubleUtil.getRoundStr(item.getMolingAmount()),
									DoubleUtil.getRoundStr(item.getReceiptedAmount()) });
						}
						bw.setTable(head, list);
						bw.setTotalLabel("收款总额", DoubleUtil.getRoundStr(info.getReceiptedAmount()));
						try {
							bw.write(info.getReceiptsNo());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

	}

	@Override
	protected boolean isNeedExport() {
		if (null != info && info.getStatus() == ReceiptStatus.Receipted) {
			return true;
		}
		return false;
	}

}
