package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class GoodsBomManageFunction extends PSIFunction {
	public String getName() {
		return "GoodsBomManage";
	}

	public String getGroup() {
		return "05";
	}

	public String getTitle() {
		return "��ƷBOM����";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] { new BaseFunction(new PageControllerInstance("BOM_GOODSLIST"), "��Ʒ�б�", true),
				new BaseFunction(new PageControllerInstance("BOM_SUBMITINGLIST"), "���ύBOM"),
				new BaseFunction(new PageControllerInstance("BOM_APPROVINGLIST"), "������BOM") };
	}

	@Override
	protected String getIconName() {
		return "ShangPinGuanLi";
	}

	public String getCode() {
		return Auth.MainFunction_GoodsBomManage.getCode();
	}
}
