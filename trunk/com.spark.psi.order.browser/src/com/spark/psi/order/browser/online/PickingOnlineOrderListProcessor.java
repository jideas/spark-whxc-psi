package com.spark.psi.order.browser.online;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.spark.b2c.publish.base.member.entity.MemberAccountInfo;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.FormPrintEntity;
import com.spark.psi.base.browser.PSIMultiItemListPageProcessor;
import com.spark.psi.base.browser.PSIPrinter;
import com.spark.psi.base.browser.PrintColumn;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderShowItem;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;

public class PickingOnlineOrderListProcessor<Item> extends
		PSIMultiItemListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Search = "Search";
	public static final String ID_Search_Advanced = "Search_Advanced";
	public static final String ID_Button_Distribute = "Button_Distribute";
	public static final String ID_Button_Print = "Button_Print";
	public static final String ID_Button_Summary = "Button_Summary";
	public static final String ID_Area_Hide = "Area_Hide";

	public static enum ColumnName {
		code, goodsName, properties, count, customerName, amount, bookingTime, deliveredTime, station, isToDoor
	}

	private Label countLabel = null;
	private Label amountLabel = null;

	private SSearchText2 search = null;
	private AdvanceSearchCondition advanceCondition = null;
	private List<OnlineOrderItem> orderList = new ArrayList<OnlineOrderItem>();

	private LoginInfo loginInfo = null;

	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	public void process(final Situation context) {
		super.process(context);
		countLabel = createControl(ID_Label_Count, Label.class);
		amountLabel = createControl(ID_Label_Amount, Label.class);

		final Button button = createControl(ID_Button_Distribute, Button.class);
		final Button printbutton = createButtonControl(ID_Button_Print);
		final Button summaryButton = createButtonControl(ID_Button_Summary);
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_Deliver)) {
			addDeliverAction(button);
			addPrintAction(printbutton);

		} else {
			button.dispose();
			printbutton.dispose();

		}
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_Deliver)
				|| loginInfo.hasAuth(Auth.Distribute)) {
			addSummaryAction(summaryButton);
		} else {
			summaryButton.dispose();
		}

		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				advanceCondition = null;
				table.render();
			}
		});
		final Button advanceButton = createControl(ID_Search_Advanced,
				Button.class);
		advanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MsgRequest request = OnlineCommon.createAdvanceRequest();
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (null != returnValue) {
							advanceCondition = (AdvanceSearchCondition) returnValue;
							table.render();
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addSummaryAction(Button button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 统计
				PageController pc = new PageController(
						OnlineGoodsSummaryProcessor.class,
						OnlineGoodsSummaryRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "商品统计");
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addPrintAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 批量打印
				if (null == table.getSelections()
						|| table.getSelections().length < 1) {
					alert("请先选择订单。");
					return;
				}
				OnlineOrderItem[] selectedItems = getSelectedItemList();
				FormPrintEntity[] printEntities = new FormPrintEntity[selectedItems.length];
				for (int itemIndex = 0; itemIndex < selectedItems.length; itemIndex++) {
					OnlineOrderItem item = selectedItems[itemIndex];
					printEntities[itemIndex] = getPrintEntityByOnineItem(item);
				}
				Composite hideArea = createControl(ID_Area_Hide,
						Composite.class);
				OnlineOrderPrintConentProvider printProvider = new OnlineOrderPrintConentProvider(
						printEntities);
				PSIPrinter printer = new PSIPrinter(printProvider);
				hideArea.clear();
				Browser browser = new Browser(hideArea);
				browser.setLayoutData(GridData.INS_FILL_BOTH);
				browser.applyHTML(printer.getPrinterContent());
				hideArea.layout();
			}
		});
	}

	private FormPrintEntity getPrintEntityByOnineItem(OnlineOrderItem item) {
		PrintColumn[] columns = new PrintColumn[4];
		columns[0] = new PrintColumn("商品名称", PrintColumn.NAME_COLUMN_WIDTH,
				JWT.LEFT);
		columns[1] = new PrintColumn("规格", PrintColumn.SPEC_COLUMN_WIDTH,
				JWT.CENTER);
		columns[2] = new PrintColumn("数量", PrintColumn.COUNT_COLUMN_WIDTH,
				JWT.CENTER);
		columns[3] = new PrintColumn("金额", PrintColumn.AMOUNT_COLUMN_WIDTH,
				JWT.LEFT);
		// String tableTitle0 = "客户：" + item.getRealName();
		// MemberAccountInfo memberAccount =
		// getContext().find(MemberAccountInfo.class, item.getMemberId());
		// String memberBalance = null;
		// if (null != memberAccount) {
		// memberBalance = "帐户余额：" +
		// DoubleUtil.getRoundStr(memberAccount.getMoneyBalance()) + "元";
		// memberBalance += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩余积分：" +
		// DoubleUtil.getRoundStr(memberAccount.getVantages(), 0);
		// }
		// String tableTitle1 = "联系电话：" + item.getConsigneeTel();
		// String tableTitle2 = "订单编号：" + item.getBillsNo().split("WSDD")[1];
		// String tableTitle3 = "下单时间：" +
		// DateUtil.dateFromat(item.getCreateDate(),
		// DateUtil.DATE_TIME_PATTERN);
		// String tableTitle4 = "站点：" + item.getStationName();
		// String tableTitle5 = "收货地址：" + item.getAddress();
		// String tableTitle6 = "收货日期：" +
		// DateUtil.dateFromat(item.getDeliveryeDate(),
		// DateUtil.DATE_TIME_PATTERN);
		// String[] titles = null;
		// if (memberBalance == null) {
		// titles = new String[] {tableTitle0, tableTitle1, tableTitle2,
		// tableTitle3, tableTitle4, tableTitle5, tableTitle6};
		// } else {
		// titles = new String[] {tableTitle0, memberBalance, tableTitle1,
		// tableTitle2, tableTitle3, tableTitle4, tableTitle5, tableTitle6};
		// }
		String tableTitle0 = "服务站点：<font size='3'><strong>"
				+ item.getStationName() + "</strong></font>";
		String tableTitle1 = "订单编号：" + item.getBillsNo().split("WSDD")[1];
		String tableTitle2 = "收货人：" + item.getConsignee();
		String tableTitle3 = "联系电话：" + item.getConsigneeTel();
		String tableTitle4 = "收货地址：" + item.getAddress();
		String tableTitle5 = "收货日期："
				+ DateUtil.dateFromat(item.getDeliveryeDate(),
						DateUtil.DATE_TIME_PATTERN);
		String[] titles = { tableTitle0, tableTitle1, tableTitle2, tableTitle3,
				tableTitle4, tableTitle5 };
		// double totalCount = 0.0;
		// double totalAmount = 0.0;
		// for (OnlineOrderInfoItem oItem : item.getItems()) {
		// totalCount += oItem.getCount();
		// totalAmount += oItem.getAmount();
		// }
		// summaryInfo = "总件数：" + DoubleUtil.getRoundStr(totalCount) +
		// "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总金额：" +
		// DoubleUtil.getRoundStr(totalAmount);
		// String summaryInfo = "";
		// 商品数：2 件数：3 合计：20.00
		// 账户余额：1,558.90
		// 可用积分：800
		// 打印日期：2013-01-27
		double totalCount = 0.0;
		double totalAmount = 0.0;
		for (OnlineOrderInfoItem oItem : item.getItems()) {
			totalCount += oItem.getCount();
			totalAmount += oItem.getAmount();
		}
		String summaryInfo = "";
		summaryInfo = "商品数：" + item.getItems().length
				+ "&nbsp;&nbsp;&nbsp;总件数："
				+ DoubleUtil.getRoundStr(totalCount, 0)
				+ "&nbsp;&nbsp;&nbsp;合计：" + DoubleUtil.getRoundStr(totalAmount);
		MemberAccountInfo memberAccount = getContext().find(
				MemberAccountInfo.class, item.getMemberId());
		List<String> footerList = new ArrayList<String>();
		footerList.add(summaryInfo);
		if (null != memberAccount) {
			footerList.add("帐户余额："
					+ DoubleUtil.getRoundStr(memberAccount.getMoneyBalance())
					+ "元");
			footerList.add("剩余积分："
					+ DoubleUtil.getRoundStr(memberAccount.getVantages(), 0));
		}
		footerList.add("打印日期：" + DateUtil.dateFromat(new Date().getTime()));
		FormPrintEntity entity = new FormPrintEntity("网上订单", columns, item
				.getItems(), titles);
		// entity.setSummaryInfo(summaryInfo);
		entity.setTableFooters(footerList.toArray(new String[0]));
		entity.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}

			public String getText(Object element, int columnIndex) {
				OnlineOrderInfoItem item = (OnlineOrderInfoItem) element;
				switch (columnIndex) {
				case 0:
					// if (item.getGoodsName().length() > 12) {
					// return item.getGoodsName().substring(0, 12);
					// }
					return item.getGoodsName();
				case 1:
					return item.getGoodsSpec();
				case 2:
					return DoubleUtil.getRoundStr(item.getCount(), 0);
				case 3:
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
		return entity;
	}

	private void addDeliverAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 配送
				if (null == table.getSelections()
						|| table.getSelections().length < 1) {
					alert("请先选择订单。");
					return;
				}
				OnlineOrderItem[] selectedItems = getSelectedItemList();
				CreateDeliverItem[] cItems = getCreateDeliverItems(selectedItems);
				PageController pc = new PageController(
						DeliverPageProcessor.class, DeliverPageRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc,
						cItems);
				MsgRequest request = new MsgRequest(pci, "生成配送单");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				context.bubbleMessage(request);
			}
		});
	}

	@Override
	public String getElementId(Object element) {
		OnlineOrderShowItem order = (OnlineOrderShowItem) element;
		return order.getId();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tableState) {
		initData();
		countLabel.setText("" + orderList.size());
		countLabel.setWidth(200);
		amountLabel.setText(""
				+ DoubleUtil.getRoundStr(getTotalAmount(orderList), 2));
		amountLabel.setWidth(200);
		List<OnlineOrderShowItem> showItemList = getShowItemList(orderList);
		return showItemList.toArray(new OnlineOrderShowItem[0]);
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			// 打开详情界面
			PageController pc = new PageController(
					OnlineOrderDetailProcessor.class,
					OnlineOrderDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID
					.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "订单详情");
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	private OnlineOrderItem[] getSelectedItemList() {
		String[] rowIds = table.getSelections();
		OnlineOrderItem[] items = new OnlineOrderItem[rowIds.length];
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			items[rowIndex] = getOrderItemById(rowIds[rowIndex]);
		}
		return items;
	}

	private OnlineOrderItem getOrderItemById(String id) {
		for (OnlineOrderItem item : orderList) {
			if (item.getId().toString().equals(id)) {
				return item;
			}
		}
		return null;
	}

	private CreateDeliverItem[] getCreateDeliverItems(OnlineOrderItem[] orders) {
		Map<GUID, List<OnlineOrderItem>> stationOrders = new HashMap<GUID, List<OnlineOrderItem>>();
		for (OnlineOrderItem item : orders) {
			if (stationOrders.containsKey(item.getStationId())) {
				stationOrders.get(item.getStationId()).add(item);
			} else {
				List<OnlineOrderItem> iOrderList = new ArrayList<OnlineOrderItem>();
				iOrderList.add(item);
				stationOrders.put(item.getStationId(), iOrderList);
			}
		}
		Iterator<GUID> it = stationOrders.keySet().iterator();
		List<CreateDeliverItem> itemList = new ArrayList<CreateDeliverItem>();
		CreateDeliverItem cItem = null;
		while (it.hasNext()) {
			GUID stationId = it.next();
			cItem = new CreateDeliverItem(stationId);
			cItem.setOrders(stationOrders.get(stationId).toArray(
					new OnlineOrderItem[0]));
			itemList.add(cItem);
		}
		return itemList.toArray(new CreateDeliverItem[0]);
	}

	private double getTotalAmount(List<OnlineOrderItem> itemList) {
		double totalAmount = 0;
		for (OnlineOrderItem item : itemList) {
			totalAmount += item.getTotalAmount();
		}

		return totalAmount;
	}

	private List<OnlineOrderShowItem> getShowItemList(
			List<OnlineOrderItem> itemList) {
		List<OnlineOrderShowItem> showItemList = new ArrayList<OnlineOrderShowItem>();
		for (OnlineOrderItem item : itemList) {
			OnlineOrderShowItem showItem = null;
			int infoItemIndex = 0;
			for (OnlineOrderInfoItem infoItem : item.getItems()) {
				int rowSpan = 0;
				// 同一OnlineOrderItem的记录显示多行时，行id用"itemID_index"的方式显示
				String id = item.getId().toString();
				boolean isFirstItem = false;
				if (0 == infoItemIndex) {
					rowSpan = item.getItems().length;
					isFirstItem = true;
				} else {
					id = id + "_" + infoItemIndex;
				}
				showItem = new OnlineOrderShowItem(id, rowSpan, isFirstItem);
				showItem.setBillsNo(item.getBillsNo());
				showItem.setRealName(item.getRealName());
				showItem.setCreateDate(item.getCreateDate());
				showItem.setDeliverDate(item.getDeliveryeDate());
				showItem.setStationName(item.getStationName());
				showItem.setTotalAmount(item.getTotalAmount());
				showItem.setConsignor(item.getDeliverPerson());
				showItem.setToDoor(item.isToDoor());

				showItem.setGoodsName(infoItem.getGoodsName());
				showItem.setGoodsSpec(infoItem.getGoodsSpec());
				showItem.setCount(infoItem.getCount());

				showItemList.add(showItem);

				infoItemIndex++;
			}
		}
		return showItemList;
	}

	private void initData() {
		GetOnlineOrderListKey key = new GetOnlineOrderListKey(0,
				Integer.MAX_VALUE, true);
		if (null != advanceCondition) {
			key.setSearchText(null);
			GetOnlineOrderListKey.AdvanceValues av = key.new AdvanceValues();
			av.setBillsNo(advanceCondition.getBillsNo());
			av.setCreateDateBegin(advanceCondition.getCreateDateBegin());
			av.setCreateDateEnd(advanceCondition.getCreateDateEnd());
			av.setDeliveryeDateBegin(advanceCondition.getDeliveryeDateBegin());
			av.setDeliveryeDateEnd(advanceCondition.getDeliveryeDateEnd());
			av.setRealName(advanceCondition.getRealName());
			av.setStationName(advanceCondition.getStationName());
			av.setDeliverTime(advanceCondition.getDeliverTime());
			key.setAdvanceValues(av);
		} else {
			key.setSearchText(search.getText());
		}
		key.setStatus(OnlineOrderStatus.Picking);
		ListEntity<OnlineOrderItem> listEntity = getContext().find(
				OnlineOrderListEntity.class, key);
		if (null != listEntity) {
			orderList = listEntity.getItemList();
		}
	}

	@Override
	protected String getExportFileTitle() {
		return "网上订单";
	}
}
