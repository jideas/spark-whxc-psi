package com.spark.psi.account.browser.receipt;

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
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.account.browser.DealingsWaySource;
import com.spark.psi.account.browser.PartnerSearchMsg;
import com.spark.psi.account.browser.PartnerSelectPage;
import com.spark.psi.account.browser.PartnerSelectionMsg;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.ReceiptType;
import com.spark.psi.publish.account.entity.ReceiptingOrPayingItem;
import com.spark.psi.publish.account.task.CreateReceiptTask;
import com.spark.psi.publish.account.task.SubmitReceiptTask;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.inventory.checkin.key.GetReceiptingInventorySheetKey;

/**
 * 新增收款界面处理器（需要选择收款对象）
 */
public class NewReceipt2Processor extends PageProcessor {
	public final static String ID_Text_Search = "Text_Search";
	public final static String ID_Button_Search = "Button_Search";
	public final static String ID_Composite_Parters = "Composite_Parters";
	public final static String ID_Label_Partner = "Label_Partner";
	
	public final static String ID_Date_Date = "Date_Date";
	public final static String ID_Label_Type = "Label_Type";
	public final static String ID_List_Way = "List_Way";
	public final static String ID_Text_Memo = "Text_Memo";
	
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Submit = "Button_Submit";
	public final static String ID_PartnerPage = "PartnerPage";
	public final static String ID_Search = "Search";
	public static final String ID_Table = "Table";
	public static final String ID_Text_Remark = "Text_Remark";
	public static final String ID_Text_TotalAmount = "Label_TotalAmount";

	public enum Columns {
		checkDate, billsNo, relaBillsNo, checkedAmount, // receiptAmount,thisTimeAmount,
		molingAmount;
	}
	
	public static enum TableExtraValueName {
		checkoutSheetId, sheetNo, relevantBillId, relevantBillNo, checkoutDate, askedAmount, amount
	}

	private Label partnerLabel;
	private SDatePicker datePicker;
//	private LWComboList receiptTypeList;
	private Label typeLabel;
	private LWComboList wayList;
	private SEditTable table   = null;
	private Text amountText;
	private Text memoText;
	private PartnerSelectPage partnerPage;

	private PartnerInfo partnerInfo = null;
	private ReceiptType receiptType = null;
	@Override
	public void process(final Situation situation) {
		partnerPage = createControl(ID_PartnerPage, PartnerSelectPage.class);
		partnerLabel = createControl(ID_Label_Partner, Label.class);
		datePicker = createControl(ID_Date_Date, SDatePicker.class);
//		receiptTypeList = createControl(ID_List_Type, LWComboList.class);
		typeLabel = createLabelControl(ID_Label_Type);
		wayList = createControl(ID_List_Way, LWComboList.class);
		table = createControl(ID_Table, SEditTable.class);
		amountText = createControl(ID_Text_TotalAmount, Text.class);
		memoText = createControl(ID_Text_Remark, Text.class, JWT.MULTI | JWT.VERTICAL | JWT.WRAP | JWT.APPEARANCE3);
		final Button saveButton = createButtonControl(ID_Button_Save);
		final Button subitButton = createButtonControl(ID_Button_Submit);

		wayList.getList().setSource(new DealingsWaySource());
		wayList.getList().setInput(null);
		wayList.setSelection(DealingsWay.Cash.getCode());
		
//		receiptTypeList.getList().setSource(new ReceiptTypeResource());
//		receiptTypeList.getList().setInput(null);
//		receiptTypeList.setSelection(ReceiptType.RECEIPT_XSHK.getCode());
		
		datePicker.setDate(new Date());
		
		table.setContentProvider(new TableContentProvider());
		table.setLabelProvider(new TableLabelProvider());
		
		amountText.setEnabled(false);
		
		
		initDataShow(partnerPage.getDefaultSelectId());
		
		situation.regMessageListener(PartnerSelectionMsg.class, new MessageListener<PartnerSelectionMsg>() {

			public void onMessage(Situation context,
					PartnerSelectionMsg message,
					MessageTransmitter<PartnerSelectionMsg> transmitter) {
				initDataShow(message.getPartnerId());
			}
		});
		
		final SSearchText2 search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				situation.broadcastMessage(new PartnerSearchMsg(search
						.getText()));
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
					SubmitReceiptTask task = new SubmitReceiptTask(sheetId);
					getContext().handle(task);
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
	}

