package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class ReceiptManageFunction extends PSIFunction {

	public String getName() {
		return "ReceiptManage";
	}

	public String getGroup() {
		return "03";
	}

	public String getTitle() {
		return "收款登记";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		// list.add(new BaseFunction(new PageControllerInstance(
		// "ReceiptingListPage"), "应收情况"));
		if (login.hasAuth(Auth.Tag_ReceiptManage_Add)) {
			list.add(new BaseFunction(new PageControllerInstance("SubmittingReceiptListPage"), "待提交")); 
		}
		if (login.hasAuth(Auth.Tag_ReceiptManage_Retial)) {
			list.add(new BaseFunction(new PageControllerInstance("ReceiptsListPage"), "进行中"));
		}
		if (login.hasAuth(Auth.Tag_ReceiptManage_List)) {
			list.add(new BaseFunction(new PageControllerInstance("ReceiptLogListPage"), "收款记录"));
		}

		return list.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "ShouKuanDengJi";
	}

	public String getCode() {
		return Auth.MainFunction_ReceiptManage.getCode();
	}

}
