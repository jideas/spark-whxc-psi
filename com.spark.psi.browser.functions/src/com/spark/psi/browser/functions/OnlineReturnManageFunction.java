package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class OnlineReturnManageFunction extends PSIFunction {
	public String getName() {
		return "EffectedOnlineReturnListPage";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "网上退货";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo loginInfo = context.find(LoginInfo.class);
		
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Create)) {
			list.add(new BaseFunction(new PageControllerInstance("EffectedOnlineReturnListPage"), "待提交", true));
			list.add(new BaseFunction(new PageControllerInstance("ApprovingOnlineReturnListPage"), "待审核"));
			list.add(new BaseFunction(new PageControllerInstance("DeliveringOnlineReturnListPage"), "进行中"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedOnlineReturnListPage"), "退货记录"));
		} else if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Approval)) {
			list.add(new BaseFunction(new PageControllerInstance("ApprovingOnlineReturnListPage"), "待审核"));
			list.add(new BaseFunction(new PageControllerInstance("DeliveringOnlineReturnListPage"), "进行中"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedOnlineReturnListPage"), "退货记录"));
		} else if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Confirm)) {
			list.add(new BaseFunction(new PageControllerInstance("DeliveringOnlineReturnListPage"), "进行中"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedOnlineReturnListPage"), "退货记录"));
		}
		return list.toArray(new BaseFunction[list.size()]);
	}

	@Override
	protected String getIconName() {
		return "DingDanXiaoShou";
	}

	public String getCode() {
		return Auth.MainFunction_OnlineReturnManager.getCode();
	}
}
