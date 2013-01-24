package com.spark.psi.inventory.browser.loss;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey.ViewStatus;
import com.spark.psi.publish.inventory.task.DeleteReportLossInfoTask;

public class SubmittingReportLossListProcessor<Item> extends PSIListPageProcessor<Item> {
	
	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Button_New = "Button_New";
	
	public static enum ColumnName {
		sheetCode, storeName, createDate, status
	}
	
	private Label countLabel     = null;
	private Button newBtn        = null;
	
	@Override
	public void process(final Situation context) {
		super.process(context);
		countLabel = createControl(ID_Label_Count, Label.class);
		newBtn = createControl(ID_Button_New, Button.class);
		
		newBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 打开新增报损单页面
				PageController pc = new PageController(NewReportLossSheetProcessor.class, NewReportLossSheetRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "新增报损");
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
		ReportLossInfo info = (ReportLossInfo)element;
		return info.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetReportLossInfoListKey key = new GetReportLossInfoListKey(0, 100, false);
//		key.setStatus(ReportLossStatus.Submitting, ReportLossStatus.Deied);
		key.setViewStatus(ViewStatus.submitting);
		List<ReportLossInfo> infoList = context.getList(ReportLossInfo.class, key);
		countLabel.setText(String.valueOf(infoList.size()));
		return infoList.toArray(new ReportLossInfo[0]);
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Submit.name(), Action.Delete.name()};
	}

	@Override
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (Action.Delete.name().equals(actionName)) {
			confirm("确定要删除该记录吗？", new Runnable() {
				
				public void run() {
					getContext().handle(new DeleteReportLossInfoTask(GUID.tryValueOf(rowId)));
					table.render();
				}
			});
		} else if (Action.Detail.name().equals(actionName) || Action.Submit.name().equals(actionName)) {
			PageController pc = new PageController(NewReportLossSheetProcessor.class, NewReportLossSheetRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "报损单详情");
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
		return "报损单";
	}
}
