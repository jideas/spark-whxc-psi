/**
 * 
 */
package com.spark.psi.inventory.intf.task.pub;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;

/**
 * 生成物品入库单
 *
 */
public class KitCheckingInTask extends SimpleTask {
	
	private Instorage entity;
	private List<InstorageItem> list;
	
	public Instorage getEntity() {
		return entity;
	}
	public void setEntity(Instorage entity) {
		this.entity = entity;
	}
	public List<InstorageItem> getList() {
		return list;
	}
	public void setList(List<InstorageItem> list) {
		this.list = list;
	}
	
	
}
