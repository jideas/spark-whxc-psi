package com.spark.order.entityTo;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.sales2.SalesOrderInfo2;
import com.spark.order.sales2.SalesOrderItem2;
import com.spark.order.util2.OrderUtilFactory2;
import com.spark.order.util2.IOrderButton2.OrderButtonParamError;
import com.spark.order.util2.OrderUtilFactory2.OrderUtilFactoryException;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;

/**
 * 销售订单详情维护
 
 *
 */
public final class SalesOrderInfoImpl2 extends OrderInfoImpl2<SalesOrderInfo2> implements SalesOrderInfo{

	public SalesOrderInfoImpl2(Context context, SalesOrderInfo2 t) throws OrderButtonParamError, OrderUtilFactoryException {
		super(context, t);
		init();
	}
	

	private void init() throws OrderButtonParamError, OrderUtilFactoryException {
		actions = OrderUtilFactory2.newOrderButton2(OrderAction.class, getContext(), OrderEnum.Sales).getOrderAction(getEntity());
	}
	private OrderAction[] actions;

	@Override
	public OrderAction[] getActions() {
		return actions;
	}

	@Override
	public ContactBookInfo getConsigneeInfo() {
		if(null != getEntity().getConsigneeId()){
			return getContext().find(ContactBookInfo.class, getEntity().getConsigneeId());
		}
		return null;
	}

	@Override
	public OrderStatus getOrderStatus() {
		return getOrderStatus(OrderEnum.Sales, TypeEnum.getType(getEntity().getType()), getEntity().getStatus());
	}

	@Override
	public OrderType getOrderType() {
		return getOrderType(OrderEnum.Sales, getEntity().getType());
	}

	public double getDiscountAmount() {
		return getEntity().getDisAmount();
	}

	public SalesOrderGoodsItem[] getSalesOrderGoodsItems() {
		List<SalesOrderGoodsItem> items = new ArrayList<SalesOrderGoodsItem>();
		List<SalesOrderItem2> dets = getContext().getList(SalesOrderItem2.class, getEntity().getRecid());
		for(SalesOrderItem2 det : dets){
			items.add(new SalesOrderGoodsItemImpl2(getContext(), det));
		}
		return items.toArray(new SalesOrderGoodsItem[items.size()]);
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
