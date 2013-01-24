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
		return "�ͻ�����";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"FormalCustomerListPage"), "��ʽ�ͻ�",true),
				new BaseFunction(new PageControllerInstance(
						"PotentialCustomerListPage"), "Ǳ�ڿͻ�"),
				new BaseFunction(new PageControllerInstance("NewCustomerPage"),
						"�����ͻ�")
		// ,new BaseFunction(new PageControllerInstance(
		//		"B2BCustomerListPage"), "��Ȩ�ͻ�") 
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
