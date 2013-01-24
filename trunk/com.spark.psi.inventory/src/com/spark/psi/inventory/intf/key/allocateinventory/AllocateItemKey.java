
package com.spark.psi.inventory.intf.key.allocateinventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>调拨明细Key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class AllocateItemKey {
	private GUID allocateOrdGuid;

	
	public GUID getAllocateOrdGuid() {
		return allocateOrdGuid;
	}

	public void setAllocateOrdGuid(GUID allocateOrdGuid) {
		this.allocateOrdGuid = allocateOrdGuid;
	}
	
}	
