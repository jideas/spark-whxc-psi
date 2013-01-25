/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.invoke.AsyncTask;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.events.SelectionChangingEvent;
import com.jiuqi.dna.ui.wt.events.SelectionChangingListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.order.browser.common.OrderCheckInfoWindow;
import com.spark.psi.order.browser.util.OrderDetailProcessor;
import com.spark.psi.order.browser.util.PurchaseUtil;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;
import com.spark.psi.publish.inventory.key.GetOrderCheckOutSheetItemKey;
import com.spark.psi.publish.order.entity.OrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnGoodsItem;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnStatusTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnTask;

/**
 * 采购退货单明细处理器
 * 
 */
public class PurchaseReturnSheetDetailProcessor extends OrderDetailProcessor<PurchaseReturnGoodsItem> {
	public final static String ID_SupplierInfo_Area = "SupplierInfo_Area";
	public final static String ID_DeliveryInfo_Area = "DeliveryInfo_Area";
	public final static String ID_StoreList = "StoreList";
	public final static String ID_SupplierSelect_Button = "SupplierSelectButton";
	public final static String ID_OrderStatusLabel = "OrderStatusLabel";
	public final static String ID_CheckInfoLabel = "CheckInfoLabel";

	PurchaseReturnInfo returnInfo;
	private PartnerInfo supplierInfo; 

	private Button addGoodsButton;
	private LWComboList storeList;
	private Composite supplierInfoArea;
	private Composite deliveryInfoArea;
	private Label supplierSelectButton;
	private Label orderstatusLabel;
	private Label checkInfoLabel;

	@Override
	protected void addApproveAction(ActionEvent e) {
		getContext().handle(new UpdatePurchaseReturnStatusTask(returnInfo.getId(), OrderAction.Approval));
		getContext().bubbleMessage(new MsgResponse(true));
	}

	@Override
	protected void addExecutAction(ActionEvent e) {
		getContext().handle(new UpdatePurchaseReturnStatusTask(returnInfo.getId(), OrderAction.Execut));
	}

	@Override
	protected void addReturnAction(ActionEvent e, String cause) {
		getContext().handle(new UpdatePurchaseReturnStatusTask(returnInfo.getId(), OrderAction.Deny, cause));
	}

	@Override
	protected void addStopAction(ActionEvent e, String cause) {
		getContext().handle(new UpdatePurchaseReturnStatusTask(returnInfo.getId(), OrderAction.Stop, cause));
	}

	@Override
	protected void addSubmitAction(ActionEvent e) {
		getContext().handle(new UpdatePurchaseReturnStatusTask(returnInfo.getId(), OrderAction.Submit));
		getContext().bubbleMessage(new MsgResponse(true));
	}

