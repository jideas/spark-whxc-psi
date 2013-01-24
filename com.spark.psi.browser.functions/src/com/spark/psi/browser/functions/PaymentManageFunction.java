package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class PaymentManageFunction extends PSIFunction {

	public String getName() {
		return "PaymentManage";
	}

	public String getGroup() {
		return "03";
	}

	public String getTitle() {
		return "付款登记";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		if(login.hasAuth(Auth.Tag_PaymentManage_Add)){
			list.add(new BaseFunction(new PageControllerInstance("SubmittingPaymentListPage"),
			"待提交", true));
		}
		list.add(new BaseFunction(new PageControllerInstance("SubmittedPaymentListPage"),
				"待审批"));
		list.add(new BaseFunction(new PageControllerInstance("PayingListPage"),
				"进行中"));
		if(login.hasAuth(Auth.Tag_PaymentManage_List)){
			list.add(new BaseFunction(new PageControllerInstance("PaymentListPage"),
						"付款记录"));
		}
		return list.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "FuKuanDengJi";
	}

	public String getCode(){
	    return Auth.MainFunction_PaymentManage.getCode();
    }

}
