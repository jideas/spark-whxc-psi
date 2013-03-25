/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnListEntity;
import com.spark.psi.publish.onlinereturn.key.GetOnlineReturnListKey;
import com.spark.psi.publish.onlinereturn.key.GetOnlineReturnListKey.OnlineReturnTab;
import com.spark.psi.publish.onlinereturn.task.DeleteOnlineReturnTask;

/**
 * 待提交销售退货单列表处理器
 * 
 * 销售退货
 * 
 */
public class SubmitingOnlineReturnSheetListProcessor extends OnlineReturnSheetListProcessor {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "ID_Search";
	// 申请退货
	public final static String ID_BUTTON_SALESRETURN = "Button_SalesReturn";
	// 提交申请
	public final static String ID_BUTTON_SUBMIT_SALESRETURN = "Button_Submit_SalesReturn";
	// 删除申请
	public final static String ID_BUTTON_DELETE_SALESRETURN = "Button_Delete_SalesReturn";
//	private Button btnSubmitSalesReturn, btnDeleteSalesReturn;

	private Label countLabel    = null;
	private SSearchText2 search = null;
	
	@Override
	public void process(Situation situation) {

		super.process(situation);

		Button btnSalesReturn = this.createControl(ID_BUTTON_SALESRETURN, Button.class, JWT.NONE);
		btnSalesReturn.setText(" 申请退货 ");
		
		countLabel = createLabelControl(ID_Label_Count);
		search = createControl(ID_Search, SSearchText2.class);
		
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		
		btnSalesReturn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// step 1 选择要退货的订单
				
				PageController pc = new PageController(OnlineOrderForSelectListProcessor.class,
						OnlineOrderForSelectListRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				WindowStyle wStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
				wStyle.setSize(850, 450);
				MsgRequest request = new MsgRequest(pci, "选择订单", wStyle);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						// step 2 填写退货信息
						if (null == returnValue) {
							// alert("操作出现错误。");
							return;
						}
						GUID orderId = (GUID)returnValue;
						PageController pc = new PageController(OnlineReturnSheetDetailProcessor.class, OnlineReturnSheetDetailRender.class);
						PageControllerInstance pci = new PageControllerInstance(pc, orderId, OnlineReturnSheetDetailProcessor.Type.New);
						MsgRequest request = new MsgRequest(pci, "新增网上退货单");
						request.setResponseHandler(new ResponseHandler() {
							
							public void handle(Object returnValue, Object returnValue2,
									Object returnValue3, Object returnValue4) {
								table.render();
							}
						});
						getContext().bubbleMessage(request);
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		

//		btnSubmitSalesReturn = this.createControl(ID_BUTTON_SUBMIT_SALESRETURN, Button.class, JWT.NONE);
//		btnSubmitSalesReturn.setText(" 提交申请 ");
//		btnSubmitSalesReturn.setEnabled(false);
//		btnSubmitSalesReturn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				for (String rowId : table.getSelections()) {
////					getContext().handle(
////							new UpdateSalesReturnStatusTask(GUID.valueOf(rowId), OrderAction.Submit));
////				}
////				table.render();
//				// TODO 列表 提交
//				
//			}
//		});
//
//		btnDeleteSalesReturn = this.createControl(ID_BUTTON_DELETE_SALESRETURN, Button.class, JWT.NONE);
//		btnDeleteSalesReturn.setText(" 删除申请 ");
//		btnDeleteSalesReturn.setEnabled(false);
//		btnDeleteSalesReturn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				confirm("确认删除选择的退货申请单？", new Runnable() {
//					public void run() {
//						for (String rowId : table.getSelections()) {
//							getContext().handle(new DeleteOnlineReturnTask(GUID.valueOf(rowId)));
//						}
//						table.render();
//					}
//				});
//			}
//		});
//		table.addSelectionListener(new SelectionListener() {
//			public void widgetSelected(SelectionEvent e) {
//				String[] rowIds = table.getSelections();
//				if (null == rowIds || 0 == rowIds.length) {
//					btnSubmitSalesReturn.setEnabled(false);
//					btnDeleteSalesReturn.setEnabled(false);
//				} else {
//					btnSubmitSalesReturn.setEnabled(true);
//					btnDeleteSalesReturn.setEnabled(true);
//
//				}
//			}
//		});
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Submit.name(), Action.Delete.name() };
	}

	
	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Submit.name(), Action.Delete.name() };
	}

	@Override
	public String getElementId(Object element) {
		OnlineReturnItem item = (OnlineReturnItem)element;
		return item.getId().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetOnlineReturnListKey key = new GetOnlineReturnListKey(0, Integer.MAX_VALUE, true, OnlineReturnTab.Submiting);
		key.setSearchText(search.getText());
		OnlineReturnListEntity listEntity = context.find(OnlineReturnListEntity.class, key);
		if (null == listEntity) return null;
		countLabel.setText("" + listEntity.getTotalCount());
		return listEntity.getItemList().toArray();
	}

	@Override
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)
				|| Action.Submit.name().equals(actionName)) {
			PageController pc = new PageController(OnlineReturnSheetDetailProcessor.class, OnlineReturnSheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId), OnlineReturnSheetDetailProcessor.Type.Detail);
			MsgRequest request = new MsgRequest(pci, "网上退货单详情");
			request.setResponseHandler(new ResponseHandler() {
				
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		} else if (Action.Delete.name().equals(actionName)){
			alert("确定要删除该记录吗？", new Runnable() {
				
				public void run() {
					DeleteOnlineReturnTask task = new DeleteOnlineReturnTask(GUID.tryValueOf(rowId));
					getContext().handle(task);
					table.render();
				}
			});
		}
	}
	
	
}