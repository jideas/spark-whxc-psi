/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.common.utils.excel.DoubleString;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.FormPrintEntity;
import com.spark.psi.base.browser.PSIPrinter;
import com.spark.psi.base.browser.PrintColumn;
import com.spark.psi.order.browser.common.OrderCheckInfoWindow;
import com.spark.psi.order.browser.util.OrderDetailProcessor;
import com.spark.psi.order.browser.util.PurchaseUtil;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;
import com.spark.psi.publish.inventory.key.GetOrderCheckInSheetItemKey;
import com.spark.psi.publish.order.entity.OrderGoodsItem;
import com.spark.psi.publish.order.entity.OrderInfo;
import com.spark.psi.publish.order.entity.PurchaseOrderGoodsItem;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderStatusTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask;

/**
 * 采购订单明细处理器
 * 
 */
public class PurchaseOrderDetailProcessor extends OrderDetailProcessor<PurchaseOrderGoodsItem> {

	public final static String ID_SupplierInfo_Area = "SupplierInfo_Area";
	public final static String ID_DeliveryInfo_Area = "DeliveryInfo_Area";
	public final static String ID_DeliveryDate_Label = "DeliveryDate_Label";
	public final static String ID_SourceName_Label = "SourceName_Label";
	public final static String ID_OrderStatusLabel = "OrderStatusLabel";
	public final static String ID_CheckInfoLabel = "CheckInfoLabel";
	public static final String ID_Area_Hide = "Area_Hide";
	
	PurchaseOrderInfo orderInfo;

	private Composite supplierInfoArea;
	private Composite deliveryInfoArea;
	private Label deliveryDateLabel;
	private Label sourceNameLabel;
	private Label orderstatusLabel;
	private Label checkInfoLabel;

