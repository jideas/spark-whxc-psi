package com.spark.psi.order.browser.trading;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.partner.PartnerProcessingOrderListProcessor;
import com.spark.psi.order.browser.internal.SalesOrderInfoImpl;
import com.spark.psi.order.browser.internal.SalesReturnInfoImpl;
import com.spark.psi.order.browser.sales.SalesOrderDetailProcessor;
import com.spark.psi.order.browser.sales.SalesOrderDetailRender;
import com.spark.psi.order.browser.sales.SalesReturnSheetDetailProcessor;
import com.spark.psi.order.browser.sales.SalesReturnSheetDetailRender;
import com.spark.psi.order.browser.sales.SetPlanOutDate;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.ValidationReturn;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnInfo;
import com.spark.psi.publish.order.entity.TradingRecordListEntity;
import com.spark.psi.publish.order.key.GetSalesOrderByCustomerKey;
import com.spark.psi.publish.order.task.DeleteSalesOrderTask;
import com.spark.psi.publish.order.task.UpdateSalesOrderStatusTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnStatusTask;

/**
 * 客户未完成交易列表处理器
 * 
 */
public class CustomerProcessingOrderListProcessor extends PartnerProcessingOrderListProcessor {
	private Map<String, OrderItem> itemMap = new HashMap<String, OrderItem>();

	@Override
	public void process(Situation situation) {
		super.process(situation);

		Button button = this.createControl(ID_BUTTON_NEW, Button.class);
		button.setText(" 新增销售 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PageController pc = new PageController(SalesOrderDetailProcessor.class, SalesOrderDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, initOrderInfo());
				MsgRequest request = new MsgRequest(pci, "销售订单明细");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);

			}

