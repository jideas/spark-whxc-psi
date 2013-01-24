package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class DistributeManageFunction extends PSIFunction {

	public String getName() {
		return "DistributeManage";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "�������";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(
				new PageControllerInstance("DistributingSalesOrderListPage"),
				"�������", true) };
	}

	@Override
	protected String getIconName() {
		return "XiaoShouPeiHuo";
	}

	public String getCode(){
	    return Auth.MainFunction_DistributeManage.getCode();
    }

}
