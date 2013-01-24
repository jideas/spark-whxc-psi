package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 商品库存
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-1
 */
public interface MaterialsInventory {

	/**
	 * 仓库id
	 */
	public GUID getStoreId();

	/**
	 * 商品条目id
	 */
	public GUID getGoodsItemId();

	/**
	 * 库存数量
	 */
	public double getCount();

	/**
	 * 库存金额
	 */
	public double getAmount();

	/**
	 * 交货需求数量
	 */
	public double getDeliveryingCount();

	/**
	 * 交货需求金额
	 */
	public double getDeliveryingAmount();

	/**
	 * 库存数量上限
	 */
	public double getUpperLimitCount();

	/**
	 * 库存数量下限
	 */
	public double getLowerLimitCount();

	/**
	 * 库存数量上限金额
	 */
	public double getUpperLimitAmount();

	/**
	 * 采购在途数量
	 */
	public double getOnWayCount();

	/**
	 * 可用库存
	 */
	// public double getAvailableInventory();

	/**
	 * 
	 * @return
	 */
	public double getLockedCount();

}
