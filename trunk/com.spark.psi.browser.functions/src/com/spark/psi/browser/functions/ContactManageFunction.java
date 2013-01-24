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
		return "ͨѶ¼";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo loginInfo = context.find(LoginInfo.class);
		BaseFunction[] baseFunctions = null;
		// ����Ȩ����ʾ��ͬ��ҳǩ
		if (loginInfo.hasAuth(Auth.Boss)) { // �ܾ��������Ȩ��
			baseFunctions = new BaseFunction[] {
					new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "����", true),
					// new BaseFunction(new
					// PageControllerInstance("CustomerContactListPage"), "�ͻ�"),
					// new BaseFunction(new
					// PageControllerInstance("SupplierContactListPage"),
					// "��Ӧ��"),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "ͬ��"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "ȫ��") };
		} else if (loginInfo.hasAuth(Auth.SalesManager) || loginInfo.hasAuth(Auth.Sales)) { // ������Ա
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "����", true),
					// new BaseFunction(new
					// PageControllerInstance("CustomerContactListPage"), "�ͻ�"),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "ͬ��"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "ȫ��") };
		} else if (loginInfo.hasAuth(Auth.PurchaseManager) || loginInfo.hasAuth(Auth.Purchase)) { // �ɹ���Ա
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "����", true),
					// new BaseFunction(new
					// PageControllerInstance("SupplierContactListPage"),
					// "��Ӧ��"),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "ͬ��"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "ȫ��") };
		} else if (loginInfo.hasAuth(Auth.AccountManager) || loginInfo.hasAuth(Auth.Account)) { // ������Ա
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "����", true),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "ͬ��"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "ȫ��") };
		} else if (loginInfo.hasAuth(Auth.StoreKeeper) || loginInfo.hasAuth(Auth.Distribute)) { // �����Ա
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "����", true),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "ͬ��"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "ȫ��") };
		} else { // Ĭ��
			baseFunctions = new BaseFunction[] { new BaseFunction(new PageControllerInstance("PersonalContactListPage"), "����", true),
					new BaseFunction(new PageControllerInstance("ColleagueContactListPage"), "ͬ��"),
					new BaseFunction(new PageControllerInstance("AllContactListPage"), "ȫ��") };
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
