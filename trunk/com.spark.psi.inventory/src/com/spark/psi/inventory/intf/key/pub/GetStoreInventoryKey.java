/**
 * 
 */
package com.spark.psi.inventory.intf.key.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯָ���ֿ�Ŀ���б�
 * @author 97
 *
 */
public class GetStoreInventoryKey {
	/**
	 * �ֿ�Id
	 */
	private GUID storeId;

	/**
	 * ���캯��
	 * 
	 * @param goodsItemId
	 */
	public GetStoreInventoryKey(GUID storeId) {
		this.storeId = storeId;
	}
	
	public GUID getStoreId() {
		return storeId;
	}
	
}
