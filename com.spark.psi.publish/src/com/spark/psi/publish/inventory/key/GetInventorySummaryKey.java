package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯָ����Ʒ��Ŀ�ڸ����ֿ�Ŀ���б�
 * 
 */
public class GetInventorySummaryKey {

	/**
	 * ��Ʒ��ĿId
	 */
	private GUID goodsItemId;

	/**
	 * ���캯��
	 * 
	 * @param goodsItemId
	 */
	public GetInventorySummaryKey(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
}
