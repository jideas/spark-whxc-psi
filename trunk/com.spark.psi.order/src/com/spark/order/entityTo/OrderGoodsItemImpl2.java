package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderItemFather;
import com.spark.psi.publish.order.entity.OrderGoodsItem;

/**
 * 订单商品明细
 * @author MoDI
 *
 */
public class OrderGoodsItemImpl2<T extends OrderItemFather> extends EntityToFather<T> implements OrderGoodsItem{

	public OrderGoodsItemImpl2(Context context, T t) {
		super(context, t);
	}


	public double getAmount() {
		return getEntity().getAmount();
	}

	public String getCode() {
		return getEntity().getGoodsCode();
	}

	public double getCount() {
		return getEntity().getNum();
	}

	public int getScale() {
		return getEntity().getScale();
	}

	public GUID getGoodsItemId() {
		return getEntity().getGoodsItemId();
	}

	public GUID getId() {
		return getEntity().getRecid();
	}

	public String getName() {
		return getEntity().getGoodsName();
	}

	public double getPrice() {
		return getEntity().getPrice();
	}

	public String getProperties() {
		return getEntity().getGoodsProperties();
	}

	public String getUnit() {
		return getEntity().getGoodsUnit();
	}


	public String getGoodsCode() { 
		return getCode();
	}


	public String getGoodsNo() { 
		return null;
	}


	public String getSpec() { 
		return getProperties();
	}
	
}
