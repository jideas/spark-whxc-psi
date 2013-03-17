package com.spark.psi.order.browser.online;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.order.browser.common.DistributeGoodsItem;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.OnlineOrderType;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.produceorder.task.CreateProduceOrderTask;

public class EffectedOnlineOrderListProcessor<Item> extends
	UnHandledOnlineOrderListProcessor<Item> { 
	public static final String ID_Button_Summarizing = "Button_Summarizing";
	public static final String ID_Button_Summary = "Button_Summary";
	
	@Override
	public void process(final Situation context) {
		super.process(context);
		final Button summaryBtn = createControl(ID_Button_Summarizing, Button.class);
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_Summary)) {
			addSummaryAction(summaryBtn);
		} else {
			summaryBtn.dispose();
		}
		
		final Button button = createButtonControl(ID_Button_Summary);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 统计商品
				PageController pc = new PageController(
						OnlineGoodsSummaryProcessor.class,
						OnlineGoodsSummaryRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, OnlineOrderStatus.Effected);
				MsgRequest request = new MsgRequest(pci, "商品统计（新订单）");
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addSummaryAction(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 汇总
				TotalMaterialsItem totalMaterial = getTotalMaterialsItem();
				if (null == totalMaterial) return;
				if (totalMaterial.getMaterials() == null 
						|| totalMaterial.getMaterials().length < 1) {
					// 没有材料,直接生产
					CreateProduceOrderTask task = new CreateProduceOrderTask();
					CreateProduceOrderTask.GoodsItem[] goodsItems = new CreateProduceOrderTask.GoodsItem[totalMaterial.getGoods().length];
					for (int index = 0; index < goodsItems.length; index++) { 
						CreateProduceOrderTask.GoodsItem cGoodsItem = task.new GoodsItem();
						TotalMaterialsItem.GoodsItem tGoodsItem = totalMaterial.getGoods()[0];
						cGoodsItem.setBomId(tGoodsItem.getBomId());
						cGoodsItem.setCount(tGoodsItem.getCount());
						cGoodsItem.setGoodsCode(tGoodsItem.getGoodsCode());
						cGoodsItem.setGoodsId(tGoodsItem.getGoodsId());
						cGoodsItem.setGoodsName(tGoodsItem.getGoodsName());
						cGoodsItem.setGoodsNo(tGoodsItem.getGoodsNo());
						cGoodsItem.setGoodsScale(tGoodsItem.getGoodsScale());
						cGoodsItem.setGoodsSpec(tGoodsItem.getGoodsSpec());
						cGoodsItem.setUnit(tGoodsItem.getUnit());
						//TODO 锁定库存
//						cGoodsItem.setLockCount(tGoodsItem.getLockCount());
						goodsItems[index] = cGoodsItem;
					}
					task.setGoods(goodsItems);
					task.setOnlineOrderIds(getSelectedOrderItemIds());
					task.setMaterials(null);
					task.setGoodsCount(task.getGoods().length);
					task.setPlanDate(0);
					task.setRemark(null);
					task.setStatus(ProduceOrderStatus.Producing);
					getContext().handle(task);
				} else {
//					PageController pc = new PageController(DistributeOnlineOrderProcessor.class, DistributeOnlineOrderRender.class);
					PageController pc = new PageController(SummaryOnlineOrderGoodsProcessor.class, SummaryOnlineOrderGoodsRender.class);
					DistributeGoodsItem[] disItems = new DistributeGoodsItem[totalMaterial.getGoods().length];
					DistributeGoodsItem disItem = null;
					int itemIndex = 0;
					for (TotalMaterialsItem.GoodsItem kItem : totalMaterial.getGoods()) {
						disItem = convetKeyItemToDisGoodsItem(kItem);
						disItems[itemIndex] = disItem;
						itemIndex++;
					}
					PageControllerInstance pci = new PageControllerInstance(pc, totalMaterial.getMaterials(), getSelectedOrderItemIds(), disItems);
					MsgRequest request = new MsgRequest(pci, "汇总信息");
					request.setResponseHandler(new ResponseHandler() {
						
						public void handle(Object returnValue, Object returnValue2,
								Object returnValue3, Object returnValue4) {
							table.render();
						}
					});
					getContext().bubbleMessage(request);
				}
			}
		});
	}
	
	private DistributeGoodsItem convetKeyItemToDisGoodsItem(TotalMaterialsItem.GoodsItem kItem) {
		DistributeGoodsItem item = new DistributeGoodsItem();
		item.setBomId(kItem.getBomId());
		item.setCount(kItem.getCount());
		item.setGoodsCode(kItem.getGoodsCode());
		item.setGoodsId(kItem.getGoodsId());
		item.setGoodsName(kItem.getGoodsName());
		item.setGoodsNo(kItem.getGoodsNo());
		item.setGoodsScale(kItem.getGoodsScale());
		item.setGoodsSpec(kItem.getGoodsSpec());
		item.setUnit(kItem.getUnit());
		return item;
	}
	
	@Override
	protected OnlineOrderType getType() {
		return OnlineOrderType.Common;
	}

	
