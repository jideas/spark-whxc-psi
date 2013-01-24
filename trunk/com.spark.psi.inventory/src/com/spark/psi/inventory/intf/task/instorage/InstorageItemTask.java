
package com.spark.psi.inventory.intf.task.instorage;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 
 * <p>入库明细Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */

public class InstorageItemTask extends Task<Method>{
	
	private InstorageItem instorageItemEntity;
	public int count;

	public InstorageItemTask(InstorageItem det) {
		this.instorageItemEntity = det;
	}

	public void setInstorageItemEntity(InstorageItem instorageItemEntity) {
		this.instorageItemEntity = instorageItemEntity;
	}

	public InstorageItem getInstorageItemEntity() {
		return instorageItemEntity;
	}
	
	
}
