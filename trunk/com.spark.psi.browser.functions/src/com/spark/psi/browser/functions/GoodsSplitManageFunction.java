package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class GoodsSplitManageFunction extends PSIFunction {
	public String getName() {
		return "GoodsSplitManage";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "商品拆分管理";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		LoginInfo login = context.find(LoginInfo.class);
		if (login.hasAuth(Auth.SubFunction_GoodsSplitManage_Create)
				&& login.hasAuth(Auth.SubFunction_GoodsSplitManage_Approval)) {
			//同时具有创建和审批权限
			list.add(new BaseFunction(new PageControllerInstance("NewGoodsSplitList"), "新拆分单",
					false));
			list.add(new BaseFunction(new PageControllerInstance("ApprovalGoodsSplitList"), "待审批",
					true));
		} else if (login.hasAuth(Auth.SubFunction_GoodsSplitManage_Create)) {
			//具有创建权限
			list.add(new BaseFunction(new PageControllerInstance("NewGoodsSplitList"), "新拆分单",
					true));
		} else if (login.hasAuth(Auth.SubFunction_GoodsSplitManage_Approval)) {
			//具有审批权限
			list.add(new BaseFunction(new PageControllerInstance("ApprovalGoodsSplitList"), "待审批",
					true));
		}
		list
				.add(new BaseFunction(new PageControllerInstance("DistributingGoodsSplitList"), "待分配",
						true));
		list
				.add(new BaseFunction(new PageControllerInstance("ProcessingGoodsSplitList"), "进行中",
						true));
		list
				.add(new BaseFunction(new PageControllerInstance("FinishedGoodsSplitList"), "已完成",
						true));
		return list.toArray(new BaseFunction[list.size()]);
	}

	@Override
	protected String getIconName() {
		return "ShangPinGuanLi";
	}

	public String getCode() {
		return Auth.MainFunction_GoodsSplitManage.getCode();
	}
}
