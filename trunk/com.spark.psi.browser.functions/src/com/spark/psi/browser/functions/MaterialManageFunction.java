package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class MaterialManageFunction extends PSIFunction {

	@Override
	protected String getIconName() {
		return "ShangPinGuanLi";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		return new BaseFunction[] {
				new BaseFunction(new PageControllerInstance(
						"OnSaleMaterialListPage"), "���۲���", true),
				new BaseFunction(new PageControllerInstance("JointMaterialListPage"), "��Ӫ����"),
				new BaseFunction(new PageControllerInstance("OffSaleMaterialListPage"), "ͣ�۲���")};
	}

	public String getCode() {
		return Auth.MainFunction_MaterialManage.getCode();
	}

	public String getGroup() {
		return "05";
	}

	public String getName() {
		return "MaterialManage";
	}

	public String getTitle() {
		return "���Ϲ���";
	}

}
