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
 * 修改销售订单状态
 * @author zhoulijun
 *
 */
public class UpdateSalesReturnStatusTask extends SimpleTask {
	

	private GUID id;
	
	private OrderAction orderAction;
	
	private String cause;
	
	/**
	 * 修改订单的状态
	 * @param id
	 * @param orderstatus
	 */
	public UpdateSalesReturnStatusTask(final GUID id,final OrderAction orderAction){
		this.id = id;
		this.orderAction = orderAction;
	}
	
	/**
	 * 修改订单状态，带原因
	 * @param id
	 * @param orderstatus
	 * @param cause
	 */
	public UpdateSalesReturnStatusTask(final GUID id,final OrderAction orderAction,final String cause){
		this(id,orderAction);
		this.cause = cause;
	}
	
	public GUID getId() {
		return id;
	}

	public OrderAction getActions() {
		return orderAction;
	}

	public String getCause(){
    	return cause;
    }
		
	
}
