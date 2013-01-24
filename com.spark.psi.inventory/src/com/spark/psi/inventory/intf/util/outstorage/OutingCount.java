/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.dbox
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-30       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>尚未出库的信息</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-30
 */

public class OutingCount{
	public OutingCount(GUID goodsGuid, GUID storeGuid, double count){
		this.goodsGuid = goodsGuid;
		this.storeGuid = storeGuid;
		this.count = count;
	}

	private GUID goodsGuid;
	private GUID storeGuid;
	private double count;

	/**
	 * @return the goodsGuid
	 */
	public GUID getGoodsGuid(){
		return goodsGuid;
	}

	/**
	 * @param goodsGuid the goodsGuid to set
	 */
	public void setGoodsGuid(GUID goodsGuid){
		this.goodsGuid = goodsGuid;
	}

	/**
	 * @return the storeGuid
	 */
	public GUID getStoreGuid(){
		return storeGuid;
	}

	/**
	 * @param storeGuid the storeGuid to set
	 */
	public void setStoreGuid(GUID storeGuid){
		this.storeGuid = storeGuid;
	}

	/**
	 * @return the count
	 */
	public double getCount(){
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(double count){
		this.count = count;
	}
}
