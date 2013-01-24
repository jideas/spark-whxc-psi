package com.spark.psi.inventory.intf.key.dismounting;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>拆装明细Key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class DismountingItemKey {
	private GUID storeDisGuid;//拆装单的GUID

	public GUID getStoreDisGuid() {
		return storeDisGuid;
	}

	public void setStoreDisGuid(GUID storeDisGuid) {
		this.storeDisGuid = storeDisGuid;
	}
	
	
}