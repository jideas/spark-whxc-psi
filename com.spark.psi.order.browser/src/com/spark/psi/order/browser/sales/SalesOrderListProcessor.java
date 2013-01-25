package com.spark.psi.order.browser.sales;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.spi.application.SituationSPI;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.SetPSICaseUtil;
import com.spark.psi.order.browser.util.OrderListProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.ValidationReturn;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnInfo;
import com.spark.psi.publish.order.task.DeleteSalesOrderTask;
import com.spark.psi.publish.order.task.UpdateSalesOrderStatusTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnStatusTask;

/**
 * <p>
 * 销售订单控制父类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author modi
 * @version 2012-4-17
 */
abstract class SalesOrderListProcessor<T extends OrderItem> extends
		OrderListProcessor<T> {

	private Hashtable<String, T> itemMap = new Hashtable<String, T>();

	@SuppressWarnings("unchecked")
	@Override
	public String getElementId(Object element) {
		OrderItem item = (OrderItem) element;
		itemMap.put(item.getId().toString(), (T) element);
		return item.getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Submit.name(), Action.Delete.name(), Action.Approval.name(),
				 Action.Stop.name(), Action.ReExecute.name()};
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		OrderItem item = (OrderItem) element;
		OrderAction[] actions = item.getActions();
		return OrderAction.getItemActions(actions);
	}

	/**
	 * 获得表格Item的对象
	 * 
	 * @param rowId
	 * @return T
	 */
	protected T getItemObject(String rowId) {
		return itemMap.get(rowId);
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Submit.name())) {
			if (isReturn(rowId)) {
				getContext().handle(
						new UpdateSalesReturnStatusTask(GUID.valueOf(rowId),
								OrderAction.Submit));
			} else {
				UpdateSalesOrderStatusTask task = new UpdateSalesOrderStatusTask(GUID.valueOf(rowId),
						OrderAction.Submit);
//				getContext().handle(task);
//				if(!task.isSuccess()){
				try{
					getContext().handle(task);
				}
				catch(Throwable ex){
					ValidationReturn<UpdateSalesOrderStatusTask.Error> error = task.getValidationReturn();
					List<UpdateSalesOrderStatusTask.Error> errors = error.getErrors1();
					for(UpdateSalesOrderStatusTask.Error er : errors){
						if(UpdateSalesOrderStatusTask.Error.SetPlanDate == er){
							//预警出库日期
							new SetPlanOutDate(getContext()){

								@Override
								protected void deny() {
								}

								@Override
								protected void setPlanOutDate(Date date) {
									getContext().handle(new UpdateSalesOrderStatusTask(GUID.valueOf(rowId), OrderAction.Submit, date.getTime()));
									table.render();
								}
							};
						}
					}
//					getContext().abort();
					if("已超促销数量！".equals(ex.getMessage())){
						alert(ex.getMessage());
						((SituationSPI)getContext()).resolveTrans();
					}
				}
			}
			table.render();
		} else if (actionName.equals(Action.Delete.name())) {
			confirm("您确认要删除该订单？", new Runnable() {

				public void run() {
					OrderItem item = itemMap.get(rowId);
					if(item.getOrderType().isIn(OrderType.Purchase_Return, OrderType.SALES_RETURN)){
						getContext().handle(new DeleteSalesOrderTask(GUID.valueOf(rowId), com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Return));
					}
					else{
						getContext().handle(new DeleteSalesOrderTask(GUID.valueOf(rowId), com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Order));
					}
					table.render();
				}
				
			});
		} else if (actionName.equals(Action.Approval.name())) {
			inOrderDetail(rowId);
		} else if (actionName.equals(Action.Stop.name())) {
			new SetPSICaseUtil(getContext(), SetPSICaseUtil.StopCause) {
				
				@Override
				protected void action(String cause) {
					if (isReturn(rowId)) {
						getContext().handle(
								new UpdateSalesReturnStatusTask(GUID
										.valueOf(rowId), OrderAction.Stop,
										cause));
					} else {
						getContext().handle(
								new UpdateSalesOrderStatusTask(GUID
										.valueOf(rowId), OrderAction.Stop,
										cause));
					}
					table.render();
				}
			};
//			PageControllerInstance pci = new PageControllerInstance("采购原因");
//			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
//			windowStyle.setSize(600, 260);
//			MsgRequest request = new MsgRequest(pci, "中止原因", windowStyle);
//			request.setResponseHandler(new ResponseHandler() {
//
//				public void handle(Object returnValue, Object returnValue2,
//						Object returnValue3, Object returnValue4) {
//					if (null != returnValue) {
//						if (isReturn(rowId)) {
//							getContext().handle(
//									new UpdateSalesReturnStatusTask(GUID
//											.valueOf(rowId), OrderAction.Stop,
//											String.valueOf(returnValue)));
//						} else {
//							getContext().handle(
//									new UpdateSalesOrderStatusTask(GUID
//											.valueOf(rowId), OrderAction.Stop,
//											String.valueOf(returnValue)));
//						}
//						table.render();
//					}
//				}
//			});
//			getContext().bubbleMessage(request);
		} else if (actionName.equals(Action.ReExecute.name())) {
			confirm("您确认要重新执行该订单？", new Runnable() {
				
				public void run() {
					if (isReturn(rowId)) {
						getContext().handle(
								new UpdateSalesReturnStatusTask(GUID.valueOf(rowId),
										OrderAction.Execut));
					} else {
						getContext().handle(
								new UpdateSalesOrderStatusTask(GUID.valueOf(rowId),
										OrderAction.Execut));
					}
					table.render();
				}
			});
		} else if (actionName.equals(Action.Deny.name())) {
			new SetPSICaseUtil(getContext(), SetPSICaseUtil.ReturnCause) {
				
				@Override
				protected void action(String cause) {
					if (isReturn(rowId)) {
						getContext().handle(
								new UpdateSalesReturnStatusTask(GUID
										.valueOf(rowId), OrderAction.Deny,
										cause));
					} else {
						getContext().handle(
								new UpdateSalesOrderStatusTask(GUID
										.valueOf(rowId), OrderAction.Deny,
										cause));
					}
					table.render();
				}
			};
//			PageControllerInstance pci = new PageControllerInstance("采购原因");
//			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
//			windowStyle.setSize(600, 260);
//			MsgRequest request = new MsgRequest(pci, "退回原因", windowStyle);
//			request.setResponseHandler(new ResponseHandler() {
//
//				public void handle(Object returnValue, Object returnValue2,
//						Object returnValue3, Object returnValue4) {
//					if (null != returnValue) {
//						if (isReturn(rowId)) {
//							getContext().handle(
//									new UpdateSalesReturnStatusTask(GUID
//											.valueOf(rowId), OrderAction.Deny,
//											String.valueOf(returnValue)));
//						} else {
//							getContext().handle(
//									new UpdateSalesOrderStatusTask(GUID
//											.valueOf(rowId), OrderAction.Deny,
//											String.valueOf(returnValue)));
//						}
//						table.render();
//					}
//				}
//			});
//			getContext().bubbleMessage(request);
		}
	}

	/**
	 * 当前单据是否是退货单
	 * 
	 * @param rowId
	 * @return boolean
	 */
	protected boolean isReturn(String rowId) {
		OrderItem item = itemMap.get(rowId);
		return OrderType.SALES_RETURN == item.getOrderType();
	}

	
