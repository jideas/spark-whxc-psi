package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class DeliverTicketManageFunction extends PSIFunction {

	public String getName() {
		return "DeliverTicketManage";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "发货单";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(new PageControllerInstance("DeliverTicketListPage"),"发货单", true)};
	}

	@Override
	protected String getIconName() {
		return "XiaoShouPeiHuo";
	}
	public String getCode(){
	    return Auth.MainFunction_DeliveryTicketManager.getCode();
    }

}
