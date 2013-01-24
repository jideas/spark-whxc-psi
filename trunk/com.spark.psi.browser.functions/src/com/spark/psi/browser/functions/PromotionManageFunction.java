package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class PromotionManageFunction extends PSIFunction {

	public String getName() {
		return "PromotionManage";
	}

	public String getGroup() {
		return "01";
	}

	public String getTitle() {
		return "��������";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		if(login.hasAuth(Auth.Tag_PromotionMange_Edit)){
			list.add(new BaseFunction(new PageControllerInstance(
			"SubmitingPromotionGoodsListPage"), "��������"));
		}
		if(login.hasAuth(Auth.Tag_PromotionMange_Approval)){
			list.add(new BaseFunction(new PageControllerInstance(
			"ApprovalingPromotionGoodsListPage"), "��������"));
		}
		if(login.hasAuth(Auth.Tag_PromotionMange_Promotioning)){
			list.add(new BaseFunction(new PageControllerInstance(
			"PromotionGoodsListPage"), "������Ʒ", true));
		}
		if(login.hasAuth(Auth.Tag_PromotionMnage_Promotioned)){
			list.add(new BaseFunction(new PageControllerInstance(
			"ProcessedPromotionGoodsListPage"), "������¼"));
		}
		return list.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "ChuXiaoGuanLi";
	}

	public String getCode(){
	    return Auth.MainFunction_PromotionManage.getCode();
    }

}
