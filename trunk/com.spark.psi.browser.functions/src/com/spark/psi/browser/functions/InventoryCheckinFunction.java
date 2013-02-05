package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class InventoryCheckinFunction extends PSIFunction {

	public String getName() {
		return "InventoryCheckin";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "入库管理";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance("PurchaseCheckingInListPage"), "采购入库", true),
				new BaseFunction(new PageControllerInstance("SalesReturnCheckingInListPage"), "销售退货"),
				new BaseFunction(new PageControllerInstance("GoodsSplitCheckingInListPage"), "成品拆分"),
				new BaseFunction(new PageControllerInstance("RealGoodsCheckingInListPage"), "成品入库"),
				new BaseFunction(new PageControllerInstance("KitCheckingInDetailPage"), "其他入库"),
				new BaseFunction(new PageControllerInstance("ProcessedCheckingInListPage"), "入库记录") };
	}

	@Override
	protected String getIconName() {
		return "RuKuGuanLi";
	}

	public String getCode() {
		return Auth.MainFunction_InventoryCheckin.getCode();
	}

}
