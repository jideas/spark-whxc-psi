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
						"PurchaseOrderSummary"), "�ɹ�����", true),
				new BaseFunction(new PageControllerInstance(
						"ProduceOrderSummary"), "��������"),
				new BaseFunction(new PageControllerInstance(
						"SalesOrderSummary"), "���۶���"),
				new BaseFunction(new PageControllerInstance(
						"MaterialsCheckinSummary"), "������ⵥ"),
				new BaseFunction(new PageControllerInstance(
						"ReceiptMaterialsCheckinSummary"), "���ϵ�"),
				new BaseFunction(new PageControllerInstance(
						"GoodsChecinSummary"), "��Ʒ��ⵥ"),
				new BaseFunction(new PageControllerInstance(
						"GoodsDeliverSummary"), "��Ʒ������") ,
				new BaseFunction(new PageControllerInstance(
						"OnlineSalesSummary"), "��������"),
						new BaseFunction(new PageControllerInstance(
						"ShelfLifeWarningMaterials"), "������Ԥ������"),
				new BaseFunction(new PageControllerInstance(
						"CalculateGoodsMaterialPage"), "���ϼ���"),
						
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
		return "��ѯ����";
	}

}
