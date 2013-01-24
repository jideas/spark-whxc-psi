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
						"OnSaleMaterialListPage"), "在售材料", true),
				new BaseFunction(new PageControllerInstance("JointMaterialListPage"), "联营材料"),
				new BaseFunction(new PageControllerInstance("OffSaleMaterialListPage"), "停售材料")};
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
		return "材料管理";
	}

}
