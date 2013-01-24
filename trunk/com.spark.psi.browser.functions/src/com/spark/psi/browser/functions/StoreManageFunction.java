package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class StoreManageFunction extends PSIFunction {

	public String getName() {
		return "StoreManage";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "≤÷ø‚…Ë÷√";
	}

	@Override
	protected String getIconName() {
		return "CangKuSheZhi";
	}

	public BaseFunction[] getBaseFunctions(Context context) {

		return new BaseFunction[] { new BaseFunction(
				new PageControllerInstance("StoreListPage"), "≤÷ø‚¡–±Ì") };
	}

	public String getCode(){
	    return Auth.MainFunction_StoreManage.getCode();
    }
}
