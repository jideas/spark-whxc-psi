package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>采购订单详情</p>
 *


 *
 
 * @version 2012-3-1
 */
@StructClass
public final class PurchaseOrderInfoImpl extends OrderInfoImpl implements com.spark.psi.publish.order.entity.PurchaseOrderInfo{

	@StructField
	private GUID sourceId; //仓库id
	@StructField
	private String sourceName; //仓库名称

	@StructField
	private PurchaseOrderGoodsItemImpl[] goodsItems; //商品明细


	
	
	public GUID getSourceId(){
    	return sourceId;
    }

	public String getSourceName(){
    	return sourceName;
    }

	public PurchaseOrderGoodsItemImpl[] getGoodsItems(){
    	return goodsItems;
    }

	/**
	 * @param SourceId the SourceId to set
	 */
	public void setSourceId(GUID sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @param SourceName the SourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @param goodsItems the goodsItems to set
	 */
	public void setGoodsItems(PurchaseOrderGoodsItemImpl[] goodsItems) {
		this.goodsItems = goodsItems;
	}
	
	
}

