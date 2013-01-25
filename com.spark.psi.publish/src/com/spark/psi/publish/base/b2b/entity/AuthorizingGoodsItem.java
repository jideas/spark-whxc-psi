package com.spark.psi.publish.base.b2b.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 向客户授权的商品列表<br>
 * 查询方法：无单独查询
 */
public interface AuthorizingGoodsItem {

	/**
	 * 获取商品条目ID
	 */
	public GUID getGoodsItemId();

	/**
	 * 获取授权价格
	 * 
	 * @return
	 */
	public double getAuthorizingPrice();

	/**
	 * 获取说明
	 */
	public String getRemark();

	/**
	 * 获取状态
	 * 
	 * @return
	 */
	public boolean isEnabled();
}
