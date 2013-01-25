package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯָ����Ʒ��Ŀ�б���ָ���ֿ�Ŀ����ϸ���
 * 
 */
public class GetInventoryInfoListKey {

	/**
	 * ��Ʒ��ĿId����
	 */
	private GUID[] goodsItemIds;

	/**
	 * �ֿ�ID���飬Ϊ�ղ�ѯ�����вֿ�
	 */
	private GUID[] storeIds;

	/**
	 * ���캯��
	 * 
	 * @param goodsItemIds ��Ʒ��ĿId���� ������Ϊ��
	 * @param storeId �ֿ�ID���飬Ϊ�ղ�ѯ�����вֿ�
	 */
	public GetInventoryInfoListKey(GUID[] goodsItemIds, GUID[] storeIds) {
		super();
		this.goodsItemIds = goodsItemIds;
		this.storeIds = storeIds;
	}

	/**
	 * @return the goodsItemIds
	 */
	public GUID[] getGoodsItemIds() {
		return goodsItemIds;
	}

	/**
	 * @return the storeIds
	 */
	public GUID[] getStoreIds() {
		return storeIds;
	}
}
