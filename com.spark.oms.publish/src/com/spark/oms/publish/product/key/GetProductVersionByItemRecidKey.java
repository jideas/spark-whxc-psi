package com.spark.oms.publish.product.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductCycle;

/**
 * ͨ����Ʒ�ı����ȡ��Ʒ����Ŀ��
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