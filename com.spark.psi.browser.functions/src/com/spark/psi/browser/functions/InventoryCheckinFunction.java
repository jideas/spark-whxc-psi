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
		return "������";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance("PurchaseCheckingInListPage"), "�ɹ����", true),
				new BaseFunction(new PageControllerInstance("SalesReturnCheckingInListPage"), "�����˻�"),
				new BaseFunction(new PageControllerInstance("GoodsSplitCheckingInListPage"), "��Ʒ���"),
				new BaseFunction(new PageControllerInstance("RealGoodsCheckingInListPage"), "��Ʒ���"),
				new BaseFunction(new PageControllerInstance("KitCheckingInDetailPage"), "�������"),
				new BaseFunction(new PageControllerInstance("ProcessedCheckingInListPage"), "����¼") };
	}

	@Override
	protected String getIconName() {
		return "RuKuGuanLi";
	}

	public String getCode() {
		return Auth.MainFunction_InventoryCheckin.getCode();
	}

}
