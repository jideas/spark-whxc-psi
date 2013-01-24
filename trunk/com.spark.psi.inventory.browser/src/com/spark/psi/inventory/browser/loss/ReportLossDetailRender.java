package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.ReportLossStatus;

public class ReportLossDetailRender extends ReportLossSheetRender {

	@Override
	protected void renderBaseInfo(Composite baseInfoArea) {
		new Label(baseInfoArea).setText("报损仓库：" + reportLossInfo.getStoreName());
		new Label(baseInfoArea).setText("        ");
		new Label(baseInfoArea).setText("申请日期：" + DateUtil.dateFromat(reportLossInfo.getApplyDate()));
	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		if (ReportLossStatus.Approvling.equals(reportLossInfo.getStatus())) {
			Button approvalBtn = new Button(sheetButtonArea, JWT.APPEARANCE3);
			approvalBtn.setText(" 批准申请 ");
			approvalBtn.setID(ReportLossDetailProcessor.ID_Button_Approval);
			
			Button rejectBtn = new Button(sheetButtonArea, JWT.APPEARANCE3);
			rejectBtn.setText(" 退回申请 ");
			rejectBtn.setID(ReportLossDetailProcessor.ID_Button_Reject);
		} else if (ReportLossStatus.AccountApprovling.equals(reportLossInfo.getStatus())) {
			Button approvalBtn = new Button(sheetButtonArea, JWT.APPEARANCE3);
			approvalBtn.setText(" 批准申请 ");
			approvalBtn.setID(ReportLossDetailProcessor.ID_Button_AccountApproval);
			
			Button rejectBtn = new Button(sheetButtonArea, JWT.APPEARANCE3);
			rejectBtn.setText(" 退回申请 ");
			rejectBtn.setID(ReportLossDetailProcessor.ID_Button_AccountReject);
		}
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
	}
	
	
}
