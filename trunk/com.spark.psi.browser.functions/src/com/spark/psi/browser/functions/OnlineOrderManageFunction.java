package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class OnlineOrderManageFunction extends PSIFunction{
	public String getName() {
		return "EffectedOnlineOrderListPage";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "网上订单";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		LoginInfo loginInfo = context.find(LoginInfo.class);
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_DeliverOnly)&&!loginInfo.hasAuth(Auth.Distribute) && !loginInfo.hasAuth(Auth.Boss)) {
			list.add(new BaseFunction(new PageControllerInstance("PickingOnlineOrderListPage"), "拣货中"));
		} else {
			list.add(new BaseFunction(new PageControllerInstance("EffectedOnlineOrderListPage"), "新订单", true));
			list.add(new BaseFunction(new PageControllerInstance("BookingGoodsListPage"), "预定商品"));
			list.add(new BaseFunction(new PageControllerInstance("PickingOnlineOrderListPage"), "拣货中"));
			list.add(new BaseFunction(new PageControllerInstance("DeliveringOnlineOrderListPage"), "配送中"));
			list.add(new BaseFunction(new PageControllerInstance("ArrivaledOnlineOrderListPage"), "已到货"));
			list.add(new BaseFunction(new PageControllerInstance("FinishedOnlineOrderListPage"), "订单记录"));
		}
		return list.toArray(new BaseFunction[list.size()]);
	}

	@Override
	protected String getIconName() {
		return "DingDanXiaoShou";
	}

	public String getCode(){
	    return Auth.MainFunction_OnlineOrderManager.getCode();
    }
}