	private void addGoods() {
		addGoodsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectMaterialsRequest(GUID.valueOf(storeList.getText()), false);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						MaterialsItemInfo[] itemList = (MaterialsItemInfo[]) returnValue;
						for (MaterialsItemInfo item : itemList) {
							table.addRow(PurchaseUtil.getPurchaseReturnGoodsItem(item)); // XXX：表格控件提供插入多行数据接口后修改，目前效率
						}
						table.renderUpate();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	protected void save() {
		this.createButton(" 保存 ").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSaveAction(true);
			}
		});

	}

	@Override
	protected OrderInfo getOrderInfo() {
		if (getArgument() instanceof PurchaseReturnInfo) {
			returnInfo = (PurchaseReturnInfo) getArgument();
		} else if (getArgument() instanceof String) {
			returnInfo = getContext().find(PurchaseReturnInfo.class, GUID.valueOf((String) getArgument()));
		}
		return returnInfo;
	}

	@Override
	public void process(Situation context) {
		super.process(getContext());
		supplierInfoArea = createControl(ID_SupplierInfo_Area, Composite.class);
		deliveryInfoArea = createControl(ID_DeliveryInfo_Area, Composite.class);
		supplierSelectButton = createControl(ID_SupplierSelect_Button, Label.class);
		orderstatusLabel = createControl(ID_OrderStatusLabel, Label.class);
		checkInfoLabel = createControl(ID_CheckInfoLabel, Label.class);
		//
		this.supplierInfo = this.returnInfo.getPartnerInfo(); 
		updatePartnerInfo();

		//
		if (this.viewEnum.isIn(View.Create, View.Edit)) {
			addGoodsButton = this.createControl(ID_BUTTON_ADDGOODS, Button.class);
			addGoodsButton.setEnabled(false);
			// 添加材料
			addGoods();
			// 保存
			save();
			// 提交
			addSubmit();
			//
			supplierSelectButton.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent event) {
					MsgRequest request = CommonSelectRequest.createSelectSupplierRequest(supplierInfo.getId());
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
							supplierInfo = getContext().find(PartnerInfo.class, returnValue);
							updatePartnerInfo();
							markDataChanged();
						}
					});
					getContext().bubbleMessage(request);
				}
			});
			//
			storeList = createControl(ID_StoreList, LWComboList.class);
			StoreSource storeSource = new StoreSource(true);
			storeList.getList().setSource(storeSource);
			storeList.getList().setInput(null);
			storeList.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent event) {
					addGoodsButton.setEnabled(!StringUtils.isEmpty(storeList.getText()));
				}
			});
			PurchaseReturnGoodsItem[] items = returnInfo.getGoodsItems();
			if (items == null || items.length == 0) {
				if (storeSource.getSize() == 1) {
					storeList.setSelection(storeSource.getFirstStoreId());
				}
			} else {
				storeList.setSelection(returnInfo.getStoreId());
			}
			storeList.getList().addSelectionChangingListener(new SelectionChangingListener() {
				public void selectionChanging(final SelectionChangingEvent event) {
					event.doIt = false;
					storeList.setPanelVisible(false);
					if (event.target.equals(storeList.getText())) {
						return;
					}
					Runnable selectRunnable = new Runnable() {
						public void run() {
							storeList.setSelection((String) event.target);
							for (String rowId : table.getAllRowId()) {
								table.removeRow(rowId);
							}
							table.renderUpate();
							markDataChanged();
						}
					};
					if (table.getAllRowId().length > 0) {
						confirm("切换退货仓库将清空所有已选退货材料，确定切换？", selectRunnable);
					} else {
						selectRunnable.run();
					}
				}
			});
		} else {
			OrderAction[] actions = returnInfo.getActions();
			for (OrderAction oa : actions) {
				addButton(oa);
			}
			//
			orderstatusLabel.setText("订单状态：" + returnInfo.getOrderStatus().getName());
			List<OrderCheckSheetItem> itemList = getContext().getList(OrderCheckSheetItem.class,
					new GetOrderCheckOutSheetItemKey(returnInfo.getId()));
			if (itemList != null && itemList.size() > 0) {
				if (returnInfo.getOrderStatus().isIn(OrderStatus.CheckingOut_Part, OrderStatus.CheckedOut, OrderStatus.Stop,
						OrderStatus.finish)) {
					orderstatusLabel.setText(orderstatusLabel.getText() + "  ");
					checkInfoLabel.setText("查看出库情况");
					new OrderCheckInfoWindow(checkInfoLabel, returnInfo, itemList).bindTargetControl(checkInfoLabel);
				}
			}
		}
	}

	private void updatePartnerInfo() {
		supplierInfoArea.clear();
		deliveryInfoArea.clear();
		//
		fillPartnerInfoArea(supplierInfoArea, "供应商", supplierInfo);
		fillDeliveryInfoArea(deliveryInfoArea);
		//
		table.getParent().layout();
	}

	@Override
	public String getValue(Object element, String columnName) {
		if (this.viewEnum.isIn(View.Create, View.Edit)) {
			PurchaseReturnGoodsItem item = (PurchaseReturnGoodsItem) element;
			if (Cloumns.ReturnPrice.name().equals(columnName)) {
				return String.valueOf(item.getPrice());
			}
			if (Cloumns.ReturnCount.name().equals(columnName)) {
				return String.valueOf(item.getCount());
			}
			if (Cloumns.ReturnAmount.name().equals(columnName)) {
				return item.getAmount() + "";
			}
		}
		return null;
	}

	@Override
	protected PurchaseReturnGoodsItem[] initItemList() {
		if (null != returnInfo) {
			return returnInfo.getGoodsItems();
		}
		return null;
	}

	@Override
	protected void lookInventory(String rowId, Point location) {
		inventoryInfoWindow.refresh(getGoodsItem(rowId).getGoodsItemId(), null, location, false);
	}

	@Override
	protected String getSheetTitle() {
		return "采购退货单";
	}

	@Override
	protected boolean addSaveAction(boolean haveTitle) {
		if (!this.validateInput()) {
			return false;
		}
		UpdatePurchaseReturnTask task = new UpdatePurchaseReturnTask(); 
		task.setMemo(memoText.getText());
		task.setPartnerId(supplierInfo.getId());

		List<com.spark.psi.publish.order.task.UpdatePurchaseReturnTask.PurchaseReturnGoodsItem> list = new ArrayList<com.spark.psi.publish.order.task.UpdatePurchaseReturnTask.PurchaseReturnGoodsItem>();
		double totalAmount = 0;
		GUID storeId = GUID.valueOf(storeList.getText());
		task.setStoreId(storeId);
		for (String rowId : table.getAllRowId()) {
			PurchaseReturnGoodsItem item = getGoodsItem(rowId);
			// task明细
			UpdatePurchaseReturnTask.PurchaseReturnGoodsItem taskItem = new UpdatePurchaseReturnTask.PurchaseReturnGoodsItem();
			taskItem.setCount(Double.valueOf(table.getEditValue(rowId, Cloumns.ReturnCount.name())[0]));
			taskItem.setPrice(Double.valueOf(table.getEditValue(rowId, Cloumns.ReturnPrice.name())[0]));
			taskItem.setGoodsItemId(item.getGoodsItemId());
			taskItem.setAmount(taskItem.getCount() * taskItem.getPrice());
			totalAmount += taskItem.getPrice() * taskItem.getCount();
			list.add(taskItem);
		}
		task.setPurchaseReturnGoodsItems(list
				.toArray(new com.spark.psi.publish.order.task.UpdatePurchaseReturnTask.PurchaseReturnGoodsItem[list.size()]));
		task.setAmount(totalAmount);
		task.setId(returnInfo.getId());
		if (null == getContext().find(PurchaseReturnInfo.class, returnInfo.getId())) {
			if (haveTitle) {
				getContext().handle(task, UpdatePurchaseReturnTask.Method.Create);
			} else {
				AsyncTask<UpdatePurchaseReturnTask, UpdatePurchaseReturnTask.Method> async = getContext().asyncHandle(task,
						UpdatePurchaseReturnTask.Method.Create);
				try {
					getContext().waitFor(async);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			getContext().handle(task, UpdatePurchaseReturnTask.Method.Update);
		}
		// returnInfo
		if (haveTitle) {
			hint("保存成功！");
		}
		getContext().bubbleMessage(new MsgResponse(false));
		resetDataChangedstatus();
		return true;
	}

	@Override
	protected String getTableNullTitle() {
		return "请添加需要退货的材料";
	}

	@Override
	protected String validateTitle(com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns columnsEnum,
			com.spark.psi.order.browser.util.OrderDetailProcessor.ValiDateErrorEnum error) {
		switch (columnsEnum) {
		// case ReturnStore:
		// switch (error) {
		// case NULL:
		// return "退货仓库不能为空！";
		// case FormatException:
		// return "系统错误，请及时与管理员联系";
		// default:
		// return null;
		// }
		case ReturnCount:
			switch (error) {
			case NULL:
				return "退货数量不能为空";
			case Less_than_zero:
				return "退货数量必须大于0";
			case Zero:
				return "退货数量必须大于0";
			case Greater_than_Max:
				return "本次退货数量过大";
			case FormatException:
				return "数字格式不对，正确样式应如：97.9或97";
			default:
				return null;
			}
		case ReturnPrice:
			switch (error) {
			case NULL:
				return "退货单价不能为空";
			case Less_than_zero:
				return "退货单价不能小于0";
			case Greater_than_Max:
				return "本次退货单价过大";
			case FormatException:
				return "数字格式不对，正确样式应如：97.97";
			default:
				return null;
			}
		case ReturnAmount:
			switch (error) {
			case NULL:
				return "退货金额不能为空";
			case Less_than_zero:
				return "退货金额不能小于0";
			case Greater_than_Max:
				return "本次退货金额过大";
			case FormatException:
				return "数字格式不对，正确样式应如：97.97";
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
