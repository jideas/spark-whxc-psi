/**
 * 
 */
package com.spark.psi.order.browser.sales;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.exception.AbortException;
import com.jiuqi.dna.core.invoke.AsyncTask;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.spi.application.SituationSPI;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
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
import com.spark.psi.base.browser.material.MaterialsSelectParameters;
import com.spark.psi.order.browser.common.OrderCheckInfoWindow;
import com.spark.psi.order.browser.util.SalesMaterialsUtil;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.ValidationReturn;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.partner.entity.CustomerOverCredit;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.key.GetCreditAmountUsableKey;
import com.spark.psi.publish.base.partner.key.GetCustomerOverCreditDayKey;
import com.spark.psi.publish.inventory.entity.OrderCheckSheetItem;
import com.spark.psi.publish.inventory.key.GetOrderCheckOutSheetItemKey;
import com.spark.psi.publish.order.entity.OrderInfo;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.task.UpdateSalesOrderStatusTask;
import com.spark.psi.publish.order.task.UpdateSalesOrderTask;

/**
 * 
 *
 */
public class SalesOrderDetailProcessor extends AbstractSalesOrderDetailProcessor {

	public final static String ID_CustomerInfo_Area = "CustomerInfo_Area";
	public final static String ID_DeliveryInfo_Area = "DeliveryInfo_Area";
	public final static String ID_CreditAmountLabel = "CreditAmountLabel";
	public final static String ID_CreditInfoLabel = "CreditInfoLabel";
	public final static String ID_DeliveryDate = "DeliveryDate";
	public final static String ID_CustomerSelect_Button = "CustomerSelect_Button";
	public final static String ID_OrderStatusLabel = "OrderStatusLabel";
	public final static String ID_CheckInfoLabel = "CheckInfoLabel";
	public static final String ID_Area_Hide = "Area_Hide";

	private Composite customerInfoArea;
	private Composite deliveryInfoArea;
	private Label creditAmountLabel;
	private Label creditInfoLabel;
	private Label customerSelectButton;

	private Label orderstatusLabel;
	private Label checkInfoLabel;

	SalesOrderInfo orderInfo;
	private PartnerInfo customerInfo;

	//
	private DatePicker deliveryDatePicker;

	private void updatePartnerInfo() {
		customerInfoArea.clear();
		deliveryInfoArea.clear();

		//
		fillPartnerInfoArea(customerInfoArea, "�ͻ�", customerInfo);
		fillDeliveryInfoArea(deliveryInfoArea);

		if (orderInfo.getOrderStatus() == null
				|| orderInfo.getOrderStatus().isIn(OrderStatus.Submit, OrderStatus.Approval_Yes,
						OrderStatus.Approval_No, OrderStatus.Denied)) {
			creditAmountLabel.setText("�������ö�ȣ�"
					+ DoubleUtil.getRoundStr(getContext().find(Double.class,
							new GetCreditAmountUsableKey(customerInfo.getId()))));
			CustomerOverCredit coc = getContext().find(CustomerOverCredit.class,
					new GetCustomerOverCreditDayKey(customerInfo.getId()));
			StringBuilder l = new StringBuilder();
			if (coc.getApproachedCreditAmount() == 0 && coc.getOverCreditAmount() == 0) {
				l.append("�������ѹ����ں��ٽ����ڵ�Ӧ�տ�");
			} else {
				l.append("�ѳ����ڽ�").append(coc.getOverCreditAmount());
				l.append("  �ٽ����ڽ�").append(coc.getApproachedCreditAmount());
			}
			creditInfoLabel.setText(l.toString());
		}

		//
		table.getParent().layout();
	}

