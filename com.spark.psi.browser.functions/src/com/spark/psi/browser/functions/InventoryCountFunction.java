package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class InventoryCountFunction extends PSIFunction {

	public String getName() {
		return "InventoryCount";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "ø‚¥Ê≈Ãµ„";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(
				new PageControllerInstance("InventoryCountSheetListPage"),
				"ø‚¥Ê≈Ãµ„") };
	}

	@Override
	protected String getIconName() {
		return "KuCunPanDian";
	}

	public String getCode(){
	    return Auth.MainFunction_InventoryCount.getCode();
    }

}
