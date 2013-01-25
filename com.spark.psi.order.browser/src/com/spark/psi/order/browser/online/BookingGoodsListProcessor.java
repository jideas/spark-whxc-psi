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
import com.spark.psi.publish.OnlineOrderType;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.produceorder.task.CreateProduceOrderTask;


public class BookingGoodsListProcessor<Item> extends UnHandledOnlineOrderListProcessor<Item> {

	public static final String ID_Button_NoticePurchase = "Button_NoticePurchase";
	public static final String ID_Button_Summarizing = "Button_Summarizing";
	
	@Override
	public void process(final Situation context) {
		super.process(context);
		final Button button = createButtonControl(ID_Button_NoticePurchase);
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_Summary)) {
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// 生成采购需求
					TotalMaterialsItem totalMaterial = getTotalMaterialsItem();
					if (null == totalMaterial) return;
					if (totalMaterial.getMaterials() == null 
							|| totalMaterial.getMaterials().length < 1) {
						alert("获得所需材料失败。");
						return ;
					}
					PageController pc = new PageController(GeneratePurchaseProcessor.class, GeneratePurchaseRender.class);
					PageControllerInstance pci = new PageControllerInstance(pc, totalMaterial.getMaterials());
					MsgRequest request = new MsgRequest(pci, "生成采购需求");
					request.setResponseHandler(new ResponseHandler() {
						
						public void handle(Object returnValue, Object returnValue2,
								Object returnValue3, Object returnValue4) {
							table.render();
						}
					});
					context.bubbleMessage(request);
				}
			});
		} else {
			button.dispose();
		}
		final Button summaryBtn = createControl(ID_Button_Summarizing, Button.class);
		if (loginInfo.hasAuth(Auth.SubFunction_OnlineOrder_Summary)) {
			addSummaryAction(summaryBtn);
		} else {
			summaryBtn.dispose();
		}
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
						//TODO
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
		return OnlineOrderType.Booking;
	}

}
