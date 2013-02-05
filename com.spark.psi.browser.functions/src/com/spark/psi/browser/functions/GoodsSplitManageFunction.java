package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

public class GoodsSplitManageFunction extends PSIFunction {
	public String getName() {
		return "GoodsSplitManage";
	}

	public String getGroup() {
		return "02";
	}

	public String getTitle() {
		return "��Ʒ��ֹ���";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		LoginInfo login = context.find(LoginInfo.class);
		if (login.hasAuth(Auth.SubFunction_GoodsSplitManage_Create)
				&& login.hasAuth(Auth.SubFunction_GoodsSplitManage_Approval)) {
			//ͬʱ���д���������Ȩ��
			list.add(new BaseFunction(new PageControllerInstance("NewGoodsSplitList"), "�²�ֵ�",
					false));
			list.add(new BaseFunction(new PageControllerInstance("ApprovalGoodsSplitList"), "������",
					true));
		} else if (login.hasAuth(Auth.SubFunction_GoodsSplitManage_Create)) {
			//���д���Ȩ��
			list.add(new BaseFunction(new PageControllerInstance("NewGoodsSplitList"), "�²�ֵ�",
					true));
		} else if (login.hasAuth(Auth.SubFunction_GoodsSplitManage_Approval)) {
			//��������Ȩ��
			list.add(new BaseFunction(new PageControllerInstance("ApprovalGoodsSplitList"), "������",
					true));
		}
		list
				.add(new BaseFunction(new PageControllerInstance("DistributingGoodsSplitList"), "������",
						true));
		list
				.add(new BaseFunction(new PageControllerInstance("ProcessingGoodsSplitList"), "������",
						true));
		list
				.add(new BaseFunction(new PageControllerInstance("FinishedGoodsSplitList"), "�����",
						true));
		return list.toArray(new BaseFunction[list.size()]);
	}

	@Override
	protected String getIconName() {
		return "ShangPinGuanLi";
	}

	public String getCode() {
		return Auth.MainFunction_GoodsSplitManage.getCode();
	}
}
