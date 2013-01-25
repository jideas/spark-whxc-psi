package com.spark.order.entityTo;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.purchase.intf.PurchaseGoods2;
import com.spark.order.purchase.intf.PurchaseGoodsDirect2;
import com.spark.order.sales2.SalesOrderInfo2;
import com.spark.psi.publish.order.entity.PurchaseGoodsItem;
/**
 * 
 * <p>采购清单</p>
 * 获得采购需求清单
 * 查询方法：ListEntity<PurchaseGoodsItem>+String
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-2-22
 */
public class PurchaseGoodsItemImpl2 extends EntityToFather<PurchaseGoods2> implements PurchaseGoodsItem{
	private SalesOrderInfo2 sale;
	public PurchaseGoodsItemImpl2(Context context, PurchaseGoods2 t) {
		super(context, t);
		if(getEntity() instanceof PurchaseGoodsDirect2){
			sale = getContext().find(SalesOrderInfo2.class, ((PurchaseGoodsDirect2)getEntity()).getSourceSaleId());
		}
	} 

	public GUID getContactId() {
		return getEntity().getContactId();
	}

	public double getCount() {
		return getEntity().getPurchaseCount();
	}

	public int getScale() {
		return getEntity().getScale();
	}

	public long getDirectDeliveryDate() {
		return sale == null?0:sale.getDeliveryDate();
	}

	public GUID getGoodsItemId() {
		return getEntity().getGoodsItemId();
	}

	public String getGoodsName() {
		return getEntity().getGoodsName();
	}

	public double getPrice() {
		return getEntity().getPrice_purchase();
	}

	public String getProperties() {
		return getEntity().getGoodsProperties();
	}

	public double getRecentPrice() {
		return getEntity().getPrice_lastPurchase();
	}

	public String getSalesMemo() {
		return sale == null?null:sale.getRemark();
	}

	public GUID getStoreId() {
		return getEntity().getSourceId();
	}

	public String getStoreName() {
		return getEntity().getSourceName();
	}

	public GUID getSupplierId() {
		return getEntity().getPartnerId();
	}

	public String getSupplierName() {
		return getEntity().getPartnerName();
	}

	public String getSupplierShorName() {
		return getEntity().getPartnerShortName();
	}

	public String getUnit() {
		return getEntity().getGoodsUnit();
	}

	public boolean isDirectDelivery() {
		return getEntity() instanceof PurchaseGoodsDirect2;
	}

	public String getGoodsCode() { 
		return getEntity().getGoodsCode();
	}

	public String getGoodsNo() {
		return getEntity().getGoodsNo();
	}
}
