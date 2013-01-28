package com.spark.psi.order.browser.delivery;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

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
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SSpanProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.AbstractFormProcessor;
import com.spark.psi.base.browser.FormPrintEntity;
import com.spark.psi.base.browser.PSIPrinter;
import com.spark.psi.base.browser.PrintColumn;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.deliver.entity.DeliverInfo;
import com.spark.psi.publish.deliver.entity.DeliverInfoItem;
import com.spark.psi.publish.deliver.task.UpdateDeliverStatausTask;

public class DeliverDetailPageProcessor extends AbstractFormProcessor {
	
	public static final String ID_Label_StationName = "Label_StationName";
	public static final String ID_Label_Address = "Label_Address";
	public static final String ID_Label_DeliverInfo = "Label_DeliverInfo";
	public static final String ID_Text_PackageCount = "Text_PackageCount";
	public static final String ID_Label_Status = "Label_Status";
	public static final String ID_Label_OtherInfo = "Label_OtherInfo";
	public static final String ID_Button_ConfirmDeliver = "Button_ConfirmDeliver";
	public static final String ID_Button_Exception = "Button_Exception";
	public static final String ID_Button_ConfrimArrive = "Button_ConfirmArrive";
	public static final String ID_Button_Handle = "Button_Handle";
	public static final String ID_Button_Print = "Button_Print";
	public static final String ID_Area_Hide = "Area_Hide";
	public static final String ID_Table = "Table";
	
	public static enum ColumnName {
		sheetNo, goodsName, goodsSpec, goodsCount, memeberName
		//, amount
	}
	
	protected STable table  =  null;
	
	protected DeliverInfo deliverInfo;
	
	private LoginInfo loginInfo;
	
	private Text packageText;
	
	@Override
	public void init(final Situation context) {
		super.init(context);
		GUID deliverId = (GUID)getArgument();
		deliverInfo = context.find(DeliverInfo.class, deliverId);
		loginInfo = context.find(LoginInfo.class);
	}
	
	@Override
	public void process(Situation context) {
		super.process(context);
		table = createControl(ID_Table, STable.class);
		table.setContentProvider(new TableContentProvider());
		table.render();
		
		showInfo();
		addActions();
	}
	
	private void showInfo() {
		final Label stationLabel = createControl(ID_Label_StationName, Label.class);
		packageText = createControl(ID_Text_PackageCount, Text.class);
		final Label addressLabel = createControl(ID_Label_Address, Label.class);
		final Label statusLabel = createControl(ID_Label_Status, Label.class);
		final Label otherLabel = createLabelControl(ID_Label_OtherInfo);
		
		String otherInfo = "";
		if (!StringUtils.isEmpty(deliverInfo.getCreator())) {
			otherInfo += "制单：" + deliverInfo.getCreator() + "(" + DateUtil.dateFromat(deliverInfo.getCreateDate()) + ")";
		}
		otherLabel.setText(otherInfo);
		stationLabel.setText(deliverInfo.getStationName());
		if(DeliverStatus.Deliver.equals(deliverInfo.getStatus()))
		{
			registerNotEmptyValidator(packageText, "包装箱数");
		}
		else
		{
			packageText.setEnabled(false);
			packageText.setText("" + deliverInfo.getDeliveredPackageCount());
		}
		
		addressLabel.setText(deliverInfo.getAddress());
		statusLabel.setText(deliverInfo.getStatus().getName());
	}
	
