package com.spark.psi.account.browser.receipt;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.psi.publish.account.entity.ReceiptListEntity;
import com.spark.psi.publish.account.entity.RetailSubmitingItem;
import com.spark.psi.publish.account.key.GetReceiptListKey;

/**
 * 进行中的收款通知单列表处理器
 */
public class ReceiptsListProcessor extends PSIListPageProcessor<RetailSubmitingItem> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	public static final String ID_Button_New = "Button_New";
	
	public static enum ColumnName {
		planDate, sheetNo, partnerName, receiptType, receiptAmount
	}

	private SSearchText2 search;

	public enum Colums {
		PlanReceiptDate, ReceiptsNo, Partner, ReceiptsType, PlanAmount, ReceiptAmount
	}

	private Label countLabel    = null;
	
	@Override
	public void process(Situation situation) {
		super.process(situation);
		countLabel = createLabelControl(ID_Label_Count);
		search = this.createControl(ID_Search, SSearchText2.class);
		if (null != search) {
			search.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					table.render();
				}
			});
		}

	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetReceiptListKey key = new GetReceiptListKey(0, Integer.MAX_VALUE, true);
		key.setSearchText(search.getText());
		key.setStatus(ReceiptStatus.Receipting);
		ListEntity<ReceiptItem> listEntity = context.find(ReceiptListEntity.class, key);
		if (null == listEntity) return null;
		updateRecordCount(listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new ReceiptItem[0]);
	}


	// private SortField getSortField(String sortColumn) {
	// if (Colums.Amount.name().equals(sortColumn)) {
	// return SortField.Amount;
	// } else if (Colums.BeginDate.name().equals(sortColumn)) {
	// return SortField.BeginDate;
	// } else if (Colums.CardRecordAmount.name().equals(sortColumn)) {
	// return SortField.CardRecordAmount;
	// } else if (Colums.CardRecordCount.name().equals(sortColumn)) {
	// return SortField.CardRecordCount;
	// } else if (Colums.SalesName.name().equals(sortColumn)) {
	// return SortField.SalesName;
	// }
	// return null;
	// }

	public String getElementId(Object element) {
		return ((ReceiptItem) element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		if (getContext().find(Boolean.class, Auth.Account) || getContext().find(Boolean.class, Auth.Boss)
				|| getContext().find(Boolean.class, Auth.AccountManager)) {
			return new String[] { Action.Receipt.name() };
		}
		return null;
	}

	// protected String[] getElementActionIds(Object element) {
	// RetailSubmitingItem item = (RetailSubmitingItem) element;
	// /*if (==) {
	// return new String[] { Action.Confirm.name() };
	// }*/
	// return null;
	// }

	@Override
	protected String[] getElementActionIds(Object element) {
		if (getContext().find(Boolean.class, Auth.Account) || getContext().find(Boolean.class, Auth.Boss)
				|| getContext().find(Boolean.class, Auth.AccountManager)) {
			return new String[] { Action.Receipt.name() };
		}
		return null;
	}

	private void updateRecordCount(int count) {
		countLabel.setText("" + count);
	}
	
	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (Action.Detail.name().equals(actionName) || Action.Receipt.name().equals(actionName)) {
			// 打开详情界面
			PageController pc = new PageController(ReceiptDetailProcessor.class, ReceiptDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "收款详情");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
			
		} 
	}

	@Override
	protected String getExportFileTitle() {
		return "收款记录-未完成";
	}
}
