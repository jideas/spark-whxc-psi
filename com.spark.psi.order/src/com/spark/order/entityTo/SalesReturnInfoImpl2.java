package com.spark.order.entityTo;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.sales2.SalesReturnInfo2;
import com.spark.order.sales2.SalesReturnItem2;
import com.spark.order.util2.OrderUtilFactory2;
import com.spark.order.util2.IOrderButton2.OrderButtonParamError;
import com.spark.order.util2.OrderUtilFactory2.OrderUtilFactoryException;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.order.entity.SalesReturnGoodsItem;
import com.spark.psi.publish.order.entity.SalesReturnInfo;


/**
 * 销售退货详情维护
 * @author zhoulijun
 *
 */
public final class SalesReturnInfoImpl2 extends OrderInfoImpl2<SalesReturnInfo2> implements SalesReturnInfo{

	public SalesReturnInfoImpl2(Context context, SalesReturnInfo2 t) throws OrderButtonParamError, OrderUtilFactoryException {
		super(context, t);
		init();
	}

	private void init() throws OrderButtonParamError, OrderUtilFactoryException {
		actions = OrderUtilFactory2.newOrderButton2(OrderAction.class, getContext(), OrderEnum.Sales_Return).getOrderAction(getEntity());
	}
	private OrderAction[] actions;
	@Override
	public OrderAction[] getActions() {
		return actions;
	}

	@Override
	public ContactBookInfo getConsigneeInfo() {
//		if(null != getEntity().getConsigneeId()){
//			return getContext().find(ContactBookInfo.class, getEntity().getConsigneeId());
//		}
		return null;
	}

	@Override
	public OrderStatus getOrderStatus() {
		return getOrderStatus(OrderEnum.Sales_Return, TypeEnum.getType(getEntity().getType()), getEntity().getStatus());
	}

	@Override
	public OrderType getOrderType() {
		return getOrderType(OrderEnum.Sales_Return, getEntity().getType());
	}

	public SalesReturnGoodsItem[] getReturnGoodsItems() {
		List<SalesReturnGoodsItem> items = new ArrayList<SalesReturnGoodsItem>();
		List<SalesReturnItem2> dets = getContext().getList(SalesReturnItem2.class, getEntity().getRecid());
		for(SalesReturnItem2 det : dets){
			items.add(new SalesReturnGoodsItemImpl2(getContext(), det));
		}
		return items.toArray(new SalesReturnGoodsItem[items.size()]);
	}

	public String getConsignee() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLinkman() {
		// TODO Auto-generated method stub
		return null;
	}

}
