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

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>保存促销</p>
 * 


 *
 
 * @version 2012-3-6
 */

public class UpdatePromotionTask extends SimpleTask{
	
	
	/**
	 * 促销列表
	 * id 为空的对象 新增
	 *    不为空的 修改
	 */
	private final Promotion[] promotions;  
	
	public UpdatePromotionTask(final Promotion... promotions){
		this.promotions = promotions;
	}
		
	
	public Promotion[] getPromotions(){
    	return promotions;
    }


	public static final class Promotion {

		private GUID id;
		
		/**
		 * 商品id
		 */
		private GUID goodsItemId;  
				
		/**
		 * 促销商品数量
		 */
		private double promotionCount;

		/**
		 * 促销单价
		 */
		private double promotionPrice;
		
		/**
		 * 开始日期
		 */
		private long startDate;
		
		/**
		 * 结束日期
		 */
		private long endDate;

		public GUID getId(){
        	return id;
        }

		public void setId(GUID id){
        	this.id = id;
        }

		
		public GUID getGoodsItemId(){
        	return goodsItemId;
        }

		public void setGoodsItemId(GUID goodsItemId){
        	this.goodsItemId = goodsItemId;
        }

		public double getPromotionCount(){
        	return promotionCount;
        }

		public void setPromotionCount(double promotionCount){
        	this.promotionCount = promotionCount;
        }

		public double getPromotionPrice(){
        	return promotionPrice;
        }

		public void setPromotionPrice(double promotionPrice){
        	this.promotionPrice = promotionPrice;
        }

		public long getStartDate(){
        	return startDate;
        }

		public void setStartDate(long startDate){
        	this.startDate = startDate;
        }

		public long getEndDate(){
        	return endDate;
        }

		public void setEndDate(long endDate){
        	this.endDate = endDate;
        }
		
		
		


	}
}
