package com.spark.order.sales2;

import com.spark.order.intf.entity.OrderFather;

/**
 * <p>�����˻���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */
public class SalesReturnInfo2 extends OrderFather{

	@Override
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
