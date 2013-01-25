package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderFather;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

/**
 * 
 * <p>采购订单详情</p>
 *


 *
 
 * @version 2012-3-1
 */
@StructClass
public final class PurchaseOrderInfoImpl2 extends OrderInfoImpl2 implements com.spark.psi.publish.order.entity.PurchaseOrderInfo{

	public PurchaseOrderInfoImpl2(Context context, OrderFather t) {
		super(context, t);
		// TODO Auto-generated constructor stub
	}

	@StructField
	private GUID sourceId; //仓库id
	@StructField
	private String sourceName; //仓库名称

	@StructField
	private PurchaseOrderGoodsItemImpl2[] goodsItems; //商品明细


	
	
	public GUID getSourceId(){
    	return sourceId;
    }

	public String getSourceName(){
    	return sourceName;
    }

	public PurchaseOrderGoodsItemImpl2[] getGoodsItems(){
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
	public void setGoodsItems(PurchaseOrderGoodsItemImpl2[] goodsItems) {
		this.goodsItems = goodsItems;
	}

	@Override
	public OrderAction[] getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactBookInfo getConsigneeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderStatus getOrderStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderType getOrderType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getConsignee() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLinkman() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

