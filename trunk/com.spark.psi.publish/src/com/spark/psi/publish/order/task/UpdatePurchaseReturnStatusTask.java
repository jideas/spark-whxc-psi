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
 * �޸Ĳɹ�����״̬
 
 *
 */
public class UpdatePurchaseReturnStatusTask extends SimpleTask {

	private GUID id;
	
	private OrderAction action;
	
	private String cause;
	
	/**
	 * �޸Ķ�����״̬
	 * @param id
	 * @param orderstatus
	 */
	public UpdatePurchaseReturnStatusTask(final GUID id,final OrderAction action){
		this.id = id;
		this.action = action;
	}
	
	/**
	 * �޸Ķ���״̬����ԭ��
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
