package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class QueryMangeFunction extends PSIFunction {

	@Override
	protected String getIconName() {
		return "JingYingGuanKong";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"PurchaseOrderSummary"), "采购订单", true),
				new BaseFunction(new PageControllerInstance(
						"ProduceOrderSummary"), "生产订单"),
				new BaseFunction(new PageControllerInstance(
						"SalesOrderSummary"), "销售订单"),
				new BaseFunction(new PageControllerInstance(
						"MaterialsCheckinSummary"), "材料入库单"),
				new BaseFunction(new PageControllerInstance(
						"ReceiptMaterialsCheckinSummary"), "领料单"),
				new BaseFunction(new PageControllerInstance(
						"GoodsChecinSummary"), "成品入库单"),
				new BaseFunction(new PageControllerInstance(
						"GoodsDeliverSummary"), "成品发货单") ,
				new BaseFunction(new PageControllerInstance(
						"OnlineSalesSummary"), "线上销售"),
						new BaseFunction(new PageControllerInstance(
						"ShelfLifeWarningMaterials"), "保质期预警材料"),
				new BaseFunction(new PageControllerInstance(
						"CalculateGoodsMaterialPage"), "材料计算"),
						
						};
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
