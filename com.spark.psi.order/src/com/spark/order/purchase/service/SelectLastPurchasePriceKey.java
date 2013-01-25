package com.spark.order.purchase.service;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ѯ�ϴβɹ�����
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-11
 */
public class SelectLastPurchasePriceKey {
	private final GUID partnerId;
	private final GUID goodsItemId;
	/**
	 * @param partnerId
	 * @param goodsItemId
	 */
	public SelectLastPurchasePriceKey(GUID partnerId, GUID goodsItemId) {
		super();
		this.partnerId = partnerId;
		this.goodsItemId = goodsItemId;
	}
	GUID getPartnerId() {
		return partnerId;
	}
	GUID getGoodsItemId() {
		return goodsItemId;
	}
}
