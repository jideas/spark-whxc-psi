package com.spark.psi.order.browser.online;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIMultiItemListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderShowItem;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;

public class DeliveringOnlinOrderListProcessor<Item> extends
			PSIMultiItemListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Search = "Search";
	public static final String ID_Search_Advanced = "Search_Advanced";
	
	public static enum ColumnName {
		code, goodsName, properties, count, customerName, 
		amount, station, deliverTime, deliverPerson,isToDoor
	}
	
	private Label countLabel       =  null;
	private Label amountLabel      =  null;
	
	private SSearchText2 search   = null;
	private AdvanceSearchCondition advanceCondition  = null;
	private List<OnlineOrderItem> orderList = new ArrayList<OnlineOrderItem>();
	
	
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
	
	@Override
	public String getElementId(Object element) {
		OnlineOrderShowItem item = (OnlineOrderShowItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		initData();
		countLabel.setText("" + orderList.size());
		amountLabel.setText("" + DoubleUtil.getRoundStr(getTotalAmount(orderList),2));
		List<OnlineOrderShowItem> showItemList = getShowItemList(orderList);
		return showItemList.toArray(new OnlineOrderShowItem[0]);
	}

	private void initData() {
		GetOnlineOrderListKey key = new GetOnlineOrderListKey(0, Integer.MAX_VALUE, true);
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
			key.setAdvanceValues(av);
		} else {
			key.setSearchText(search.getText());
		}
		key.setStatus(OnlineOrderStatus.Delivering);
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
				showItem.setDeliverDate(item.getConsignedDate());
				showItem.setStationName(item.getStationName());
				showItem.setTotalAmount(item.getTotalAmount());
				showItem.setConsignor(item.getConsignor());
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
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			// 打开详情界面
			PageController pc = new PageController(OnlineOrderDetailProcessor.class, OnlineOrderDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
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

	@Override
	protected String[] getElementActionIds(Object element) {
//		return new String[] {Action.Detail.name()};
		return null;
	}

	@Override
	public String[] getTableActionIds() {
//		return new String[] {Action.Detail.name()};
		return null;
	}

	@Override
	protected String getExportFileTitle() {
		return "网上订单";
	}
	
	
}
