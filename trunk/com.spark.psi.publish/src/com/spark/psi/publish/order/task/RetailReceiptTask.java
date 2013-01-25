/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PsiSimpleTask;
import com.spark.psi.publish.order.task.CreateRetailOrderTask.RetailOrderGoodsItem;

/**
 * <p>零售单收款</p>
 *


 *
 
 * @version 2012-3-6
 */

public class RetailReceiptTask extends PsiSimpleTask<RetailReceiptTask.Error>{
	
	private GUID id;
	public static enum Error{
		NotHaveStore;
	}
	
	/**
	 * 付款方式
	 */
	private DealingsWay dealingsWay;
	private double discountAmount;//整单折扣
	private double amount;

	private String memo;
	private RetailOrderGoodsItem[] retailOrderGoodsItems;  //销售商品明细	

//	public RetailReceiptTask(final GUID id,final DealingsWay dealingsWay){
//		this.id = id;
//		this.dealingsWay = dealingsWay;
//	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public void setDealingsWay(DealingsWay dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	public GUID getId(){
    	return id;
    }

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public RetailOrderGoodsItem[] getRetailOrderGoodsItems(){
    	return retailOrderGoodsItems;
    }

	public void setRetailOrderGoodsItems(
            RetailOrderGoodsItem[] retailOrderGoodsItems)
    {
    	this.retailOrderGoodsItems = retailOrderGoodsItems;
    }

	
		
	
}
