/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;

/**
 * �������Ĳɹ������б�����
 * 
 */
public class ApprovalingPurchaseOrderListProcessor extends
		PurchaseOrderListProcessor<OrderItem> {

	private OrderListEntity listEntity;

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Approval.name() };
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		OrderItem item = (OrderItem) element;
		return OrderAction.getItemActions(item.getActions());
	}

	/**
	 * ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Approval.name())) {
			MsgRequest request;
			if (OrderType.Purchase_Return == getItemObject(rowId)
					.getOrderType()) {
				//�˻�������棬���޸�
				PurchaseReturnInfo info = getContext().find(PurchaseReturnInfo.class,
						GUID.valueOf(rowId));
				PageControllerInstance pci = new PageControllerInstance(
						new PageController(
								PurchaseReturnSheetDetailProcessor.class,
								PurchaseReturnSheetDetailRender.class), info);
				request = new MsgRequest(pci, "�ɹ��˻���ϸ");
			} else {
				PurchaseOrderInfo info = getContext().find(PurchaseOrderInfo.class,
						GUID.valueOf(rowId));
				PageControllerInstance pci = new PageControllerInstance(
						new PageController(PurchaseOrderDetailProcessor.class,
								PurchaseOrderDetailRender.class), info);
				request = new MsgRequest(pci, "�ɹ���ϸ");
			}
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		// OrderListEntity, GetPurchaseOrderListKey
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setOrderStatus(OrderStatus.Approval_No);
		// key.setQueryScope(queryScope)
		// key.setQueryTerm(queryTerm)
		key.setSearchText(searchText.getText());
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn())
					.getSortField());
		}
		//����
		setLimitKeySort(key, tablestatus);
		
		listEntity = context.find(OrderListEntity.class, key);
		return listEntity.getItemList().toArray();
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}
