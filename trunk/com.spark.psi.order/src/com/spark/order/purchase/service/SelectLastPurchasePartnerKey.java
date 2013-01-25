package com.spark.order.purchase.service;

import com.jiuqi.dna.core.type.GUID;


/**
 * ��ѯָ����Ʒ�Ͳֿ������ɹ���Ӧ�̣����زɹ�����PurchaseOrderInfo
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-11
 */
public class SelectLastPurchasePartnerKey{
	private final GUID goodsItemid;
	private final GUID storeId;
	/**
	 * @param goodsItemid
	 * @param storeId
	 */
	public SelectLastPurchasePartnerKey(GUID goodsItemid, GUID storeId) {
		super();
		this.goodsItemid = goodsItemid;
		this.storeId = storeId;
	}
	GUID getGoodsItemid() {
		return goodsItemid;
	}
	GUID getStoreId() {
		return storeId;
	}
	
}
