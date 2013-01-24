/**
 * 
 */
package com.spark.psi.inventory.intf.key.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯ��Ʒ��Ŀ��ָ���ֿ�Ŀ����Ϣ
 * @author 97
 *
 */
public class GetGoodsStoreInventoryKey {
	private GUID goodsItemId;
	
	private GUID storeId;
	
	public GetGoodsStoreInventoryKey(GUID goodsItemId, GUID storeId) {
		this.goodsItemId = goodsItemId;
		this.storeId = storeId;
	}

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	/**
	 * @return the storeId
	 */
	public GUID getStoreId() {
		return storeId;
	}
	
	
}
