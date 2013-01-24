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
 * �޸����۶���״̬
 * @author zhoulijun
 *
 */
public class UpdateSalesReturnStatusTask extends SimpleTask {
	

	private GUID id;
	
	private OrderAction orderAction;
	
	private String cause;
	
	/**
	 * �޸Ķ�����״̬
	 * @param id
	 * @param orderstatus
	 */
	public UpdateSalesReturnStatusTask(final GUID id,final OrderAction orderAction){
		this.id = id;
		this.orderAction = orderAction;
	}
	
	/**
	 * �޸Ķ���״̬����ԭ��
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
