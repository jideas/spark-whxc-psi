package com.spark.psi.account.browser.receipt;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.ReceiptStatus;
import com.spark.psi.publish.account.entity.ReceiptItem;
import com.spark.psi.publish.account.entity.ReceiptListEntity;
import com.spark.psi.publish.account.key.GetReceiptListKey;
import com.spark.psi.publish.account.task.DeleteReceiptTask;

public class SubmittingReceiptListProcessor<Item> extends
		PSIListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	public static final String ID_Button_New = "Button_New";
	
	public static enum ColumnName {
		planDate, sheetNo, partnerName, receiptType, receiptAmount
	}
	
	private Label countLabel   = null;
	private SSearchText2 search  = null;
	
	@Override
	public void process(final Situation context) {
		super.process(context);
		countLabel = createLabelControl(ID_Label_Count);
		search = createControl(ID_Search, SSearchText2.class);
		final Button button = createButtonControl(ID_Button_New);
		
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(NewReceipt2Processor.class, NewReceipt2Render.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "新增收款");
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				context.bubbleMessage(request);
			}
		});
	}

	@Override
	public String getElementId(Object element) {
		ReceiptItem item = (ReceiptItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetReceiptListKey key = new GetReceiptListKey(0, Integer.MAX_VALUE, true);
		key.setStatus(ReceiptStatus.Submitting);
		key.setSearchText(search.getText());
		ListEntity<ReceiptItem> listEntity = context.find(ReceiptListEntity.class, key);
		if (null == listEntity) return null;
		updateRecordCount(listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new ReceiptItem[0]);
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Submit.name(), Action.Delete.name() };
	}
	
	@Override
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName) || Action.Submit.name().equals(actionName)) {
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
			
		} else if (Action.Delete.name().equals(actionName)) {
			// 删除记录
			alert("确定要删除该记录吗？", new Runnable(){

				public void run() {
					DeleteReceiptTask task = new DeleteReceiptTask(GUID.tryValueOf(rowId));
					getContext().handle(task);
					table.render();
				}
				
			});
		}
	}

	private void updateRecordCount(int count) {
		countLabel.setText("" + count);
	}

	@Override
	protected String getExportFileTitle() {
		return "收款登记-待提交";
	}

}
