package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class InvoiceManageFunction extends PSIFunction {

	public String getName() {
		return "InvoiceManage";
	}

	public String getGroup() {
		return "03";
	}

	public String getTitle() {
		return "��Ʊ��¼";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance("NewInvoicePage"),
						"��Ʊ�Ǽ�"),
				new BaseFunction(new PageControllerInstance("InvoiceListPage"),
						"��Ʊ��¼", true) };
	}

	@Override
	protected String getIconName() {
		return "KaiPiaoJiLu";
	}

	public String getCode(){
	    return Auth.MainFunction_InvoiceManange.getCode();
    }

}
