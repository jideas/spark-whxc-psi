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
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.QueryScopeSource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderStatusTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnStatusTask;

/**
 * 所有处理完的采购订单列表处理器
 * 
 */
public class ProcessedPurchaseOrderListProcessor extends
		PurchaseOrderListProcessor<OrderItem> {

	public final static String ID_COMBOLIST_TYPE = "ComboList_Type";
	public final static String ID_COMBOLIST_DATE = "ComboList_Date";
	// 单据数量
	public final static String ID_LABEL_ORDER_COUNT = "Label_Order_Count";
	// 采购金额
	public final static String ID_LABEL_PURCHASE_AMOUNT = "Label_Purchase_Amount";
	// 退货金额
	public final static String ID_LABEL_REJECTED_AMOUNT = "Label_Rejected_Amount";

	private Label lblOrderCount;
	private Label lblPurchaseAmount;
	private Label lblReturnAmount;
	private LWComboList colstDate, queryScopeList;
	private QueryScopeSource queryScopeSource;
	@Override
	public void process(Situation situation) {

		super.process(situation);

		//下拉
		if(!isEmployee()){
			queryScopeList = this.createControl(ID_COMBOLIST_TYPE, LWComboList.class);
			queryScopeSource = PSIProcessorUtils.initQueryScopeSource(
					queryScopeList, "我的单据", Auth.Purchase);
			queryScopeList.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					table.render();
				}
			});
		}

		colstDate = this.createControl(ID_COMBOLIST_DATE,
				LWComboList.class, JWT.NO);
		PSIProcessorUtils.initQueryTermSource(colstDate);
		colstDate.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		lblOrderCount = this.createControl(ID_LABEL_ORDER_COUNT, Label.class,
				JWT.NO);

		lblPurchaseAmount = this.createControl(ID_LABEL_PURCHASE_AMOUNT,
				Label.class, JWT.NO);

		lblReturnAmount = this.createControl(ID_LABEL_REJECTED_AMOUNT,
				Label.class, JWT.NO);
	}

	private OrderListEntity listEntity;

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.ReExecute.name() };
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
		if (actionName.equals(Action.ReExecute.name())) {
			String title = "确认重新执行此订单？";
			if (OrderType.Purchase_Return == getItemObject(rowId)
					.getOrderType()) {
				title = "确认重新执行此退货申请？";
			}
			confirm(
					title, new Runnable() {

						public void run() {
							if (OrderType.Purchase_Return == getItemObject(
									rowId).getOrderType()) {
								UpdatePurchaseReturnStatusTask task = new UpdatePurchaseReturnStatusTask(
										GUID.valueOf(rowId), OrderAction.Execut);
								getContext().handle(task);
							} else {
								UpdatePurchaseOrderStatusTask task = new UpdatePurchaseOrderStatusTask(
										GUID.valueOf(rowId), OrderAction.Execut);
								getContext().handle(task);
							}
							table.render();
						}
					});
		}
	}

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		// OrderListEntity, GetPurchaseOrderListKey
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setOrderStatus(OrderStatus.finish, OrderStatus.Stop);
		if(null != queryScopeSource){
			key.setQueryScope(queryScopeSource.getQueryScope(queryScopeList
						.getText()));
		}
		key.setQueryTerm(getContext().find(QueryTerm.class,colstDate.getText()));
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
			String preReturnAmount = lblReturnAmount.getText();
			if (StringHelper.isNotEmpty(preReturnAmount)) {
				returnAmount = DoubleUtil.sub(returnAmount, DoubleUtil.strToDouble(preReturnAmount));
			}
		}
		lblOrderCount.setText(String.valueOf(size));
		lblPurchaseAmount.setText(DoubleUtil.getRoundStr(orderAmount));
		lblReturnAmount.setText(DoubleUtil.getRoundStr(returnAmount));
		
		return listEntity.getItemList().toArray();
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "采购订单";
	}
}