//	public final static String ID_TEXT_SEARCH = "TEXT_SEARCH";
	protected Text searchText;
	
	@Override
	public void process(Situation context) {
		super.process(context);
		searchText = this.createControl(ID_TEXT_SEARCH, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		table.addActionListener(new SActionListener() {

			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				if (Columns.OrderNumber.name().equals(actionName)) {
					inOrderDetail(rowId);
				}
			}
		});
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		itemMap.clear();
		return getOrderItem(tablestatus);
	}
	
	/**
	 * 进入订单详情界面
	 * @param orderId void
	 */
	private void inOrderDetail(String rowId){
		MsgRequest request;
		if (isReturn(rowId)) {
			PageController pc = new PageController(
					SalesReturnSheetDetailProcessor.class,
					SalesReturnSheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(
					pc, getContext().find(SalesReturnInfo.class,
							GUID.valueOf(rowId)));
			request = new MsgRequest(pci, "销售退货单明细");
		} else {
			PageController pc = new PageController(
					SalesOrderDetailProcessor.class,
					SalesOrderDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(
					pc, getContext().find(SalesOrderInfo.class,
							GUID.valueOf(rowId)));
			request = new MsgRequest(pci, "销售订单明细");
		}
		request.setResponseHandler(new ResponseHandler() {
			
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}
	
	@Override
	protected boolean isEmployee() {
		LoginInfo login = getContext().find(LoginInfo.class);
		if(!login.hasAuth(Auth.Boss, Auth.SalesManager)){
			return true;
		}
		return false;
	}
	
	protected abstract Object[] getOrderItem(STableStatus tablestatus);

}