	@Override
	public void process(Situation context) {
		super.process(context);
		supplierInfoArea = createControl(ID_SupplierInfo_Area, Composite.class);
		deliveryInfoArea = createControl(ID_DeliveryInfo_Area, Composite.class);
		deliveryDateLabel = createControl(ID_DeliveryDate_Label, Label.class);
		sourceNameLabel = createControl(ID_SourceName_Label, Label.class);
		orderstatusLabel = createControl(ID_OrderStatusLabel, Label.class);
		checkInfoLabel = createControl(ID_CheckInfoLabel, Label.class);
		//
		fillPartnerInfoArea(supplierInfoArea, "供应商", orderInfo.getPartnerInfo());
		fillDeliveryInfoArea(deliveryInfoArea);
		sourceNameLabel.setText((getContext().find(StoreInfo.class, orderInfo.getSourceId()) != null ? "仓库：" : "直供客户：")
				+ orderInfo.getSourceName() + "   ");
		deliveryDateLabel.setText("   交货日期：" + DateUtil.dateFromat(orderInfo.getDeliveryDate()));
		//
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
			OrderAction[] actions = orderInfo.getActions();
			for (OrderAction oa : actions) {
				addButton(oa);
			}
			//
			orderstatusLabel.setText("订单状态：" + orderInfo.getOrderStatus().getName());
			List<OrderCheckSheetItem> itemList = getContext().getList(OrderCheckSheetItem.class,
					new GetOrderCheckInSheetItemKey(orderInfo.getId()));
			if (itemList != null && itemList.size() > 0) {
				if (orderInfo.getOrderStatus().isIn(OrderStatus.CheckingIn_Part, OrderStatus.CheckedIn,
						OrderStatus.Stop, OrderStatus.finish)) {
					orderstatusLabel.setText(orderstatusLabel.getText() + "  ");
					checkInfoLabel.setText("查看入库情况");
					new OrderCheckInfoWindow(checkInfoLabel, orderInfo, itemList).bindTargetControl(checkInfoLabel);
				}
			}
		}
	}

	@Override
	protected OrderInfo getOrderInfo() {
		if (null != getArgument()) {
			if (getArgument() instanceof PurchaseOrderInfo) {
				orderInfo = (PurchaseOrderInfo) getArgument();
			} else if (getArgument() instanceof String) {
				orderInfo = getContext().find(PurchaseOrderInfo.class, GUID.valueOf((String) getArgument()));
			}
		}
		return orderInfo;
	}

	@Override
	protected PurchaseOrderGoodsItem[] initItemList() {
		return orderInfo.getGoodsItems();
	}

	// =-========================按钮功能区================================
	private void addGoods() {
		this.createControl(ID_BUTTON_ADDGOODS, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectGoodsRequest(true);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						GoodsItemInfo[] itemList = (GoodsItemInfo[]) returnValue;
						for (GoodsItemInfo item : itemList) {
							PurchaseOrderGoodsItem gi = PurchaseUtil.getPurchaseOrderGoodsItem(item);
							table.addRow(gi); // XXX：表格控件提供插入多行数据接口后修改，目前效率
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
	public String getValue(Object element, String columnName) {
		if (View.Look == viewEnum) {
			return null;
		}
		PurchaseOrderGoodsItem item = (PurchaseOrderGoodsItem) element;
		if (Cloumns.Count.name().equals(columnName)) {
			return item.getCount() + "";
		}
		if (Cloumns.Price.name().equals(columnName)) {
			return item.getPrice() + "";
		}
		return super.getValue(element, columnName);
	}

	@Override
	protected void addApproveAction(ActionEvent e) {
		getContext().handle(new UpdatePurchaseOrderStatusTask(orderInfo.getId(), OrderAction.Approval));
		getContext().bubbleMessage(new MsgResponse(true));
	}

	@Override
	protected void addExecutAction(ActionEvent e) {
		getContext().handle(new UpdatePurchaseOrderStatusTask(orderInfo.getId(), OrderAction.Execut));
	}

	@Override
	protected void addReturnAction(ActionEvent e, String cause) {
		getContext().handle(new UpdatePurchaseOrderStatusTask(orderInfo.getId(), OrderAction.Deny, cause));
	}

	@Override
	protected void addStopAction(ActionEvent e, String cause) {
		getContext().handle(new UpdatePurchaseOrderStatusTask(orderInfo.getId(), OrderAction.Stop, cause));
	}

	@Override
	protected void addSubmitAction(ActionEvent e) {
		getContext().handle(new UpdatePurchaseOrderStatusTask(orderInfo.getId(), OrderAction.Submit));
		getContext().bubbleMessage(new MsgResponse(true));
	}

	@Override
	protected void addConsignmentAction(ActionEvent e) {
		getContext().handle(new UpdatePurchaseOrderStatusTask(orderInfo.getId(), OrderAction.consignment));
	}

	@Override
	protected void lookInventory(String rowId, Point location) {
		inventoryInfoWindow.refresh(getGoodsItem(rowId).getGoodsItemId(), null, location, false);
	}

	@Override
	protected String getSheetTitle() {
		return "采购订单";
	}

	@Override
	protected boolean addSaveAction(boolean haveTitle) {
		if (!this.validateInput()) {
			return false;
		}
		UpdatePurchaseOrderTask task = new UpdatePurchaseOrderTask();
		task.setDeliveryDate(System.currentTimeMillis());
		task.setDirectSupply(orderInfo.getOrderType() == OrderType.PLAIN_DIRECT);
		task.setRelatedId(orderInfo.getSourceId());
		task.setRelateName(orderInfo.getSourceName());
		task.setMemo(memoText.getText());
		task.setPartnerId(orderInfo.getPartnerInfo().getId());
		List<com.spark.psi.publish.order.task.UpdatePurchaseOrderTask.PurchaseOrderGoodsItem> list = new ArrayList<com.spark.psi.publish.order.task.UpdatePurchaseOrderTask.PurchaseOrderGoodsItem>();
		for (String rowId : table.getAllRowId()) {
			PurchaseOrderGoodsItem item = getGoodsItem(rowId);
			// task明细
			UpdatePurchaseOrderTask.PurchaseOrderGoodsItem taskItem = new UpdatePurchaseOrderTask.PurchaseOrderGoodsItem();
			taskItem.setCount(Double.valueOf(table.getEditValue(rowId, Cloumns.Count.name())[0]));
			taskItem.setPrice(Double.valueOf(table.getEditValue(rowId, Cloumns.Price.name())[0]));
			taskItem.setGoodsItemId(item.getGoodsItemId());
			taskItem.setAmount(taskItem.getCount() * taskItem.getPrice());
			list.add(taskItem);
		}
		task.setPurchaseOrderGoodsItems(list
				.toArray(new com.spark.psi.publish.order.task.UpdatePurchaseOrderTask.PurchaseOrderGoodsItem[list
						.size()]));
		// task.setAmount(amount);
		task.setId(orderInfo.getId());
		getContext().handle(task, UpdatePurchaseOrderTask.Method.Update);
		getContext().bubbleMessage(new MsgResponse(false));
		if (haveTitle) {
			hint("保存成功！");
		}
		resetDataChangedstatus();
		return true;
	}

	@Override
	protected String getTableNullTitle() {
		return "请添加需要采购的材料！";
	}

	@Override
	protected String validateTitle(com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns columnsEnum,
			com.spark.psi.order.browser.util.OrderDetailProcessor.ValiDateErrorEnum error) {
		switch (columnsEnum) {
		case Count:
			switch (error) {
			case NULL:
				return "采购数量不能为空";
			case Less_than_zero:
				return "采购数量必须大于0";
			case Zero:
				return "采购数量必须大于0";
			case Greater_than_Max:
				return "本次采购数量过大";
			case FormatException:
				return "数字格式不对，正确样式应如：97.9或97";
			default:
				return null;
			}
		case Price:
			switch (error) {
			case NULL:
				return "采购单价不能为空";
			case Less_than_zero:
				return "采购单价不能小于0";
			case Greater_than_Max:
				return "本次采购单价过大";
			case FormatException:
				return "数字格式不对，正确样式应如：97.97";
			default:
				return null;
			}
		case Amount:
			switch (error) {
			case NULL:
				return "采购金额不能为空";
			case Less_than_zero:
				return "采购单价不能小于0";
			case Greater_than_Max:
				return "本次采购金额过大";
			case FormatException:
				return "数字格式不对，正确样式应如：97.97";
			default:
				return null;
			}
		default:
			return null;
		}
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile("采购订单" + orderInfo.getOrderNumber() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle("材料采购单");
						bw.addLabel("采购单号", orderInfo.getOrderNumber());
						bw.addLabel("交货日期", DateUtil.dateFromat(orderInfo.getDeliveryDate()));
						if (null != orderInfo.getPartnerInfo()) {
							bw.addLabel("供应商名称", orderInfo.getPartnerInfo().getShortName());
							bw.addLabel("供应商联系人", orderInfo.getPartnerInfo().getLinkmanName() != null ? orderInfo
									.getPartnerInfo().getLinkmanName() : "无");
							bw.addLabel("交货地址", orderInfo.getPartnerInfo().getAddress() == null ? "无" : orderInfo
									.getPartnerInfo().getAddress());
						}
						bw.addLabel("仓库", orderInfo.getSourceName());
						bw.addLabel("订单状态：", orderInfo.getOrderStatus().getName());
						bw.addLabel("制单", orderInfo.getCreatorLabel() + "("
								+ DateUtil.dateFromat(orderInfo.getCreateDate()) + ")");
						bw.addLabel("备注", orderInfo.getRemark());
						String[] head = new String[] { "材料编号", "材料条码", "材料名称", "材料规格", "材料单位", "数量", "单价", "金额" };
						List<String[]> list = new ArrayList<String[]>();
						Object[] object = getElements(context, null);
						for (Object obj : object) {
							OrderGoodsItem item = (OrderGoodsItem) obj;
							list.add(new String[] { item.getGoodsCode(), item.getGoodsNo(), item.getName(),
									item.getSpec(), item.getUnit(), DoubleUtil.getRoundStr(item.getCount()),
									DoubleUtil.getRoundStr(item.getPrice()),
									DoubleUtil.getRoundStr(DoubleUtil.mul(item.getCount(), item.getPrice())) });
						}
						bw.setTable(head, list);
						bw.setTotalLabel(new DoubleString("订单金额", DoubleUtil.getRoundStr(orderInfo.getAmount())));
						try {
							bw.write(orderInfo.getOrderNumber());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	}

	@Override
	protected boolean isNeedExport() {
		if (null == orderInfo || orderInfo.getOrderStatus() == OrderStatus.Submit) {
			return false;
		}
		return true;
	}
	@Override
	protected boolean isNeedPrint() {
		return true;
	}

	@Override
	protected void printAction() {
		PrintColumn[] columns = new PrintColumn[8];
		columns[0] = new PrintColumn("材料编号", 100, JWT.LEFT);
		columns[1] = new PrintColumn("材料条码", 100, JWT.LEFT);
		columns[2] = new PrintColumn("材料名称", 120, JWT.LEFT);
		columns[3] = new PrintColumn("规格", 60, JWT.CENTER);
		columns[4] = new PrintColumn("单位", 45, JWT.CENTER);
		columns[5] = new PrintColumn("数量", 50, JWT.RIGHT);
		columns[6] = new PrintColumn("单价", 50, JWT.RIGHT);
		columns[7] = new PrintColumn("金额", 70, JWT.RIGHT);
		String tableTitle0 = "供应商名称：" + orderInfo.getPartnerInfo().getShortName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供应商联系人：" + (orderInfo.getLinkman() == null ? "无" : orderInfo.getLinkman()) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
				+ "交货日期：" + DateUtil.dateFromat(orderInfo.getDeliveryDate()) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单据状态：" + orderInfo.getOrderStatus().getName();
		String tableTitle1 = "仓库：" + orderInfo.getSourceName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收货地址：" + (orderInfo.getPartnerInfo().getAddress() == null ? "无" : orderInfo.getPartnerInfo().getAddress());
		
		String tableBottom1 = "备注：" + (orderInfo.getRemark() == null ? "无" : orderInfo.getRemark());
		String tableBottom2 = getSheetCreateInfo() + (getSheetApprovalInfo() == null ? "" : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getSheetApprovalInfo());
		String[] extraInfo = getSheetExtraInfo();
		if (null != extraInfo) {
			for (String info : extraInfo) {
				tableBottom2 += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + info;
			}
		}
		FormPrintEntity fpe = new FormPrintEntity("采购单", columns, orderInfo.getGoodsItems(), tableTitle0, tableTitle1);
		fpe.setTableFooters(tableBottom1, tableBottom2);
		fpe.setLabelProvider(new SLabelProvider() {
			
			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}
			
			public String getText(Object element, int columnIndex) {
				PurchaseOrderGoodsItem item = (PurchaseOrderGoodsItem)element;
				switch(columnIndex) {
				case 0:
					return item.getGoodsCode();
				case 1:
					return item.getGoodsNo();
				case 2:
					return item.getName();
				case 3:
					return item.getSpec();
				case 4:
					return item.getUnit();
				case 5:
					return DoubleUtil.getRoundStr(item.getCount(), 0);
				case 6:
					return DoubleUtil.getRoundStr(item.getPrice(), 2);
				case 7:
					return DoubleUtil.getRoundStr(item.getAmount(), 2);
				}
				return null;
			}
			
			public Color getForeground(Object element, int columnIndex) {
				return null;
			}
			
			public Color getBackground(Object element, int columnIndex) {
				return null;
			}
		});
		PurchaseOrderPrintContentProvider pProvider = new PurchaseOrderPrintContentProvider(fpe);
		PSIPrinter printer = new PSIPrinter(pProvider);
		printer.setNeedPreview(true);
		Composite hideArea = createControl(ID_Area_Hide, Composite.class);
		Browser browser = new Browser(hideArea);
		browser.setLayoutData(GridData.INS_FILL_BOTH);
		browser.applyHTML(printer.getPrinterContent());
		hideArea.layout();
	}
}
