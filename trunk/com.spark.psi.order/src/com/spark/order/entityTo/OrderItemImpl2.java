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
 * <p>���ۡ��ɹ����������˻��൥�ݻ���</p>
 * 
 * ���ύ�ɹ���������
 * �������ɹ���������
 * ���ٲɹ�����
 * �ɹ�������¼
 * ��ѯ�ɹ������б� OrderListEntity<PurchaseItem>+GetOrderItemKey
 * 
 * ���۶����б�
 * ���ύ���۶�������
 * ���������۶�������
 * ���۶�������
 * ���۶�����¼
 * ��ѯ���� OrderListEntity<SalesOrderItem>+GetOrderItemKey
 * 
 * ���׼�¼
 * ��Ӧ��δ��ɽ����б� TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id)
 * ��Ӧ������ɽ��׼�¼�б� TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id��false)
 * �ͻ�δ��ɽ����б� TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id)
 * �ͻ�����ɽ��׼�¼�б� TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id��false) * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
