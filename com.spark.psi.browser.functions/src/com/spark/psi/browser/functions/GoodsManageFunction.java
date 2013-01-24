package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class GoodsManageFunction extends PSIFunction {

	public String getName() {
		return "GoodsManage";
	}

	public String getGroup() {
		return "05";
	}

	public String getTitle() {
		return "商品管理";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"OnSaleGoodsListPage"), "在售商品", true),
//				new BaseFunction(new PageControllerInstance(
//						"JointGoodsListPage"), "联营商品"),
				new BaseFunction(new PageControllerInstance(
						"OffSaleGoodsListPage"), "停售商品") };
	}

	@Override
	protected String getIconName() {
		return "ShangPinGuanLi";
	}

	public String getCode(){
	    return Auth.MainFunction_GoodsManage.getCode();
    }

}
