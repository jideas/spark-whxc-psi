package com.spark.psi.order.browser.online;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.b2c.publish.base.member.entity.MemberAccountInfo;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.FormPrintEntity;
import com.spark.psi.base.browser.PSIPrinter;
import com.spark.psi.base.browser.PrintColumn;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfo;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.task.UpdateOnlineOrderStatusTask;

public class OnlineOrderDetailProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	public static final String ID_Label_CustomerName = "Label_CustomerName";
	public static final String ID_Label_MobileNo = "Label_MobileNo";
	public static final String ID_Label_BookingDate = "Label_BookingDate";
	public static final String ID_Label_SendDate = "ID_Label_SendDate";
	public static final String ID_Label_IsToDoor = "ID_Label_IsToDoor";
	public static final String ID_Label_Status = "Label_Status ";
	public static final String ID_Label_TotalAmount = "Label_TotalAmount";
	public static final String ID_Button_Separate = "Button_Separate";
	public static final String ID_Button_Arrive = "Button_Arrive";
	public static final String ID_Button_Finish = "Button_Finish";
	public static final String ID_Area_Hide = "Area_Hide";

	public static enum ColumnName {
		goodsName, goodsCode, goodsSpec, unit, count, price, amount, goodsNo
	}

	@Override
	protected boolean isNeedPrint() {
		return true;
	}

	@Override
	protected void printAction() {
		PrintColumn[] columns = new PrintColumn[4];
		columns[0] = new PrintColumn("��Ʒ����", PrintColumn.NAME_COLUMN_WIDTH, JWT.LEFT);
		columns[1] = new PrintColumn("���", PrintColumn.SPEC_COLUMN_WIDTH, JWT.CENTER);
		columns[2] = new PrintColumn("����", PrintColumn.COUNT_COLUMN_WIDTH, JWT.CENTER);
		columns[3] = new PrintColumn("���", PrintColumn.AMOUNT_COLUMN_WIDTH, JWT.LEFT);
//		String tableTitle0 = "�ͻ���" + orderInfo.getRealName();
//		MemberAccountInfo memberAccount = getContext().find(MemberAccountInfo.class, orderInfo.getMemberId());
//		String memberBalance  = null;
//		if (null != memberAccount) {
//			memberBalance = "�ʻ���" + DoubleUtil.getRoundStr(memberAccount.getMoneyBalance()) + "Ԫ";
//			memberBalance += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ʣ����֣�" + DoubleUtil.getRoundStr(memberAccount.getVantages(), 0);
//		}
//		String tableTitle1 = "��ϵ�绰��" + orderInfo.getConsigneeTel();
//		String tableTitle2 = "������ţ�" + orderInfo.getBillsNo().split("WSDD")[1];
//		String tableTitle3 = "�µ�ʱ�䣺" + DateUtil.dateFromat(orderInfo.getCreateDate(), DateUtil.DATE_TIME_PATTERN);
//		String tableTitle4 = "վ�㣺" + orderInfo.getStationName();
//		String tableTitle5 = "�ջ���ַ��" + orderInfo.getAddress();
//		String tableTitle6 = "�ջ����ڣ�" + DateUtil.dateFromat(orderInfo.getDeliveryeDate(), DateUtil.DATE_TIME_PATTERN);
//		����վ�㣺xxxվ��ע�⣺xxxվ������Ӵ�Ӵ֣�
//		������ţ�
//		�ջ��ˣ���������ʾ���ջ��������������û�����
//		��ϵ�绰��
//		�ջ���ַ����ע����������ϸ��ַ������+�ֵ���
		String tableTitle0 = "����վ�㣺<font size='3'><strong>" + orderInfo.getStationName() + "</strong></font>";
		String tableTitle1 = "������ţ�" + orderInfo.getBillsNo().split("WSDD")[1];
		String tableTitle2 = "�ջ��ˣ�" + orderInfo.getConsignee();
		String tableTitle3 = "��ϵ�绰��" + orderInfo.getConsigneeTel();
		String tableTitle4 = "�ջ���ַ��" + orderInfo.getAddress();
		String tableTitle5 = "�ջ����ڣ�" + DateUtil.dateFromat(orderInfo.getDeliveryeDate(), DateUtil.DATE_TIME_PATTERN);
		String[] titles = {tableTitle0, tableTitle1, tableTitle2, tableTitle3, tableTitle4, tableTitle5};
		String summaryInfo = "";
//		��Ʒ����2           ������3             �ϼƣ�20.00
//		�˻���1,558.90    
//		���û��֣�800
//		��ӡ���ڣ�2013-01-27
		double totalCount = 0.0;
		double totalAmount = 0.0;
		for (OnlineOrderInfoItem item : orderInfo.getItems()) {
			totalCount += item.getCount();
			totalAmount += item.getAmount();
		}
		summaryInfo = "��Ʒ����" + orderInfo.getItems().length + "&nbsp;&nbsp;&nbsp;&nbsp;�ܼ�����" + DoubleUtil.getRoundStr(totalCount, 0) + "&nbsp;&nbsp;&nbsp;&nbsp;�ϼƣ�12" + DoubleUtil.getRoundStr(totalAmount); 
		MemberAccountInfo memberAccount = getContext().find(MemberAccountInfo.class, orderInfo.getMemberId());
		List<String> footerList = new ArrayList<String>();
		footerList.add(summaryInfo);
		if (null != memberAccount) {
			footerList.add("�ʻ���" + DoubleUtil.getRoundStr(memberAccount.getMoneyBalance()) + "Ԫ");
			footerList.add("ʣ����֣�" + DoubleUtil.getRoundStr(memberAccount.getVantages(), 0));
		}
		footerList.add("��ӡ���ڣ�" + DateUtil.dateFromat(new Date().getTime()));
		FormPrintEntity fpe = new FormPrintEntity("���϶���", columns, orderInfo.getItems(), titles);
		//fpe.setSummaryInfo(summaryInfo);
		fpe.setTableFooters(footerList.toArray(new String[0]));
		fpe.setLabelProvider(new SLabelProvider() {
			
			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}
			
			public String getText(Object element, int columnIndex) {
				OnlineOrderInfoItem item = (OnlineOrderInfoItem)element;
				switch(columnIndex) {
				case 0:
//					if (item.getGoodsName().length() > 12) {
//						return item.getGoodsName().substring(0, 12);
//					}
					return item.getGoodsName();
				case 1:
					return item.getGoodsSpec();
				case 2:
					return DoubleUtil.getRoundStr(item.getCount(), 0);
				case 3:
					return DoubleUtil.getRoundStr(item.getAmount());
//					return DoubleUtil.getRoundStr(1666.00);
//				case 4:
//					return DoubleUtil.getRoundStr(item.getAmount());
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
		OnlineOrderPrintConentProvider pProvider = new OnlineOrderPrintConentProvider(fpe);
		PSIPrinter printer = new PSIPrinter(pProvider);
		printer.setNeedPreview(true);
		Composite hideArea = createControl(ID_Area_Hide, Composite.class);
		Browser browser = new Browser(hideArea);
		browser.setLayoutData(GridData.INS_FILL_BOTH);
		browser.applyHTML(printer.getPrinterContent());
		hideArea.layout();
	}

	private OnlineOrderInfo orderInfo = null;

	private LoginInfo loginInfo = null;

	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		final Label customerLabel = createControl(ID_Label_CustomerName, Label.class);
		final Label mobileNoLabel = createControl(ID_Label_MobileNo, Label.class);
		final Label statusLabel = createControl(ID_Label_Status, Label.class);
		final Label totalAmountLabel = createControl(ID_Label_TotalAmount, Label.class);
		final Label bookingDateLabel = createLabelControl(ID_Label_BookingDate);
		final Label sendDateLabel = createLabelControl(ID_Label_SendDate);
		final Label isToDoorLabel = createLabelControl(ID_Label_IsToDoor);
		final Text remarkText = createMemoText();

		remarkText.setEnabled(false);
		bookingDateLabel.setText(DateUtil.dateFromat(orderInfo.getCreateDate(), DateUtil.DATE_TIME_PATTERN));
		sendDateLabel.setText(DateUtil.dateFromat(orderInfo.getDeliveryeDate(), DateUtil.DATE_TIME_PATTERN));
		customerLabel.setText(orderInfo.getRealName());
		mobileNoLabel.setText(orderInfo.getConsigneeTel());
		statusLabel.setText(OnlineOrderStatus.getStatus(orderInfo.getStatus()).getName());
		totalAmountLabel.setText(DoubleUtil.getRoundStr(orderInfo.getTotalAmount()));
		isToDoorLabel.setText(orderInfo.isToDoor()?"��":"��");
		initFormActions();
	}

	private void initFormActions() {
		Button button = null;
		if (OnlineOrderStatus.Picking.getCode().equals(orderInfo.getStatus())) {
			button = createButtonControl(ID_Button_Separate);
			if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_Separate)) {
				addSeparateOrderAction(button);
			} else {
				button.dispose();
			}
		} else if (OnlineOrderStatus.Delivering.getCode().equals(orderInfo.getStatus())) {
			button = createButtonControl(ID_Button_Arrive);
			if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_ConfirmArrive)) {
				addArriveAction(button);
			} else {
				button.dispose();
			}
		} else if (OnlineOrderStatus.Arrivaled.getCode().equals(orderInfo.getStatus())) {
			button = createButtonControl(ID_Button_Finish);
			if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_ConfirmFinish)) {
				addFinishAction(button);
			} else {
				button.dispose();
			}
		}
	}

	private void addArriveAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// ȷ�ϵ���
				confirm("һ�������ֻ�����ͷ����쳣ʱ����Ҫ�ֶ�ִ������������Ƿ������", new Runnable() {

					public void run() {
						UpdateOnlineOrderStatusTask task = new UpdateOnlineOrderStatusTask(orderInfo.getId());
						getContext().handle(task, UpdateOnlineOrderStatusTask.Method.Arrival);
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
			}
		});
	}

	private void addFinishAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// ȷ�����
				PageController pc = new PageController(ConfirmFinishProcessor.class, ConfirmFinishRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, orderInfo.getVerificationCode());
				WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
				windowStyle.setSize(357, 160);
				MsgRequest request = new MsgRequest(pci, "��֤��Ϣ", windowStyle);
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if ((Boolean) returnValue) {
							UpdateOnlineOrderStatusTask task = new UpdateOnlineOrderStatusTask(orderInfo.getId());
							if (null != returnValue3) {
								task.setNoVerificationReason((String) returnValue2);
							}
							getContext().handle(task, UpdateOnlineOrderStatusTask.Method.Finish);
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addSeparateOrderAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// ��ֶ���
				PageController pc = new PageController(SeparateOnlineOrderProcessor.class, SeparateOnlineOrderRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, orderInfo);
				MsgRequest request = new MsgRequest(pci, "��ֶ���");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (null != returnValue) {
							boolean isSeparateSuccess = (Boolean) returnValue;
							if (isSeparateSuccess) {
								getContext().bubbleMessage(new MsgResponse(true));
							}
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		return null;
	}

	@Override
	protected String getRemark() {
		return null;
	}

	@Override
	protected String getSheetApprovalInfo() {
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return orderInfo.getBillsNo();
	}

	@Override
	protected String getSheetTitle() {
		return "���϶���";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	@Override
	protected void initSheetData() {
		GUID orderId = (GUID) getArgument();
		orderInfo = getContext().find(OnlineOrderInfo.class, orderId);
	}

	@Override
	public String getElementId(Object element) {
		OnlineOrderInfoItem item = (OnlineOrderInfoItem) element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return orderInfo.getItems();
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + orderInfo.getBillsNo() + ".xls", "application/vnd.ms-excel", 1000000,
				new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("�տ��", orderInfo.getBillsNo());
						bw.addLabel("�ͻ�", orderInfo.getRealName());
						bw.addLabel("�ֻ���", orderInfo.getConsigneeTel());
						bw.addLabel("�µ�����", DateUtil.dateFromat(orderInfo.getCreateDate()));
						bw.addLabel("��������", DateUtil.dateFromat(orderInfo.getDeliveryeDate()));
						bw.addLabel("��ע", orderInfo.getRemark());
						String[] head = new String[] { "��Ʒ���", "��Ʒ����", "��Ʒ����", "���", "��λ", "����", "����", "���" };
						List<String[]> list = new ArrayList<String[]>();
						for (Object obj : getElements(context, null)) {
							OnlineOrderInfoItem item = (OnlineOrderInfoItem) obj;
							list.add(new String[] { item.getGoodsCode(), item.getGoodsNo(), item.getGoodsName(), item.getGoodsSpec(), item.getUnit(),
									DoubleUtil.getRoundStr(item.getCount()), DoubleUtil.getRoundStr(item.getPrice()),
									DoubleUtil.getRoundStr(item.getAmount()) });
						}
						bw.setTable(head, list);
						bw.setTotalLabel("�������", DoubleUtil.getRoundStr(orderInfo.getTotalAmount()));
						try {
							bw.write(orderInfo.getBillsNo());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

	}

	@Override
	protected boolean isNeedExport() {
		if (null != orderInfo && OnlineOrderStatus.Finished.getCode().equals(orderInfo.getStatus())) {
			return true;
		}
		return false;
	}
}
