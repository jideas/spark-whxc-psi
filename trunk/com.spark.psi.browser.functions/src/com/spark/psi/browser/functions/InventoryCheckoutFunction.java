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
		return "�������";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(new PageControllerInstance("SalesCheckingOutListPage"), "��������", true),
				new BaseFunction(new PageControllerInstance("MaterialsCheckingOutListPage"), "���ϳ���"),
				new BaseFunction(new PageControllerInstance("RealGoodsCheckOutListPage"), "��Ʒ����"),
				new BaseFunction(new PageControllerInstance("PurchaseReturnCheckingOutListPage"), "�ɹ��˻�"),
				new BaseFunction(new PageControllerInstance("KitCheckingOutDetailPage"), "��������"),
				new BaseFunction(new PageControllerInstance("ProcessedCheckingOutListPage"), "�����¼") };
	}

	@Override
	protected String getIconName() {
		return "ChuKuGuanLi";
	}

	public String getCode() {
		return Auth.MainFunction_InventoryCheckout.getCode();
	}

}
