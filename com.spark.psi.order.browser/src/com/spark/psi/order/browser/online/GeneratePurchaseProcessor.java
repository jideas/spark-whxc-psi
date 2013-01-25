package com.spark.psi.order.browser.online;

import java.util.Iterator;
import java.util.Map;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.order.task.CreatePurchaseGoodsTask;

public class GeneratePurchaseProcessor extends AbstractDistributePageProcessor {

	@Override
	protected boolean doConfirmAction() {
		Iterator<String> materialDisRsIt = materialDistributeResult.keySet().iterator();
		while (materialDisRsIt.hasNext()) {
			String materialId = materialDisRsIt.next();
			Map<String, Double> storeCountMap = materialDistributeResult.get(materialId);
			Iterator<String> storeCountIt = storeCountMap.keySet().iterator();
			while (storeCountIt.hasNext()) {
				String storeId = storeCountIt.next();
				double count = storeCountMap.get(storeId);
				if (count <= 0) continue;
				CreatePurchaseGoodsTask task = new CreatePurchaseGoodsTask();
				task.setGoodsItemId(GUID.tryValueOf(materialId));
				task.setStoreId(GUID.tryValueOf(storeId));
				task.setPurchaseCount(count);
				getContext().handle(task);
			}
		}
		return true;
	}

}
