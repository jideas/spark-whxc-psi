/**
 * 
 */
package com.spark.psi.inventory.intf.task.pub;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;

/**
 * 生成物品出库单
 *
 */
public class KitCheckingOutTask extends SimpleTask {
	
	private Outstorage entity;

	private List<OutstorageItem> detailList;
	
	public Outstorage getEntity() {
		return entity;
	}

	public void setEntity(Outstorage entity) {
		this.entity = entity;
	}

	public List<OutstorageItem> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OutstorageItem> detailList) {
		this.detailList = detailList;
	}
}
