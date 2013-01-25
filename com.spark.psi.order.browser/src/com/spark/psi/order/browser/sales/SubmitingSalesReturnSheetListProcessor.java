/**
 * 
 */
package com.spark.psi.order.browser.sales;

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
import com.spark.psi.order.browser.internal.SalesReturnInfoImpl;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.entity.SalesReturnInfo;
import com.spark.psi.publish.order.key.GetOrderListKey;
import com.spark.psi.publish.order.key.GetSalesOrderListKey;
import com.spark.psi.publish.order.task.DeleteSalesOrderTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnStatusTask;

/**
 * 待提交销售退货单列表处理器 
 * 
 * 销售退货
 *
 */
public class SubmitingSalesReturnSheetListProcessor extends SalesOrderListProcessor<OrderItem> {
	
	//申请退货
	public final static String ID_BUTTON_SALESRETURN = "Button_SalesReturn";
	//提交申请
	public final static String ID_BUTTON_SUBMIT_SALESRETURN = "Button_Submit_SalesReturn";
	//删除申请
	public final static String ID_BUTTON_DELETE_SALESRETURN = "Button_Delete_SalesReturn";
	private Button btnSubmitSalesReturn, btnDeleteSalesReturn;
	@Override
	public void process(Situation situation) {
		
		super.process(situation);
		
		Button btnSalesReturn = this.createControl(ID_BUTTON_SALESRETURN, Button.class,JWT.NONE);
		btnSalesReturn.setText(" 申请退货 ");
		btnSalesReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectCustomerRequest(true, false, false);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						PageController pc = new PageController(
								SalesReturnSheetDetailProcessor.class,
								SalesReturnSheetDetailRender.class);
						PageControllerInstance pci = new PageControllerInstance(pc,
								initOrderInfo((GUID)returnValue, (GUID)returnValue2, (GUID)returnValue3));
						MsgRequest request = new MsgRequest(pci, "销售退货单明细");
						request.setResponseHandler(new ResponseHandler() {
							
							public void handle(Object returnValue, Object returnValue2,
									Object returnValue3, Object returnValue4) {
								table.render();
							}
						});
						getContext().bubbleMessage(request);
					}

					private SalesReturnInfo initOrderInfo(GUID orderId,
							GUID contactId, GUID adressId) {
						SalesReturnInfoImpl info = new SalesReturnInfoImpl();
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
		
		btnSubmitSalesReturn = this.createControl(ID_BUTTON_SUBMIT_SALESRETURN, Button.class,JWT.NONE);
		btnSubmitSalesReturn.setText(" 提交申请 ");
		btnSubmitSalesReturn.setEnabled(false);
		btnSubmitSalesReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(String rowId : table.getSelections()){
					getContext().handle(new UpdateSalesReturnStatusTask(GUID.valueOf(rowId), OrderAction.Submit));
				}
				table.render();
			}
		});
		
		btnDeleteSalesReturn = this.createControl(ID_BUTTON_DELETE_SALESRETURN, Button.class,JWT.NONE);
		btnDeleteSalesReturn.setText(" 删除申请 ");
		btnDeleteSalesReturn.setEnabled(false);
		btnDeleteSalesReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm("确认删除选择的退货申请单？", new Runnable() {
					public void run() {
						for(String rowId : table.getSelections()){
							getContext().handle(new DeleteSalesOrderTask(GUID.valueOf(rowId), com.spark.psi.publish.order.key.GetOrderListKey.OrderType.Return));
						}
						table.render();
					}
				});
			}
		});
		table.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				String[] rowIds = table.getSelections();
				if(null == rowIds || 0 == rowIds.length){
					btnSubmitSalesReturn.setEnabled(false);
					btnDeleteSalesReturn.setEnabled(false);
				}
				else{
					btnSubmitSalesReturn.setEnabled(true);
					btnDeleteSalesReturn.setEnabled(true);
					
				}
			}
		});
	}

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		GetSalesOrderListKey key = new GetSalesOrderListKey(0, Integer.MAX_VALUE, false, GetOrderListKey.OrderType.Return);
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
		btnSubmitSalesReturn.setEnabled(false);
		btnDeleteSalesReturn.setEnabled(false);
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