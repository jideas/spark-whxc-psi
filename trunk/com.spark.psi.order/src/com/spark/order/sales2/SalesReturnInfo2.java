package com.spark.order.sales2;

import com.spark.order.intf.entity.OrderFather;

/**
 * <p>销售退货单</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
