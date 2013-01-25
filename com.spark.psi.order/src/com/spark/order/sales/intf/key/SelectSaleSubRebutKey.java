package com.spark.order.sales.intf.key;

import com.spark.order.intf.key.SelectMainKey;
import com.spark.order.purchase.intf.key.SelectPurchaseSubRebutKey;


/**
 * <p>查询待提交和已退回采购单</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-18
 */
public class SelectSaleSubRebutKey extends SelectPurchaseSubRebutKey{

	public SelectSaleSubRebutKey(SelectMainKey mainKey) {
		super(mainKey);
	}
}
