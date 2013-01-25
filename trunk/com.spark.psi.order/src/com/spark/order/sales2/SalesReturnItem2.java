package com.spark.order.sales2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderItemFather;

/**
 * <p>销售退货明细</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */
public class SalesReturnItem2 extends OrderItemFather{
	protected GUID storeId;//	仓库GUID	guid	
	protected String storeName;//	仓库名称	nvarchar	50
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
