package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class QueryMangeFunction extends PSIFunction {

	@Override
	protected String getIconName() {
		return "JingYingGuanKong";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		if(login.hasAuth(Auth.Boss)||login.hasAuth(Auth.Account)||login.hasAuth(Auth.AccountManager))
		{
			list.add(new BaseFunction(new PageControllerInstance("PurchaseOrderSummary"),
					"采购订单", true));
			list.add(new BaseFunction(new PageControllerInstance("ProduceOrderSummary"),
					"生产订单"));
			list.add(new BaseFunction(new PageControllerInstance("SalesOrderSummary"),
					"销售订单"));
			list.add(new BaseFunction(new PageControllerInstance("MaterialsCheckinSummary"),
					"材料入库单"));
			list.add(new BaseFunction(new PageControllerInstance(
					"ReceiptMaterialsCheckinSummary"), "领料单"));
			list.add(new BaseFunction(new PageControllerInstance("GoodsChecinSummary"),
					"成品入库单"));
			list.add(new BaseFunction(new PageControllerInstance("GoodsDeliverSummary"),
					"成品发货单"));
			list.add(new BaseFunction(new PageControllerInstance("OnlineSalesSummary"),
					"线上销售"));
			list.add(new BaseFunction(
					new PageControllerInstance("ShelfLifeWarningMaterials"),
					"保质期预警材料"));
			list.add(new BaseFunction(new PageControllerInstance(
					"CalculateGoodsMaterialPage"), "材料计算"));
		}
		else if(login.hasAuth(Auth.StationManager))
		{
			list.add(new BaseFunction(new PageControllerInstance("OnlineSalesSummary"),
					"线上销售"));
		}
		return list.toArray(new BaseFunction[0]);
	}

	public String getCode() {
		return Auth.MainFunction_QueryManager.getCode();
	}

	public String getGroup() {
		return "05";
	}

	public String getName() {
		return "QueryManager";
	}

	public String getTitle() {
		return "查询管理";
	}

}
