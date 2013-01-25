package com.spark.order.util2;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.EntityFather;

abstract class OrderActionImpl2<ActionEnum, status> implements IOrderAction2<ActionEnum, status>{
//	private List<IServiceCheck> checkList = new ArrayList<IServiceCheck>();
	private GUID orderId;
	private EntityFather entity;
	private final Context context;
	/**
	 * @param context
	 */
	protected OrderActionImpl2(Context context) {
		super();
		this.context = context;
	}

	public boolean action(GUID id, ActionEnum orderAction) throws OrderActionLoseException {
		return action(id, orderAction, false);
	}
	
	public boolean action(GUID id, ActionEnum orderAction,
			boolean ignoredWarning) throws OrderActionLoseException {
		if(null == id){
			throw new OrderActionLoseException("订单Id不能为空");
		}
		this.orderId = id;
		return doAction(orderAction, ignoredWarning);
	}
	/**
	 * 
	 * @param orderAction
	 * @param ignoredWarning true忽略警告
	 * @return
	 * @throws Throwable boolean 失败返回false
	 */
	protected abstract boolean doAction(ActionEnum orderAction,
			boolean ignoredWarning) throws OrderActionLoseException;

//	public IServiceCheck[] getServiceCheck() {
//		return checkList.toArray(new IServiceCheck[checkList.size()]);
//	}
//	
//	/**
//	 * 添加校验结果
//	 * @param check
//	 * @return OrderActionImpl<ActionEnum,status>
//	 */
//	protected OrderActionImpl2<ActionEnum, status> addCheck(IServiceCheck check){
//		checkList.add(check);
//		return this;
//	}

	public void setEntity(EntityFather entity) {
		this.entity = entity;
	}
	
	protected GUID getOrderId() {
		return orderId;
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends EntityFather> T getEntity(Class<T> c){
		if(!c.isInstance(entity)){
			setEntity(context.find(c, orderId));
		}
		return (T) entity;
//		if(c.isInstance(entity)){
//			return (T)entity;
//		}
//		else{
//			T t = context.find(c, orderId);
//			setEntity(t);
//			return t;
//		}
	}
	
	public Context getContext() {
		return context;
	}

	public void setPlanOutDate(Long l) {
		
	}
}
