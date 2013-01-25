/**
 * 
 */
package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ø‚¥ÊÃ®’À
 */
public interface InventoryBookInfo {

	/**
	 * @return the goodsScale
	 */
	public int getGoodsScale();

	/**
	 * @return the goodsId
	 */
	public GUID getGoodsId();

	/**
	 * @return the goodsNo
	 */
	public String getGoodsNo();

	/**
	 * @return the goodsName
	 */
	public String getGoodsName();

	/**
	 * @return the goodsAttr
	 */
	public String getGoodsAttr();

	/**
	 * @return the goodsUnit
	 */
	public String getGoodsUnit();

	/**
	 * @return the count_begin
	 */
	public double getCount_begin();

	/**
	 * @return the amount_begin
	 */
	public double getAmount_begin();

	/**
	 * @return the instoCount
	 */
	public double getInstoCount();

	/**
	 * @return the instoAmount
	 */
	public double getInstoAmount();

	/**
	 * @return the outstoCount
	 */
	public double getOutstoCount();

	/**
	 * @return the outstoAmount
	 */
	public double getOutstoAmount();

	/**
	 * @return the count_end
	 */
	public double getCount_end();

	/**
	 * @return the amount_end
	 */
	public double getAmount_end();

}
