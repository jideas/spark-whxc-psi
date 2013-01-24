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
		return "ҵ������";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"ApprovalingSalesOrderListPage"), "��������", true),
				new BaseFunction(new PageControllerInstance(
						"ApprovalingPurchaseOrderListPage"), "�ɹ�����"),
				new BaseFunction(new PageControllerInstance(
						"ApprovalingPromotionGoodsListPage"), "��������"),
				new BaseFunction(new PageControllerInstance(
						"ApprovalingAllocateSheetListPage"), "��������"),
//				new BaseFunction(new PageControllerInstance(
//						"ApprovalLogListPage"), "������¼"),
				new BaseFunction(new PageControllerInstance(
						"SalesmanCreditConfigPage"), "ҵ����Ȩ"),
				new BaseFunction(new PageControllerInstance(
						"ApprovalConfigPage"), "��������") };
	}

	@Override
	protected String getIconName() {
		return "YeWuShengPi";
	}

	public String getCode(){
	    return "1023";
    }

}
