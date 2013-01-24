package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class UserConfigFunction extends PSIFunction {

	public String getName() {
		return "UserConfig";
	}

	public String getGroup() {
		return "06";
	}

	public String getTitle() {
		return "个人设置";
	}

	@Override
	protected String getIconName() {
		return "QueSheng";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(
				new PageControllerInstance("UserConfigPage"), "个人设置") };
	}

	public String getCode(){
	    return Auth.MainFunction_UserConfig.getCode();
    }

}
