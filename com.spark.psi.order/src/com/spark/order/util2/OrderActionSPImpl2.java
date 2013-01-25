package com.spark.order.util2;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.entity.OrderFather;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.event.PurchaseReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.promotion.intf.PromotionSaledCountTask;
import com.spark.order.sales2.SalesOrderTask2;
import com.spark.order.sales2.SalesReturnTask2;
import com.spark.order.service.OrderStatusTask2;
import com.spark.order.service.StopOrderTask2;
import com.spark.order.util2.OrderUtilFactory2.OrderUtilFactoryException;
import com.spark.psi.base.ApprovalConfig;
import com.spark.psi.inventory.intf.task.pub.ExcuteCheckingInTask;
import com.spark.psi.inventory.intf.task.pub.ExcuteCheckingOutTask;
import com.spark.psi.inventory.intf.task.pub.StopCheckingInTask;
import com.spark.psi.inventory.intf.task.pub.StopCheckingOutTask;
import com.spark.psi.publish.OrderAction;

abstract class OrderActionSPImpl2<Order extends OrderFather> extends
		OrderActionImpl2<OrderAction, StatusEnum> {
	protected boolean ignoredWarning;
	private final OrderEnum orderEnum;
	private String cause;
	private StatusEnum newstatus;

	protected OrderActionSPImpl2(Context context, OrderEnum orderEnum) {
		super(context);
		this.orderEnum = orderEnum;
	}

	@Override
	protected boolean doAction(OrderAction orderAction, boolean ignoredWarning)
			throws OrderActionLoseException {
		this.ignoredWarning = ignoredWarning;
		boolean succeed = true;
		OrderStatusTask2 task = null;
		try {
			OrderFather order;
			IProcessManage2<StatusEnum> process;
			switch (orderAction) {
			case Submit:
				order = getOrder();
				process = OrderUtilFactory2.newProcessManage2(StatusEnum.class, getContext(), orderEnum);
				process.setEntity(order);
				newstatus = process.next(StatusEnum.getstatus(order.getStatus()));
				task = new OrderStatusTask2(orderEnum, getOrderId(), order.getStatus(), newstatus.getKey());
				task.setDeptId(process.getOrderDepartment());
				break;
			case Delete:
				succeed = delete(getOrderId());
				break;
			case Deny:
				order = getOrder();
				process = OrderUtilFactory2.newProcessManage2(StatusEnum.class, getContext(), orderEnum);
				process.setEntity(order);
				newstatus = process.prov(StatusEnum.getstatus(order.getStatus()));
				task = new OrderStatusTask2(orderEnum, getOrderId(), order.getStatus(), newstatus.getKey(), cause);
				break;
			case Approval:
				order = getOrder();
				process = OrderUtilFactory2.newProcessManage2(StatusEnum.class, getContext(), orderEnum);
				process.setEntity(order);
				newstatus = process.next(StatusEnum.getstatus(order.getStatus()));
				task = new OrderStatusTask2(orderEnum, getOrderId(), order.getStatus(), newstatus.getKey());
				task.setDeptId(process.getOrderDepartment());
				task.setExamineDeptId(process.getOrderExamDept());
				break;
			case Allocate:
				succeed = allocate();
				break;
			case consignment:
				newstatus = consignment();
				break;
			case Stop:
				order = getOrder();
				succeed = stop(order);
				break;
			case Execut:
				order = getOrder();
				succeed = execut(order);
				break;
			default:
				throw new OrderActionLoseException("��ǰ���������ڣ�����չ��");
			}
		} catch (OrderUtilFactoryException e1){
			throw new OrderActionLoseException(e1.getMessage());
		}
		if(null != task){
			getContext().handle(task);
			//������Ч�����������
			if(task.isSucceed() && StatusEnum.Store_N0 == newstatus){
				effectual();
			}
			succeed = task.isSucceed();
		}
		if(succeed){
			//���䷨�¼�
			changedEvent(getOrderId(), orderAction);
		}
		return succeed;
	}
	
	/**
	 *  ���䷨�¼�void
	 * @param id 
	 */
	private void changedEvent(GUID id, OrderAction action)  throws OrderActionLoseException{
		switch (orderEnum) {
		case Sales:
			getContext().dispatch(new SalesOrderChangedEvent(id, action));
			break;
		case Sales_Return:
			getContext().dispatch(new SalesReturnChangedEvent(id, action));
			break;
		case Purchase:
			getContext().dispatch(new PurchaseOrderChangedEvent(id, action));
			break;
		case Purchase_Return:
			getContext().dispatch(new PurchaseReturnChangedEvent(id, action));
			break;
		default:
			throw new OrderActionLoseException("��ǰ���Ͷ�����ʱ�ޱ仯����������չ��");
		}
	}

	/**
	 * ������Ч��ִ����ز�����
	 * @return boolean
	 */
	protected abstract void effectual() throws OrderActionLoseException;

	/**
	 * ��ö�������
	 * @return Order
	 */
	protected abstract Order getOrder();

	/**
	 * ����ִ�ж���
	 * @param order 
	 * @return
	 * @throws OrderActionLoseException boolean
	 */
	protected boolean execut(OrderFather order){
		StopOrderTask2 task = new StopOrderTask2(orderEnum, getOrderId(), order.getStatus());
		getContext().handle(task, StopOrderTask2.Method.Execut);
		return task.isSucceed() && exectInventory();
	}

	/**
	 * ��ֹ����
	 * @param order 
	 * @return
	 * @throws OrderActionLoseException boolean
	 */
	protected boolean stop(OrderFather order){
		StopOrderTask2 task = new StopOrderTask2(orderEnum, getOrderId(), order.getStatus(), cause);
		getContext().handle(task, StopOrderTask2.Method.Stop);
		return task.isSucceed() && stopInventory(order.getReturnCause());
	}
	
	/**
	 * ��ֹ����ⵥ
	 * @return boolean
	 */
	private boolean stopInventory(String cause) {
		if(orderEnum.isIn(OrderEnum.Purchase, OrderEnum.Sales_Return)){
			StopCheckingInTask task = new StopCheckingInTask();
			task.setRelationOrderId(getOrderId());
			task.setStopReason(cause);
			getContext().handle(task);
		}else{
			StopCheckingOutTask task = new StopCheckingOutTask();
			task.setRelationOrderId(getOrderId());
			task.setStopReason(cause);
			getContext().handle(task);
		}
		return true;
	}
	
	/**
	 * ����ִ�г����
	 * @return boolean
	 */
	private boolean exectInventory() {
		if(orderEnum.isIn(OrderEnum.Purchase, OrderEnum.Sales_Return)){
			ExcuteCheckingInTask task = new ExcuteCheckingInTask();
			task.setRelationOrderId(getOrderId());
			getContext().handle(task);
		}else{
			ExcuteCheckingOutTask task = new ExcuteCheckingOutTask();
			task.setRelationOrderId(getOrderId());
			getContext().handle(task);
		}
		return true;
	}

	/**
	 * �ɹ�����������
	 * @return StatusEnum
	 */
	protected StatusEnum consignment() throws OrderActionLoseException{
		throw new OrderActionLoseException("���ɹ�������������");
	}

	/**
	 * ���۶���������
	 * @param id
	 * @return boolean
	 */
	protected boolean allocate() throws OrderActionLoseException{
		throw new OrderActionLoseException("�����۶�����������");
	}

	/**
	 * ɾ������
	 * @param id 
	 * @return boolean
	 */
	private boolean delete(GUID id) throws OrderActionLoseException{
		boolean isSucceed = false;
		switch (orderEnum) {
		case Sales:
			SalesOrderTask2 sales = new SalesOrderTask2();
			sales.recid = id;
			getContext().handle(sales, SalesOrderTask2.Method.DELETE);
			isSucceed = sales.isSucceed();
			break;
		case Sales_Return:
			SalesReturnTask2 sReturn = new SalesReturnTask2();
			sReturn.recid = id;
			getContext().handle(sReturn, SalesReturnTask2.Method.DELETE);
			isSucceed = sReturn.isSucceed();
			break;
//		case Sales_Promotion:
//			PromotionTask2 promotion = new PromotionTask2();
//			promotion.recid = id;
//			getContext().handle(promotion, PromotionTask2.Method.DELETE);
//			isSucceed = promotion.isSucceed();
//			break;
//		case Retail_Return:
//		case Retail:
//			RetailTask2 retail = new RetailTask2();
//			retail.recid = id;
//			getContext().handle(retail, RetailTask2.Method.DELETE);
//			isSucceed = retail.isSucceed();
//			break;
		case Purchase:
//			break;
		case Purchase_Return:
//			break;
//		case Purchase_Goods:
//			break;
		default:
			throw new OrderActionLoseException("��ǰ���Ͷ�����ʱ��ɾ������������չ��");
		}
		return isSucceed;
	}

	public StatusEnum getNewstatus() {
		return newstatus;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	//=========����ʹ�÷���===========
	/**
	 * �޸���ʹ�ô�������
	 * @param promotinoId
	 * @param count
	 * @throws OrderActionPromotionCountException void
	 */
	protected void updatePromotionCount(GUID promotinoId, double count) throws OrderActionPromotionCountException{
		PromotionSaledCountTask task = new PromotionSaledCountTask(promotinoId, count);
		getContext().handle(task);
		if(!task.isSucceed()){
			throw new OrderActionPromotionCountException();
		}
	}
	//======�Լӷ���===========
	private ApprovalConfig ac;
	private void initApprovalConfig(){
		if(null == ac){
			ac = BillsConstant.getApprovalConfig(getContext());
		}
	}
	/**
	 * ����޶���ȣ����ж��Ƿ������
	 * @return Double
	 */
	protected double getLimitAmount(){
		initApprovalConfig();
		switch (orderEnum) {
		case Purchase:
			return ac.getPurchaseApprovalLimit();
		case Purchase_Return:
			return ac.getPurchaseReturnApprovalLimit();
		case Sales:
			return ac.getSalesApprovalLimit();
		case Sales_Return:
			return ac.getSalesReturnApprovalLimit();
		}
		return Double.MAX_VALUE;
	}
	
	/**
	 * �ж϶����Ƿ��Ѿ��������
	 * @return boolean true ����
	 */
	protected boolean isLimited(Context context, OrderEnum orderEnum){
		initApprovalConfig();
		switch (orderEnum) {
		case Purchase:
			return ac.isPurchaseOrderNeedApproval();
		case Purchase_Return:
			return ac.isPurchaseReturnNeedApproval();
		case Sales:
			return ac.isSalesOrderNeedApproval();
		case Sales_Return:
			return ac.isSalesReturnNeedApproval();
		}
		return false;
	}
}
