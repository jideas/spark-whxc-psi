package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�ɹ���������</p>
 *


 *
 
 * @version 2012-3-1
 */
@StructClass
public final class PurchaseOrderInfoImpl extends OrderInfoImpl implements com.spark.psi.publish.order.entity.PurchaseOrderInfo{

	@StructField
	private GUID sourceId; //�ֿ�id
	@StructField
	private String sourceName; //�ֿ�����

	@StructField
	private PurchaseOrderGoodsItemImpl[] goodsItems; //��Ʒ��ϸ


	
	
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

