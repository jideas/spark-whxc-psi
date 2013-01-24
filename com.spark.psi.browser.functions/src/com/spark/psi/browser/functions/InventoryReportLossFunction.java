package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class InventoryReportLossFunction extends PSIFunction {

	@Override
	protected String getIconName() {
		return "KuCunPanDian";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		LoginInfo loginInfo = context.find(LoginInfo.class);
		if (loginInfo.hasAuth(Auth.SubFunction_ReportLoss_Create)) {
			list.add(new BaseFunction(new PageControllerInstance(
						"SubmittingReportLossListPage"), "待提交", true));
		}
		list.add(new BaseFunction(new PageControllerInstance(
			"SubmittedReportLossListPage"), "待审批"));
		list.add(new BaseFunction(new PageControllerInstance(
			"FinishedReportLossListPage"), "已完成"));
		return list.toArray(new BaseFunction[0]);
	}

	public String getCode() {
		return Auth.MainFunction_InventoryReportLoss.getCode();
	}

	public String getGroup() {
		return "02";
	}

	public String getName() {
		return "InventoryReportLoss";
	}

	public String getTitle() {
		return "库存报损";
	}

}