	@Override
	protected final boolean addSaveAction(boolean haveTitle) {
		if (!this.validateInput()) {
			return false;
		}
		UpdateSalesOrderTask task = new UpdateSalesOrderTask();
		task.setDeliveryDate(deliveryDatePicker.getDate().getTime());
		task.setId(orderInfo.getId());
		task.setMemo(memoText.getText());
		task.setPartnerId(customerInfo.getId());

		if (!fillTaskBaseData(task)) {
			return false;
		}
		//
		SalesOrderInfo info = getContext().find(SalesOrderInfo.class, orderInfo.getId());
		if (null == info) {
			if (haveTitle) {
				getContext().handle(task, UpdateSalesOrderTask.Method.Create);
			} else {
				AsyncTask<UpdateSalesOrderTask, UpdateSalesOrderTask.Method> async = getContext().asyncHandle(task,
						UpdateSalesOrderTask.Method.Create);
				try {
					getContext().waitFor(async);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			getContext().handle(task, UpdateSalesOrderTask.Method.Update);
		}
		if (task.isSuccess()) {
			if (haveTitle) {
				hint("����ɹ���");
			}
			SalesOrderInfo order = getContext().find(SalesOrderInfo.class, task.getId());
			if (null != order) {
				orderInfo = order;
			}
			getContext().bubbleMessage(new MsgResponse(false));
			resetDataChangedstatus();
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void addApproveAction(ActionEvent e) {
		UpdateSalesOrderStatusTask task = new UpdateSalesOrderStatusTask(orderInfo.getId(), OrderAction.Approval);
		try {
			getContext().handle(task);
			getContext().bubbleMessage(new MsgResponse(true));
		} catch (AbortException ex) {
			ValidationReturn<UpdateSalesOrderStatusTask.Error> error = task.getValidationReturn();

			if (error.isHaveError(UpdateSalesOrderStatusTask.Error.PromotionCountError)) {
				alert("����������Ϣ�ѹ�ʱ����ˢ�½������ԣ�");
			} else if (error.isHaveError(UpdateSalesOrderStatusTask.Error.SetPlanDate)) {
				// Ԥ����������
				new SetPlanOutDate(getContext(), true) {

					@Override
					protected void deny() {
						handleReturn(null);
					}

					@Override
					protected void setPlanOutDate(Date date) {
						getContext()
								.handle(
										new UpdateSalesOrderStatusTask(orderInfo.getId(), OrderAction.Approval, date
												.getTime()));
						getContext().bubbleMessage(new MsgResponse(true));
					}
				};
			}
			if ("�ѳ�����������".equals(ex.getMessage())) {
				alert(ex.getMessage());
				((SituationSPI) getContext()).resolveTrans();
			}
		}
	}

	@Override
	protected void addExecutAction(ActionEvent e) {
		getContext().handle(new UpdateSalesOrderStatusTask(orderInfo.getId(), OrderAction.Execut));
	}

	@Override
	protected void addReturnAction(ActionEvent e, String cause) {
		getContext().handle(new UpdateSalesOrderStatusTask(orderInfo.getId(), OrderAction.Deny, cause));
	}

	@Override
	protected void addStopAction(ActionEvent e, String cause) {
		getContext().handle(new UpdateSalesOrderStatusTask(orderInfo.getId(), OrderAction.Stop, cause));
	}

	@Override
	protected void addSubmitAction(ActionEvent e) {
		UpdateSalesOrderStatusTask task = new UpdateSalesOrderStatusTask(orderInfo.getId(), OrderAction.Submit);
		try {
			getContext().handle(task);
			getContext().bubbleMessage(new MsgResponse(true));
		} catch (AbortException ex) {
			ValidationReturn<UpdateSalesOrderStatusTask.Error> error = task.getValidationReturn();
			if (error.isHaveError(UpdateSalesOrderStatusTask.Error.PromotionCountError)) {
				alert("����������Ϣ�ѹ�ʱ����ˢ�½������ԣ�");
			} else if (error.isHaveError(UpdateSalesOrderStatusTask.Error.SetPlanDate)) {
				// Ԥ����������
				new SetPlanOutDate(getContext()) {
					@Override
					protected void deny() {
					}

					@Override
					protected void setPlanOutDate(Date date) {
						getContext().handle(
								new UpdateSalesOrderStatusTask(orderInfo.getId(), OrderAction.Submit, date.getTime()));
						getContext().bubbleMessage(new MsgResponse(true));
					}
				};
			}
			if ("�ѳ�����������".equals(ex.getMessage())) {
				alert(ex.getMessage());
				((SituationSPI) getContext()).resolveTrans();
			}
		}
	}

	@Override
	protected OrderInfo getOrderInfo() {
		if (getArgument() instanceof SalesOrderInfo) {
			orderInfo = (SalesOrderInfo) getArgument();
		} else if (getArgument() instanceof String) {
			orderInfo = getContext().find(SalesOrderInfo.class, GUID.valueOf((String) getArgument()));
		}
		return orderInfo;
	}

	@Override
	protected SalesOrderGoodsItem[] initItemList() {
		return orderInfo.getSalesOrderGoodsItems();
	}

	public void process(Situation context) {
		super.process(getContext());
		customerInfoArea = createControl(ID_CustomerInfo_Area, Composite.class);
		deliveryInfoArea = createControl(ID_DeliveryInfo_Area, Composite.class);
		creditAmountLabel = createControl(ID_CreditAmountLabel, Label.class);
		creditInfoLabel = createControl(ID_CreditInfoLabel, Label.class);
		deliveryDatePicker = createControl(ID_DeliveryDate, DatePicker.class);
		customerSelectButton = createControl(ID_CustomerSelect_Button, Label.class);
		orderstatusLabel = createControl(ID_OrderStatusLabel, Label.class);
		checkInfoLabel = createControl(ID_CheckInfoLabel, Label.class);
		this.customerInfo = this.orderInfo.getPartnerInfo();
		//
		updatePartnerInfo();
		if (viewEnum != View.Look) {
			deliveryDatePicker
					.setDate(this.orderInfo.getDeliveryDate() > 0 ? new Date(this.orderInfo.getDeliveryDate())
							: new Date());
			customerSelectButton.addMouseClickListener(new MouseClickListener() {
				public void click(MouseEvent event) {
					MsgRequest request = CommonSelectRequest.createSelectCustomerRequest(true, true, true, customerInfo
							.getId());
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3,
								Object returnValue4) {
							customerInfo = getContext().find(PartnerInfo.class, returnValue);
							updatePartnerInfo();
							markDataChanged();
						}
					});
					getContext().bubbleMessage(request);
				}
			});
			this.memoText.setEnabled(true);
		} else {
			orderstatusLabel.setText("����״̬��" + orderInfo.getOrderStatus().getName());
			List<OrderCheckSheetItem> itemList = getContext().getList(OrderCheckSheetItem.class,
					new GetOrderCheckOutSheetItemKey(orderInfo.getId()));
			if (itemList != null && itemList.size() > 0) {
				if (orderInfo.getOrderStatus().isIn(OrderStatus.CheckingOut_Part, OrderStatus.CheckedOut,
						OrderStatus.finish, OrderStatus.Stop)) {
					orderstatusLabel.setText(orderstatusLabel.getText() + "  ");
					checkInfoLabel.setText("�鿴�������");
					new OrderCheckInfoWindow(checkInfoLabel, orderInfo, itemList).bindTargetControl(checkInfoLabel);
				}
			}
		}
	}

	protected void initPageButton() {
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
		}
	}

