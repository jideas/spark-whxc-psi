package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�ɹ��˻���Ʒ��ϸ</p>
 *


 *
 
 * @version 2012-3-1
 */
public interface PurchaseReturnGoodsItem extends OrderGoodsItem{	
	
//	private String storeName; //�˻��ֿ�����
//	
//	private GUID storeId; //�˻��ֿ�id

	public String getStoreName();

	public GUID getStoreId();
}
