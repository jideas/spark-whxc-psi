/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.inventory
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-29       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>总库存查询Key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-29
 */

public class GoodsInventorySumKey {

	private GUID goodsItemId;

//	private GUID tenantsId;
	
//	public GUID getTenantsId() {
//		return tenantsId;
//	}
//
//	public GoodsInventorySumKey(GUID tenantsId) {
//		super();
//		this.tenantsId = tenantsId;
//	}

	/**
	 * 商品条目ID(不设值则查询所有商品的总库存信息)
	 * 
	 * @param goodsItemId void
	 */
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * 商品条目ID(不设值则查询所有商品的总库存信息)
	 * 
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
}
