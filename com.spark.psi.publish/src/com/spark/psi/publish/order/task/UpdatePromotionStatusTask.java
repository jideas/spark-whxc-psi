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
import com.spark.psi.publish.PromotionAction;
import com.spark.psi.publish.PsiSimpleTask;

/**
 * <p>修改促销状态</p>
 * 确认促销
 * 审批促销
 * 停止促销


 *
 
 * @version 2012-3-6
 */

public class UpdatePromotionStatusTask extends PsiSimpleTask<UpdatePromotionStatusTask.Error>{

	/**
	 * <p>错误枚举</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	public enum Error{
		EndDateError("当前商品已促销结束！"),
		BeginDateError("开始日期不能小于当前时间!");
		private String msg;
		private Error(String msg){
			this.msg = msg;
		}
		public String getMsg() {
			return msg;
		}
	}
	
	private final GUID id;
	
	private final PromotionAction promotionAction;
	
	private String cause;
	
	public UpdatePromotionStatusTask(final GUID id,final PromotionAction promotionAction){
		this.id = id;
		this.promotionAction = promotionAction;
	}
	
	public UpdatePromotionStatusTask(final GUID id,final PromotionAction promotionAction,String cause){
		this.id = id;
		this.promotionAction = promotionAction;
		this.cause = cause;
	}

	public GUID getId(){
    	return id;
    }

	public PromotionAction getPromotionAction(){
    	return promotionAction;
    }

	/**
	 * 原因
	 * 
	 * @return String
	 */
	public String getCause(){
    	return cause;
    }
	
	
	
	
}