//	private void addSummaryAction(Button button) {
//		button.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				// 汇总
//				if (table.getSelections() == null || table.getSelections().length < 1) {
//					alert("请先选择订单");
//					return ;
//				}
//				if (isSummaryCountOutOfMax()) return;
//				GetTotalMaterialsKey getTotalKey = buildGetTotalMaterialKey();
//				TotalMaterialsItem totalMaterial = null;
//				try {
//					totalMaterial =  context.find(TotalMaterialsItem.class, getTotalKey);
//				} catch(Exception ex) {
//					ex.printStackTrace();
//					alert(ex.getMessage());
//					return;
//				} catch(Throwable th) {
//					th.printStackTrace();
//					alert(th.getMessage());
//					return;
//				}
//				if (totalMaterial.getMaterials() == null 
//						|| totalMaterial.getMaterials().length < 1) {
//					// 没有材料,直接生产
//					CreateProduceOrderTask task = new CreateProduceOrderTask();
//					CreateProduceOrderTask.GoodsItem[] goodsItems = new CreateProduceOrderTask.GoodsItem[totalMaterial.getGoods().length];
//					for (int index = 0; index < goodsItems.length; index++) { 
//						CreateProduceOrderTask.GoodsItem cGoodsItem = task.new GoodsItem();
//						TotalMaterialsItem.GoodsItem tGoodsItem = totalMaterial.getGoods()[0];
//						cGoodsItem.setBomId(tGoodsItem.getBomId());
//						cGoodsItem.setCount(tGoodsItem.getCount());
//						cGoodsItem.setGoodsCode(tGoodsItem.getGoodsCode());
//						cGoodsItem.setGoodsId(tGoodsItem.getGoodsId());
//						cGoodsItem.setGoodsName(tGoodsItem.getGoodsName());
//						cGoodsItem.setGoodsNo(tGoodsItem.getGoodsNo());
//						cGoodsItem.setGoodsScale(tGoodsItem.getGoodsScale());
//						cGoodsItem.setGoodsSpec(tGoodsItem.getGoodsSpec());
//						cGoodsItem.setUnit(tGoodsItem.getUnit());
//						cGoodsItem.setLockCount(tGoodsItem.getLockCount());
//						goodsItems[index] = cGoodsItem;
//					}
//					task.setGoods(goodsItems);
//					task.setOnlineOrderIds(getSelectedOrderItemIds());
//					task.setMaterials(null);
//					task.setGoodsCount(task.getGoods().length);
//					task.setPlanDate(0);
//					task.setRemark(null);
//					task.setStatus(ProduceOrderStatus.Producing);
//					getContext().handle(task);
//				} else {
//					PageController pc = new PageController(DistributeOnlineOrderProcessor.class, DistributeOnlineOrderRender.class);
//					DistributeGoodsItem[] disItems = new DistributeGoodsItem[totalMaterial.getGoods().length];
//					DistributeGoodsItem disItem = null;
//					int itemIndex = 0;
//					for (TotalMaterialsItem.GoodsItem kItem : totalMaterial.getGoods()) {
//						disItem = convetKeyItemToDisGoodsItem(kItem);
//						disItems[itemIndex] = disItem;
//						itemIndex++;
//					}
//					PageControllerInstance pci = new PageControllerInstance(pc, totalMaterial.getMaterials(), getSelectedOrderItemIds(), disItems);
//					MsgRequest request = new MsgRequest(pci, "汇总信息");
//					request.setResponseHandler(new ResponseHandler() {
//						
//						public void handle(Object returnValue, Object returnValue2,
//								Object returnValue3, Object returnValue4) {
//							table.render();
//						}
//					});
//					getContext().bubbleMessage(request);
//				}
//			}
//		});
//	}
	
