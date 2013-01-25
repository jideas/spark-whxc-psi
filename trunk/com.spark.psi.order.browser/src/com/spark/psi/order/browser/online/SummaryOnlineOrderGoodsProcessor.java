package com.spark.psi.order.browser.online;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.psi.order.browser.common.DistributeGoodsItem;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.produceorder.task.CreateProduceOrderTask;

public class SummaryOnlineOrderGoodsProcessor extends
		AbstractDistributePageProcessor {

	public static final String ID_Date_Date   = "Date_Date";
	public static final String ID_Text_Remark = "Text_Remark";
	
	private DistributeGoodsItem[] distributeGoodsItems = null;
	private GUID[] onlineOrderIds                      = null;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		onlineOrderIds = (GUID[])getArgument2();
		distributeGoodsItems = (DistributeGoodsItem[])getArgument3();
	}

	@Override
	protected boolean doConfirmAction() {
		CreateProduceOrderTask task = new CreateProduceOrderTask();
		CreateProduceOrderTask.GoodsItem[] goodsItems = buildGoodsItems(task);
		task.setGoods(goodsItems);
		task.setOnlineOrderIds(onlineOrderIds);
		
		List<CreateProduceOrderTask.MaterialsItem> materialItemList = new ArrayList<CreateProduceOrderTask.MaterialsItem>();
		Iterator<String> materialDisRsIt = materialDistributeResult.keySet().iterator();
		while (materialDisRsIt.hasNext()) {
			String materialId = materialDisRsIt.next();
			Map<String, Double> storeCountMap = materialDistributeResult.get(materialId);
			Iterator<String> storeCountIt = storeCountMap.keySet().iterator();
			CreateProduceOrderTask.MaterialsItem mItem = null;
			while (storeCountIt.hasNext()) {
				String storeId = storeCountIt.next();
				double count = storeCountMap.get(storeId);
				if (count <= 0) continue;
				mItem = buildMaterialItem(task, getMaterialItemById(materialId), getStoreItem(storeId), count);
				materialItemList.add(mItem);
			}
		}
		final SDatePicker finishDatePicker = createControl(ID_Date_Date, SDatePicker.class);
		final Text remarkText = createControl(ID_Text_Remark, Text.class);
		task.setMaterials(materialItemList.toArray(new CreateProduceOrderTask.MaterialsItem[0]));
		task.setGoodsCount(task.getGoods().length);
		task.setPlanDate(finishDatePicker.getDate().getTime());
		task.setRemark(remarkText.getText());
		if (onlineOrderIds == null) {
			task.setStatus(ProduceOrderStatus.Submiting);
		} else {
			task.setStatus(ProduceOrderStatus.Producing);
		}
		getContext().handle(task);
		
		return true;
	}
	
	private CreateProduceOrderTask.GoodsItem[] buildGoodsItems(CreateProduceOrderTask task) {
		CreateProduceOrderTask.GoodsItem[] goodsItems = new CreateProduceOrderTask.GoodsItem[distributeGoodsItems.length];
		CreateProduceOrderTask.GoodsItem goodsItem = null;
		int goodsIndex = 0;
		for (DistributeGoodsItem pgItem : distributeGoodsItems) {
			goodsItem = task.new GoodsItem();
			goodsItem.setBomId(pgItem.getBomId());
			goodsItem.setCount(pgItem.getCount());
			goodsItem.setGoodsCode(pgItem.getGoodsCode());
			goodsItem.setGoodsId(pgItem.getGoodsId());
			goodsItem.setGoodsName(pgItem.getGoodsName());
			goodsItem.setGoodsNo(pgItem.getGoodsNo());
			goodsItem.setGoodsScale(pgItem.getGoodsScale());
			goodsItem.setGoodsSpec(pgItem.getGoodsSpec());
			goodsItem.setUnit(pgItem.getUnit());
			goodsItems[goodsIndex] = goodsItem;
			goodsIndex++;
		}
		return goodsItems;
	}
	
	private CreateProduceOrderTask.MaterialsItem buildMaterialItem(CreateProduceOrderTask task,
			TotalMaterialsItem.MaterialsItem tItem, StoreItem store, double count) {
		CreateProduceOrderTask.MaterialsItem item = task.new MaterialsItem();
		item.setCount(count);
		item.setMaterialCode(tItem.getMaterialCode());
		item.setMaterialId(tItem.getMaterialId());
		item.setMaterialName(tItem.getMaterialName());
		item.setMaterialNo(tItem.getMaterialNo());
		item.setMaterialScale(tItem.getMaterialScale());
		item.setStoreId(store.getId());
		item.setStoreName(store.getName());
		item.setUnit(tItem.getUnit());
		return item;
	}
}
