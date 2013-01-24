package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class JointSettlementFunction extends PSIFunction {

	public String getName() {
		return "PaymentManage";
	}

	public String getGroup() {
		return "03";
	}

	public String getTitle() {
		return "联营结算";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
//		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		list.add(new BaseFunction(new PageControllerInstance("SubmittingJointPay"),
				"待提交", true));
		list.add(new BaseFunction(new PageControllerInstance("SubmittedJointPay"),
				"待审批"));
		list.add(new BaseFunction(new PageControllerInstance("PayingJointPay"),
				"进行中"));
		list.add(new BaseFunction(new PageControllerInstance("FinishedJointPay"),
					"结算记录"));
		return list.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "FuKuanDengJi";
	}

	public String getCode(){
	    return Auth.MainFunction_JointSettleMent.getCode();
    }

}
