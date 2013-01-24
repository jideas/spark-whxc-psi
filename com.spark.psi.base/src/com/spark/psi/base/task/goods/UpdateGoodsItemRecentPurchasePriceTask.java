/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.goods
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-14    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.task.goods
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-5-14    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.goods;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>修改商品最近采购单价</p>
 * 
 */
//Deprecated 2012-10-14
@Deprecated
public class UpdateGoodsItemRecentPurchasePriceTask extends SimpleTask{
	
	private GUID id;
	
	private double price;
	
	public UpdateGoodsItemRecentPurchasePriceTask(GUID id,double price){
	    this.id = id;
	    this.price = price;
    }

	public GUID getId(){
    	return id;
    }

	public double getPrice(){
    	return price;
    }
	
	
	
}