//	private boolean isSummaryCountOutOfMax() {
//		if (table.getSelections().length >= MAX_SUMMARY_ORDER_COUNT) return true;
//		return false;
//	}
//	
//	@Override
//	public String getElementId(Object element) {
//		OnlineOrderShowItem order = (OnlineOrderShowItem)element;
//		return order.getId();
//	}
//
//	@Override
//	public Object[] getElements(Context context, STableStatus tableState) {
//		initData();
//		countLabel.setText("" + orderList.size());
//		amountLabel.setText(DoubleUtil.getRoundStr(getTotalAmount(orderList), 2));
//		List<OnlineOrderShowItem> showItemList = getShowItemList(orderList);
//		return showItemList.toArray(new OnlineOrderShowItem[0]);
//	}
//	
//	@Override
//	public void actionPerformed(String rowId, String actionName,
//			String actionValue) {
//		if (Action.Detail.name().equals(actionName)) {
//			// 打开详情界面
//			PageController pc = new PageController(OnlineOrderDetailProcessor.class, OnlineOrderDetailRender.class);
//			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
//			MsgRequest request = new MsgRequest(pci, "订单详情");
//			getContext().bubbleMessage(request);
//		}
//	}
//
//	private void initData() {
//		GetOnlineOrderListKey key = new GetOnlineOrderListKey(0, Integer.MAX_VALUE, true);
//		key.setStatus(OnlineOrderStatus.Effected);
//		if (null != advanceCondition) {
//			key.setSearchText(null);
//			GetOnlineOrderListKey.AdvanceValues av = key.new AdvanceValues();
//			av.setBillsNo(advanceCondition.getBillsNo());
//			av.setCreateDateBegin(advanceCondition.getCreateDateBegin());
//			av.setCreateDateEnd(advanceCondition.getCreateDateEnd());
//			av.setDeliveryeDateBegin(advanceCondition.getDeliveryeDateBegin());
//			av.setDeliveryeDateEnd(advanceCondition.getDeliveryeDateEnd());
//			av.setRealName(advanceCondition.getRealName());
//			av.setStationName(advanceCondition.getStationName());
//			key.setAdvanceValues(av);
//		} else {
//			key.setSearchText(search.getText());
//		}
//		ListEntity<OnlineOrderItem> listEntity = getContext().find(OnlineOrderListEntity.class, key);
//		if (null != listEntity) {
//			orderList = listEntity.getItemList();
//		}
//		
//	}
//	
//	private DistributeGoodsItem convetKeyItemToDisGoodsItem(TotalMaterialsItem.GoodsItem kItem) {
//		DistributeGoodsItem item = new DistributeGoodsItem();
//		item.setBomId(kItem.getBomId());
//		item.setCount(kItem.getCount());
//		item.setGoodsCode(kItem.getGoodsCode());
//		item.setGoodsId(kItem.getGoodsId());
//		item.setGoodsName(kItem.getGoodsName());
//		item.setGoodsNo(kItem.getGoodsNo());
//		item.setGoodsScale(kItem.getGoodsScale());
//		item.setGoodsSpec(kItem.getGoodsSpec());
//		item.setUnit(kItem.getUnit());
//		return item;
//	}
//
//	private GetTotalMaterialsKey buildGetTotalMaterialKey() {
////		GetTotalMaterialsKey key = new GetTotalMaterialsKey();
//		String[] selectedRowIds = table.getSelections();
//		GUID[] ids = new GUID[selectedRowIds.length];
//		int index = 0;
//		for (String rowId : selectedRowIds) {
//			ids[index] = GUID.tryValueOf(rowId);
//			index++;
//		}
//		GetTotalMaterialsKey key = new GetTotalMaterialsKey(ids);
////		List<GoodsItem> goodsItemList = new ArrayList<GoodsItem>();
////		for (String rowId : selectedRowIds) {
////			OnlineOrderItem orderItem = getOrderItemById(rowId);
////			GoodsItem goodsItem = null;
////			for (OnlineOrderInfoItem itemDet : orderItem.getItems()) {
////				goodsItem = key.new GoodsItem();
////				GoodsItemInfo goodsItemInfo = getContext().find(GoodsItemInfo.class, itemDet.getGoodsId());
//////				goodsItem.setBomId(goodsItemInfo.getItemData().getBomId());
////				goodsItem.setGoodsScale(goodsItemInfo.getItemData().getScale());
////				goodsItem.setCount(itemDet.getCount());
////				goodsItem.setGoodsCode(itemDet.getGoodsCode());
////				goodsItem.setGoodsId(itemDet.getGoodsId());
////				goodsItem.setGoodsName(itemDet.getGoodsName());
////				goodsItem.setGoodsNo(itemDet.getGoodsNo());
////				goodsItem.setGoodsSpec(itemDet.getGoodsSpec());
////				goodsItem.setUnit(itemDet.getUnit());
////				goodsItemList.add(goodsItem);
////			}
////		}
////		key.setGoodsItems(goodsItemList.toArray(new GoodsItem[0]));
//		return key;
//	}
//	
//	private GUID[] getSelectedOrderItemIds() {
//		String[] selectedRowIds = table.getSelections();
//		GUID[] selectedOrderIds = new GUID[selectedRowIds.length];
//		int index = 0;
//		for (String rowId : selectedRowIds) {
//			selectedOrderIds[index] = GUID.tryValueOf(rowId);
//			index++;
//		}
//		return selectedOrderIds;
//	}
//	
////	private OnlineOrderItem getOrderItemById(String id) {
////		for (OnlineOrderItem item : orderList) {
////			if (item.getId().toString().equals(id)) {
////				return item;
////			}
////		}
////		return null;
////	}
//	
//	private double getTotalAmount(List<OnlineOrderItem> itemList) {
//		double totalAmount = 0;
//		for (OnlineOrderItem item : itemList) {
//			totalAmount += item.getTotalAmount();
//		}
//			
//		return totalAmount;
//	}
//	
//	private List<OnlineOrderShowItem> getShowItemList(List<OnlineOrderItem> itemList) {
//		List<OnlineOrderShowItem> showItemList = new ArrayList<OnlineOrderShowItem>();
//		for (OnlineOrderItem item : itemList) {
//			OnlineOrderShowItem showItem = null;
//			int infoItemIndex = 0;
//			for (OnlineOrderInfoItem infoItem : item.getItems()) {
//				int rowSpan = 0;
//				// 同一OnlineOrderItem的记录显示多行时，行id用"itemID_index"的方式显示
//				String id = item.getId().toString();
//				boolean isFirstItem = false;
//				if (0 == infoItemIndex) {
//					rowSpan = item.getItems().length;
//					isFirstItem = true;
//				} else {
//					id = id + "_" + infoItemIndex;
//				}
//				showItem = new OnlineOrderShowItem(id, rowSpan, isFirstItem);
//				showItem.setBillsNo(item.getBillsNo());
//				showItem.setRealName(item.getRealName());
//				showItem.setCreateDate(item.getCreateDate());
//				showItem.setDeliverDate(item.getDeliveryeDate());
//				showItem.setStationName(item.getStationName());
//				showItem.setTotalAmount(item.getTotalAmount());
//				showItem.setConsignor(item.getDeliverPerson());
//				
//				showItem.setGoodsName(infoItem.getGoodsName());
//				showItem.setGoodsSpec(infoItem.getGoodsSpec());
//				showItem.setCount(infoItem.getCount());
//				
//				showItemList.add(showItem);
//				
//				infoItemIndex++;
//			}
//		}
//		return showItemList;
//	}

}