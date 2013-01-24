package com.spark.psi.inventory.browser.loss;

import java.util.Date;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ReportLossStatus;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItem;
import com.spark.psi.publish.inventory.task.ChangReportLossInfoStautsTask;
import com.spark.psi.publish.inventory.task.ChangReportLossInfoStautsTask.Operation;

public class ReportLossDetailProcessor<TItem> extends ReportLossSheetProcessor<TItem> {

	public static final String ID_Button_AccountApproval = "Button_AccountApproval";
	public static final String ID_Button_AccountReject = "Button_AccountReject";
	public static final String ID_Button_Approval = "Button_Approval";
	public static final String ID_Button_Reject = "Button_Reject";
	
	
	@Override
	public void process(final Situation situation) {
		super.process(situation);
		final Button approvalBtn = createControl(ID_Button_Approval, Button.class);
		final Button rejectBtn = createControl(ID_Button_Reject, Button.class);
		final Button acApprovalBtn = createControl(ID_Button_AccountApproval, Button.class);
		final Button acRejectBtn = createControl(ID_Button_AccountReject, Button.class);
		final Text memoText = createMemoText();
		memoText.setText(reportLossInfo.getRemark());
		if (ReportLossStatus.Submitting.equals(reportLossInfo.getStatus())
				|| ReportLossStatus.Deied.equals(reportLossInfo.getStatus())) {
			memoText.setEnabled(true);
		} else {
			memoText.setEnabled(false);
		}
		
		if (loginInfo.hasAuth(Auth.SubFunction_ReportLoss_Approval)) {
			addApprovalAction(approvalBtn);
			addRejectAction(rejectBtn);
		} else {
			if (null != approvalBtn) approvalBtn.dispose();
			if (null != rejectBtn)  rejectBtn.dispose();
		}
		if (loginInfo.hasAuth(Auth.SubFunction_ReportLoss_AccountApproval)) {
			addApprovalAction(acApprovalBtn);
			addRejectAction(acRejectBtn);
		} else {
			if (null != acApprovalBtn) acApprovalBtn.dispose();
			if (null != acRejectBtn) acRejectBtn.dispose();
		}
	}
	
	private void addApprovalAction(Button button) {
		if (button == null) return ;
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 批准
				ChangReportLossInfoStautsTask task = new ChangReportLossInfoStautsTask(reportLossInfo.getId(), reportLossInfo.getStatus());
				task.setChangePersonId(loginInfo.getEmployeeInfo().getId());
				task.setChangePersonName(loginInfo.getEmployeeInfo().getName());
				task.setChangeTime(new Date().getTime());
				task.setOperation(Operation.approval);
				getContext().handle(task);
				
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
	private void addRejectAction(Button button) {
		if (button == null) return ;
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 退回
				MsgRequest request = CommonSelectRequest.createCommonDenyRequest(false);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (null != returnValue2) {
							ChangReportLossInfoStautsTask submitTask = new ChangReportLossInfoStautsTask(reportLossInfo.getId(), reportLossInfo.getStatus());
							submitTask.setChangePersonId(loginInfo.getEmployeeInfo().getId());
							submitTask.setChangePersonName(loginInfo.getEmployeeInfo().getName());
							submitTask.setChangeTime(new Date().getTime());
							submitTask.setOperation(Operation.reject);
							submitTask.setRejectReason((String)returnValue2);
							getContext().handle(submitTask);
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
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
		return reportLossInfo.getRemark();
	}

	@Override
	protected String getSheetApprovalInfo() {
		if (null != reportLossInfo.getApprovalPersonName()) {
			return "审批：" + reportLossInfo.getApprovalPersonName() + "(" + DateUtil.dateFromat(reportLossInfo.getApprovalDate()) + ")";
		} else {
			return null;
		}
		
	}

	@Override
	protected String getSheetCreateInfo() {
		return "制单：" + reportLossInfo.getCreateor() + "(" + DateUtil.dateFromat(reportLossInfo.getCreateDate()) + ")";
	}

	@Override
	protected String[] getSheetExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return reportLossInfo.getSheetNo();
	}

	@Override
	protected String getSheetTitle() {
		return "报损单";
	}

	@Override
	protected String[] getSheetType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	@Override
	public String getElementId(Object element) {
		ReportLossInfoItem item = (ReportLossInfoItem)element;
		return item.getGoodsId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return reportLossInfo.getItems();
	}

}
