package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯ��Ʒ���ÿ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-4-19
 */
public class GetAvailableCountKey {

	private GUID storeId,goodsItemId;

	public GUID getStoreId() {
		return storeId;
	}

	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	/**
	 * 
	 * @param storeId �ֿ�ID
	 * @param goodsItemId ��Ʒ��ĿID
	 */
	public GetAvailableCountKey(GUID storeId, GUID goodsItemId) {
		this.storeId = storeId;
		this.goodsItemId = goodsItemId;
	}
}
