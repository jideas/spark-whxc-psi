package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;

public class BalanceManageFunction extends PSIFunction {

	public String getName() {
		return "BalanceManage";
	}

	public String getGroup() {
		return "03";
	}

	public String getTitle() {
		return "往来管理";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
//		TenantInfo tenantInfo = context.find(TenantInfo.class);
//		LoginInfo loginInfo = context.find(LoginInfo.class);
		
		//
		com.spark.psi.publish.LoginInfo login = context.find(com.spark.psi.publish.LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		if(login.hasAuth(Auth.Tag_BalanceManage_Customer)){
			list.add(new BaseFunction(new PageControllerInstance(
			"CustomerBalanceListPage"), "客户往来"));
		}
		if(login.hasAuth(Auth.Tag_BalanceManage_Supplier)){
			list.add(new BaseFunction(new PageControllerInstance(
			"SupplierBalanceListPage"), "供应商往来"));
		}
		BaseFunction[] normalFunctions = list.toArray(new BaseFunction[0]);
		
//		if(!tenantInfo.isDealingsInited() && (loginInfo.hasAuth(Auth.Boss) || loginInfo.hasAuth(Auth.Account))) {
//			return new BaseFunction[] {new BaseFunction(new PageControllerInstance("BalanceInit",normalFunctions), "往来管理")};
//		} else {
//			return normalFunctions;
//		}
		return normalFunctions;
	}

	@Override
	protected String getIconName() {
		return "WanLaiGuanLi";
	}

	public String getCode(){
	    return Auth.MainFunction_BalanceManage.getCode();
    }

}
