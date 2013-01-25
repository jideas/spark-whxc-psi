/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.QueryScopeSource;
import com.spark.psi.base.browser.SetPSICaseUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderStatusTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnStatusTask;

/**
 * 所有待处理的采购订单列表处理器
 * 
 */
public class ProcessingPurchaseOrderListProcessor extends
		PurchaseOrderListProcessor<OrderItem> {

	public final static String ID_COMBOLIST_TYPE = "ComboList_Type";
	// 数量
	public final static String ID_LABEL_ORDER_COUNT = "Label_Order_Count";
	// 采购金额
	public final static String ID_LABEL_PURCHASE_AMOUNT = "Label_Purchase_Amount";
	// 退货金额
	public final static String ID_LABEL_REJECTED_AMOUNT = "Label_Rejected_Amount";
	private Label lblOrderCount;
	private Label lblPurchaseAmount;
	private Label lblReectedAmount;
	private LWComboList queryScopeList;
	private QueryScopeSource queryScopeSource;

	@Override
	public void process(Situation situation) {

		super.process(situation);
		// 下拉
		if(!isEmployee()){
			queryScopeList = this.createControl(ID_COMBOLIST_TYPE,
					LWComboList.class);
			queryScopeSource = PSIProcessorUtils.initQueryScopeSource(
					queryScopeList, "我的单据", Auth.Purchase);
			queryScopeList.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					table.render();
				}
			});
		}

		lblOrderCount = this.createControl(ID_LABEL_ORDER_COUNT, Label.class,
				JWT.NO);

		lblPurchaseAmount = this.createControl(ID_LABEL_PURCHASE_AMOUNT,
				Label.class, JWT.NO);

		lblReectedAmount = this.createControl(ID_LABEL_REJECTED_AMOUNT,
				Label.class, JWT.NO);
	}

	private OrderListEntity listEntity;

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Approval.name(),
				Action.Consignment.name(), Action.Stop.name() };
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		OrderItem item = (OrderItem) element;
		return OrderAction.getItemActions(item.getActions());
	}


	/**
	 * 指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Approval.name())) {
			MsgRequest request;
			if (OrderType.Purchase_Return == getItemObject(rowId)
					.getOrderType()) {
				PurchaseReturnInfo info = getContext().find(PurchaseReturnInfo.class,
						GUID.valueOf(rowId));
				PageControllerInstance pci = new PageControllerInstance(
						new PageController(
								PurchaseReturnSheetDetailProcessor.class,
								PurchaseReturnSheetDetailRender.class), info);
				request = new MsgRequest(pci, "采购退货明细");
			} else {
				PurchaseOrderInfo info = getContext().find(PurchaseOrderInfo.class,
						GUID.valueOf(rowId));
				PageControllerInstance pci = new PageControllerInstance(
						new PageController(PurchaseOrderDetailProcessor.class,
								PurchaseOrderDetailRender.class), info);
				request = new MsgRequest(pci, "采购明细");
			}
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
			// table.render();
		} else if (actionName.equals(Action.Consignment.name())) {
			String title = "您确认进行发货操作？";
			confirm(
					title, new Runnable() {

						public void run() {
							getContext().handle(
									new UpdatePurchaseOrderStatusTask(GUID
											.valueOf(rowId),
											OrderAction.consignment));
							table.render();
						}
					});
		} else if (actionName.equals(Action.Stop.name())) {
			new SetPSICaseUtil(getContext(), SetPSICaseUtil.StopCause) {
				
				@Override
				protected void action(String cause) {
					if (OrderType.Purchase_Return == getItemObject(rowId)
							.getOrderType()) {
						getContext().handle(
								new UpdatePurchaseReturnStatusTask(GUID
										.valueOf(rowId), OrderAction.Stop,
										cause));
					} else {
						getContext().handle(
								new UpdatePurchaseOrderStatusTask(GUID
										.valueOf(rowId), OrderAction.Stop,
										cause));
					}
					table.render();
				}
			};
		}
	}

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		// OrderListEntity, GetPurchaseOrderListKey
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setOrderStatus(OrderStatus.CheckedIn, OrderStatus.CheckedOut,
				OrderStatus.CheckingIn_NO, OrderStatus.CheckingIn_Part,
				OrderStatus.CheckingOut_No, OrderStatus.CheckingOut_Part);
		if(null != queryScopeSource){
			key.setQueryScope(queryScopeSource.getQueryScope(queryScopeList
					.getText()));
		}
		// key.setQueryTerm(queryTerm)

		key.setSearchText(searchText.getText());
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn())
					.getSortField());
		}
		//排序
		setLimitKeySort(key, tablestatus);
		
		
		listEntity = context.find(OrderListEntity.class, key);
		
		long size = listEntity.getTotalCount();
		double orderAmount = listEntity.getOrderAmount();
		double returnAmount = listEntity.getReturnAmount();
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = lblOrderCount.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
			String preOrderAmount = lblPurchaseAmount.getText();
			if (StringHelper.isNotEmpty(preOrderAmount)) {
				orderAmount = DoubleUtil.sub(orderAmount, DoubleUtil.strToDouble(preOrderAmount));
			}
			String preReturnAmount = lblReectedAmount.getText();
			if (StringHelper.isNotEmpty(preReturnAmount)) {
				returnAmount = DoubleUtil.sub(returnAmount, DoubleUtil.strToDouble(preReturnAmount));
			}
		}
		lblOrderCount.setText(String.valueOf(size));
		lblPurchaseAmount.setText(DoubleUtil.getRoundStr(orderAmount));
		lblReectedAmount.setText(DoubleUtil.getRoundStr(returnAmount));
		return listEntity.getItemList().toArray();
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "采购订单";
	}
}
