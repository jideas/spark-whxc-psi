package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.entity.OrderFather;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.util2.OrderUtilFactory2;
import com.spark.order.util2.IOrderButton2.OrderButtonParamError;
import com.spark.order.util2.OrderUtilFactory2.OrderUtilFactoryException;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.order.entity.OrderItem;

/**
 * 
 * <p>销售、采购、订货、退货类单据基类</p>
 * 
 * 待提交采购订单界面
 * 待审批采购订单界面
 * 跟踪采购订单
 * 采购订单记录
 * 查询采购订单列表 OrderListEntity<PurchaseItem>+GetOrderItemKey
 * 
 * 销售订单列表
 * 待提交销售订单界面
 * 待审批销售订单界面
 * 销售订单跟踪
 * 销售订单记录
 * 查询方法 OrderListEntity<SalesOrderItem>+GetOrderItemKey
 * 
 * 交易记录
 * 供应商未完成交易列表 TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id)
 * 供应商已完成交易记录列表 TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id，false)
 * 客户未完成交易列表 TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id)
 * 客户已完成交易记录列表 TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id，false) * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-2-22
 */
@StructClass
public class OrderItemImpl2 extends EntityToFather<OrderFather> implements OrderItem{
	public static enum Method{
		Sales,
		Purchase
	}
	private final Method methodType;
	public OrderItemImpl2(Context context, OrderFather t, Method type) throws OrderButtonParamError, OrderUtilFactoryException{
		super(context, t);
		this.methodType = type;
		init();
	}
	private OrderAction[] actions;
	private OrderEnum orderEnum;
	private TypeEnum typeEnum;
	
	private void init() throws OrderButtonParamError, OrderUtilFactoryException {
		typeEnum = TypeEnum.getType(getEntity().getType());
		switch (typeEnum) {
		case PLAIN:
			if(Method.Sales == methodType)orderEnum = OrderEnum.Sales;
			else orderEnum = OrderEnum.Purchase; 
			break;
		case CANCEL:
			if(Method.Sales == methodType)orderEnum = OrderEnum.Sales_Return;
			else orderEnum = OrderEnum.Purchase_Return; 
			break;
		}
		actions = OrderUtilFactory2.newOrderButton2(OrderAction.class, getContext(), orderEnum).getOrderAction(getEntity());
	}


	public OrderAction[] getActions() {
		return actions;
	}

	public double getAmount() {
		return getEntity().getTotalAmount();
	}

	public long getDeliveryDate() {
		return getEntity().getDeliveryDate() == null ? 0 : getEntity().getDeliveryDate();
	}

	public String getOrderNumber() {
		return getEntity().getOrderNumber();
	}

	public OrderStatus getOrderStatus() {
		return getOrderStatus(orderEnum, typeEnum, getEntity().getStatus());
	}

	public OrderType getOrderType() {
		return getOrderType(orderEnum, getEntity().getType());
	}

	public GUID getPartnerId() {
		return getEntity().getPartnerId();
	}

	public String getPartnerName() {
		return getEntity().getPartnerName();
	}

	public String getPartnerShortName() {
		return getEntity().getPartnerShortName();
	}

	public boolean isStoped() {
		return getEntity().isStoped();
	}

	public String getCreator() {
		return getEntity().getCreator();
	}	
	
}
