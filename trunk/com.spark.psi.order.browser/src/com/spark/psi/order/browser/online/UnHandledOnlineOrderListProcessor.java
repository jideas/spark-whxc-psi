package com.spark.psi.order.browser.online;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.OnlineBillWriter;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.OnlineOrderType;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderShowItem;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;
import com.spark.psi.publish.onlineorder.key.GetTotalMaterialsKey;

public abstract class UnHandledOnlineOrderListProcessor<Item> extends OnlinOrderListProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Search = "Search";
	public static final String ID_Search_Advanced = "Search_Advanced";

	// 最大汇总数量
	public static final int MAX_SUMMARY_ORDER_COUNT = 200;

	public static enum ColumnName {
		code, goodsName, properties, count, customerName, amount, bookingTime, deliveredTime, station,isToDoor
	}

	private Label countLabel = null;
	private Label amountLabel = null;

	private SSearchText2 search = null;
	private AdvanceSearchCondition advanceCondition = null;
	private List<OnlineOrderItem> orderList = new ArrayList<OnlineOrderItem>();

	protected LoginInfo loginInfo = null;

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
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				advanceCondition = null;
				table.render();
			}
		});

		final Button advanceButton = createControl(ID_Search_Advanced, Button.class);
		advanceButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MsgRequest request = OnlineCommon.createAdvanceRequest();
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
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

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			// 打开详情界面
			PageController pc = new PageController(OnlineOrderDetailProcessor.class, OnlineOrderDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "订单详情");
			getContext().bubbleMessage(request);
		}
	}

	protected TotalMaterialsItem getTotalMaterialsItem() {
		if (table.getSelections() == null || table.getSelections().length < 1) {
			alert("请先选择订单");
			return null;
		}
		if (isSummaryCountOutOfMax())
			return null;
		GetTotalMaterialsKey getTotalKey = buildGetTotalMaterialKey();
		TotalMaterialsItem totalMaterial = null;
		try {
			totalMaterial = context.find(TotalMaterialsItem.class, getTotalKey);
		} catch (Exception ex) {
			ex.printStackTrace();
			alert(ex.getMessage());
			return null;
		} catch (Throwable th) {
			th.printStackTrace();
			alert(th.getMessage());
			return null;
		}
		return totalMaterial;
	}

	protected GUID[] getSelectedOrderItemIds() {
		String[] selectedRowIds = table.getSelections();
		GUID[] selectedOrderIds = new GUID[selectedRowIds.length];
		int index = 0;
		for (String rowId : selectedRowIds) {
			selectedOrderIds[index] = GUID.tryValueOf(rowId);
			index++;
		}
		return selectedOrderIds;
	}

	private boolean isSummaryCountOutOfMax() {
		if (table.getSelections().length >= MAX_SUMMARY_ORDER_COUNT)
			return true;
		return false;
	}

	private GetTotalMaterialsKey buildGetTotalMaterialKey() {
		String[] selectedRowIds = table.getSelections();
		GUID[] ids = new GUID[selectedRowIds.length];
		int index = 0;
		for (String rowId : selectedRowIds) {
			ids[index] = GUID.tryValueOf(rowId);
			index++;
		}
		GetTotalMaterialsKey key = new GetTotalMaterialsKey(ids);
		return key;
	}

	@Override
	public String getElementId(Object element) {
		OnlineOrderShowItem order = (OnlineOrderShowItem) element;
		return order.getId();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		initData();
		countLabel.setText("" + orderList.size());
		amountLabel.setText(DoubleUtil.getRoundStr(getTotalAmount(orderList), 2));
		List<OnlineOrderShowItem> showItemList = getShowItemList(orderList);
		return showItemList.toArray(new OnlineOrderShowItem[0]);
	}

	private void initData() {
		GetOnlineOrderListKey key = new GetOnlineOrderListKey(0, Integer.MAX_VALUE, true);
		key.setStatus(OnlineOrderStatus.Effected);
		key.setOrderType(getType());
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
		ListEntity<OnlineOrderItem> listEntity = getContext().find(OnlineOrderListEntity.class, key);
		if (null != listEntity) {
			orderList = listEntity.getItemList();
		}

	}

	private double getTotalAmount(List<OnlineOrderItem> itemList) {
		double totalAmount = 0;
		for (OnlineOrderItem item : itemList) {
			totalAmount += item.getTotalAmount();
		}

		return totalAmount;
	}

	private List<OnlineOrderShowItem> getShowItemList(List<OnlineOrderItem> itemList) {
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

				showItem.setGoodsName(infoItem.getGoodsName());
				showItem.setGoodsSpec(infoItem.getGoodsSpec());
				showItem.setCount(infoItem.getCount());
				showItem.setToDoor(item.isToDoor());

				showItemList.add(showItem);

				infoItemIndex++;
			}
		}
		return showItemList;
	}

	// protected abstract OnlineOrderStatus getStatus();

	protected abstract OnlineOrderType getType();

	@Override
	protected void doExport() {
		final List<String[]> list = new ArrayList<String[]>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Object element : getElements(context, null)) {
			OnlineOrderShowItem order = (OnlineOrderShowItem) element;
			String s[] = null;
			if (map.get(order.getBillsNo()) == null) {
				s = new String[] { order.getBillsNo(), order.getGoodsName(), order.getGoodsSpec(),
						DoubleUtil.getRoundStr(order.getCount()), order.getRealName(),
						DoubleUtil.getRoundStr(order.getTotalAmount()),
						DateUtil.dateFromat(order.getCreateDate(), DateUtil.DATE_TIME_PATTERN),
						DateUtil.dateFromat(order.getDeliverDate(), DateUtil.DATE_TIME_PATTERN),
						order.getStationName(), "" };
			} else {
				s = new String[] { "", order.getGoodsName(), order.getGoodsSpec(),
						DoubleUtil.getRoundStr(order.getCount()), "", "", "", "", "", "" };
			}
			Integer count = map.get(order.getBillsNo());
			if (null == count) {
				count = 0;
			}
			count++;
			map.put(order.getBillsNo(), count);
			list.add(s);
		}
		for (String[] s : list) {
			s[s.length - 1] = map.get(s[0]) + "";
			map.remove(s[0]);
		}
		Display.getCurrent().exportFile(getExportFileTitle() + ".xls", "application/vnd.ms-excel", 100000,
				new ExporterWithContext() {
					public void run(Context context, OutputStream outputStream) throws IOException {
						OnlineBillWriter writer = new OnlineBillWriter(outputStream);
						writer.setData(list);
						try {
							writer.write(getExportFileTitle());
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
	}
}
