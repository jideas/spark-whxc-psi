package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * �ɹ��˻�����
 * </p>
 * 
 * 
 * 
 * 
 * 
 * @version 2012-3-1
 */
public interface PurchaseReturnInfo extends OrderInfo {
	
	// �˻���Ʒ��ϸ
	// private PurchaseReturnGoodsItem[] goodsItems;

	public GUID getStoreId();

	public String getStoreName();

	public PurchaseReturnGoodsItem[] getGoodsItems();

}
