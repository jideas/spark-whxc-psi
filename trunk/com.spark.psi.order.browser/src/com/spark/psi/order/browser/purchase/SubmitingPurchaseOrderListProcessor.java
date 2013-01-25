/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;
import com.spark.psi.publish.order.task.DeletePurchaseOrderTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderStatusTask;

/**
 * ���ύ�ɹ������б�����
 * 
 */
public class SubmitingPurchaseOrderListProcessor extends PurchaseOrderListProcessor<OrderItem> {

	public final static String ID_BUTTON_NEW_ORADER = "Button_New_Order";// ��������
	public final static String ID_BUTTON_SUBMIT_ORADER = "Button_Submit_Order";// �ύ����
	private Button btnCreateOrder;

	@Override
	public void process(Situation situation) {

		super.process(situation);

		btnCreateOrder = this.createControl(ID_BUTTON_NEW_ORADER, Button.class, JWT.NONE);
		btnCreateOrder.setText(" �������� ");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageControllerInstance pci = new PageControllerInstance("PurchasingGoodsListPage");
				MsgRequest request = new MsgRequest(pci, "�ɹ��嵥");
				getContext().bubbleMessage(request);
			}
		});
		Button btnSubmitOrder = this.createControl(ID_BUTTON_SUBMIT_ORADER, Button.class, JWT.NONE);
		// TODO ��������ʱ����Ҫ�˰�ť
		btnSubmitOrder.setVisible(false);
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
	 * ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Submit.name())) {
			getContext().handle(new UpdatePurchaseOrderStatusTask(GUID.valueOf(rowId), OrderAction.Submit));
			table.render();
		} else if (actionName.equals(Action.Delete.name())) {
			String title = "��ȷ��ɾ�����Ųɹ����룿";
			confirm(title, new Runnable() {

				public void run() {
					getContext().handle(
							new DeletePurchaseOrderTask(GUID.valueOf(rowId),
									com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Order));
					table.render();
				}
			});
		}
	}

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(0, Integer.MAX_VALUE, false, com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Order);
		key.setOrderStatus(OrderStatus.Submit, OrderStatus.Denied);
		key.setSearchText(searchText.getText());
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn()).getSortField());
		}
		// ����
		setLimitKeySort(key, tablestatus);

		listEntity = context.find(OrderListEntity.class, key);
		btnCreateOrder.setEnabled(0 >= listEntity.getItemList().size());
		return listEntity.getItemList().toArray();
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}