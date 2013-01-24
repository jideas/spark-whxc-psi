package com.spark.psi.browser.functions;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class NoticeManageFunction extends PSIFunction{

	public String getName(){
		return "NoticeManage";
	}

	public String getGroup(){
		return "05";
	}

	public String getTitle(){
		return "公告栏";
	}

	public BaseFunction[] getBaseFunctions(Context context){
		LoginInfo loginInfo = context.find(LoginInfo.class);
		BaseFunction[] baseFunctions = null;

		//总经理、财务经理、销售经理、采购经理或库管经理，拥有"未发布公告"、"已发布公告"和"历史公告"
		if(loginInfo.hasAuth(Auth.Boss) || loginInfo.hasAuth(Auth.AccountManager)
		        || loginInfo.hasAuth(Auth.SalesManager) || loginInfo.hasAuth(Auth.PurchaseManager)
		        || loginInfo.hasAuth(Auth.StoreKeeperManager))
		{
			baseFunctions =
			        new BaseFunction[] {
			                new BaseFunction(new PageControllerInstance("PublishingNoticeListPage"), "未发布公告", true),
			                new BaseFunction(new PageControllerInstance("PublishedNoticeListPage"), "已发布公告"),
			                new BaseFunction(new PageControllerInstance("HistoryNoticeListPage"), "历史公告")};
		}
		else{ //员工只能查看已发布公告
			baseFunctions =
			        new BaseFunction[] {new BaseFunction(new PageControllerInstance("PublishedNoticeListPage"), "已发布公告")};
		}
		return baseFunctions;
	}

	@Override
	protected String getIconName(){
		return "GongGaoLan";
	}

	public String getCode(){
		return Auth.MainFunction_NoticeManage.getCode();
	}
}
