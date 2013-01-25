package com.spark.psi.order.browser.trading;

import java.util.HashMap;
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
import com.spark.psi.order.browser.internal.PurchaseReturnInfoImpl;
import com.spark.psi.order.browser.purchase.PurchaseOrderDetailProcessor;
import com.spark.psi.order.browser.purchase.PurchaseOrderDetailRender;
import com.spark.psi.order.browser.purchase.PurchaseReturnSheetDetailProcessor;
import com.spark.psi.order.browser.purchase.PurchaseReturnSheetDetailRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.entity.TradingRecordListEntity;
import com.spark.psi.publish.order.key.GetPurchaseOrderBySupplierKey;
import com.spark.psi.publish.order.task.DeletePurchaseOrderTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderStatusTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnStatusTask;

/**
 * 供应商未完成交易列表处理器
 * 
 */
public class SupplierProcessingOrderListProcessor extends PartnerProcessingOrderListProcessor {
	private Map<String, OrderItem> itemMap = new HashMap<String, OrderItem>();

	@Override
	public void process(Situation situation) {
		super.process(situation);

		Button button = this.createControl(ID_BUTTON_NEW, Button.class);
		button.setText(" 新增采购 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageControllerInstance pci = new PageControllerInstance("PurchasingGoodsListPage");
				MsgRequest request = new MsgRequest(pci, "采购清单");
				getContext().bubbleMessage(request);
			}
		});

		button = this.createControl(ID_BUTTON_NEWRETURN, Button.class);
		button.setText(" 新增采购退货 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PageController pc = new PageController(PurchaseReturnSheetDetailProcessor.class, PurchaseReturnSheetDetailRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, initReturnInfo());
				MsgRequest request = new MsgRequest(pci, "采购退货单明细");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);

			}

			private PurchaseReturnInfo initReturnInfo() { 
				ContactBookInfo contact = null; 
				ContactBookInfo adress = null; 
				PurchaseReturnInfoImpl info = new PurchaseReturnInfoImpl(partnerInfo);
				info.setConsigneeInfo(contact);
				info.setContactBookInfo(adress);
				info.setDeliveryDate(System.currentTimeMillis());
				info.setId(getContext().newRECID());
				return info;
			}
		});

	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus, String searchText) {
		GetPurchaseOrderBySupplierKey key = new GetPurchaseOrderBySupplierKey(this.partnerInfo.getId(), true);
		key.setSearchText(searchText);
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn()).getPurchaseField());
		}
		if (null != tablestatus.getSortDirection()) {
			key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
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
		return new String[] { Action.Submit.name(), Action.Approval.name(), Action.Stop.name(), Action.Delete.name(),
				Action.Consignment.name() };
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		OrderItem item = (OrderItem) element;
		OrderAction[] actions = item.getActions();
		if (null == actions || 0 == actions.length) {
			return null;
		}
		String[] oas = new String[actions.length];
		int index = 0;
		for (OrderAction oa : actions) {
			oas[index++] = oa.getAction().name();
		}
		return oas;
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Submit.name())) {
			if (isReturn(rowId)) {
				getContext().handle(new UpdatePurchaseReturnStatusTask(GUID.valueOf(rowId), OrderAction.Submit));
			} else {
				UpdatePurchaseOrderStatusTask task = new UpdatePurchaseOrderStatusTask(GUID.valueOf(rowId), OrderAction.Submit);
				// getContext().handle(task);
				// if(!task.isSuccess()){
				try {
					getContext().handle(task);
				} catch (Throwable ex) {
				}
			}
			table.render();
		} else if (actionName.equals(Action.Delete.name())) {
			OrderItem item = itemMap.get(rowId);
			if (item.getOrderType().isIn(OrderType.Purchase_Return, OrderType.SALES_RETURN)) {
				getContext().handle(
						new DeletePurchaseOrderTask(GUID.valueOf(rowId), com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Return));
			} else {
				getContext().handle(
						new DeletePurchaseOrderTask(GUID.valueOf(rowId), com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Order));
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
									new UpdatePurchaseReturnStatusTask(GUID.valueOf(rowId), OrderAction.Stop, String.valueOf(returnValue)));
						} else {
							getContext().handle(
									new UpdatePurchaseOrderStatusTask(GUID.valueOf(rowId), OrderAction.Stop, String.valueOf(returnValue)));
						}
						table.render();
					}
				}
			});
			getContext().bubbleMessage(request);
		} else if (actionName.equals(Action.ReExecute.name())) {
			if (isReturn(rowId)) {
				getContext().handle(new UpdatePurchaseReturnStatusTask(GUID.valueOf(rowId), OrderAction.Execut));
			} else {
				getContext().handle(new UpdatePurchaseOrderStatusTask(GUID.valueOf(rowId), OrderAction.Execut));
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
									new UpdatePurchaseReturnStatusTask(GUID.valueOf(rowId), OrderAction.Deny, String.valueOf(returnValue)));
						} else {
							getContext().handle(
									new UpdatePurchaseOrderStatusTask(GUID.valueOf(rowId), OrderAction.Deny, String.valueOf(returnValue)));
						}
						table.render();
					}
				}
			});
			getContext().bubbleMessage(request);
		} else if (actionName.equals(Action.Consignment.name())) {
			String title = "您确认进行发货操作？";
			confirm(title, new Runnable() {

				public void run() {
					getContext().handle(new UpdatePurchaseOrderStatusTask(GUID.valueOf(rowId), OrderAction.consignment));
					table.render();
				}
			});
		}
	}

	/**
	 * 当前单据是否是退货单
	 * 
	 * @param rowId
	 * @return boolean
	 */
	private boolean isReturn(String rowId) {
		OrderItem item = itemMap.get(rowId);
		return OrderType.Purchase_Return == item.getOrderType();
	}

	@Override
	protected PartnerInfo getPartnerInfo(GUID partnerId) {
		return getContext().find(SupplierInfo.class, partnerId);
	}

	@Override
	protected void inOrderDetail(String rowId) {
		MsgRequest request;
		OrderItem item = itemMap.get(rowId);
		if (OrderType.Purchase_Return == item.getOrderType()) {
			PurchaseReturnInfo info = getContext().find(PurchaseReturnInfo.class, GUID.valueOf(rowId));
			PageControllerInstance pci = new PageControllerInstance(new PageController(PurchaseReturnSheetDetailProcessor.class,
					PurchaseReturnSheetDetailRender.class), info);
			request = new MsgRequest(pci, "采购退货单明细");
		} else {
			PurchaseOrderInfo info = getContext().find(PurchaseOrderInfo.class, GUID.valueOf(rowId));
			PageControllerInstance pci = new PageControllerInstance(new PageController(PurchaseOrderDetailProcessor.class,
					PurchaseOrderDetailRender.class), info);
			request = new MsgRequest(pci, "采购订单明细");
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
