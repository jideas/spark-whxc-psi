package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class StationManageFunction extends PSIFunction {

	@Override
	protected String getIconName() {
		return "CangKuSheZhi";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(new PageControllerInstance("StationListPage"), "站点列表", true) };
	}

	public String getCode() {
		return Auth.MainFunction_StationManage.getCode();
	}

	public String getGroup() {
		return "05";
	}

	public String getName() {
		return "StationManage";
	}

	public String getTitle() {
		return "站点管理";
	}

}
