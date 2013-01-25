package com.spark.order.util2;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.EntityFather;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.psi.publish.PromotionAction;

public class OrderButton_Promotion2 extends OrderButtonImpl2<PromotionAction>{
	protected OrderButton_Promotion2(Context context){
		super(context);
	}

	@Override
	protected void initButton(EntityFather entity) throws OrderButtonParamError {
		Promotion2 promotion = getEntityFatherImpl(Promotion2.class);
		UserAuthEnum auth = BillsConstant.getUserAuth(getContext(), BillsEnum.SALE_PROMOTION);
		PromotionStatus2 status = PromotionStatus2.getPromotionStatus2(promotion.getStatus());
		switch (auth) {
		case EMPLOYEE:
			return;
		case MANGER:
			if(status.isInEnum(PromotionStatus2.Submit, PromotionStatus2.Return)){
				addOrderAction(PromotionAction.Submit);
			}
			if(status.isInEnum(PromotionStatus2.Promotioning)){
				addOrderAction(PromotionAction.Stop);
			}
			else{
				addOrderAction(PromotionAction.Delete);
			}
			break;
		case BOSS:
			if(status.isInEnum(PromotionStatus2.Submit, PromotionStatus2.Return)){
				addOrderAction(PromotionAction.Submit);
			}
			if(status.isInEnum(PromotionStatus2.Approve)){
				addOrderAction(PromotionAction.Approval);
				addOrderAction(PromotionAction.Deny);
			}
			if(status.isInEnum(PromotionStatus2.Promotioning)){
				addOrderAction(PromotionAction.Stop);
			}
			else{
				addOrderAction(PromotionAction.Delete);
			}
			break;
		}
	
	}

	@Override
	protected PromotionAction[] getActions(List<PromotionAction> orderActions) {
		return orderActions.toArray(new PromotionAction[orderActions.size()]);
	}

}
