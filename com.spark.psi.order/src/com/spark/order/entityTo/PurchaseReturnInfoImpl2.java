package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderFather;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;



/**
 * 
 * <p>采购退货详情</p>
 *


 *
 
 * @version 2012-3-1
 */
public final class PurchaseReturnInfoImpl2 extends OrderInfoImpl2 implements com.spark.psi.publish.order.entity.PurchaseReturnInfo{

	public PurchaseReturnInfoImpl2(Context context, OrderFather t) {
		super(context, t);
		// TODO Auto-generated constructor stub
	}

	//退货商品明细
	private PurchaseReturnGoodsItemImpl2[] goodsItems;
	private GUID storeId;
	private String storeName;
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
	public PurchaseReturnGoodsItemImpl2[] getGoodsItems(){
    	return goodsItems;
    }

	/**
	 * @param goodsItems the goodsItems to set
	 */
	public void setGoodsItems(PurchaseReturnGoodsItemImpl2[] goodsItems) {
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
