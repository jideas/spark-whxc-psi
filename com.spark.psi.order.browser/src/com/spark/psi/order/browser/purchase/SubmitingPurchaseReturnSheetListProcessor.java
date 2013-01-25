package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.order.browser.internal.PurchaseReturnInfoImpl;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;
import com.spark.psi.publish.order.key.GetOrderListKey.OrderType;
import com.spark.psi.publish.order.task.DeletePurchaseOrderTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnStatusTask;

/**
 * 待提交采购退货单列表处理器
 * 
 * 采购退货
 * 
 */
public class SubmitingPurchaseReturnSheetListProcessor extends
		PurchaseOrderListProcessor<OrderItem> {

	// 申请退货
	public final static String ID_BUTTON_APPLYRETURN = "Button_ApplyReturn";
	// 提交申请
	public final static String ID_BUTTON_SUBMIT_APPLYRETURN = "Button_Submit_ApplyReturn";
	// 删除申请
	public final static String ID_BUTTON_DELETE_APPLYRETURN = "Button_Delete_ApplyReturn";

	private Button btnSubmitApplyReturn,btnDeleteApplyReturn;
	@Override
	public void process(Situation situation) {

		super.process(situation);

		Button btnApplyReturn = this.createControl(ID_BUTTON_APPLYRETURN,
				Button.class, JWT.NONE);
		btnApplyReturn.setText(" 申请退货 ");
		btnApplyReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest
						.createSelectSupplierRequest();
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						PageController pc = new PageController(
								PurchaseReturnSheetDetailProcessor.class,
								PurchaseReturnSheetDetailRender.class);
						PageControllerInstance pci = new PageControllerInstance(
								pc, initReturnInfo(returnValue, returnValue2,
										returnValue3));
						MsgRequest request = new MsgRequest(pci, "采购退货单明细");
						request.setResponseHandler(new ResponseHandler() {

							public void handle(Object returnValue,
									Object returnValue2, Object returnValue3,
									Object returnValue4) {
								table.render();
							}
						});
						getContext().bubbleMessage(request);
					}
				});
				getContext().bubbleMessage(request);
			}

			private PurchaseReturnInfo initReturnInfo(Object returnValue,
					Object returnValue2, Object returnValue3) {
				// PartnerInfo partner = getContext().find(PartnerInfo.class,
				// returnValue);
				SupplierInfo supp = getContext().find(SupplierInfo.class,
						returnValue);
				PurchaseReturnInfoImpl info = new PurchaseReturnInfoImpl(supp);
				if(null != returnValue2){
					info.setContactBookInfo(getContext().find(ContactBookInfo.class,
							returnValue2));
				}
				if(null != returnValue3){
					info.setConsigneeInfo(getContext().find(
							ContactBookInfo.class, returnValue3));
				}
				info.setDeliveryDate(System.currentTimeMillis());
				info.setId(getContext().newRECID());
				return info;
			}

		});

		btnSubmitApplyReturn = this.createControl(
				ID_BUTTON_SUBMIT_APPLYRETURN, Button.class, JWT.NONE);
		btnSubmitApplyReturn.setText(" 提交申请 ");
		btnSubmitApplyReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String rowId : table.getSelections()) {
					getContext().handle(
							new UpdatePurchaseReturnStatusTask(GUID
									.valueOf(rowId), OrderAction.Submit));
				}
				table.render();
			}
		});

		btnDeleteApplyReturn = this.createControl(
				ID_BUTTON_DELETE_APPLYRETURN, Button.class, JWT.NONE);
		btnDeleteApplyReturn.setText(" 删除申请 ");
		btnDeleteApplyReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "您确认删除此张退货申请？";
				confirm(
						title, new Runnable() {

							public void run() {
								for (String rowId : table.getSelections()) {
									getContext().handle(
											new DeletePurchaseOrderTask(GUID
													.valueOf(rowId), com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Return));
								}
								table.render();
							}
						});
			}
		});
		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String[] rowIds = table.getSelections();
				if (null == rowIds || 0 == rowIds.length) {
					btnSubmitApplyReturn.setEnabled(false);
					btnDeleteApplyReturn.setEnabled(false);
				} else {
					btnSubmitApplyReturn.setEnabled(true);
					btnDeleteApplyReturn.setEnabled(true);
				}
			}
		});

	}

	private OrderListEntity listEntity;

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Submit.name(), Action.Delete.name() };
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
		if (actionName.equals(Action.Submit.name())) {
			getContext().handle(
					new UpdatePurchaseReturnStatusTask(GUID.valueOf(rowId),
							OrderAction.Submit));
			table.render();
		} else if (actionName.equals(Action.Delete.name())) {
			String title = "您确认删除此张退货申请？";
			confirm(
					title, new Runnable() {

						public void run() {
							getContext().handle(
									new DeletePurchaseOrderTask(GUID
											.valueOf(rowId), com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Return));
							table.render();
						}
					});
		}
	}

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		// OrderListEntity, GetPurchaseOrderListKey
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(0, Integer.MAX_VALUE, false,
				OrderType.Return);
		key.setOrderStatus(OrderStatus.Submit, OrderStatus.Denied);
		 
		 key.setSearchText(searchText.getText());
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn())
					.getSortField());
		}
		//排序
		setLimitKeySort(key, tablestatus);
		
		listEntity = context.find(OrderListEntity.class, key);
		btnSubmitApplyReturn.setEnabled(false);
		btnDeleteApplyReturn.setEnabled(false);
		return listEntity.getItemList().toArray();
	
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}