	private void addActions() {
		Button button = null;
		switch(deliverInfo.getStatus()) {
		case Deliver:
			Button confirmButton = createControl(ID_Button_ConfirmDeliver, Button.class);
			Button printButton = createControl(ID_Button_Print, Button.class);
			if (loginInfo.hasAuth(Auth.SubFunction_DeliveryOrder_Deliver)) {
				addConfrimDeliverActionListener(confirmButton);
				addPrintActionListener(printButton);
			} else {
				confirmButton.dispose();
				printButton.dispose();
			}
			break;
		case Delivering:
			Button exceptionButton = createControl(ID_Button_Exception, Button.class);
			Button arriveButton = createControl(ID_Button_ConfrimArrive, Button.class);
			if (loginInfo.hasAuth(Auth.SubFunction_DeliveryOrder_Confirm)) {
				addExceptionActionListener(exceptionButton);
				addConfrimArriveListener(arriveButton);
			} else {
				exceptionButton.dispose();
				arriveButton.dispose();
			}
			break;
		case Exception:
			button = createControl(ID_Button_Handle, Button.class);
			if (loginInfo.hasAuth(Auth.SubFunction_DeliveryOrder_HandlException)) {
				addHandleExceptionListener(button);
			} else {
				button.dispose();
			}
			break;
		}
	}
	
	private void addPrintActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 打印
				PrintColumn[] columns = new PrintColumn[6];
				columns[0] = new PrintColumn("订单编号", 150, JWT.LEFT);
				columns[1] = new PrintColumn("商品名称", 200, JWT.LEFT);
				columns[2] = new PrintColumn("规格", 70, JWT.LEFT);
				columns[3] = new PrintColumn("数量", 60, JWT.RIGHT);
				columns[4] = new PrintColumn("会员", 100, JWT.CENTER);
				columns[5] = new PrintColumn("金额", 100, JWT.RIGHT);
				String tableTitle0 = "单据编号：" + deliverInfo.getSheetNo() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门店：" + deliverInfo.getStationName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
						+ "地址：" + deliverInfo.getAddress()
						+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;配送时间：";
//				String tableTitle1 = "地址：" + deliverInfo.getAddress();
//				String tableTitle2 = "包装箱数：" + deliverInfo.getDeliveredPackageCount();
				FormPrintEntity fpe = new FormPrintEntity("配送单", columns, getShowItems(), tableTitle0);
				fpe.setLabelProvider(new SLabelProvider() {
					
					public String getToolTipText(Object element, int columnIndex) {
						return null;
					}
					
					public String getText(Object element, int columnIndex) {
						DeliverDetailShowItem item = (DeliverDetailShowItem)element;
						switch(columnIndex) {
						case 0:
							return item.getSheetNo().split("WSDD")[1];
						case 1:
							return item.getGoodsName();
						case 2:
							return item.getGoodsSpec();
						case 3:
							return DoubleUtil.getRoundStr(item.getGoodsCount(), 0);
						case 4:
							return item.getMemeberName();
						case 5:
							return DoubleUtil.getRoundStr(item.getAmount());
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
				fpe.setSpanProvider(new SSpanProvider() {
					
					public int getColSpan(Object element, int columnIndex) {
						return 0;
					}

					public int getMaxRowSpan(Object element) {
						DeliverDetailShowItem item = (DeliverDetailShowItem)element;
						return item.getRowSpan();
					}

					public int getRowSpan(Object element, int columnIndex) {
						DeliverDetailShowItem item = (DeliverDetailShowItem)element;
						switch(columnIndex) {
						case 0:
						case 4:
						case 5:
							return item.getRowSpan();
						}
						return 0;
					}

					public boolean isSpanAlready(Object element, int columnIndex) {
						DeliverDetailShowItem item = (DeliverDetailShowItem)element;
						if (item.isFirstItem()) return false;
						switch(columnIndex) {
						case 0:
						case 4:
						case 5:
							return true;
						default:
							return false;
						}
					}
				});

				DeliverPrintContentProvider pProvider = new DeliverPrintContentProvider(fpe);
				PSIPrinter printer = new PSIPrinter(pProvider);
				printer.setNeedPreview(true);
				Composite hideArea = createControl(ID_Area_Hide, Composite.class);
				Browser browser = new Browser(hideArea);
				browser.setLayoutData(GridData.INS_FILL_BOTH);
				browser.applyHTML(printer.getPrinterContent());
				hideArea.layout();
			}
		});
	}
	
