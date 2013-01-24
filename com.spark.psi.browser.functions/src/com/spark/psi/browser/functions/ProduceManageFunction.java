package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class ProduceManageFunction extends PSIFunction {

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo loginInfo = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		if (loginInfo.hasAuth(Auth.SubFunction_ProduceOrder_Create)) {
			list.add(new BaseFunction(new PageControllerInstance("SubmitingProduceOrderListPage"), "新订单", true));
			list.add(new BaseFunction(new PageControllerInstance("SubmitedProduceOrderListPage"), "待审批"));
			list.add(new BaseFunction(new PageControllerInstance("ProducingProduceOrderListPage"), "加工中"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedProduceOrderListPage"), "已完成"));
		} else if (loginInfo.hasAuth(Auth.SubFunction_ProduceOrder_Approval)){
			list.add(new BaseFunction(new PageControllerInstance("SubmitedProduceOrderListPage"), "待审批"));
			list.add(new BaseFunction(new PageControllerInstance("ProducingProduceOrderListPage"), "加工中"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedProduceOrderListPage"), "已完成"));
		}
		return list.toArray(new BaseFunction[0]);
	}

	public String getCode() {
		return Auth.MainFunction_ProduceOrderManager.getCode();
	}

	public String getGroup() {
		return "01";
	}


	public String getName() {
		return "SubmitingProduceOrderListPage";
	}


	public String getTitle() {
		return "生产单";
	}

	@Override
	protected String getIconName() {
		return "DingDanXiaoShou";
	}


}
