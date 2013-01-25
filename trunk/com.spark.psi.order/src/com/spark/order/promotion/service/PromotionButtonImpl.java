package com.spark.order.promotion.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.Department;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.PromotionAction;

/**
 * <p>促销单按钮工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-1
 */
class PromotionButtonImpl implements IPromotionButton{
	protected final Context context;
	protected final Promotion2 promotion;
	protected final List<PromotionAction> actions = new ArrayList<PromotionAction>();

	public PromotionButtonImpl(Context context, Promotion2 promotion) {
		this.context = context;
		this.promotion = promotion;
	}

	public PromotionAction[] getActions() {
		doAction();
		return actions.toArray(new PromotionAction[actions.size()]);
	}

	private void doAction() {
		UserAuthEnum auth = BillsConstant.getUserAuth(context, BillsEnum.SALE_PROMOTION);
		PromotionStatus2 status = PromotionStatus2.getPromotionStatus2(promotion.getStatus());
		switch (auth) {
		case EMPLOYEE:
			return;
		case MANGER:
			if(status.isInEnum(PromotionStatus2.Submit, PromotionStatus2.Return)){
				if(isHaveButton())
					actions.add(PromotionAction.Submit);
			}
			if(status.isInEnum(PromotionStatus2.Promotioning)){
				if(isHaveButton())
					actions.add(PromotionAction.Stop);
			}
			else{
				if(isHaveButton()) 
					actions.add(PromotionAction.Delete);
			}
			break;
		case BOSS:
			if(status.isInEnum(PromotionStatus2.Submit, PromotionStatus2.Return)){
				actions.add(PromotionAction.Submit);
			}
			if(status.isInEnum(PromotionStatus2.Approve)){
				actions.add(PromotionAction.Approval);
				actions.add(PromotionAction.Deny);
			}
			if(status.isInEnum(PromotionStatus2.Promotioning)){
				actions.add(PromotionAction.Stop);
			}
			else{
				actions.add(PromotionAction.Delete);
			}
			break;
		}
	}
	
	private boolean isHaveButton(){
		if(promotion.getCreatorId().equals(BillsConstant.getUserGuid(context))){
			return true;
		}
		if(getChildrenDept().contains(promotion.getDeptId())){
			return true;
		}
		return false;
	}
	
	private List<GUID> getChildrenDept(){
		Department[] list = OrderUtil.getDepartment(context).getChildren(context, Auth.Sales, Auth.SalesManager);
		List<GUID> result = new ArrayList<GUID>();
		for(Department d : list){
			result.add(d.getId());
		}
		return result;
	}

}
