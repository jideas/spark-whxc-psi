package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class ContactManageFunction extends PSIFunction {

	public String getName() {
		return "ContactManage";
	}

	public String getGroup() {
		return "05";
	}

	public String getTitle() {
		return "通讯录";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo loginInfo = context.find(LoginInfo.class);
		BaseFunction[] baseFunctions = null;
		// 根据权限显示不同的页签
		if (loginInfo.hasAuth(Auth.Boss)) { // 总经理或助理权限
			baseFunctions = new BaseFunction[] {
					new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "个人", true),
					// new BaseFunction(new
					// PageControllerInstance("CustomerContactListPage"), "客户"),
					// new BaseFunction(new
					// PageControllerInstance("SupplierContactListPage"),
					// "供应商"),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "同事"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "全部") };
		} else if (loginInfo.hasAuth(Auth.SalesManager) || loginInfo.hasAuth(Auth.Sales)) { // 销售人员
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "个人", true),
					// new BaseFunction(new
					// PageControllerInstance("CustomerContactListPage"), "客户"),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "同事"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "全部") };
		} else if (loginInfo.hasAuth(Auth.PurchaseManager) || loginInfo.hasAuth(Auth.Purchase)) { // 采购人员
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "个人", true),
					// new BaseFunction(new
					// PageControllerInstance("SupplierContactListPage"),
					// "供应商"),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "同事"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "全部") };
		} else if (loginInfo.hasAuth(Auth.AccountManager) || loginInfo.hasAuth(Auth.Account)) { // 财务人员
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "个人", true),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "同事"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "全部") };
		} else if (loginInfo.hasAuth(Auth.StoreKeeper) || loginInfo.hasAuth(Auth.Distribute)) { // 库馆人员
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "个人", true),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "同事"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "全部") };
		} else { // 默认
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "个人", true),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "同事"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "全部") };
		}
		return baseFunctions;
	}

	@Override
	protected String getIconName() {
		return "TongXunLu";
	}

	public String getCode() {
		return Auth.MainFunction_ContactManage.getCode();
	}
}
