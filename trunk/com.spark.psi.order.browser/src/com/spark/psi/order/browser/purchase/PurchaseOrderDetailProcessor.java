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
 * �ɹ�������ϸ������
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
		fillPartnerInfoArea(supplierInfoArea, "��Ӧ��", orderInfo.getPartnerInfo());
		fillDeliveryInfoArea(deliveryInfoArea);
		sourceNameLabel.setText((getContext().find(StoreInfo.class, orderInfo.getSourceId()) != null ? "�ֿ⣺" : "ֱ���ͻ���")
				+ orderInfo.getSourceName() + "   ");
		deliveryDateLabel.setText("   �������ڣ�" + DateUtil.dateFromat(orderInfo.getDeliveryDate()));
		//
		if (this.viewEnum.isIn(View.Create, View.Edit)) {
			// ��Ӳ���
			addGoods();
			// ����
			save();
			// // ���沢�½�
			// saveAndCreate();
			// �ύ
			addSubmit();
		} else {
			OrderAction[] actions = orderInfo.getActions();
			for (OrderAction oa : actions) {
				addButton(oa);
			}
			//
			orderstatusLabel.setText("����״̬��" + orderInfo.getOrderStatus().getName());
			List<OrderCheckSheetItem> itemList = getContext().getList(OrderCheckSheetItem.class,
					new GetOrderCheckInSheetItemKey(orderInfo.getId()));
			if (itemList != null && itemList.size() > 0) {
				if (orderInfo.getOrderStatus().isIn(OrderStatus.CheckingIn_Part, OrderStatus.CheckedIn,
						OrderStatus.Stop, OrderStatus.finish)) {
					orderstatusLabel.setText(orderstatusLabel.getText() + "  ");
					checkInfoLabel.setText("�鿴������");
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

	// =-========================��ť������================================
	private void addGoods() {
		this.createControl(ID_BUTTON_ADDGOODS, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MsgRequest request = CommonSelectRequest.createSelectGoodsRequest(true);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						GoodsItemInfo[] itemList = (GoodsItemInfo[]) returnValue;
						for (GoodsItemInfo item : itemList) {
							PurchaseOrderGoodsItem gi = PurchaseUtil.getPurchaseOrderGoodsItem(item);
							table.addRow(gi); // XXX�����ؼ��ṩ����������ݽӿں��޸ģ�ĿǰЧ��
						}
						table.renderUpate();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	protected void save() {
		this.createButton(" ���� ").addActionListener(new ActionListener() {

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
		return "�ɹ�����";
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
			// task��ϸ
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
			hint("����ɹ���");
		}
		resetDataChangedstatus();
		return true;
	}

	@Override
	protected String getTableNullTitle() {
		return "�������Ҫ�ɹ��Ĳ��ϣ�";
	}

	@Override
	protected String validateTitle(com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns columnsEnum,
			com.spark.psi.order.browser.util.OrderDetailProcessor.ValiDateErrorEnum error) {
		switch (columnsEnum) {
		case Count:
			switch (error) {
			case NULL:
				return "�ɹ���������Ϊ��";
			case Less_than_zero:
				return "�ɹ������������0";
			case Zero:
				return "�ɹ������������0";
			case Greater_than_Max:
				return "���βɹ���������";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.9��97";
			default:
				return null;
			}
		case Price:
			switch (error) {
			case NULL:
				return "�ɹ����۲���Ϊ��";
			case Less_than_zero:
				return "�ɹ����۲���С��0";
			case Greater_than_Max:
				return "���βɹ����۹���";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		case Amount:
			switch (error) {
			case NULL:
				return "�ɹ�����Ϊ��";
			case Less_than_zero:
				return "�ɹ����۲���С��0";
			case Greater_than_Max:
				return "���βɹ�������";
			case FormatException:
				return "���ָ�ʽ���ԣ���ȷ��ʽӦ�磺97.97";
			default:
				return null;
			}
		default:
			return null;
		}
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile("�ɹ�����" + orderInfo.getOrderNumber() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle("���ϲɹ���");
						bw.addLabel("�ɹ�����", orderInfo.getOrderNumber());
						bw.addLabel("��������", DateUtil.dateFromat(orderInfo.getDeliveryDate()));
						if (null != orderInfo.getPartnerInfo()) {
							bw.addLabel("��Ӧ������", orderInfo.getPartnerInfo().getShortName());
							bw.addLabel("��Ӧ����ϵ��", orderInfo.getPartnerInfo().getLinkmanName() != null ? orderInfo
									.getPartnerInfo().getLinkmanName() : "��");
							bw.addLabel("������ַ", orderInfo.getPartnerInfo().getAddress() == null ? "��" : orderInfo
									.getPartnerInfo().getAddress());
						}
						bw.addLabel("�ֿ�", orderInfo.getSourceName());
						bw.addLabel("����״̬��", orderInfo.getOrderStatus().getName());
						bw.addLabel("�Ƶ�", orderInfo.getCreatorLabel() + "("
								+ DateUtil.dateFromat(orderInfo.getCreateDate()) + ")");
						bw.addLabel("��ע", orderInfo.getRemark());
						String[] head = new String[] { "���ϱ��", "��������", "��������", "���Ϲ��", "���ϵ�λ", "����", "����", "���" };
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
						bw.setTotalLabel(new DoubleString("�������", DoubleUtil.getRoundStr(orderInfo.getAmount())));
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
		columns[0] = new PrintColumn("���ϱ��", 100, JWT.LEFT);
		columns[1] = new PrintColumn("��������", 100, JWT.LEFT);
		columns[2] = new PrintColumn("��������", 120, JWT.LEFT);
		columns[3] = new PrintColumn("���", 60, JWT.CENTER);
		columns[4] = new PrintColumn("��λ", 45, JWT.CENTER);
		columns[5] = new PrintColumn("����", 50, JWT.RIGHT);
		columns[6] = new PrintColumn("����", 50, JWT.RIGHT);
		columns[7] = new PrintColumn("���", 70, JWT.RIGHT);
		String tableTitle0 = "��Ӧ�����ƣ�" + orderInfo.getPartnerInfo().getShortName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ӧ����ϵ�ˣ�" + (orderInfo.getLinkman() == null ? "��" : orderInfo.getLinkman()) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
				+ "�������ڣ�" + DateUtil.dateFromat(orderInfo.getDeliveryDate()) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����״̬��" + orderInfo.getOrderStatus().getName();
		String tableTitle1 = "�ֿ⣺" + orderInfo.getSourceName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ջ���ַ��" + (orderInfo.getPartnerInfo().getAddress() == null ? "��" : orderInfo.getPartnerInfo().getAddress());
		
		String tableBottom1 = "��ע��" + (orderInfo.getRemark() == null ? "��" : orderInfo.getRemark());
		String tableBottom2 = getSheetCreateInfo() + (getSheetApprovalInfo() == null ? "" : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getSheetApprovalInfo());
		String[] extraInfo = getSheetExtraInfo();
		if (null != extraInfo) {
			for (String info : extraInfo) {
				tableBottom2 += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + info;
			}
		}
		FormPrintEntity fpe = new FormPrintEntity("�ɹ���", columns, orderInfo.getGoodsItems(), tableTitle0, tableTitle1);
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
