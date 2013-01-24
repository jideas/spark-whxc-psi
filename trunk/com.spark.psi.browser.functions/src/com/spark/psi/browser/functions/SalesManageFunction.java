package com.spark.psi.browser.functions;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.browser.functions.internal.PSIFunction;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.key.GetSalesOrderListKey;

public class SalesManageFunction extends PSIFunction {

	public String getName() {
		return "SalesManage";
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
		ApprovalConfigInfo approvalConfig = context.find(ApprovalConfigInfo.class);
		// boolean isSeletionNew = true; //�Ƿ�Ĭ��ѡ���¶���ҳǩ
		GetSalesOrderListKey key = new GetSalesOrderListKey(0, Integer.MAX_VALUE, false);
		key.setOrderStatus(OrderStatus.Approval_No);
		OrderListEntity entity = context.find(OrderListEntity.class, key);
		boolean isSeletionNew = entity.getItemList().isEmpty();
		if ((login.hasAuth(Auth.Tag_SalesMange_Approval) && (!isSeletionNew || approvalConfig.isSalesOrderNeedApproval() || approvalConfig
				.isSalesReturnNeedApproval()))) {
			list.add(new BaseFunction(new PageControllerInstance("ApprovalingSalesOrderListPage"), "������", !isSeletionNew));
		}
		list.add(new BaseFunction(new PageControllerInstance("SubmitingSalesOrderListPage"), "�¶���", isSeletionNew));
		list.add(new BaseFunction(new PageControllerInstance("ProcessingSalesOrderListPage"), "������"));
		list.add(new BaseFunction(new PageControllerInstance("ProcessedSalesOrderListPage"), "�����"));
		list.add(new BaseFunction(new PageControllerInstance("SubmitingSalesReturnSheetListPage"), "�����˻�"));
		return list.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "DingDanXiaoShou";
	}

	public String getCode() {
		return Auth.MainFunction_SalesManage.getCode();
	}

}