	private void initDataShow(GUID partnerId) {
		if(partnerId == null) return; 
		partnerInfo = getContext().find(PartnerInfo.class, partnerId);
		if (PartnerType.Customer.equals(partnerInfo.getPartnerType())) {
			typeLabel.setText(ReceiptType.RECEIPT_XSHK.getName());
			receiptType = ReceiptType.RECEIPT_XSHK;
		} else if (PartnerType.Supplier.equals(partnerInfo.getPartnerType())) {
			typeLabel.setText(ReceiptType.RECEIPT_CGTK.getName());
			receiptType = ReceiptType.RECEIPT_CGTK;
		}
		
		partnerLabel.setText(partnerInfo.getShortName());
		table.render();
	}
	
	private void calTotalApplyAmount() {
		double totalAmount = 0.0;
		String[] selectedRowIds = table.getSelections();
		if (null == selectedRowIds || selectedRowIds.length < 1) {
			totalAmount = 0.0;
		} else {
			for (String rowId : selectedRowIds) {
				String amountStr = table.getExtraData(rowId, TableExtraValueName.amount.name())[0];
				String molingAmountStr = table.getEditValue(rowId, Columns.molingAmount.name())[0];
				
				double amount = StringUtils.isEmpty(amountStr) ? 0.0 : DoubleUtil.strToDouble(amountStr);
				double molingAmount = StringUtils.isEmpty(molingAmountStr) ? 0.0 : DoubleUtil.strToDouble(molingAmountStr);
				
				totalAmount += amount - molingAmount;
			}
		}
		amountText.setText(DoubleUtil.getRoundStr(totalAmount));
	}
	
	private GUID doSave() {
		if (!validateInput_This()) {
			return null;
		}
		CreateReceiptTask task = new CreateReceiptTask();
		task.setId(GUID.randomID());
		task.setPartnerName(partnerInfo.getShortName());
		task.setPartnerId(partnerInfo.getId());
		task.setReceiptMode(wayList.getList().getSeleted());
		task.setReceiptDate(datePicker.getDate().getTime());
		task.setAmount(DoubleUtil.strToDouble(amountText.getText()));
		task.setRemark(memoText.getText());
		task.setReceiptType(receiptType.getCode());
		
		String[] selectedRowIds = table.getSelections();
		CreateReceiptTask.Item[] items = new CreateReceiptTask.Item[selectedRowIds.length];
		CreateReceiptTask.Item item = null;
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
			alert("请先选择入库单。");
			return false;
		}
		return true;
	}

	private class TableContentProvider implements SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem)element;
			return new SNameValue[] {new SNameValue(TableExtraValueName.checkoutSheetId.name(), item.getSheetId().toString()),
					new SNameValue(TableExtraValueName.sheetNo.name(), item.getSheetNo()),
					new SNameValue(TableExtraValueName.relevantBillId.name(), item.getRelaBillsId().toString()),
					new SNameValue(TableExtraValueName.relevantBillNo.name(), item.getRelaBillsNo()),
					new SNameValue(TableExtraValueName.checkoutDate.name(), String.valueOf(item.getCheckInOrOutDate())),
					new SNameValue(TableExtraValueName.askedAmount.name(), String.valueOf(item.getAskedAmount())),
					new SNameValue(TableExtraValueName.amount.name(), String.valueOf(item.getAmount()))};
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			if (Columns.molingAmount.name().equals(columnName)) {
				return "";
			}
			return null;
		}

		public String getElementId(Object element) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem)element;
			return item.getSheetId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (null == partnerInfo) {
				return null;
			} else {
//				ReceiptType receiptType = ReceiptType.getReceiptType(receiptType);
				GetReceiptingInventorySheetKey key = new GetReceiptingInventorySheetKey(partnerInfo.getId(), receiptType);
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
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			ReceiptingOrPayingItem item = (ReceiptingOrPayingItem)element;
			switch(columnIndex) {
			case 0:
				return DateUtil.dateFromat(item.getCheckInOrOutDate());
			case 1:
				return item.getSheetNo();
			case 2:
				return item.getRelaBillsNo();
			case 3:
				return DoubleUtil.getRoundStr(item.getAmount());
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}
		
	}

}
class ReceiptTypeResource extends ListSourceAdapter {

	public Object[] getElements(Object inputElement) {
		return new ReceiptType[]{ReceiptType.RECEIPT_XSHK,  ReceiptType.RECEIPT_CGTK};
	}

	public String getText(Object element) {
		ReceiptType type = (ReceiptType)element;
		return type.getName();
	}

	public Object getElementById(String id) {
		return ReceiptType.getReceiptType(id);
	}

	public String getElementId(Object element) {
		ReceiptType type = (ReceiptType)element;
		return type.getCode();
	}
	
}