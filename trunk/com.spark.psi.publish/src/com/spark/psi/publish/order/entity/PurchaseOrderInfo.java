package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�ɹ���������</p>
 *


 *
 
 * @version 2012-3-1
 */
@StructClass
public interface PurchaseOrderInfo extends OrderInfo{

//	@StructField
//	private GUID storeId; //�ֿ�id
//	@StructField
//	private String storeName; //�ֿ�����
//
//	@StructField
//	private PurchaseOrderGoodsItem[] goodsItems; //��Ʒ��ϸ


	
	
	public GUID getSourceId();

	public String getSourceName();

	public PurchaseOrderGoodsItem[] getGoodsItems();
}

