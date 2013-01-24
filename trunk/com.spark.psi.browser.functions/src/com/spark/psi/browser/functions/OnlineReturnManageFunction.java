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
		return "�����˻�";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo loginInfo = context.find(LoginInfo.class);
		
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Create)) {
			list.add(new BaseFunction(new PageControllerInstance("EffectedOnlineReturnListPage"), "���ύ", true));
			list.add(new BaseFunction(new PageControllerInstance("ApprovingOnlineReturnListPage"), "�����"));
			list.add(new BaseFunction(new PageControllerInstance("DeliveringOnlineReturnListPage"), "������"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedOnlineReturnListPage"), "�˻���¼"));
		} else if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Approval)) {
			list.add(new BaseFunction(new PageControllerInstance("ApprovingOnlineReturnListPage"), "�����"));
			list.add(new BaseFunction(new PageControllerInstance("DeliveringOnlineReturnListPage"), "������"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedOnlineReturnListPage"), "�˻���¼"));
		} else if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Confirm)) {
			list.add(new BaseFunction(new PageControllerInstance("DeliveringOnlineReturnListPage"), "������"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedOnlineReturnListPage"), "�˻���¼"));
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
