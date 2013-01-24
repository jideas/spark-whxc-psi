package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.AdapterPageProcessor;
import com.spark.common.components.pages.AdapterPageRender;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;

public class ReportFunction extends PSIFunction {

	public String getName() {
		return "ReportFunction";
	}

	public String getGroup() {
		return "05";
	}

	public String getTitle() {
		return "经营管控";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		PageController pc = new PageController(AdapterPageProcessor.class,
				AdapterPageRender.class);
		return new BaseFunction[] { new BaseFunction(
				new PageControllerInstance(pc, "PSI_ReportMainPage"), "经营管控") };
	}

	@Override
	protected String getIconName() {
		return "JingYingGuanKong";
	}

	public String getCode(){
	    return null;
    }

}
