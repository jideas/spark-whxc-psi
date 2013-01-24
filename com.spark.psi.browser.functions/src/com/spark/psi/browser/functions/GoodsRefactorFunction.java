package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class GoodsRefactorFunction extends PSIFunction {

	public String getName() {
		return "GoodsRefactor";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "商品拆装";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(
				new PageControllerInstance("GoodsRefactorSheetListPage"),
				"商品拆装") };
	}

	@Override
	protected String getIconName() {
		return "ShangPinCaiZhuang";
	}

	public String getCode(){
	    return Auth.MainFunction_GoodsRefactor.getCode();
    }

}
