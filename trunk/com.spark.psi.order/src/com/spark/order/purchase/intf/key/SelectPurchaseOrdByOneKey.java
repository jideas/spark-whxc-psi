package com.spark.order.purchase.intf.key;

import com.spark.order.intf.key.SelectMainKey;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.PageEnum;

/**
 * <p>独立下单专用key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-12
 */
public class SelectPurchaseOrdByOneKey extends SelectMainKey{

	public SelectPurchaseOrdByOneKey(BillsEnum billsEnum, PageEnum tabEnum) {
		super(billsEnum, tabEnum);
	}

}
