package com.spark.psi.publish.order.key;


/**
 * 
 * <p>��ѯ�ɹ��������ɹ��˻��б�</p>
 *


 *
 
 * @version 2012-3-12
 */
public class GetPurchaseOrderListKey extends GetOrderListKey{
	public GetPurchaseOrderListKey(int offset, int count, boolean queryTotal, OrderType... orderTypes){
		super(offset, count, queryTotal, orderTypes);
	}
	public GetPurchaseOrderListKey(int offset, int count, boolean queryTotal){
		super(offset, count, queryTotal, OrderType.values());
	}
}
