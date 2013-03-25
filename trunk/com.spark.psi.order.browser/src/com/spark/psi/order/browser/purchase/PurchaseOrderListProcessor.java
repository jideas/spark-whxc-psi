package com.spark.psi.order.browser.purchase;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
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
import com.spark.psi.order.browser.util.OrderListProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;

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
abstract class PurchaseOrderListProcessor<T extends OrderItem> extends
		OrderListProcessor<T> {
//	protected final static String supplierName = "供应商";
//	private Map<String, T> itemMap = new HashMap<String, T>();
	private Hashtable<String, T> itemMap = new Hashtable<String, T>();
	/**
	 * 上下文
	 */
	protected Situation context;
	@Override
	public void init(Situation context) {
		super.init(context);
		this.context = context;
	}
	
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
	 * 当前单据是否是退货单
	 * 
	 * @param rowId
	 * @return boolean
	 */
	protected boolean isReturn(String rowId) {
		OrderItem item = itemMap.get(rowId);
		return OrderType.Purchase_Return == item.getOrderType();
	}

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
//		itemMap.clear();
		return getOrderItem(tablestatus);
	}
	
	/**
	 * 进入订单详情界面
	 * @param orderId void
	 */
	private void inOrderDetail(String rowId){
		MsgRequest request;
		if (isReturn(rowId)) {
			PurchaseReturnInfo info = getContext().find(
					PurchaseReturnInfo.class, GUID.valueOf(rowId));
			PageControllerInstance pci = new PageControllerInstance(
					new PageController(
							PurchaseReturnSheetDetailProcessor.class,
							PurchaseReturnSheetDetailRender.class),
					info);
			request = new MsgRequest(pci, "采购退货单明细");
		} else {
			PurchaseOrderInfo info = getContext().find(
					PurchaseOrderInfo.class, GUID.valueOf(rowId));
			PageControllerInstance pci = new PageControllerInstance(
					new PageController(
							PurchaseOrderDetailProcessor.class,
							PurchaseOrderDetailRender.class), info);
			request = new MsgRequest(pci, "采购订单明细");
		}
		request.setResponseHandler(new ResponseHandler() {

			public void handle(Object returnValue,
					Object returnValue2, Object returnValue3,
					Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}
	
	@Override
	protected boolean isEmployee() {
		LoginInfo login = context.find(LoginInfo.class);
		if(!login.hasAuth(Auth.Boss, Auth.PurchaseManager)){
			return true;
		}
		return false;
	}
	
	protected abstract Object[] getOrderItem(STableStatus tablestatus);

}
