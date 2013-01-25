package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderStatus;

/**
 * 采购订单查询实体<BR>
 * 查询方法：<BR>
 * PurchaseListEntity+GetPurchaseListKey;
 *
 */
public interface PurchaseItem {

	public String getSupplierNo();
	public String getSupplierName();
	public GUID getSupplierId();
	public String getBillsNo();
	public GUID getBillsId();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsName();
	public String getUnit();
	public double getPrice();
	public double getStandardPrice();
	public double getCount();
	public double getAmount();
	public double getCheckedinCount();
	public double getCheckedinAmount();
	public double getCheckinCount();
	public double getCheckinAmount();
	public long getCreateDate();
	public long getDeliveryDate();
	public OrderStatus getStatus();
}
