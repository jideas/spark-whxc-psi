package com.spark.psi.order.browser.trading;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.partner.PartnerProcessedOrderListProcessor;
import com.spark.psi.order.browser.sales.SalesOrderDetailProcessor;
import com.spark.psi.order.browser.sales.SalesOrderDetailRender;
import com.spark.psi.order.browser.sales.SalesReturnSheetDetailProcessor;
import com.spark.psi.order.browser.sales.SalesReturnSheetDetailRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnInfo;
import com.spark.psi.publish.order.entity.TradingRecordListEntity;
import com.spark.psi.publish.order.key.GetSalesOrderByCustomerKey;
import com.spark.psi.publish.order.task.UpdateSalesOrderStatusTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnStatusTask;

/**
 * 客户已完成交易列表处理器
 * 
 */
public class CustomerProcessedOrderListProcessor extends
		PartnerProcessedOrderListProcessor {
	private static final String space = "    ";
	private Map<String, OrderItem> itemMap = new HashMap<String, OrderItem>();
	@Override
	public String getTradeInfo() {
		StringBuilder title = new StringBuilder();
		title.append("销售总额：");
		title.append(entity.getTotalAmount());
		title.append(space);
		title.append("销售收款：");
		title.append(entity.getOrderAmount());
		title.append(space);
		title.append("销售退款：");
		title.append(entity.getReturnAmount());
		title.append(space);
		title.append("销售次数：");
		title.append(entity.getOrderCount());
		title.append(space);
		title.append("销售退货次数：");
		title.append(entity.getReturnCount());
		return title.toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus,
			QueryTerm queryTerm, String searchText) {
		GetSalesOrderByCustomerKey key = new GetSalesOrderByCustomerKey(this.partnerInfo.getId(), false);
		key.setQueryTerm(getContext().find(QueryTerm.class,queryTermList.getText()));
		key.setSearchText(searchText);
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn())
					.getSalesField());
		}
		if(null != tablestatus.getSortDirection()){
			key.setSortType(SSortDirection.ASC == tablestatus
							.getSortDirection() ? SortType.Asc : SortType.Desc);
		}
		entity = context.find(TradingRecordListEntity.class, key);
		itemMap.clear();
		return entity.getItemList().toArray();
	}

	public String getElementId(Object element) {
		OrderItem item = (OrderItem) element;
		itemMap.put(item.getId().toString(), item);
		return item.getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[]{Action.ReExecute.name()};
	}
	
	@Override
	protected String[] getElementActionIds(Object element) {
		OrderItem item = (OrderItem) element;
		OrderAction[] actions = item.getActions();
		if(null == actions || 0 == actions.length){
			return null;
		}
		String[] oas = new String[actions.length];
		int index = 0;
		for(OrderAction oa : actions){
			oas[index++] = oa.getAction().name();
		}
		return oas;
	}
	
	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Submit.name())) {
			
		} else if (actionName.equals(Action.Delete.name())) {
			
		} else if (actionName.equals(Action.Approval.name())) {
			inOrderDetail(rowId);
		} else if (actionName.equals(Action.Stop.name())) {
			
		} else if (actionName.equals(Action.ReExecute.name())) {
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
		} else if (actionName.equals(Action.Deny.name())) {
			
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
			PageController pc = new PageController(
					SalesReturnSheetDetailProcessor.class,
					SalesReturnSheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(
					pc, getContext().find(SalesReturnInfo.class,
							GUID.valueOf(rowId)));
			request = new MsgRequest(pci, "销售订单明细");
		} else {
			PageController pc = new PageController(
					SalesOrderDetailProcessor.class,
					SalesOrderDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(
					pc, getContext().find(SalesOrderInfo.class,
							GUID.valueOf(rowId)));
			request = new MsgRequest(pci, "销售退货单明细");
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
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "交易记录";
	}
}
