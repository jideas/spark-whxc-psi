/**
 * 
 */
package com.spark.psi.inventory.intf.key.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯָ����Ʒ��Ŀ�ڸ����ֿ�Ŀ���б�
 * @author 97
 *
 */
public class GetGoodsInventoryKey {
	/**
	 * ��Ʒ��ĿId
	 */
	private GUID goodsItemId;

	/**
	 * ���캯��
	 * 
	 * @param goodsItemId
	 */
	public GetGoodsInventoryKey(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
}
