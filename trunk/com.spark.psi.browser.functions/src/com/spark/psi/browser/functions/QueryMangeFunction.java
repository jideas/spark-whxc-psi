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
					"�ɹ�����", true));
			list.add(new BaseFunction(new PageControllerInstance("ProduceOrderSummary"),
					"��������"));
			list.add(new BaseFunction(new PageControllerInstance("SalesOrderSummary"),
					"���۶���"));
			list.add(new BaseFunction(new PageControllerInstance("MaterialsCheckinSummary"),
					"������ⵥ"));
			list.add(new BaseFunction(new PageControllerInstance(
					"ReceiptMaterialsCheckinSummary"), "���ϵ�"));
			list.add(new BaseFunction(new PageControllerInstance("GoodsChecinSummary"),
					"��Ʒ��ⵥ"));
			list.add(new BaseFunction(new PageControllerInstance("GoodsDeliverSummary"),
					"��Ʒ������"));
			list.add(new BaseFunction(new PageControllerInstance("OnlineSalesSummary"),
					"��������"));
			list.add(new BaseFunction(
					new PageControllerInstance("ShelfLifeWarningMaterials"),
					"������Ԥ������"));
			list.add(new BaseFunction(new PageControllerInstance(
					"CalculateGoodsMaterialPage"), "���ϼ���"));
		}
		else if(login.hasAuth(Auth.StationManager))
		{
			list.add(new BaseFunction(new PageControllerInstance("OnlineSalesSummary"),
					"��������"));
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
		return "��ѯ����";
	}

}
