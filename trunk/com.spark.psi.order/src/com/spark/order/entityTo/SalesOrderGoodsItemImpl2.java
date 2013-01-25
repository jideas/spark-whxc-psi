package com.spark.order.entityTo;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.internal.service.DataConverterUtil;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.sales2.SalesOrderItem2;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.publish.order.entity.PromotionItem;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;

/**
 * 
 * <p>
 * 销售订单商品明细
 * </p>
 * 
 * 
 * 
 * 
 * 
 * @version 2012-3-1
 */
public class SalesOrderGoodsItemImpl2 extends OrderGoodsItemImpl2<SalesOrderItem2> implements SalesOrderGoodsItem {

	public SalesOrderGoodsItemImpl2(Context context, SalesOrderItem2 t) {
		super(context, t);
	}

	public double getDiscountAmount() {
		return getEntity().getDisAmount();
	}

	public double getDiscountCount() {
		return getEntity().getDiscount();
	}

	public double getGoodsItemPrice() {
		return getContext().find(GoodsItem.class, getEntity().getGoodsItemId()).getSalePrice();
	}

	public GUID getPromotionId() {
		return getEntity().getPromotionId();
	}

	public PromotionItem[] getPromotionList() {
		// 促销列表
		List<PromotionItem> promotionList = new ArrayList<PromotionItem>();
		List<Promotion2> list = getContext().getList(Promotion2.class, getEntity().getGoodsItemId());
		for (Promotion2 p : list) {
			promotionList.add(DataConverterUtil.toPub_PromotionItem(getContext(), p));
		}
		return promotionList.toArray(new PromotionItem[promotionList.size()]);
	}

	private double planPrice;

	public double getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(double planPrice) {
		this.planPrice = planPrice;
	}

}
