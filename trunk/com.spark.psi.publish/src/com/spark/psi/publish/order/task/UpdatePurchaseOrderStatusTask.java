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
 * �޸Ĳɹ�����״̬
 
 *
 */
public class UpdatePurchaseOrderStatusTask extends PsiSimpleTask<UpdatePurchaseOrderStatusTask.Error> {

	public enum Error{
		/**
		 * ��泬��������
		 */
		inventory_count_upper("��泬��������"),
		/**
		 * ��泬�������
		 */
		inventory_amount_upper("��泬�������");
		
		String msg;
		/**
		 * ����쳣��ʾ
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
	 * �޸Ķ�����״̬
	 * @param id
	 * @param orderstatus
	 */
	public UpdatePurchaseOrderStatusTask(final GUID id,final OrderAction action){
		this.id = id;
		this.action = action;
	}
	
	/**
	 * �޸Ķ���״̬����ԭ��
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
