/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�������</p>
 * 


 *
 
 * @version 2012-3-6
 */

public class UpdatePromotionTask extends SimpleTask{
	
	
	/**
	 * �����б�
	 * id Ϊ�յĶ��� ����
	 *    ��Ϊ�յ� �޸�
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
		 * ��Ʒid
		 */
		private GUID goodsItemId;  
				
		/**
		 * ������Ʒ����
		 */
		private double promotionCount;

		/**
		 * ��������
		 */
		private double promotionPrice;
		
		/**
		 * ��ʼ����
		 */
		private long startDate;
		
		/**
		 * ��������
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
