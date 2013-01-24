package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class CustomerMangeFunction extends PSIFunction {

	public String getName() {
		return "CustomerMange";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "客户管理";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"FormalCustomerListPage"), "正式客户",true),
				new BaseFunction(new PageControllerInstance(
						"PotentialCustomerListPage"), "潜在客户"),
				new BaseFunction(new PageControllerInstance("NewCustomerPage"),
						"新增客户")
		// ,new BaseFunction(new PageControllerInstance(
		//		"B2BCustomerListPage"), "授权客户") 
				};
	}

	@Override
	protected String getIconName() {
		return "KeHuGuanLi";
	}

	public String getCode(){
	    return Auth.MainFunction_CustomerMange.getCode();
    }

}