	protected void addGoods() {
		this.createControl(ID_BUTTON_ADDGOODS, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaterialsSelectParameters parameters = new MaterialsSelectParameters(null, true, false, false, null);
				MsgRequest request = CommonSelectRequest.createSelectMaterialsRequest(parameters);
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						MaterialsItemInfo[] itemList = (MaterialsItemInfo[]) returnValue;
						SalesOrderGoodsItem orderItem;
						for (MaterialsItemInfo item : itemList) {
							orderItem = SalesMaterialsUtil.getSalesOrderMaterialsItem(getContext(), item);
							if (null != orderItem) {
								table.addRow(orderItem); // XXX�����ؼ��ṩ����������ݽӿں��޸ģ�ĿǰЧ��
							}
						}
						table.renderUpate();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	@Override
	protected String getSheetTitle() {
		return "���۶���";
	}

	@Override
	protected double getDiscountAmount() {
		return orderInfo.getDiscountAmount();
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile("���۶���" + orderInfo.getOrderNumber() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle("�������۵�");
						bw.addLabel("���۵���", orderInfo.getOrderNumber());
						bw.addLabel("��������", DateUtil.dateFromat(orderInfo.getDeliveryDate()));
						if (null != orderInfo.getPartnerInfo()) {
							bw.addLabel("�ͻ�����", orderInfo.getPartnerInfo().getShortName());
							bw.addLabel("�ͻ���ϵ��", orderInfo.getPartnerInfo().getLinkmanName() != null ? orderInfo
									.getPartnerInfo().getLinkmanName() : "��");
							bw.addLabel("�ջ���ַ", orderInfo.getPartnerInfo().getAddress() == null ? "��" : orderInfo
									.getPartnerInfo().getAddress());
						}
						bw.addLabel("����״̬��", orderInfo.getOrderStatus().getName());
						bw.addLabel("�Ƶ�", orderInfo.getCreatorLabel() + "("
								+ DateUtil.dateFromat(orderInfo.getCreateDate()) + ")");
						bw.addLabel("��ע", orderInfo.getRemark());
						String[] head = new String[] { "���ϱ��", "��������", "��������", "���Ϲ��", "���ϵ�λ", "����", "����", "�ۿ���",
								"�ۿ۶�", "���" };
						List<String[]> list = new ArrayList<String[]>();
						Object[] object = getElements(context, null);
						for (Object obj : object) {
							SalesOrderGoodsItem item = (SalesOrderGoodsItem) obj;
							list.add(new String[] {
									item.getGoodsCode(),
									item.getGoodsNo(),
									item.getName(),
									item.getSpec(),
									item.getUnit(),
									DoubleUtil.getRoundStr(item.getCount()),
									DoubleUtil.getRoundStr(item.getPrice()),
									DoubleUtil.getRoundStr(item.getDiscountCount()),
									DoubleUtil.getRoundStr(item.getDiscountAmount()),
									DoubleUtil.getRoundStr(DoubleUtil.mul(item.getCount(), item.getPrice())
											- item.getDiscountAmount()) });
						}
						bw.setTable(head, list);
						bw
								.setTotalLabel(new DoubleString("�����ۿ�", DoubleUtil.getRoundStr(orderInfo
										.getDiscountAmount())));
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
		PrintColumn[] columns = new PrintColumn[11];
		columns[0] = new PrintColumn("���ϱ��", 100, JWT.LEFT);
		columns[1] = new PrintColumn("��������", 100, JWT.LEFT);
		columns[2] = new PrintColumn("��������", 120, JWT.LEFT);
		columns[3] = new PrintColumn("���", 60, JWT.CENTER);
		columns[4] = new PrintColumn("��λ", 45, JWT.CENTER);
		columns[5] = new PrintColumn("����", 50, JWT.RIGHT);
		columns[6] = new PrintColumn("ԭ��", 50, JWT.RIGHT);
		columns[7] = new PrintColumn("����", 50, JWT.RIGHT);
		columns[8] = new PrintColumn("�ۿ���", 50, JWT.RIGHT);
		columns[9] = new PrintColumn("�ۿ۶�", 50, JWT.RIGHT);
		columns[10] = new PrintColumn("���", 70, JWT.RIGHT);
		String tableTitle0 = "�ͻ����ƣ�" + orderInfo.getPartnerInfo().getShortName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ͻ���ϵ�ˣ�" + (orderInfo.getLinkman() == null ? "��" : orderInfo.getLinkman()) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
				+ "�������ڣ�" + DateUtil.dateFromat(orderInfo.getDeliveryDate()) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����״̬��" + orderInfo.getOrderStatus().getName();
		String tableTitle1 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ջ���ַ��" + (orderInfo.getPartnerInfo().getAddress() == null ? "��" : orderInfo.getPartnerInfo().getAddress());
		
		String tableBottom1 = "��ע��" + (orderInfo.getRemark() == null ? "��" : orderInfo.getRemark());
		String tableBottom2 = getSheetCreateInfo() + (getSheetApprovalInfo() == null ? "" : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getSheetApprovalInfo());
		String[] extraInfo = getSheetExtraInfo();
		if (null != extraInfo) {
			for (String info : extraInfo) {
				tableBottom2 += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + info;
			}
		}
		FormPrintEntity fpe = new FormPrintEntity("���۵�", columns, orderInfo.getSalesOrderGoodsItems(), tableTitle0, tableTitle1);
		fpe.setTableFooters(tableBottom1, tableBottom2);
		fpe.setLabelProvider(new SLabelProvider() {
			
			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}
			
			public String getText(Object element, int columnIndex) {
				SalesOrderGoodsItem item = (SalesOrderGoodsItem)element;
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
					return DoubleUtil.getRoundStr(item.getPlanPrice(), 2);
				case 7:
					return DoubleUtil.getRoundStr(item.getGoodsItemPrice(), 2);
				case 8:
					return DoubleUtil.getRoundStr(item.getDiscountCount(), 2);
				case 9:
					return DoubleUtil.getRoundStr(item.getDiscountAmount(), 2);
				case 10:
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
		SalesOrderPrintContentProvider pProvider = new SalesOrderPrintContentProvider(fpe);
		PSIPrinter printer = new PSIPrinter(pProvider);
		printer.setNeedPreview(true);
		Composite hideArea = createControl(ID_Area_Hide, Composite.class);
		Browser browser = new Browser(hideArea);
		browser.setLayoutData(GridData.INS_FILL_BOTH);
		browser.applyHTML(printer.getPrinterContent());
		hideArea.layout();
	}
}