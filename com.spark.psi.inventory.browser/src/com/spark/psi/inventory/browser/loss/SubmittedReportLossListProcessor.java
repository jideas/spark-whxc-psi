package com.spark.psi.inventory.browser.loss;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.ReportLossStatus;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey.ViewStatus;

public class SubmittedReportLossListProcessor<Item> extends
		PSIListPageProcessor<Item> {
	
	public static final String ID_Label_Count = "Label_Count";
	
	public static enum ColumnName {
		submittingDate, sheetCode, storeName, reporterName
	}

	private Label countLabel    = null;
	
	private LoginInfo loginInfo = null;
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		countLabel = createControl(ID_Label_Count, Label.class);
	}

	@Override
	public String getElementId(Object element) {
		ReportLossInfo info = (ReportLossInfo)element;
		return info.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetReportLossInfoListKey key = new GetReportLossInfoListKey(0, 100, false);
//		key.setStatus(ReportLossStatus.Approvling);
		key.setViewStatus(ViewStatus.approvling);
		List<ReportLossInfo> infoList = context.getList(ReportLossInfo.class, key);
		countLabel.setText(String.valueOf(infoList.size()));
		return infoList.toArray(new ReportLossInfo[0]);
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Approval.name()};
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		ReportLossInfo info = (ReportLossInfo)element;
		if (ReportLossStatus.Approvling.equals(info.getStatus())
				&& loginInfo.hasAuth(Auth.SubFunction_ReportLoss_Approval)) {
			return new String[] { Action.Approval.name(), Action.Deny.name()};
		} else if (ReportLossStatus.AccountApprovling.equals(info.getStatus())
				&& loginInfo.hasAuth(Auth.SubFunction_ReportLoss_AccountApproval)) {
			return new String[] { Action.Approval.name(), Action.Deny.name()};
		}
		return null;
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName) || Action.Approval.name().equals(actionName)
				|| Action.Deny.name().equals(actionName)) {
			PageController pc = new PageController(ReportLossDetailProcessor.class, ReportLossDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "报损详情");
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
