package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class InventoryCheckoutFunction extends PSIFunction {

	public String getName() {
		return "InventoryCheckout";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "出库管理";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(new PageControllerInstance("SalesCheckingOutListPage"), "批发出库", true),
				new BaseFunction(new PageControllerInstance("MaterialsCheckingOutListPage"), "材料出库"),
				new BaseFunction(new PageControllerInstance("RealGoodsCheckOutListPage"), "成品出库"),
				new BaseFunction(new PageControllerInstance("PurchaseReturnCheckingOutListPage"), "采购退货"),
				new BaseFunction(new PageControllerInstance("KitCheckingOutDetailPage"), "其他出库"),
				new BaseFunction(new PageControllerInstance("ProcessedCheckingOutListPage"), "出库记录") };
	}

	@Override
	protected String getIconName() {
		return "ChuKuGuanLi";
	}

	public String getCode() {
		return Auth.MainFunction_InventoryCheckout.getCode();
	}

}
