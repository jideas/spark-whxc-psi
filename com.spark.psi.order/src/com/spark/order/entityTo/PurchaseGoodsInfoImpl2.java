package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.spark.order.purchase.intf.PurchaseGoods2;
import com.spark.psi.publish.order.entity.PurchaseGoodsInfo;

/**
 * 
 * <p>
 * �ɹ��嵥
 * </p>
 * ��òɹ������嵥 ��ѯ������ListEntity<PurchaseGoodsItem>+String
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-2-22
 */
public class PurchaseGoodsInfoImpl2 extends PurchaseGoodsItemImpl2 implements
		PurchaseGoodsInfo {

	public PurchaseGoodsInfoImpl2(Context context, PurchaseGoods2 t) {
		super(context, t);
	}
	
}
