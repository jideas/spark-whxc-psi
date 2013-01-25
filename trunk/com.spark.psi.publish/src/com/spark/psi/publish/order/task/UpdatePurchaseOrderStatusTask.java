/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.PsiSimpleTask;

/**
 * 修改采购订单状态
 
 *
 */
public class UpdatePurchaseOrderStatusTask extends PsiSimpleTask<UpdatePurchaseOrderStatusTask.Error> {

	public enum Error{
		/**
		 * 库存超数量上限
		 */
		inventory_count_upper("库存超数量上限"),
		/**
		 * 库存超金额上限
		 */
		inventory_amount_upper("库存超金额上限");
		
		String msg;
		/**
		 * 获得异常提示
		 * 
		 * @return String
		 */
		public String getMsg(){
			return msg;
		}
		
		Error(String msg){
			this.msg = msg;
		}
	}
	
	private GUID id;
	
	private OrderAction action;
	
	private String cause;
	
	/**
	 * 修改订单的状态
	 * @param id
	 * @param orderstatus
	 */
	public UpdatePurchaseOrderStatusTask(final GUID id,final OrderAction action){
		this.id = id;
		this.action = action;
	}
	
	/**
	 * 修改订单状态，带原因
	 * @param id
	 * @param orderstatus
	 * @param cause
	 */
	public UpdatePurchaseOrderStatusTask(final GUID id,final OrderAction action,final String cause){
		this(id,action);
		this.cause = cause;
	}
	
	public GUID getId() {
		return id;
	}

	public OrderAction getAction(){
    	return action;
    }

	public String getCause(){
    	return cause;
    }
	
	
	
}
