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
 * �޸����۶���״̬
 * @author zhoulijun
 *
 */
public class UpdateSalesOrderStatusTask extends PsiSimpleTask<UpdateSalesOrderStatusTask.Error> {
	public enum Error{
		/**
		 * ����Ԥ����������
		 */
		SetPlanDate(),
		/**
		 * ���ô�����������
		 *  void
		 */
		PromotionCountError();
	}

	private GUID id;
	
	private OrderAction orderAction;
	
	private String cause;
	
	private Long planOutDate;//Ԥ�Ƴ�������
	
	/**
	 * �޸Ķ�����״̬
	 * @param id
	 * @param orderstatus
	 */
	public UpdateSalesOrderStatusTask(final GUID id,final OrderAction orderAction){
		this.id = id;
		this.orderAction = orderAction;
	}
	
	/**
	 * @param id
	 * @param orderAction
	 * @param planOutDate
	 */
	public UpdateSalesOrderStatusTask(final GUID id, final OrderAction orderAction,
			final Long planOutDate) {
		super();
		this.id = id;
		this.orderAction = orderAction;
		this.planOutDate = planOutDate;
	}

	/**
	 * �޸Ķ���״̬����ԭ��
	 * @param id
	 * @param orderstatus
	 * @param cause
	 */
	public UpdateSalesOrderStatusTask(final GUID id,final OrderAction orderAction,final String cause){
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
	
	public Long getPlanOutDate() {
		return planOutDate;
	}
	
}
