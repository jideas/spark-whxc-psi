/**
 * 
 */
package com.spark.psi.order.browser.sales;

import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.order.browser.common.OrderCheckInfoWindow;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;
import com.spark.psi.publish.inventory.key.GetOrderCheckInSheetItemKey;
import com.spark.psi.publish.order.entity.OrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnGoodsItem;
import com.spark.psi.publish.order.entity.SalesReturnInfo;
import com.spark.psi.publish.order.task.UpdateSalesReturnStatusTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnTask;

/**
 * 销售退货单明细处理器
 * 
 */
public class SalesReturnSheetDetailProcessor extends AbstractSalesReturnSheetDetailProcessor {

	public final static String ID_CustomerInfo_Area = "CustomerInfo_Area";
	public final static String ID_CustomerSelect_Button = "CustomerSelect_Button";
	public final static String ID_StoreList = "StoreList";
	public final static String ID_OrderStatusLabel = "OrderStatusLabel";
	public final static String ID_CheckInfoLabel = "CheckInfoLabel";

	SalesReturnInfo returnInfo;
	private PartnerInfo customerInfo; 

	protected Composite customerInfoArea;
	private LWComboList storeList;
	private Label customerSelectButton;
	private Label orderstatusLabel;
	private Label checkInfoLabel;

	@Override
	protected void addApproveAction(ActionEvent e) {
		getContext().handle(new UpdateSalesReturnStatusTask(returnInfo.getId(), OrderAction.Approval));
		getContext().bubbleMessage(new MsgResponse(true));
	}

	@Override
	protected void addExecutAction(ActionEvent e) {
		getContext().handle(new UpdateSalesReturnStatusTask(returnInfo.getId(), OrderAction.Execut));
	}

	@Override
	protected void addReturnAction(ActionEvent e, String cause) {
		getContext().handle(new UpdateSalesReturnStatusTask(returnInfo.getId(), OrderAction.Deny, cause));
	}

	@Override
	protected void addStopAction(ActionEvent e, String cause) {
		getContext().handle(new UpdateSalesReturnStatusTask(returnInfo.getId(), OrderAction.Stop, cause));
	}

	@Override
	protected void addSubmitAction(ActionEvent e) {
		getContext().handle(new UpdateSalesReturnStatusTask(returnInfo.getId(), OrderAction.Submit));
		getContext().bubbleMessage(new MsgResponse(true));
	}

	@Override
	protected OrderInfo getOrderInfo() {
		if (getArgument() instanceof SalesReturnInfo) {
			returnInfo = (SalesReturnInfo) getArgument();
		} else if (getArgument() instanceof String) {
			returnInfo = getContext().find(SalesReturnInfo.class, GUID.valueOf((String) getArgument()));
		}
		return returnInfo;
	}

	@Override
	protected SalesReturnGoodsItem[] initItemList() {
		return returnInfo.getReturnGoodsItems();
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		//
		storeList = createControl(ID_StoreList, LWComboList.class);
		if (storeList != null) {
			StoreSource storeSource = new StoreSource(true);
			storeList.getList().setSource(storeSource);
			storeList.getList().setInput(null);
		}
		//
		customerInfoArea = createControl(ID_CustomerInfo_Area, Composite.class);
		customerSelectButton = createControl(ID_CustomerSelect_Button, Label.class);
		orderstatusLabel = createControl(ID_OrderStatusLabel, Label.class);
		checkInfoLabel = createControl(ID_CheckInfoLabel, Label.class);
		//
		//
		this.customerInfo = this.returnInfo.getPartnerInfo(); 
		//
		updatePartnerInfo();

		//
		if (this.viewEnum.isIn(View.Create, View.Edit)) {
			customerSelectButton.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent event) {
					MsgRequest request = CommonSelectRequest
							.createSelectCustomerRequest(true, false, false, customerInfo.getId());
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
							customerInfo = getContext().find(PartnerInfo.class, returnValue);
							updatePartnerInfo();
							markDataChanged();
						}
					});
					getContext().bubbleMessage(request);
				}
			});
		} else {
			orderstatusLabel.setText("订单状态：" + returnInfo.getOrderStatus().getName());
			List<OrderCheckSheetItem> itemList = getContext().getList(OrderCheckSheetItem.class,
					new GetOrderCheckInSheetItemKey(returnInfo.getId()));
			if (itemList != null && itemList.size() > 0) {
				if (returnInfo.getOrderStatus().isIn(OrderStatus.CheckingIn_Part, OrderStatus.CheckedIn, OrderStatus.Stop,
						OrderStatus.finish)) {
					orderstatusLabel.setText(orderstatusLabel.getText() + "  ");
					checkInfoLabel.setText("查看入库情况");
					new OrderCheckInfoWindow(checkInfoLabel, returnInfo, itemList).bindTargetControl(checkInfoLabel);
				}
			}
		}

		//
		initPageButton();
	}

	private void updatePartnerInfo() {
		//
		customerInfoArea.clear();
		//
		fillPartnerInfoArea(customerInfoArea, "客户", customerInfo);
		//
		table.getParent().layout();
	}

	protected void initPageButton() {
		if (this.viewEnum.isIn(View.Create, View.Edit)) {
			// 添加材料
			addGoods();
			// 保存
			save();
			// // 保存并新建
			// saveAndCreate();
			// 提交
			addSubmit();
		} else {
			OrderAction[] actions = returnInfo.getActions();
			for (OrderAction oa : actions) {
				addButton(oa);
			}
		}
	}

	@Override
	protected String getSheetTitle() {
		return "销售退货单";
	}

	@Override
	protected final boolean addSaveAction(boolean haveTitle) {
		if (!this.validateInput()) {
			return false;
		}
		UpdateSalesReturnTask task = new UpdateSalesReturnTask();

		task.setId(returnInfo.getId());
		task.setPartnerId(customerInfo.getId());

		fillTaskData(task);
		SalesReturnInfo info = getContext().find(SalesReturnInfo.class, returnInfo.getId());
		if (null == info) {
			getContext().handle(task, UpdateSalesReturnTask.Method.Create);
			SalesReturnInfo sri = getContext().find(SalesReturnInfo.class, task.getId());
			if (null != sri) {
				returnInfo = sri;
			}
		} else {
			getContext().handle(task, UpdateSalesReturnTask.Method.Update);
		}
		if (haveTitle) {
			hint("保存成功！");
		}
		getContext().bubbleMessage(new MsgResponse(false));
		resetDataChangedstatus();
		return true;
	}

}
