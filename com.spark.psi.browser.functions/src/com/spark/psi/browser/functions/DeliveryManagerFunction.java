package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class DeliveryManagerFunction extends PSIFunction {

	public String getName() {
		return "DeliveryManage";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "配送单";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo loginInfo = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		if (loginInfo.hasAuth(Auth.Tab_Delivery_UnHandle)) {
			list.add(new BaseFunction(new PageControllerInstance("UnDeliverListPage"),"待配送", true));
		}
		if (loginInfo.hasAuth(Auth.Tab_Delivery_Delivering)) {
			list.add(new BaseFunction(new PageControllerInstance("DeliveryingListPage"),"配送中"));
		}
		if (loginInfo.hasAuth(Auth.Tab_Delivery_Arrived)) {
			list.add(new BaseFunction(new PageControllerInstance("ArrivedDeliverListPage"),"已到货"));
		}
		if (loginInfo.hasAuth(Auth.Tab_Delivery_Exception)) {
			list.add(new BaseFunction(new PageControllerInstance("ExceptionDeliverListPage"),"异常"));
		}
		return list.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "XiaoShouPeiHuo";
	}

	public String getCode(){
	    return Auth.MainFunction_DeliveryOrderManager.getCode();
    }

}
