package com.spark.order.util2;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.entity.EntityFather;

abstract class OrderButtonImpl2<Action extends Enum<Action>> implements IOrderButton2<Action>{
	private Context context;
	private List<Action> orderActions;
	/**
	 * @param context
	 */
	protected OrderButtonImpl2(Context context) {
		super();
		this.context = context;
	}
	public Context getContext() {
		return context;
	}
	private EntityFather entity;
	public Action[] getOrderAction(EntityFather entity)
			throws OrderButtonParamError {
		if(null == entity || null == entity.getRecid()){
			throw new OrderButtonParamError();
		}
		initButton(entity);
		return getActions(orderActions);
	}
	/**
	 * 获得当前订单类型实例化对象
	 * @param <T>
	 * @param c
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getEntityFatherImpl(Class<T> c){
		if(c.isInstance(entity)){
			return (T)entity;
		}
		else{
			return context.find(c, entity.getRecid());
		}
	}
	/**
	 * 添加操作按钮
	 * @param orderAction
	 * @return OrderButtonImpl2
	 */
	protected OrderButtonImpl2<Action> addOrderAction(Action orderAction){
		orderActions.add(orderAction);
		return this;
	}
	/**
	 * 获取操作数组
	 * @param orderActions
	 * @return Action[]
	 */
	protected abstract Action[] getActions(List<Action> orderActions);
	/**
	 * 按钮流程初始化，通过addOrderAction(OrderAction orderAction)方法添加可操作
	 *  void
	 */
	protected abstract void initButton(EntityFather entity) throws OrderButtonParamError;
}
