package com.spark.order.util.button;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;

abstract class OrderButtonImpl implements IOrderButton{
	protected GUID id;
	protected Context context;
	protected OrderInfo orderInfo;
	protected OrderStatus[] statuss;
	private final List<OrderAction> list = new ArrayList<OrderAction>();
	/**
	 * @param context
	 * @param orderInfo
	 */
	public OrderButtonImpl(Context context, OrderInfo orderInfo) {
		super();
		this.context = context;
		this.orderInfo = orderInfo;
	}
	
	public void setOrderStatuss(OrderStatus... statuss) {
		this.statuss = statuss;
	}

	public OrderAction[] getOrderAction(GUID id) throws OrderButtonParamError {
		if(null == id){
			throw new OrderButtonParamError();
		}
		this.id = id;
		if(null == this.orderInfo){
			this.orderInfo = getOrderInfo();
		}
		 doInit();
		return list.toArray(new OrderAction[list.size()]);
	}
	
	protected abstract void doInit();
	
	/**
	 * 获得订单对象
	 * @return OrderInfo
	 */
	protected abstract OrderInfo getOrderInfo();
	
	/**
	 * 添加操作图标
	 * @param orderActions
	 * @return OrderButtonImpl
	 */
	protected OrderButtonImpl addOrderAction(OrderAction... orderActions){
		for(OrderAction oa : orderActions){
			list.add(oa);
		}
		return this;
	}
}
