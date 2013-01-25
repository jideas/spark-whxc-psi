package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 商品的出入库明细数据 <br>
 * 不提供单独查询
 */
public interface CheckingGoodsItem {

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
	 * 获取已出入库数量
	 */
	public double getCheckedCount();

	/**
	 * 获取此次出入库数量
	 */
	public double getCheckCount();
	
	/**
	 * 数量小数位
	 */
	public int getScale();
	
	public double getInspectCount();

}
