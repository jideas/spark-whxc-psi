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
		return "������";
	}

	public BaseFunction[] getBaseFunctions(Context context){
		LoginInfo loginInfo = context.find(LoginInfo.class);
		BaseFunction[] baseFunctions = null;

		//�ܾ������������۾����ɹ�������ܾ���ӵ��"δ��������"��"�ѷ�������"��"��ʷ����"
		if(loginInfo.hasAuth(Auth.Boss) || loginInfo.hasAuth(Auth.AccountManager)
		        || loginInfo.hasAuth(Auth.SalesManager) || loginInfo.hasAuth(Auth.PurchaseManager)
		        || loginInfo.hasAuth(Auth.StoreKeeperManager))
		{
			baseFunctions =
			        new BaseFunction[] {
			                new BaseFunction(new PageControllerInstance("PublishingNoticeListPage"), "δ��������", true),
			                new BaseFunction(new PageControllerInstance("PublishedNoticeListPage"), "�ѷ�������"),
			                new BaseFunction(new PageControllerInstance("HistoryNoticeListPage"), "��ʷ����")};
		}
		else{ //Ա��ֻ�ܲ鿴�ѷ�������
			baseFunctions =
			        new BaseFunction[] {new BaseFunction(new PageControllerInstance("PublishedNoticeListPage"), "�ѷ�������")};
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
