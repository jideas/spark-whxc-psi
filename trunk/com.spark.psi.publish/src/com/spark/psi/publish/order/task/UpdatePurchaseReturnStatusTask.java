/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;

/**
 * 修改采购订单状态
 
 *
 */
public class UpdatePurchaseReturnStatusTask extends SimpleTask {

	private GUID id;
	
	private OrderAction action;
	
	private String cause;
	
	/**
	 * 修改订单的状态
	 * @param id
	 * @param orderstatus
	 */
	public UpdatePurchaseReturnStatusTask(final GUID id,final OrderAction action){
		this.id = id;
		this.action = action;
	}
	
	/**
	 * 修改订单状态，带原因
	 * @param id
	 * @param orderstatus
	 * @param cause
	 */
	public UpdatePurchaseReturnStatusTask(final GUID id,final OrderAction action,final String cause){
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
