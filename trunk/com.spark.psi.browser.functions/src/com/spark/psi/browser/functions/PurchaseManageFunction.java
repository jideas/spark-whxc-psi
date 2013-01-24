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
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;

public class PurchaseManageFunction extends PSIFunction {

	public String getName() {
		return "PurchaseManage";
	}

	public String getGroup() {
		return "04";
	}

	public String getTitle() {
		return "���ϲɹ�";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		ApprovalConfigInfo approvalConfig = context.find(ApprovalConfigInfo.class);
//		boolean isSeletionNew = true;  //�Ƿ�Ĭ��ѡ���¶���ҳǩ
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(0, Integer.MAX_VALUE, false);
		key.setOrderStatus(OrderStatus.Approval_No);
		OrderListEntity listEntity = context.find(OrderListEntity.class, key);
		boolean isSeletionNew = listEntity.getItemList().isEmpty();  //����д���˶�����Ĭ�ϲ�ѡ���¶���
		if((login.hasAuth(Auth.Tag_PurchaseMange_Approval)  //ӵ�вɹ����Ȩ��
		        && (!isSeletionNew || approvalConfig.isPurchaseOrderNeedApproval() ||  //�ɹ�����������˻��߲ɹ��˻�������� 
		        	approvalConfig.isPurchaseReturnNeedApproval())))
		{
			list.add(new BaseFunction(new PageControllerInstance("ApprovalingPurchaseOrderListPage"), "������",!listEntity.getItemList().isEmpty()));
		}
		list.add(new BaseFunction(new PageControllerInstance("PurchaseListJumpPage"), "�¶���", isSeletionNew));
		list.add(new BaseFunction(new PageControllerInstance("ProcessingPurchaseOrderListPage"), "������"));
		list.add(new BaseFunction(new PageControllerInstance("ProcessedPurchaseOrderListPage"), "�����"));
		list.add(new BaseFunction(new PageControllerInstance("SubmitingPurchaseReturnSheetListPage"), "�ɹ��˻�"));
		return list.toArray(new BaseFunction[0]);
	}

	@Override
	protected String getIconName() {
		return "ShangPinCaiGou";
	}

	public String getCode() {
		return Auth.MainFunction_PurchaseManage.getCode();
	}
}
