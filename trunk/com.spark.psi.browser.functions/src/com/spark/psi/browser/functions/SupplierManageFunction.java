package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class SupplierManageFunction extends PSIFunction {

	public String getName() {
		return "SupplierManage";
	}

	public String getGroup() {
		return "04";
	}

	public String getTitle() {
		return "供应商管理";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(
						new PageControllerInstance("SupplierListPage"),
						"企业供应商", true),
				new BaseFunction(new PageControllerInstance("NewSupplierPage"),
						"新增供应商") };
	}

	@Override
	protected String getIconName() {
		return "GongYingShangGuanLi";
	}

	public String getCode(){
	    return Auth.MainFunction_SupplierManage.getCode();
    }

}
