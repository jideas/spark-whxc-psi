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
		return "材料采购";
	}

	public BaseFunction[] getBaseFunctions(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		List<BaseFunction> list = new ArrayList<BaseFunction>();
		ApprovalConfigInfo approvalConfig = context.find(ApprovalConfigInfo.class);
//		boolean isSeletionNew = true;  //是否默认选中新订单页签
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(0, Integer.MAX_VALUE, false);
		key.setOrderStatus(OrderStatus.Approval_No);
		OrderListEntity listEntity = context.find(OrderListEntity.class, key);
		boolean isSeletionNew = listEntity.getItemList().isEmpty();  //如果有待审核订单则默认不选中新订单
		if((login.hasAuth(Auth.Tag_PurchaseMange_Approval)  //拥有采购审核权限
		        && (!isSeletionNew || approvalConfig.isPurchaseOrderNeedApproval() ||  //采购订单开启审核或者采购退货开启审核 
		        	approvalConfig.isPurchaseReturnNeedApproval())))
		{
			list.add(new BaseFunction(new PageControllerInstance("ApprovalingPurchaseOrderListPage"), "待审批",!listEntity.getItemList().isEmpty()));
		}
		list.add(new BaseFunction(new PageControllerInstance("PurchaseListJumpPage"), "新订单", isSeletionNew));
		list.add(new BaseFunction(new PageControllerInstance("ProcessingPurchaseOrderListPage"), "进行中"));
		list.add(new BaseFunction(new PageControllerInstance("ProcessedPurchaseOrderListPage"), "已完成"));
		list.add(new BaseFunction(new PageControllerInstance("SubmitingPurchaseReturnSheetListPage"), "采购退货"));
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
