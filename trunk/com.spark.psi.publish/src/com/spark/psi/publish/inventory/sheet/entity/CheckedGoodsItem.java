package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;

public interface CheckedGoodsItem {

	/**
	 * 获取RECID
	 */
	public GUID getId();
	/**
	 * 获取商品条目ID
	 */
	public GUID getGoodsItemId();

	/**
	 * 获取需出入库的数量
	 */
	public double getCheckingCount();

	/**
	 * 获取单价
	 */
	public double getPrice();

	/**
	 * 数量小数位
	 */
	public int getScale();

}