	private void addConfrimDeliverActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!validateInput()) {
					return;
				}
				// 确认配送
				UpdateDeliverStatausTask task = new UpdateDeliverStatausTask(deliverInfo.getId());
				task.setPackageCount(Integer.valueOf(packageText.getText()));
				try
				{
					getContext().handle(task, UpdateDeliverStatausTask.Method.Deliver);
				}
				catch (Throwable t) {
					alert(t.getMessage());
					return;
				}
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
	private void addExceptionActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 配送异常
				PageController pc = new PageController(ExceptionDescriptionPageProcessor.class, ExceptionDescriptionPageRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, ExceptionDescriptionPageProcessor.Method.Exception);
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(360, 200);
				MsgRequest request = new MsgRequest(pci, "异常描述", windowStyle);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						String countStr = (String)returnValue;
						String description = (String)returnValue2;
						UpdateDeliverStatausTask task = new UpdateDeliverStatausTask(deliverInfo.getId());
						task.setDescription(description);
						if (!StringUtils.isEmpty(countStr)) {
							task.setReceivedPackageCount(Integer.parseInt(countStr));
						}
						getContext().handle(task, UpdateDeliverStatausTask.Method.Exception);
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}
	
	private void addConfrimArriveListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确认到货
				UpdateDeliverStatausTask task = new UpdateDeliverStatausTask(deliverInfo.getId());
				try
				{
					getContext().handle(task, UpdateDeliverStatausTask.Method.Arrive);
				}
				catch (Throwable t) {
					alert(t.getMessage());
					return;
				}
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
	private void addHandleExceptionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 处理异常
				PageController pc = new PageController(ExceptionDescriptionPageProcessor.class, ExceptionDescriptionPageRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, ExceptionDescriptionPageProcessor.Method.Handle);
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(360, 200);
				MsgRequest request = new MsgRequest(pci, "异常处理", windowStyle);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						String formula = (String)returnValue;
						UpdateDeliverStatausTask task = new UpdateDeliverStatausTask(deliverInfo.getId());
						task.setFormula(formula);
						getContext().handle(task, UpdateDeliverStatausTask.Method.Handle);
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}
	
	protected DeliverDetailShowItem[] getShowItems() {
		List<DeliverDetailShowItem> itemLsit = new ArrayList<DeliverDetailShowItem>();
		for (DeliverInfoItem dItem : deliverInfo.getItems()) {
			DeliverDetailShowItem item = null;
			int detIndex = 0;
			for (DeliverInfoItem.Item det : dItem.getItems()) {
				int rowSpan = 0;
				// 同一DeliverInfoItem的记录显示多行时，行id用"itemID_index"的方式显示
				String id = dItem.getId().toString();
				boolean isFirstItem = false;
				if (0 == detIndex) {
					rowSpan = dItem.getItems().length;
					isFirstItem = true;
				} else {
					id = id + "_" + detIndex;
				}
				
				item = new DeliverDetailShowItem(id, rowSpan, isFirstItem);
				item.setAmount(dItem.getOrderAmount());
				item.setMemeberName(dItem.getMemberRealName());
				item.setSheetNo(dItem.getOnlineOrderNo());
				
				item.setGoodsCount(det.getCount());
				item.setGoodsName(det.getGoodsName());
				item.setGoodsSpec(det.getGoodsSpec());
				
				itemLsit.add(item);
				detIndex++;
			}
		}
		return itemLsit.toArray(new DeliverDetailShowItem[0]);
	}
	
	private class TableContentProvider implements SContentProvider {

		public String getElementId(Object element) {
			DeliverDetailShowItem item = (DeliverDetailShowItem)element;
			return item.getId();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return getShowItems();
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}
		
	}

	@Override
	protected String getSheetNumber() {
		return deliverInfo.getSheetNo();
	}

	@Override
	protected String getSheetTitle() {
		return "配送单";
	}
	
}
