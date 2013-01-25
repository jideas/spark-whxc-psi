package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.util2.OrderUtilFactory2;
import com.spark.order.util2.IOrderButton2.OrderButtonParamError;
import com.spark.order.util2.OrderUtilFactory2.OrderUtilFactoryException;
import com.spark.psi.publish.PromotionAction;
import com.spark.psi.publish.PromotionStatus;
import com.spark.psi.publish.order.entity.PromotionItem;

/**
 * 
 * <p>促销列表</p>
 * 查询未确认的促销列表 ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Unrecognized
 * 查询待审批的促销列表  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Examine
 * 查询促销中的促销列表  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Promotion
 * 查询促销记录  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = null


 *
 
 * @version 2012-3-6
 */
public class PromotionItemImpl2 extends EntityToFather<Promotion2> implements PromotionItem{
	
	public PromotionItemImpl2(Context context, Promotion2 t) throws OrderButtonParamError, OrderUtilFactoryException {
		super(context, t);
		init();
	}
	private PromotionAction[] actions;
	private void init() throws OrderButtonParamError, OrderUtilFactoryException {
		actions = OrderUtilFactory2.newOrderButton2(PromotionAction.class, getContext(), OrderEnum.Sales_Promotion).getOrderAction(getEntity());
	}

	public PromotionAction[] getActions() {
		return actions;
	}

	public String getCreator() {
		return getEntity().getCreator();
	}

	public long getEndDate() {
		return getEntity().getEndDate();
	}

	public GUID getGoodsItemId() {
		return getEntity().getGoodsItemId();
	}

	public String getGoodsName() {
		return getEntity().getGoodsName();
	}

	public String getGoodsProperties() {
		return getEntity().getGoodsProperties();
	}

	public double getOriginalPrice() {
		return getEntity().getPrice_goods();
	}

	public double getPromotionCount() {
		return getEntity().getPromotionCount();
	}

	public double getPromotionPrice() {
		return getEntity().getPrice_promotion();
	}

	public PromotionStatus getPromotionStatus() {
		return PromotionStatus2.getPromotionStatus2(getEntity().getStatus()).getPubstatus();
	}

	public double getSoldCount() {
		return getEntity().getSaledCount();
	}

	public long getStartDate() {
		return getEntity().getBeginDate();
	}

	public int getScale() {
		return getEntity().getScale();
	}

}
