package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class RealGoodsSalesManageFunction extends PSIFunction {
	public String getName() {
		return "";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "成品销售";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		LoginInfo loginInfo = context.find(LoginInfo.class);
		list.add(new BaseFunction(new PageControllerInstance(""), "新订单", true));
		if (loginInfo.hasAuth(Auth.Boss)) {
			list.add(new BaseFunction(new PageControllerInstance(""), "待审批"));
		}
		list.add(new BaseFunction(new PageControllerInstance(""), "进行中"));
		list.add(new BaseFunction(new PageControllerInstance(""), "销售记录"));
		return list.toArray(new BaseFunction[list.size()]);
	}

	@Override
	protected String getIconName() {
		return "DingDanXiaoShou";
	}

	public String getCode() {
		return Auth.MainFunction_RealGoodsSales.getCode();
	}
}
