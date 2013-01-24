
package com.spark.psi.inventory.intf.task.outstorage;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.outstorage.mod.SureOutstorage;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * 
 * <p>确认出库</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class SureOutstorageTask extends Task<Method>{

	private SureOutstorage entity;
	public int count;

	public void setEntity(SureOutstorage entity) {
		this.entity = entity;
	}

	public SureOutstorage getEntity() {
		return entity;
	}

	
}
