package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;

public class ApprovalManageFunction extends PSIFunction {

	public String getName() {
		return "ApprovalManage";
	}

	public String getGroup() {
		return "05";
	}

	public String getTitle() {
		return "业务审批";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"ApprovalingSalesOrderListPage"), "销售审批", true),
				new BaseFunction(new PageControllerInstance(
						"ApprovalingPurchaseOrderListPage"), "采购审批"),
				new BaseFunction(new PageControllerInstance(
						"ApprovalingPromotionGoodsListPage"), "促销审批"),
				new BaseFunction(new PageControllerInstance(
						"ApprovalingAllocateSheetListPage"), "调拨审批"),
//				new BaseFunction(new PageControllerInstance(
//						"ApprovalLogListPage"), "审批记录"),
				new BaseFunction(new PageControllerInstance(
						"SalesmanCreditConfigPage"), "业务授权"),
				new BaseFunction(new PageControllerInstance(
						"ApprovalConfigPage"), "审批设置") };
	}

	@Override
	protected String getIconName() {
		return "YeWuShengPi";
	}

	public String getCode(){
	    return "1023";
    }

}
