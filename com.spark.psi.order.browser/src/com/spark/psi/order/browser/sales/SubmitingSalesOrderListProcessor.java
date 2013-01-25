/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.core.exception.AbortException;
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
import com.spark.psi.order.browser.internal.SalesOrderInfoImpl;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.key.GetOrderListKey;
import com.spark.psi.publish.order.key.GetSalesOrderListKey;
import com.spark.psi.publish.order.task.UpdateSalesOrderStatusTask;

/**
 * 待提交销售订单列表处理器
 *
 */
public class SubmitingSalesOrderListProcessor extends SalesOrderListProcessor<OrderItem> {


	public final static String ID_BUTTON_NEW_ORDER = "Button_New_Order";
	public final static String ID_BUTTON_SUBMIT_ORDER = "Button_Submit_Order";
	private final static String batch_submit_error = "批量订单提交失败，请单个订单提交！";
	
	private Button btnSubmitOrder;
	@Override
	public void process(Situation situation) {
		
		super.process(situation);
		
		Button btnNewOrder = this.createControl(ID_BUTTON_NEW_ORDER, Button.class,JWT.NONE);
		btnNewOrder.setText(" 新增订单 ");
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectCustomerRequest(true, true, true);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						PageController pc = new PageController(
								SalesOrderDetailProcessor.class,
								SalesOrderDetailRender.class);
						PageControllerInstance pci = new PageControllerInstance(pc,
								initOrderInfo((GUID)returnValue, (GUID)returnValue2, (GUID)returnValue3));
						MsgRequest request = new MsgRequest(pci, "销售订单明细");
						request.setResponseHandler(new ResponseHandler() {
							
							public void handle(Object returnValue, Object returnValue2,
									Object returnValue3, Object returnValue4) {
								table.render();
							}
						});
						getContext().bubbleMessage(request);
					}

					private SalesOrderInfo initOrderInfo(GUID orderId,
							GUID contactId, GUID adressId) {
						SalesOrderInfoImpl info = new SalesOrderInfoImpl();
						CustomerInfo customer = getContext().find(CustomerInfo.class, orderId);
						if(null != contactId){
							ContactBookInfo contact = getContext().find(ContactBookInfo.class, contactId);
							info.setContactBookInfo(contact);
						}
						if(null != adressId){
							ContactBookInfo adress = getContext().find(ContactBookInfo.class, adressId);
							info.setConsigneeInfo(adress);
						}
						info.setId(getContext().newRECID());
						info.setOrderType(OrderType.PLAIN);
						info.setPartnerInfo(customer);
						return info;
					}
				});
				getContext().bubbleMessage(request);
			}
		});		
		btnSubmitOrder = this.createControl(ID_BUTTON_SUBMIT_ORDER, Button.class,JWT.NONE);
		btnSubmitOrder.setText(" 提交订单 ");
		btnSubmitOrder.setEnabled(false);
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowIds = table.getSelections();
				try{
					for(String rowId : rowIds){
							getContext().handle(
								new UpdateSalesOrderStatusTask(GUID.valueOf(rowId),
										OrderAction.Submit));
					}
				}catch(AbortException abort){
					alert(batch_submit_error);
					table.render();
				}
				table.render();
			}
		});
		table.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				String[] rowIds = table.getSelections();
				if(null == rowIds || 0 == rowIds.length){
					btnSubmitOrder.setEnabled(false);
				}
				else{
					btnSubmitOrder.setEnabled(true);
				}
			}
		});
			
	}

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		GetSalesOrderListKey key = new GetSalesOrderListKey(0, Integer.MAX_VALUE, false, GetOrderListKey.OrderType.Order);
		key.setOrderStatus(OrderStatus.Submit, OrderStatus.Denied);
//		key.setQueryScope(queryScope)
//		key.setQueryTerm(queryTerm)
		key.setSearchText(searchText.getText());
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn())
					.getSortField());
		}
		//排序
		setLimitKeySort(key, tablestatus);
		
		OrderListEntity entity = getContext().find(OrderListEntity.class, key);
		btnSubmitOrder.setEnabled(false);
		return entity.getItemList().toArray();
	}
	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Submit.name(), Action.Delete.name()};
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}
