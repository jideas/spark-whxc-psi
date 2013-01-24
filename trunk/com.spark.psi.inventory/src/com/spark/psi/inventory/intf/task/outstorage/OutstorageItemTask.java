
package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 
 * <p>出库明细Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */

public class OutstorageItemTask extends Task<Method>{

	private OutstorageItem entity;
	public int count;
	
	

	public OutstorageItemTask(OutstorageItem det) {
		this.entity = det;
	}



	public void setEntity(OutstorageItem entity) {
		this.entity = entity;
	}



	public OutstorageItem getEntity() {
		return entity;
	}

	

	
	

}
