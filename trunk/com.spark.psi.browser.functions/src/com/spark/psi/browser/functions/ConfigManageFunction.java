package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.AdapterPageProcessor;
import com.spark.common.components.pages.AdapterPageRender;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class ConfigManageFunction extends PSIFunction {

	public String getName() {
		return "ConfigManage";
	}

	public String getGroup() {
		return "05";
	}

	public String getTitle() {
		return "业务配置";
	}

	@Override
	protected String getIconName() {
		return "YeWuPeiZhi";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		PageController pc = new PageController(AdapterPageProcessor.class,
				AdapterPageRender.class);
		return new BaseFunction[] { new BaseFunction(
				new PageControllerInstance(pc, "PSI_ConfigMainPage"), "业务配置") };
	}

	public String getCode(){
	    return Auth.MainFunction_ConfigManage.getCode();
    }
}
