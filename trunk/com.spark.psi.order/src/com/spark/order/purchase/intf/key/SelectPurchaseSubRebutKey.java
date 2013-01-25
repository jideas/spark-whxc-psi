package com.spark.order.purchase.intf.key;

import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.key.SelectMainKey;


/**
 * <p>查询待提交和已退回采购单</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-18
 */
public class SelectPurchaseSubRebutKey extends SelectKey{
	private final SelectMainKey mainKey;
	public SelectPurchaseSubRebutKey(SelectMainKey mainKey) {
		this.mainKey = mainKey;
	}

	/**
	 * @return the mainKey
	 */
	public SelectMainKey getMainKey() {
		return mainKey;
	}
}
