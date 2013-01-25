package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 采购清单
 * </p>
 * 获得采购需求清单 查询方法：ListEntity<PurchaseGoodsItem>
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-2-22
 */
public interface PurchaseGoodsItem {

	public GUID getId();

	public GUID getGoodsItemId();

	public String getGoodsCode();
	public String getGoodsNo();

	public String getGoodsName();

	public String getProperties();

	public String getUnit();

	public int getScale();

	public double getPrice();

	public double getCount();

	public GUID getSupplierId();

	public double getRecentPrice();

	public GUID getStoreId();

	public String getStoreName();

	public boolean isDirectDelivery();
	
	/**
	 * 获得联系人Id
	 * @return GUID
	 */
	GUID getContactId();
	/**
	 * 供应商名称
	 * @return String
	 */
	String getSupplierName();
	/**
	 * 供应商简称
	 * @return String
	 */
	String getSupplierShorName();
	/**
	 * 获得直供订单交货日期
	 * @return long
	 */
	long getDirectDeliveryDate();
	/**
	 * 销售订单备注
	 * @return String
	 */
	String getSalesMemo();
}