			private SalesOrderInfo initOrderInfo() {
				SalesOrderInfoImpl info = new SalesOrderInfoImpl();

				// ContactBookInfo[] contacts = partnerInfo.getContactPersons();
				// ContactBookInfo contact = null;
				// if(null != contacts && 0 < contacts.length){
				// contact = partnerInfo.getContactPersons()[0];
				// }
				//				
				// ContactBookInfo[] adresses =
				// partnerInfo.getDeliveryAddresses();
				// ContactBookInfo adress = null;
				// if(null != contacts && 0 < adresses.length){
				// adress = partnerInfo.getDeliveryAddresses()[0];
				// }
				// info.setConsigneeInfo(adress);
				// info.setContactBookInfo(contact);
				info.setId(getContext().newRECID());
				info.setOrderType(OrderType.PLAIN);
				info.setPartnerInfo(partnerInfo);
				return info;
			}
		});

		button = this.createControl(ID_BUTTON_NEWRETURN, Button.class);
		button.setText(" 新增销售退货 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(SalesReturnSheetDetailProcessor.class,
						SalesReturnSheetDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, initOrderInfo());
				MsgRequest request = new MsgRequest(pci, "销售退货单明细");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}

			private SalesReturnInfo initOrderInfo() {
				SalesReturnInfoImpl info = new SalesReturnInfoImpl();

				// ContactBookInfo[] contacts = partnerInfo.getContactPersons();
				// ContactBookInfo contact = null;
				// if(null != contacts && 0 < contacts.length){
				// contact = partnerInfo.getContactPersons()[0];
				// }
				//				
				// ContactBookInfo[] adresses =
				// partnerInfo.getDeliveryAddresses();
				// ContactBookInfo adress = null;
				// if(null != contacts && 0 < adresses.length){
				// adress = partnerInfo.getDeliveryAddresses()[0];
				// }
				// info.setConsigneeInfo(adress);
				// info.setContactBookInfo(contact);
				info.setId(getContext().newRECID());
				info.setOrderType(OrderType.PLAIN);
				info.setPartnerInfo(partnerInfo);
				return info;
			}
		});
	}

	public String getElementId(Object element) {
		OrderItem item = (OrderItem) element;
		itemMap.put(item.getId().toString(), item);
		return item.getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Submit.name(), Action.Approval.name(), Action.Stop.name(), Action.Delete.name() };
	}

	@Override
	public String[] getElementActionIds(Object element) {
		OrderItem item = (OrderItem) element;
		OrderAction[] actions = item.getActions();
		if (null == actions || 0 == actions.length) {
			return null;
		}
		String[] as = new String[actions.length];
		int index = 0;
		for (OrderAction oa : actions) {
			as[index++] = oa.name();
		}
		return as;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus, String searchText) {
		GetSalesOrderByCustomerKey key = new GetSalesOrderByCustomerKey(this.partnerInfo.getId(), true);
		key.setSearchText(searchText);
		// key.setSortType(sortType);
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn()).getSalesField());
		}
		if (null != tablestatus.getSortDirection()) {
			key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		}
		entity = context.find(TradingRecordListEntity.class, key);
		itemMap.clear();
		return entity.getItemList().toArray();
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Submit.name())) {
			if (isReturn(rowId)) {
				getContext().handle(new UpdateSalesReturnStatusTask(GUID.valueOf(rowId), OrderAction.Submit));
			} else {
				UpdateSalesOrderStatusTask task = new UpdateSalesOrderStatusTask(GUID.valueOf(rowId),
						OrderAction.Submit);
				// getContext().handle(task);
				// if(!task.isSuccess()){
				try {
					getContext().handle(task);
				} catch (Throwable ex) {
					ValidationReturn<UpdateSalesOrderStatusTask.Error> error = task.getValidationReturn();
					List<UpdateSalesOrderStatusTask.Error> errors = error.getErrors1();
					for (UpdateSalesOrderStatusTask.Error er : errors) {
						if (UpdateSalesOrderStatusTask.Error.SetPlanDate == er) {
							// 预警出库日期
							new SetPlanOutDate(getContext()) {

								@Override
								protected void deny() {
								}

								@Override
								protected void setPlanOutDate(Date date) {
									getContext().handle(
											new UpdateSalesOrderStatusTask(GUID.valueOf(rowId), OrderAction.Submit,
													date.getTime()));
									table.render();
								}
							};
						}
					}
				}
			}
			table.render();
		} else if (actionName.equals(Action.Delete.name())) {
			OrderItem item = itemMap.get(rowId);
			if (item.getOrderType().isIn(OrderType.Purchase_Return, OrderType.SALES_RETURN)) {
				getContext().handle(
						new DeleteSalesOrderTask(GUID.valueOf(rowId),
								com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Return));
			} else {
				getContext().handle(
						new DeleteSalesOrderTask(GUID.valueOf(rowId),
								com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Order));
			}
			table.render();
		} else if (actionName.equals(Action.Approval.name())) {
			inOrderDetail(rowId);
		} else if (actionName.equals(Action.Stop.name())) {
			PageControllerInstance pci = new PageControllerInstance("采购原因");
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			windowStyle.setSize(600, 260);
			MsgRequest request = new MsgRequest(pci, "中止原因", windowStyle);
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					if (null != returnValue) {
						if (isReturn(rowId)) {
							getContext().handle(
									new UpdateSalesReturnStatusTask(GUID.valueOf(rowId), OrderAction.Stop, String
											.valueOf(returnValue)));
						} else {
							getContext().handle(
									new UpdateSalesOrderStatusTask(GUID.valueOf(rowId), OrderAction.Stop, String
											.valueOf(returnValue)));
						}
						table.render();
					}
				}
			});
			getContext().bubbleMessage(request);
		} else if (actionName.equals(Action.ReExecute.name())) {
			if (isReturn(rowId)) {
				getContext().handle(new UpdateSalesReturnStatusTask(GUID.valueOf(rowId), OrderAction.Execut));
			} else {
				getContext().handle(new UpdateSalesOrderStatusTask(GUID.valueOf(rowId), OrderAction.Execut));
			}
			table.render();
		} else if (actionName.equals(Action.Deny.name())) {
			PageControllerInstance pci = new PageControllerInstance("采购原因");
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			windowStyle.setSize(600, 260);
			MsgRequest request = new MsgRequest(pci, "退回原因", windowStyle);
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					if (null != returnValue) {
						if (isReturn(rowId)) {
							getContext().handle(
									new UpdateSalesReturnStatusTask(GUID.valueOf(rowId), OrderAction.Deny, String
											.valueOf(returnValue)));
						} else {
							getContext().handle(
									new UpdateSalesOrderStatusTask(GUID.valueOf(rowId), OrderAction.Deny, String
											.valueOf(returnValue)));
						}
						table.render();
					}
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	private boolean isReturn(String rowId) {
		OrderItem item = itemMap.get(rowId);
		return OrderType.SALES_RETURN == item.getOrderType();
	}

	@Override
	protected PartnerInfo getPartnerInfo(GUID partnerId) {
		return getContext().find(CustomerInfo.class, partnerId);
	}

	@Override
	protected void inOrderDetail(String rowId) {
		MsgRequest request;
		OrderItem item = itemMap.get(rowId);
		if (OrderType.SALES_RETURN == item.getOrderType()) {
			PageController pc = new PageController(SalesReturnSheetDetailProcessor.class,
					SalesReturnSheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, getContext().find(SalesReturnInfo.class,
					GUID.valueOf(rowId)));
			request = new MsgRequest(pci, "销售订单明细");
		} else {
			PageController pc = new PageController(SalesOrderDetailProcessor.class, SalesOrderDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, getContext().find(SalesOrderInfo.class,
					GUID.valueOf(rowId)));
			request = new MsgRequest(pci, "销售退货单明细");
		}
		request.setResponseHandler(new ResponseHandler() {

			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "交易记录";
	}
}
