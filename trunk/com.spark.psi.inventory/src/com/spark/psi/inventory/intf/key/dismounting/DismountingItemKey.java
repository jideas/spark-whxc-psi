package com.spark.psi.inventory.intf.key.dismounting;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��װ��ϸKey</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class DismountingItemKey {
	private GUID storeDisGuid;//��װ����GUID

	public GUID getStoreDisGuid() {
		return storeDisGuid;
	}

	public void setStoreDisGuid(GUID storeDisGuid) {
		this.storeDisGuid = storeDisGuid;
	}
	
	
}