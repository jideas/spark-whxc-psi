package com.spark.oms.publish.product.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductCycle;

/**
 * 通过产品的编码获取产品的条目。
 */
public class GetProductVersionByItemRecidKey {
	private GUID itemRECID;
	private ProductCycle cycle;
	public GUID getItemRECID() {
		return itemRECID;
	}
	public void setItemRECID(GUID itemRECID) {
		this.itemRECID = itemRECID;
	}
	public ProductCycle getCycle() {
		return cycle;
	}
	public void setCycle(ProductCycle cycle) {
		this.cycle = cycle;
	}
	

}